package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Study;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingGroupPenaltyActivity extends AppCompatActivity implements Callback, View.OnClickListener{
    public static final String TAG = "SettingGPenaltyActivity";
    private RadioGroup          rgUseDeposit;
    private Button              btnComplete;
    private EditText            etDeposit, etAbsencePenalty, etLatePenalty;
    private JasminPreference    mPref;
    private Study               studyInfo;
    private ProgressDialog      progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_group_penalty);
        mPref        = JasminPreference.getInstance(this);
        studyInfo   = (Study)mPref.getObjectValue("studyInfo");
        Log.d(TAG,">>>>>>>>>>"+studyInfo.toString());

        findViews();
        initViews();
    }

    public void findViews(){
        rgUseDeposit = (RadioGroup)findViewById(R.id.rg_deposit);
        etDeposit = (EditText) findViewById(R.id.et_base_deposit);
        etAbsencePenalty = (EditText) findViewById(R.id.et_attendance_penalty);
        etLatePenalty = (EditText) findViewById(R.id.et_lateness_penalty);
        btnComplete = (Button)findViewById(R.id.btn_complete);
    }

    public void initViews(){
        etDeposit.setText(Integer.toString(studyInfo.getStudy_basicDeposit()));
        etAbsencePenalty.setText(Integer.toString(studyInfo.getStudy_absence_fee()));
        etLatePenalty.setText(Integer.toString(studyInfo.getStudy_late_fee()));
        rgUseDeposit.setClickable(true);
        switch (studyInfo.getStudy_useDeposit()){
            case 1:
                rgUseDeposit.check(R.id.rb_deposit_yes);
                break;
            case 2:
                rgUseDeposit.check(R.id.rb_deposit_no);
                break;
            default:
                rgUseDeposit.check(R.id.rb_deposit_yet);
                break;
        }
        btnComplete.setOnClickListener(this);
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            JSONObject jsObject = new JSONObject(response.body().toString());

            if (response.body() != null && response.body() != "" ) {
                if(response.body().toString().equals("{\"result\":1}")) {
                    Toast.makeText(SettingGroupPenaltyActivity.this, "성공했습니다", Toast.LENGTH_SHORT).show();
                    mPref.setStudyInfo(new Study[]{studyInfo});
                }else
                {
                    Toast.makeText(SettingGroupPenaltyActivity.this, "실패했습니다", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SettingGroupPenaltyActivity.this, "응답이 없습니다", Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e){
                Log.d(TAG, "e : " + e);
        }
        progress.cancel();
        progress.dismiss();
        finish();
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_complete:
                progress = new ProgressDialog(this);
                progress.show();
                setStudyInfoFromView();
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.updateStudyFee(studyInfo.getStudy_no(),
                                                               studyInfo.getStudy_useDeposit(),
                                                                studyInfo.getStudy_basicDeposit(),
                                                                studyInfo.getStudy_max_late(),
                                                                studyInfo.getStudy_late_unit(),
                                                                studyInfo.getStudy_late_fee(),
                                                                studyInfo.getStudy_absence_fee()
                                                                );
                call.enqueue(this);
            break;
        }
    }
    public void setStudyInfoFromView(){
        studyInfo.setStudy_absence_fee(Integer.parseInt(etAbsencePenalty.getText().toString()));
        studyInfo.setStudy_late_fee(Integer.parseInt(etLatePenalty.getText().toString()));
        studyInfo.setStudy_basicDeposit(Integer.parseInt(etDeposit.getText().toString()));
        studyInfo.setStudy_useDeposit(getDepositType());
    }

    public int getDepositType(){
        int nType = 3;
        switch (rgUseDeposit.getCheckedRadioButtonId()){
            case R.id.rb_deposit_yes:
                nType=1;
                break;
            case R.id.rb_deposit_no:
                nType=2;
                break;
            case R.id.rb_deposit_yet:
                nType=3;
                break;
        }
        return nType;
    }
}


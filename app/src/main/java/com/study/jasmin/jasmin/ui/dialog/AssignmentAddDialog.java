package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.util.JasminPreference;
import com.study.jasmin.jasmin.util.JasminUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by swan on 2016-08-04.
 */
public class AssignmentAddDialog extends Dialog implements View.OnClickListener, Callback{

    private final String TAG = "AssignmentAddDialog";
//    private View.OnClickListener buttonOkListener;
//    private View.OnClickListener buttonCancelListener;
    private Button btnOk;
    private Button btnCancel;
    private EditText etTitle;
    private EditText etContent;
    private DatePicker datePicker;

    public AssignmentAddDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_assignment_add);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);        // 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true);                  // 백키로 다이알로그 닫기

        findViews();
        initViews();

    }

    public void findViews() {
        etTitle = (EditText) findViewById(R.id.et_title);
        etContent = (EditText) findViewById(R.id.et_msg);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
    }

    public void initViews() {
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        datePicker.setMinDate(Calendar.getInstance().getTimeInMillis());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                Log.d(TAG,"click btn_ok~");
                String formattedDate = JasminUtil.dateYYYY_MM_DD(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
                RestClient.RestService service = RestClient.getClient();
                int studyNo =JasminPreference.getInstance(getContext()).getSelectedStudyNo();
                /*
                Call<JsonObject> call = service.insertAssignment(studyNo,
                                                                etTitle.getText().toString(),
                                                                etContent.getText().toString(),
                                                                "20160910",
                                                                "20160915",
                                                                3000);
                call.enqueue(this);
                */
                break;
            case R.id.btn_cancel:
                Log.d(TAG,"btn_cancel btn_ok");
                cancel();
                dismiss();
                break;
        }

    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Retro Status Code = " + response.code());
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject jsObject = new JSONObject(gson.toJson(response.body()));
            int result = (int)jsObject.get("result");
            if(result == 1) {
                cancel();
                dismiss();
                Toast.makeText(getContext(),"성공", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        }catch (JSONException e) {
            Log.d(TAG,"e : " + e);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG,"respnse Fail call : " + call);
    }
}

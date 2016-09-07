package com.study.jasmin.jasmin.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Penalty;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.ui.dialog.TwoButtonDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoReceivablesList;
import com.study.jasmin.jasmin.ui.list.ListInfoReceivables;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GroupManageReceivablesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, Button.OnClickListener, Callback {
    public static final String TAG = "GMReceivablesActivity";
    private ListView list;
    private AdaptInfoReceivablesList adapt;
    TwoButtonDialog dialog;
    JasminPreference mPref;
    ProgressDialog progress;
    Penalty listInfo;
    ArrayList<Object> penalties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_receivables);
        progress = new ProgressDialog(this);
        mPref = JasminPreference.getInstance(this);
        penalties = mPref.getListValue("penaltyList");

        findViews();
        initViews();
    }

    public void findViews(){
        list = (ListView)findViewById(R.id.lv_receivables);
    }

    public void initViews() {
        adapt = new AdaptInfoReceivablesList(this, R.layout.list_receivables_info, penalties);
        list.setAdapter(adapt);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       listInfo = (Penalty)parent.getItemAtPosition(position) ;

        String comment = listInfo.getPenalty_date()+"\n"+listInfo.getUser_name()+"님의 "
                +listInfo.getPenalty_title() +"벌금 \n"+ Integer.toString(-listInfo.getPenalty_money())+"원을 \n 수령하셨습니까?";

        dialog = new TwoButtonDialog(this);
        dialog.setCancelOnClickListener(this);
        dialog.setOkOnClickListener(this);
        dialog.showTwoButtonDialog("미수금",comment);
    }

    @Override
    public void onResponse(Call call, Response response) {
        try {
            Gson gson = new GsonBuilder().create();
            JSONObject jsObject = new JSONObject(gson.toJson(response.body()));
            Toast.makeText(this,((int)jsObject.get("result") == 1)?"성공":"실패", Toast.LENGTH_SHORT).show();
            RestClient.RestService service = RestClient.getClient();
            call = service.penaltyList(mPref.getSelStudyNo());
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.d(TAG, "commentList Code = " + response.code());
                    try {
                        String strResponse = response.body().toString();
                        JSONObject jsObject = new JSONObject(strResponse);
                        JSONArray  jsonArray = jsObject.getJSONArray("result");
                        if (jsonArray != null) {
                            mPref.putJson("penaltyList", jsonArray.toString());
                            penalties = mPref.getListValue("penaltyList");
                            adapt.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        Log.d(TAG, "e : " + e);
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
            progress.dismiss();
            progress.cancel();
            dialog.closeTwoButtonDialog();
        }catch (JSONException e) {
            Log.d(TAG,"e : " + e);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.ok_twobutton:
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.penaltyCheck(listInfo.getPenalty_no(),listInfo.getPenalty_money(),
                                                             listInfo.getUser_no(),mPref.getSelStudyNo());
                call.enqueue(this);
                dialog.closeTwoButtonDialog();
                progress.show();
                break;
            case  R.id.cancel_twobutton:
                dialog.closeTwoButtonDialog();
                break;
        }
    }

    public void callList(){
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.penaltyList(mPref.getSelStudyNo());
        call.enqueue(this);
    }
}
package com.study.jasmin.jasmin.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.QnA;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.util.CheckAvailability;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;

public class EditInfoActivity  extends AppCompatActivity implements View.OnClickListener{

    TextView    email;
    EditText    pwCurr, pwNew, pwRe;
    Button      btnComplete;
    User        userInfo;
    OneButtonDialog oneButtonDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inform);

        getPref();
        findViews();
        initViews();
    }

    public void getPref(){
        JasminPreference pref = JasminPreference.getInstance(this);
        userInfo = (User)(pref.getObjectValue("userInfo"));
    }
    public void findViews(){
        email   = (TextView)findViewById(R.id.tv_email);
        pwCurr  = (EditText)findViewById(R.id.et_pw_current);
        pwNew   = (EditText)findViewById(R.id.et_pw_new);
        pwRe    = (EditText)findViewById(R.id.et_pw_repeat);
        btnComplete = (Button)findViewById(R.id.btn_complete);
    }

    public void initViews(){
        if(userInfo !=null)    email.setText(userInfo.getUser_email());
        btnComplete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                if(checkValidity()) {
                /*
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.Login(pw., pw);
                call.enqueue(this);
                */
                    finish();
                }else {
                    showOneButtonDialog(R.string.edit_info_dialog_err, R.string.edit_info_dialog_err_pw);
                }
                break;
            case R.id.onebutton_ok:
                closeOneButtonDialog();
            default:
                break;
        }
    }

    public void showOneButtonDialog(int title, int comment) {
        //showOneButtonDialog(R.string.edit_info_dialog_err_pw_title,R.string.edit_info_dialog_err_pw);
        oneButtonDialog = new OneButtonDialog(this);
        oneButtonDialog.setCancelable(false);
        oneButtonDialog.setOkOnClickListener(this);
        oneButtonDialog.show();
        oneButtonDialog.setTitle(title);
        oneButtonDialog.setComment(comment);
    }

    public void closeOneButtonDialog(){
        oneButtonDialog.dismiss();
        oneButtonDialog.cancel();
    }



    public boolean checkValidity(){
        String pwSaved = "";//현재 비밀번호 확인 필요

        //1.현재 비밀번호 확인
        /*if(pwCurr.getText().toString() != pwSaved)    return true;
         */
        //2.새 비밀번호 조건 확인

        //3,새 비밀번호 동일 확인
        if(pwNew.getText().toString() != pwRe.getText().toString()) return true;

        if( CheckAvailability.isNotNull(pwCurr.getText().toString(), pwNew.getText().toString(), pwRe.getText().toString())) return true;

        return false;
    }
}

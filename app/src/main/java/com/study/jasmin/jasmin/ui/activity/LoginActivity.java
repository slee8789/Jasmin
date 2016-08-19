package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.FindPwDialog;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback {
    public static final String TAG = "LoginActivity";
    private Button btnDoLogin;
    private EditText etEmail;
    private EditText etPassword;
    private CheckBox cbAuto;
    private TextView tvFindPassword;
    private FindPwDialog findPwDialog;
    private JasminPreference mPref;
    private int dialogTitle;
    private int dialogMsg;
    private String mail;
    private String pw;
    private List<Object> userList;
    private ProgressDialog LoginProgress;
    private OneButtonDialog oneButtonDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        autoLogin();
        findViews();
        initViews();

    }

    private void autoLogin(){


    }
    private void findViews() {
        btnDoLogin = (Button) findViewById(R.id.btn_do_login);
        etEmail = (EditText) findViewById(R.id.login_email);
        etPassword = (EditText) findViewById(R.id.login_password);
        cbAuto = (CheckBox) findViewById(R.id.login_auto);
        tvFindPassword = (TextView) findViewById(R.id.tv_find_password);
    }

    private void initViews() {
        LoginProgress = new ProgressDialog(this);
        btnDoLogin.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);
        mPref = JasminPreference.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_share:
                Log.d(TAG, "click btn_share");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showFindPwDialog() {
        findPwDialog = new FindPwDialog(this);
        findPwDialog.setCancelable(false);
        findPwDialog.setOkOnClickListener(this);
        findPwDialog.show();
    }

    public void showOneButtonDialog(int title, int comment) {
        oneButtonDialog = new OneButtonDialog(this);
        oneButtonDialog.setCancelable(false);
        oneButtonDialog.setOkOnClickListener(this);
        oneButtonDialog.show();
        oneButtonDialog.setTitle(title);
        oneButtonDialog.setComment(comment);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_do_login:
                Log.d(TAG, "click btn_do_login");

                mail = etEmail.getText().toString();
                pw = etPassword.getText().toString();


                Gson gson = new Gson();
                String strJson = gson.toJson(userList);

                LoginProgress.show();

                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.Login(mail, pw);
                call.enqueue(this);

/*
                boolean emptyChk = CheckAvailability.isNotNull(etEmail.getText().toString(), etPassword.getText().toString());
                boolean mailChk = CheckAvailability.isEmail(etEmail.getText().toString());

                dialogTitle = R.string.regist_dialog_title;
                if (!emptyChk) {
                    dialogMsg = R.string.regist_dialog_input_error;
                    showOneButtonDialog(dialogTitle, dialogMsg);
                } else if (!mailChk) {
                    dialogMsg = R.string.regist_dialog_mailform;
                    showOneButtonDialog(dialogTitle, dialogMsg);
                } else {
                    mail = etEmail.getText().toString();
                    pw = etPassword.getText().toString();
                    LoginProgress.show();
                    RestClient.RestService service = RestClient.getClient();
                    Call<JsonObject> call = service.Login(mail, pw);
                    call.enqueue(this);
                }
                */
                break;

            case R.id.onebutton_ok:
                oneButtonDialog.cancel();
                oneButtonDialog.dismiss();
                break;

            case R.id.tv_find_password:
                Log.d(TAG, "click tv_find_password");
                showFindPwDialog();
//                startActivity(new Intent(this,FindPwDialog_backup.class));
                break;

            case R.id.send_button:
                Log.d(TAG, "click send_button");
                findPwDialog.dismiss();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
//        mPref.put("autoLogin", cbAuto.isChecked());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "click onBackPressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }



    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, "Retro Status Code = " + response.code());
        try {
            String strTest = response.body().toString();

            JSONObject jsObject = new JSONObject(strTest);
            JSONArray userObj = jsObject.getJSONArray("userInfo");
            JSONArray studyObj = jsObject.getJSONArray("studyList");
            JSONArray qnaObj = jsObject.getJSONArray("qnaList");

//            mPref.put("qnaList",qnaObj.toString());
//            mPref.put("userInfo",userObj.toString());
//            mPref.put("studyList",studyObj.toString());
            mPref.putJson("qnaList",qnaObj.toString());
            mPref.putJson("userInfo",userObj.toString());
            mPref.putJson("studyList",studyObj.toString());

           Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            LoginProgress.cancel();
            LoginProgress.dismiss();
           finish();
        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ResponseFail = " + call);
    }
}

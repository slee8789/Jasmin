package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.util.CheckAvailability;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener, Callback {
    public static final String TAG = "RegistActivity";
    private Button btnDoRegister;
    private Button btnMailAuth;
    private RelativeLayout layoutAuth;
    private EditText etAuth;
    private Button btnAuth;
    private EditText etName;
    private String name;
    private EditText etPw;
    private String pw;
    private EditText etPwCheck;
    private EditText etMail;
    private String mail;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private int sex; // 0: male, 1 : female
    private OneButtonDialog oneButtonDialog;
    private int dialogTitle;
    private int dialogMsg;
    private boolean registerNotAuth = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        findViews();
        initViews();
    }



    private void findViews() {
        layoutAuth = (RelativeLayout) findViewById(R.id.regist_layout_auth);
        btnDoRegister = (Button) findViewById(R.id.btn_do_register);
        btnMailAuth = (Button) findViewById(R.id.btn_mail_auth);
        etMail = (EditText) findViewById(R.id.regist_mail);
        etName = (EditText) findViewById(R.id.regist_name);
        etAuth = (EditText) findViewById(R.id.regist_authcode);
        btnAuth = (Button) findViewById(R.id.btn_authcode);
        etPw = (EditText) findViewById(R.id.regist_pw);
        etPwCheck = (EditText) findViewById(R.id.regist_pw_check);
        radioMale = (RadioButton) findViewById(R.id.radio_male);
        radioFemale = (RadioButton) findViewById(R.id.radio_female);
    }

    private void initViews() {
//        btnDoRegister.setBackgroundResource(getResources().);
        btnDoRegister.setOnClickListener(this);
        btnMailAuth.setOnClickListener(this);
        btnAuth.setOnClickListener(this);
        etMail.setOnClickListener(this);
        etName.setOnClickListener(this);
        etPw.setOnClickListener(this);
        etPwCheck.setOnClickListener(this);
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
            case R.id.btn_do_register:
                Log.d(TAG, "click btn_do_register");

                boolean emptyChk = CheckAvailability.isNotNull(etMail.getText().toString(), etPw.getText().toString(), etPwCheck.getText().toString(), etName.getText().toString());
                boolean mailChk = CheckAvailability.isEmail(etMail.getText().toString());
                boolean pwChk = CheckAvailability.isSameString(etPw.getText().toString(), etPwCheck.getText().toString());
                boolean pwLengthChk = CheckAvailability.isMoreThan(8, etPw.getText().toString());
                boolean sexChk = CheckAvailability.isRadioChk(radioMale, radioFemale);

                dialogTitle = R.string.regist_dialog_title;
                if(!emptyChk) {dialogMsg = R.string.regist_dialog_input_error;}
                else if(!mailChk) {dialogMsg = R.string.regist_dialog_mailform;}
                else if(!pwChk) {dialogMsg = R.string.regist_dialog_pw_disharmony;}
                else if(!pwLengthChk) {dialogMsg = R.string.regist_dialog_more8;}
                else if(!sexChk) {dialogMsg = R.string.regist_dialog_sex;}
                else if(!registerNotAuth) {
                    dialogMsg = R.string.regist_msg_not_auth;
                }
                else {
                    mail = etMail.getText().toString();
                    pw = etPw.getText().toString();
                    name = etName.getText().toString();
                    sex = radioMale.isChecked() ? 0 : 1;

                    dialogTitle = R.string.regist_regist;
                    dialogMsg = R.string.regist_dialog_ok;
                }
                showOneButtonDialog(dialogTitle, dialogMsg);

                break;

            case R.id.btn_mail_auth:
                Log.d(TAG, "click btn_mail_auth");
                dialogTitle = R.string.regist_do_mail_auth;
                dialogMsg = R.string.regist_dialog_auth;
                showOneButtonDialog(dialogTitle, dialogMsg);
                btnMailAuth.setVisibility(View.GONE);
                layoutAuth.setVisibility(View.VISIBLE);
                RestClient.RestService serviceAuth = RestClient.getClient();
                Call<JsonObject> callAuth = serviceAuth.mailCheck(etMail.getText().toString());
                callAuth.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d(TAG,"Status Code = " + response.code());
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                break;

            case R.id.onebutton_ok:
                Log.d(TAG, "click onebutton_ok");
                if(dialogMsg == R.string.regist_dialog_ok) startActivity(new Intent(this,LoginActivity.class));
                oneButtonDialog.dismiss();
                oneButtonDialog.cancel();

                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.registerUser(mail,pw,name,sex);
                call.enqueue(this);

                break;

            case R.id.btn_authcode:
                RestClient.RestService serviceAuthOk = RestClient.getClient();
                Call<JsonObject> callAuthOk = serviceAuthOk.mailAuth(etMail.getText().toString(),Integer.parseInt(etAuth.getText().toString()));
                callAuthOk.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d(TAG, "Status Code = " + response.code());
                        try {
                            String strResponse = response.body().toString();
                            JSONObject jsObject = new JSONObject(strResponse);
                            if (jsObject.getInt("result") == 1) {
                                registerNotAuth = true;
                            }
                        } catch (JSONException e) {
                            Log.d(TAG, "e : " + e);
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
                break;
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Status Code = " + response.code());
        Log.d(TAG,"Status Message = " + response.message());
        Gson gson = new Gson();
        String jsonInString = gson.toJson(response.body());
        Log.d(TAG,"Status Message to Json = " + jsonInString);


    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG,"ResponseFail = " + call);
    }

    @Override
    public void onBackPressed() {
        if(btnMailAuth.getVisibility() == View.GONE) {
            btnMailAuth.setVisibility(View.VISIBLE);
            layoutAuth.setVisibility(View.GONE);
            return ;
        }
        super.onBackPressed();


    }
}

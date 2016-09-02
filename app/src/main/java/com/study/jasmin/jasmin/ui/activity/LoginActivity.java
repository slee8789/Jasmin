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

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.FindPwDialog;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.util.CheckAvailability;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Callback {
    public static final String TAG = "LoginActivity";
    private Button btnDoLogin;
    private Button btnKakao;
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
    private ProgressDialog LoginProgress;
    private OneButtonDialog oneButtonDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initViews();
        autoLogin();
    }

    private void findViews() {
        btnDoLogin = (Button) findViewById(R.id.btn_do_login);
        btnKakao = (Button) findViewById(R.id.btn_kakao);
        etEmail = (EditText) findViewById(R.id.login_email);
        etPassword = (EditText) findViewById(R.id.login_password);
        cbAuto = (CheckBox) findViewById(R.id.login_auto);
        tvFindPassword = (TextView) findViewById(R.id.tv_find_password);
    }

    private void initViews() {
        LoginProgress = new ProgressDialog(this);
        btnDoLogin.setOnClickListener(this);
        btnKakao.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);
        cbAuto.setClickable(true);
        cbAuto.setOnClickListener(this);
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
                //if(loginValidity()){
                if(true){
                    mail = etEmail.getText().toString();
                    pw = etPassword.getText().toString();
                    doLogin(mail, pw);
                }
            case R.id.btn_kakao:
                /*// 카톡 또는 카스가 존재하면 옵션을 보여주고, 존재하지 않으면 바로 직접 로그인창.
                final List<AuthType> authTypes = getAuthTypes();
                if(authTypes.size() == 1){
                    Session.getCurrentSession().open(authTypes.get(0), (Activity) getContext());
                } else {
                    onClickLoginButton(authTypes);
                }*/
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
            case R.id.login_auto:
                mPref.put("autoLogin",cbAuto.isChecked());
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
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
            if( response.body() != null ||  response.body()!="") {

                String strTest = response.body().toString();


                JSONObject jsObject = new JSONObject(strTest);
                /*if(jsObject.getInt("result") == 0 ) {
                    Log.d(TAG,"없음요");
                    showOneButtonDialog(R.string.regist_dialog_title, R.string.regist_dialog_pw_disharmony);
                    LoginProgress.cancel();
                    LoginProgress.dismiss();
                    return ;
                }*/
                JSONArray userObj = jsObject.getJSONArray("userInfo");
                JSONArray studyObj = jsObject.getJSONArray("studyList");
                JSONArray qnaObj = jsObject.getJSONArray("qnaList");

                mPref.putJson("qnaList", qnaObj.toString());
                mPref.putJson("userInfo", userObj.toString());
                mPref.putJson("studyList", studyObj.toString());

                ArrayList<Object> studyLIst = mPref.getListValue("studyList");
                ArrayList<Object> userInfo = mPref.getListValue("userInfo");
                ArrayList<Object> qnaList = mPref.getListValue("qnaList");


                //오토 로그인 정보 저장 in user phone
                // AutoLogin ; login 성공 시 autoLoin 체크 되있으면 로그인 정보(id,pw)저장
                if (cbAuto.isChecked()) {
                    mPref.put("autoLoginId", etEmail.getText().toString());
                    mPref.put("autoLoginPw", etPassword.getText().toString());
                }

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                LoginProgress.cancel();
                LoginProgress.dismiss();
                finish();
            }else {
                Log.d(TAG, "response.body() is empty.");
            }

        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
    }

    public boolean loginValidity() {

        boolean bResult = false;
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
            bResult = true;
        }
        return bResult;
    }

    private void doLogin(String mail, String pw){
        LoginProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.Login(mail, pw);
        call.enqueue(this);
    }

    private void autoLogin(){

        String mail = mPref.getValue("autoLoginId","");
        String pw = mPref.getValue("autoLoginPw","");
        boolean bAuto =  mPref.getValue("autoLogin",false);

        if(mail !="" && pw !="" &&bAuto){
            Log.d(TAG, "doAutoLogin >>>  mail : "+mail+" pw : "+pw+" checked : "+ bAuto);
            doLogin(mail, pw);
        }
    }
    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ResponseFail = " + call);
    }
}

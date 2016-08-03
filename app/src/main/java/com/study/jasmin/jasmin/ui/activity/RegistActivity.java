package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.util.CheckAvailability;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "RegistActivity";
    private Button btnDoRegister;
    private Button btnMailAuth;
    private EditText etName;
    private EditText etPw;
    private EditText etPwCheck;
    private EditText etMail;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private OneButtonDialog mailAuthDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        findViews();
        initViews();
    }

    private void findViews() {
        btnDoRegister = (Button) findViewById(R.id.btn_do_register);
        btnMailAuth = (Button) findViewById(R.id.btn_mail_auth);
        etMail = (EditText) findViewById(R.id.regist_mail);
        etName = (EditText) findViewById(R.id.regist_name);
        etPw = (EditText) findViewById(R.id.regist_pw);
        etPwCheck = (EditText) findViewById(R.id.regist_pw_check);
        radioMale = (RadioButton) findViewById(R.id.radio_male);
        radioFemale = (RadioButton) findViewById(R.id.radio_female);
    }

    private void initViews() {
        btnDoRegister.setOnClickListener(this);
        btnMailAuth.setOnClickListener(this);
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

    public void showMailAuthDialog(int title, int comment) {
        mailAuthDialog = new OneButtonDialog(this);
        mailAuthDialog.setCancelable(false);
        mailAuthDialog.setOkOnClickListener(this);
        mailAuthDialog.show();
        mailAuthDialog.setTitle(title);
        mailAuthDialog.setComment(comment);
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

                Log.d(TAG,"emptyChk : "+emptyChk);
                Log.d(TAG,"mailChk : "+mailChk);
                Log.d(TAG,"pwChk : "+pwChk);
                Log.d(TAG,"pwLengthChk : "+pwLengthChk);
                Log.d(TAG,"sexChk : "+sexChk);
                break;

            case R.id.btn_mail_auth:
                Log.d(TAG, "click btn_mail_auth");
                showMailAuthDialog(R.string.regist_do_mail_auth, R.string.regist_dialog_auth);
                break;

            case R.id.onebutton_ok:
                Log.d(TAG, "click onebutton_ok");
                mailAuthDialog.dismiss();


        }
    }


}

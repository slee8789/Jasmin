package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.FindPwDialog;
import com.study.jasmin.jasmin.util.CheckAvailability;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "LoginActivity";
    private Button btnDoLogin;
    private EditText etEmail;
    private EditText etPassword;
    private CheckBox cbAuto;
    private TextView tvFindPassword;
    private FindPwDialog findPwDialog;
    private SharedPreferences mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        initViews();


    }

    private void findViews() {
        btnDoLogin = (Button)findViewById(R.id.btn_do_login);
        etEmail = (EditText) findViewById(R.id.login_email);
        etPassword = (EditText) findViewById(R.id.login_password);
        cbAuto = (CheckBox) findViewById(R.id.login_auto);
        tvFindPassword = (TextView)findViewById(R.id.tv_find_password);
    }

    private void initViews() {
        btnDoLogin.setOnClickListener(this);
        tvFindPassword.setOnClickListener(this);

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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_do_login:
                Log.d(TAG,"click btn_do_login");
                boolean emptyChk = CheckAvailability.isNotNull(etEmail.getText().toString(), etPassword.getText().toString());
                boolean mailChk = CheckAvailability.isEmail(etEmail.getText().toString());

                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;

            case R.id.tv_find_password:
                Log.d(TAG,"click tv_find_password");
                showFindPwDialog();

//                startActivity(new Intent(this,FindPwDialog_backup.class));
                break;

            case R.id.send_button:
                Log.d(TAG,"click send_button");
                findPwDialog.dismiss();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
        mPref = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean("autoLogin",cbAuto.isChecked());
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"click onBackPressed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        mPref = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean("autoLogin",cbAuto.isChecked());
        editor.commit();
    }


}

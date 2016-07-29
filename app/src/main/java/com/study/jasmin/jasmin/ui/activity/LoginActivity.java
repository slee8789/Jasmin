package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.FindPwDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "LoginActivity";
    Button btnDoLogin;
    TextView tvFindPassword;
    FindPwDialog findPwDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnDoLogin = (Button)findViewById(R.id.btn_do_login);
        btnDoLogin.setOnClickListener(this);
        tvFindPassword = (TextView)findViewById(R.id.tv_find_password);
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
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"click onBackPressed");
    }
}

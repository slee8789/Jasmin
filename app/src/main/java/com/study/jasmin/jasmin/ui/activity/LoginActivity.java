package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.FindPwDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "LoginActivity";
    Button btnDoLogin;
    Button btnFindPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnDoLogin = (Button)findViewById(R.id.btn_do_login);
        btnDoLogin.setOnClickListener(this);
        btnFindPassword = (Button)findViewById(R.id.btn_find_password);
        btnFindPassword.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_do_login:
                Log.d(TAG,"click btn_do_login");
                startActivity(new Intent(this,MainActivity.class));
                break;

            case R.id.btn_find_password:
                Log.d(TAG,"click btn_find_password");
                startActivity(new Intent(this,FindPwDialog.class));
                break;
        }
    }
}

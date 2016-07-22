package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.study.jasmin.jasmin.R;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "RegistActivity";
    Button btnDoRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        btnDoRegister = (Button) findViewById(R.id.btn_do_register);
        btnDoRegister.setOnClickListener(this);
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
            case R.id.btn_do_register:
                Log.d(TAG, "click btn_do_register");

                break;
        }
    }
}

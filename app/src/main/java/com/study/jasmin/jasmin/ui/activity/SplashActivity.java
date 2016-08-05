package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.study.jasmin.jasmin.R;

public class SplashActivity extends AppCompatActivity {
    public static final String TAG = "SplashActivity";
    private SharedPreferences mPrefs;
    private boolean introState = false;

    private Handler introHandler = new Handler();
    private Runnable introRunnable = new Runnable() {

        @Override
        public void run() {
            Intent intent;
            if (introState) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, Intro2Activity.class);
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);
        mPrefs = getSharedPreferences("userInfo", MODE_PRIVATE);
        introState = mPrefs.getBoolean("autoLogin", false);
        introHandler.postDelayed(introRunnable, 2000);  // 2초 후 러너블 테스트
    }
}

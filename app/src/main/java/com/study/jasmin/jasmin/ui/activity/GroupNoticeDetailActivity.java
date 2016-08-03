package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.study.jasmin.jasmin.R;

public class GroupNoticeDetailActivity extends AppCompatActivity {
    public static final String TAG = "GNDA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_notice_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_modify:
                Log.d(TAG, "click btn_modify");
                return true;

            case R.id.btn_delete:
                Log.d(TAG, "click btn_delete");
                return true;

            case R.id.btn_reply:
                Log.d(TAG, "click btn_reply");
                return true;

            case R.id.btn_favorite:
                Log.d(TAG, "click btn_favorite");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

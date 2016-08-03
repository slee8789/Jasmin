package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.study.jasmin.jasmin.R;

public class GroupStudyInfoActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "GroupStudyInfoActivity";
    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_study_info);
        btnComplete = (Button) findViewById(R.id.btn_complete);
        btnComplete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_exit:
                Log.d(TAG, "click btn_exit");
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

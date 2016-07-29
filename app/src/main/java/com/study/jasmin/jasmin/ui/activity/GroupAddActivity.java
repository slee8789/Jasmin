package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.study.jasmin.jasmin.R;

public class GroupAddActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        btn_complete = (Button) findViewById(R.id.btn_complete);
        btn_complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_complete:
                startActivity(new Intent(this,GroupInviteActivity.class));
                finish();
                break;
        }
    }
}

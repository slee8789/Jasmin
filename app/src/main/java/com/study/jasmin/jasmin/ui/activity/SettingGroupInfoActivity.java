package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.study.jasmin.jasmin.R;

public class SettingGroupInfoActivity extends AppCompatActivity {

    EditText etGroupName;
    EditText etGroupIntro;
    ImageView ivGroupCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_group_info);

        etGroupIntro    = (EditText)findViewById(R.id.et_Intro);
        etGroupName     = (EditText)findViewById(R.id.et_name);
        ivGroupCover    = (ImageView) findViewById(R.id.iv_cover);

        etGroupName.setText("스터디명");
        etGroupIntro.setText("스터디설명");
//        ivGroupCover.setImageResource("");

    }
}


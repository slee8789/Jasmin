package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.study.jasmin.jasmin.R;

public class EditInfoActivity  extends AppCompatActivity implements View.OnClickListener{

    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inform);

        btnComplete = (Button)findViewById(R.id.btn_complete);
        btnComplete.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_complete:
                finish();
                break;
            default:
                break;
        }
    }
}

package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;

public class SendCustomerCenterActivity2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_customer_center2);

        Button button = (Button) findViewById(R.id.btn_send);
        final EditText email = (EditText) findViewById(R.id.send_customer_center2_edit_email);
        final EditText title = (EditText) findViewById(R.id.send_customer_center2_edit_title);
        final EditText editText = (EditText) findViewById(R.id.send_customer_center2_edittext);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_send:
                Toast.makeText(this, "전송", Toast.LENGTH_SHORT).show();
                finish();
                //startActivity(new Intent(this,SettingFragment.class));
                break;
            default:
                break;
        }

    }
}

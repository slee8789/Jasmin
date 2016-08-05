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
import com.study.jasmin.jasmin.util.JasminProtocol;

public class GroupInviteActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "GroupInviteActivity";
    private Button btn_complete;
    private TextView btnSMS;
    private TextView btnKakao;
    private TextView btnMail;
    private String groupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_invite);
        findViews();
        initViews();
        Intent intent = getIntent(); // 수행할 action, date 를 얻기 위해.
        groupName = intent.getStringExtra("groupname");
        Log.d(TAG,"groupname : " + groupName);


    }

    private void findViews() {
        btn_complete = (Button) findViewById(R.id.btn_complete);
        btnSMS = (TextView) findViewById(R.id.invite_sms);
        btnKakao = (TextView) findViewById(R.id.invite_kakao);
        btnMail = (TextView) findViewById(R.id.invite_mail);

    }

    private void initViews() {
        btn_complete.setOnClickListener(this);
        btnSMS.setOnClickListener(this);
        btnKakao.setOnClickListener(this);
        btnMail.setOnClickListener(this);
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
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_complete:
                if(groupName != null) {
                    startActivity(new Intent(this, GroupMainActivity.class));
                    Intent intent = new Intent(JasminProtocol.BROADCAST_MESSAGE);
                    intent.putExtra("groupname", groupName);
                    sendBroadcast(intent);
                }
                finish();
                break;

            case R.id.invite_sms:
                break;

            case R.id.invite_kakao:
                break;

            case R.id.invite_mail:
                break;

        }
    }
}

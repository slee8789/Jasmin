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
import com.study.jasmin.jasmin.util.JasminProtocol;

public class GroupInviteActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "GroupInviteActivity";
    private Button btn_complete;
    private String groupName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_invite);
        Intent intent = getIntent(); // 수행할 action, date 를 얻기 위해.
        groupName = intent.getStringExtra("groupname");
        Log.d(TAG,"groupname : " + groupName);
        btn_complete = (Button) findViewById(R.id.btn_complete);
        btn_complete.setOnClickListener(this);
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
                startActivity(new Intent(this,GroupMainActivity.class));

                Intent intent = new Intent (JasminProtocol.BROADCAST_MESSAGE);
                intent.putExtra("groupname","groupname");
                sendBroadcast(intent);

                finish();
                break;
        }
    }
}

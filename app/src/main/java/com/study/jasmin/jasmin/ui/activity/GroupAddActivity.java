package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.study.jasmin.jasmin.R;

public class GroupAddActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_complete;
    private EditText groupName;
    private ImageView groupCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        btn_complete = (Button) findViewById(R.id.btn_complete);
        btn_complete.setOnClickListener(this);
        groupName = (EditText)findViewById(R.id.add_name);
        groupCover = (ImageView) findViewById(R.id.group_cover);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_complete:
                Intent intent = new Intent(this,GroupInviteActivity.class);
                intent.putExtra("groupname",groupName.getText());
//                intent.putExtra("groupcover",groupCover.getI)
                startActivity(intent);
                finish();
                break;
        }
    }
}

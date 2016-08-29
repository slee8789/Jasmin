package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Alarm;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmAdapter;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmItem;
import com.study.jasmin.jasmin.ui.item.ListViewAssignment;
import com.study.jasmin.jasmin.ui.item.ListViewBtnAdapter;
import com.study.jasmin.jasmin.util.JasminProtocol;

import java.util.ArrayList;

public class GroupSettingAlarmListActivity extends AppCompatActivity implements ListViewAlarmAdapter.ListBtnClickListener, View.OnClickListener {

    Button btnAdd;
    ListView listview;
    ArrayList<Alarm> alarmList;
    ListViewAlarmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setting_alarm_list);

        findView();
        initView();

    }

    public void findView() {
        listview = (ListView) findViewById(R.id.lv_alarm);
        btnAdd = (Button) findViewById(R.id.btn_add);
        alarmList = getIntent().getParcelableArrayListExtra("alarmList");
    }

    public void initView() {
        adapter = new ListViewAlarmAdapter(this, R.layout.listview_group_setting_alarm, alarmList, this);
        listview.setAdapter(adapter);
        btnAdd.setOnClickListener(this);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), GroupSettingAlarmAddActivity.class);
                intent.putExtra("alarmInfo", (Alarm) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onListBtnClick(int position, ImageView iv, boolean bSet) {
        iv.setImageDrawable((bSet) ? ContextCompat.getDrawable(this, R.drawable.ic_alarm_black_24dp)
                : ContextCompat.getDrawable(this, R.drawable.ic_alarm_off_black_24dp));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
               startActivity(new Intent(this, GroupSettingAlarmAddActivity.class));
               break;
        }
    }
}


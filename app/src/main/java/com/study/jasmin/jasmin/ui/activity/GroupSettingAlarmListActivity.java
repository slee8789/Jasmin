package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmAdapter;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmItem;
import com.study.jasmin.jasmin.ui.item.ListViewAssignment;
import com.study.jasmin.jasmin.ui.item.ListViewBtnAdapter;

import java.util.ArrayList;

public class GroupSettingAlarmListActivity extends AppCompatActivity implements ListViewAlarmAdapter.ListBtnClickListener, View.OnClickListener{

    Button      btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setting_alarm_list);

        //list 처리
        ListView    listview;
        ListViewAlarmAdapter adapter;

        ArrayList<ListViewAlarmItem> items = new ArrayList<ListViewAlarmItem>() ;

        // items 로드.
        loadItemsFromDB(items) ;

        // Adapter 생성
        adapter = new ListViewAlarmAdapter(this, R.layout.listview_group_setting_alarm, items, this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.lv_alarm);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        });


        // button 처리
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);


    }

    @Override
    public void onListBtnClick(int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(this, GroupSettingAlarmAddActivity.class));
                break;
        }
    }

    public boolean loadItemsFromDB( ArrayList<ListViewAlarmItem> list) {

        ListViewAlarmItem item ;
        boolean[] arrDay = {true, false, true, false, false, false, false};

        if (list == null) {
            list = new ArrayList<ListViewAlarmItem>() ;
        }
        //item 입력
        for(int i=0; i<5; i++){
            item = new ListViewAlarmItem();
            item.setStrAmpm("오전");
            item.setStrTime("10:00");
            item.setArrDay(arrDay);
            item.setSetting(true);
            item.setbRepeat(true);
            list.add(item);
        }
        return true ;
    }
}


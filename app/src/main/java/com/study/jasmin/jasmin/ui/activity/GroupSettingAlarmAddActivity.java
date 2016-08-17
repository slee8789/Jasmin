package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmAdapter;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;

public class GroupSettingAlarmAddActivity extends AppCompatActivity implements View.OnClickListener{
    TimePicker  tpTime;
    CheckBox    cbRepeat;
    EditText    etContent;
    //day : mon~sun (0~6)
    HashMap<Button,Boolean> mapDays= new HashMap<Button,Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setting_alarm_add);

        findView();
        initView();
        initDays();
    }

    public void findView(){
        tpTime = (TimePicker)findViewById(R.id.tp_time);
        cbRepeat = (CheckBox)findViewById(R.id.cb_repeat);
        etContent = (EditText)findViewById(R.id.et_content);
    }

    public void initView(){
        Calendar c = Calendar.getInstance();
        tpTime.setIs24HourView(true);
    }

    public void initDays(){
        //day button
        int[] idDays = {R.id.btn_day_mon, R.id.btn_day_tues,R.id.btn_day_wed,
                R.id.btn_day_thus, R.id.btn_day_fri, R.id.btn_day_sat,R.id.btn_day_sun};
        for(int i=0; i<7; i++){
            Button btnDay = (Button)findViewById(idDays[i]);
            btnDay.setOnClickListener(this);
            mapDays.put(btnDay,true);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //btn
        if(mapDays.containsKey(v)){
            Button btnDay = (Button)v;
            boolean bStatus = !(mapDays.get(v));
            mapDays.put(btnDay, bStatus);
            if(bStatus==true){
                btnDay.setBackgroundColor(Color.BLUE);
                btnDay.setTextColor(Color.GREEN);
                //btnDay.setTextColor(Color.WHITE);
            }else{
                btnDay.setSelected(false);
                //btnDay.setBackgroundColor(Color.GRAY);
                btnDay.setTextColor(Color.BLACK);
            }
            return;
        }
        switch (id){
            case R.id.btn_add :

                break;

        }

    }

    //status(boolean)  true = selected
    public void changeBtnStyle(Button btn, boolean status){

    }

}

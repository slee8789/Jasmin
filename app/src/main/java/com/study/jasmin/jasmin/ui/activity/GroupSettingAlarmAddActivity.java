package com.study.jasmin.jasmin.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Alarm;

import java.util.Calendar;
import java.util.HashMap;

public class GroupSettingAlarmAddActivity extends AppCompatActivity implements View.OnClickListener{
    TimePicker      tpTime;
    CheckBox        cbRepeat;
    EditText        etContent;
    Alarm           mAlarm;
    Button          btnAdd;
    boolean        bNew;        // True : 알람 새로 추가 False : 기존 알람 수정
    int[] idDays = {R.id.btn_day_mon, R.id.btn_day_tues,R.id.btn_day_wed,
            R.id.btn_day_thus, R.id.btn_day_fri, R.id.btn_day_sat,R.id.btn_day_sun};
    //day : mon~sun (0~6)
    HashMap<Button,Boolean> mapDays= new HashMap<Button,Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setting_alarm_add);

        findView();
        initView();

    }

    public void findView(){
        tpTime = (TimePicker)findViewById(R.id.tp_time);
        cbRepeat = (CheckBox)findViewById(R.id.cb_repeat);
        etContent = (EditText)findViewById(R.id.et_content);
        btnAdd  = (Button)findViewById(R.id.btn_add);

    }

    public void initView(){
        mAlarm= getIntent().getParcelableExtra("alarmInfo");

        if(mAlarm==null){                                            //알람 새로 추가
            bNew = true;
            initDays(true);
            btnAdd.setText(R.string.alarm_add_btn_new);

            //tpTime.setIs24HourView(true);
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            //(e0824_1)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                tpTime.setCurrentHour(hour);
                tpTime.setCurrentMinute(minute);
            } else {
                tpTime.setHour(hour);
                tpTime.setMinute(minute);
            }



        }else {
            bNew = false;
            initDays(false);
            btnAdd.setText(R.string.alarm_add_btn_update);
            cbRepeat.setChecked(mAlarm.getAlarmRepeat());
            etContent.setText(mAlarm.getAlarmContent());
        }

    }

    public void initDays(boolean bNew){
        boolean[] arrDates = (bNew)? new boolean[]{false} : mAlarm.getArrDates();

        for(int i=0; i<idDays.length; i++){
            Button btn = (Button)findViewById(idDays[i]);
            boolean state = (bNew)? false:arrDates[i];
            btn.setOnClickListener(this);
            mapDays.put(btn,state);
            changeBtnStyle(btn, state);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //btn
        if(mapDays.containsKey(v)){
            boolean bStatus = !(mapDays.get(v));
            mapDays.put((Button)v, bStatus);
            changeBtnStyle((Button)v,bStatus);
            return;
        }
        switch (id){
            case R.id.btn_add :

                break;

        }

    }

    //status(boolean)  true = selected
    public void changeBtnStyle(Button btn, boolean status){
        if(status==true){
            btn.setBackgroundColor(Color.BLUE);
            btn.setTextColor(Color.GREEN);
        }else{
            btn.setSelected(false);
            btn.setBackgroundColor(Color.WHITE);
            btn.setTextColor(Color.BLACK);
        }
    }

}

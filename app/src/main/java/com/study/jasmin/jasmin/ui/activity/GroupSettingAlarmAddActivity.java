package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Alarm;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupSettingAlarmAddActivity extends AppCompatActivity implements View.OnClickListener, Callback {
    public static final String TAG = "GSetAlarmAddActivity";
    public static final int SELECT_INSERT = 1;
    public static final int SELECT_UPDATE = 2;
    public static final int SELECT_DELETE = 3;
    public static final int GO_LIST_ACTIVITY = 4;

    TimePicker tpTime;
    CheckBox cbRepeat;
    EditText etContent;
    Button btnAdd;
    Button btnDelete;
    Alarm mAlarm;
    String strToast;
    boolean bNew;        // True : 알람 새로 추가 False : 기존 알람 수정
    int[] idDays = {R.id.btn_day_mon, R.id.btn_day_tues, R.id.btn_day_wed,
            R.id.btn_day_thus, R.id.btn_day_fri, R.id.btn_day_sat, R.id.btn_day_sun};
    int selType = 0;
    OneButtonDialog oneButtonDialog;
    private ProgressDialog progress;
    HashMap<Button, Boolean> mapDays = new HashMap<Button, Boolean>();//day : mon~sun (0~6)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_setting_alarm_add);

        findView();
        initView();
    }

    public void findView() {
        tpTime = (TimePicker) findViewById(R.id.tp_time);
        cbRepeat = (CheckBox) findViewById(R.id.cb_repeat);
        etContent = (EditText) findViewById(R.id.et_content);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDelete = (Button)findViewById(R.id.btn_delete);
    }

    public void initView() {
        JasminPreference pref = JasminPreference.getInstance(this);
        progress = new ProgressDialog(this);
        oneButtonDialog = new OneButtonDialog(this);
        mAlarm = new Alarm();
        mAlarm = getIntent().getParcelableExtra("alarmInfo");        // alarmInfo 가져오기
        initAlarm();

    }

    public void initAlarm(){
        if (mAlarm == null) {                                       //알람 새로 추가
            bNew = true;
            initDays(true);
            btnAdd.setText(R.string.alarm_add_btn_new);
            btnAdd.setOnClickListener(this);
            // delete test
            btnDelete.setVisibility(View.INVISIBLE);
            setTimePicker(tpTime,
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE));
        } else {                                                     // 알람 수정 하기
            bNew = false;
            initDays(false);
            btnAdd.setText(R.string.alarm_add_btn_update);
            btnAdd.setOnClickListener(this);
            //delete test
            btnDelete.setVisibility(View.VISIBLE);
            btnDelete.setOnClickListener(this);
            cbRepeat.setChecked(mAlarm.getAlarmRepeat());
            etContent.setText(mAlarm.getAlarmContent());
            setTimePicker(tpTime, mAlarm.getAlarmHour(), mAlarm.getAlarmMinute());
        }
    }

    public void initDays(boolean bNew) {
        boolean[] arrDates = (bNew) ? new boolean[]{false} : mAlarm.getArrDates();

        for (int i = 0; i < idDays.length; i++) {
            Button btn = (Button) findViewById(idDays[i]);
            boolean state = (bNew) ? false : arrDates[i];
            btn.setOnClickListener(this);
            mapDays.put(btn, state);
            changeBtnStyle(btn, state);
        }
    }

    @Override
    public void onClick(View v) {
        //if btn date
        if (mapDays.containsKey(v)) {
            boolean bStatus = !(mapDays.get(v));
            mapDays.put((Button)v, bStatus);
            changeBtnStyle((Button)v, bStatus);
            return;
        }
        switch ( v.getId()) {
            case R.id.btn_add:
                if (validityCheck()) {
                    progress.show();
                    Call<JsonObject> call = null;
                    RestClient.RestService service = RestClient.getClient();
                    mAlarm = getAlarmInfo();
                    if (bNew) {   //새로추가(insert)
                        selType = SELECT_INSERT;
                        call = service.addAlarm(JasminPreference.getInstance(this).getSelStudyNo(),
                                                    mAlarm.getAlarmDateInt(),
                                                    mAlarm.getAlarmTime(),
                                                    (mAlarm.getAlarmRepeat()) ? 1 : 0,
                                                    mAlarm.getAlarmContent());
                        strToast = "알람이 추가되었습니다.";
                    } else {      //항목수정(update)
                        selType = SELECT_UPDATE;
                        call = service.updateAlarm(mAlarm.getAlarmNo(),
                                                    mAlarm.getAlarmDateInt(),
                                                    mAlarm.getAlarmTime(),
                                                    (mAlarm.getAlarmRepeat()) ? 1 : 0,
                                                    mAlarm.getAlarmContent());
                        strToast = "알람이 수정되었습니다.";

                    }
                    call.enqueue(this);

                } else {
                    showOneButtonDialog(R.string.alarm_add_vali_dialog_title,
                            R.string.alarm_add_vali_dialog_comment);
                }
                break;
            case R.id.onebutton_ok:
                oneButtonDialog.cancel();
                oneButtonDialog.dismiss();
                break;
            case R.id.btn_delete:
                if(mAlarm != null) {
                    selType = SELECT_DELETE;
                    progress.show();
                    RestClient.RestService service = RestClient.getClient();
                    Call<JsonObject> call = service.deleteAlarm(mAlarm.getAlarmNo());
                    call.enqueue(this);
                    strToast = "알람이 삭제되었습니다.";
                }
                break;
        }
    }

    public boolean validityCheck() {
        return true;
        //return (etContent.getText().toString().equals("")) ? false : true;
    }

    //status(boolean)  true = selected
    public void changeBtnStyle(Button btn, boolean status) {
        if (status == true) {
            btn.setBackgroundColor(Color.BLUE);
            btn.setTextColor(Color.GREEN);
        } else {
            btn.setSelected(false);
            btn.setBackgroundColor(Color.WHITE);
            btn.setTextColor(Color.BLACK);
        }
    }

    //안드로이드 별 버전 분기 별 timePicker 함수 처리
    public void setTimePicker(TimePicker tp, int hour, int minute) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            tp.setCurrentHour(hour);
            tp.setCurrentMinute(minute);
        } else {
            tp.setHour(hour);
            tp.setMinute(minute);
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, ">>>>>>>>>>>>onResponse = " + call.getClass().toString());
        Log.d(TAG, "Retro Status Code = " + response.code());

        try {
            JSONObject jsObject = new JSONObject(response.body().toString());

            if (response.body() != null || response.body() != "")
            {
                switch (selType){
                    case 1: case 2 :case 3:
                        //if(response.body().toString().equals("{\"result\":1}")){
                            callAlarmList();
                        //}else{
                        //      Toast.makeText(this, "실패했습니다", Toast.LENGTH_LONG).show();
                        //}

                        break;
                    case 4:
                        goAlarmList(jsObject);
                        finish();
                        progress.cancel();
                        progress.dismiss();
                        Toast.makeText(this, strToast, Toast.LENGTH_LONG).show();
                        break;
                }

            }
        }catch (JSONException e) {
                Log.d(TAG, "e : " + e);
            }
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    public Alarm getAlarmInfo() {
        int hour, minute = 0;
        Alarm alarm = (mAlarm == null) ? new Alarm() : mAlarm;

        //get Alarm date(int)
        int date = 0;
        for(int i=0; i<idDays.length; i++){
            date = date * 10;
            date = mapDays.get(findViewById(idDays[i])) == true ? date + 1 : date;
        }
         //get hour(int) & minute(int)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            hour = tpTime.getCurrentHour();
            minute = tpTime.getCurrentMinute();
        } else {
            hour = tpTime.getHour();
            minute = tpTime.getMinute();
        }
        //set alarm information and return
        alarm.setAlarmDateInt(date);
        alarm.setAlarmRepeat((cbRepeat.isChecked()) ? 1 : 0);
        alarm.setAlarmContent(etContent.getText().toString());
        alarm.setAlarmTime(hour * 100 + minute);

        return alarm;
    }

    public void showOneButtonDialog(int title, int comment) {
        oneButtonDialog = new OneButtonDialog(this);
        oneButtonDialog.setCancelable(false);
        oneButtonDialog.setOkOnClickListener(this);
        oneButtonDialog.show();
        oneButtonDialog.setTitle(title);
        oneButtonDialog.setComment(comment);
    }

    public void callAlarmList() {
        selType = GO_LIST_ACTIVITY;
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call =service.goAlarmList(JasminPreference.getInstance(this).getSelStudyNo());
        call.enqueue(this);
    }

    public void goAlarmList(JSONObject jsObject){
        try {
            Intent intent = new Intent(this, GroupSettingAlarmListActivity.class);
            Gson gson = new GsonBuilder().create();
            JSONArray attendObj = jsObject.getJSONArray("alarmList");
            Alarm[] alarmArr = gson.fromJson(attendObj.toString(), Alarm[].class);
            ArrayList<Alarm> alarmList = new ArrayList<Alarm>();

            Collections.addAll(alarmList, alarmArr);
            intent.putParcelableArrayListExtra("alarmList", alarmList);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            if (intent != null) startActivity(intent);


        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
    }
}
package com.study.jasmin.jasmin.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.entity.AttendanceTitle;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.AttendanceCheckDialog;
import com.study.jasmin.jasmin.ui.dialog.TwoButtonDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceList;
import com.study.jasmin.jasmin.ui.list.ListInfoReceivables;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupManageAttendanceActivity extends AppCompatActivity implements AdaptInfoAttendanceList.ListImgClickListener, View.OnClickListener,
                                                                                     ListView.OnItemClickListener, Callback{
    public static final String TAG = "GMAttendanceActivity";
    Button                  btnAdd;
    ListView                listview;
    AdaptInfoAttendanceList adapter;
    AttendanceCheckDialog   dialog;
    TwoButtonDialog         deleteDialog ;
    ArrayList<Attendance> attendanceList;
    ArrayList<AttendanceTitle> titleList;
    int                         deletePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_attendance);
        findViews();
        initViews();
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()) {
             case R.id.btn_add:
                 dialog = new AttendanceCheckDialog(this,null);
                 dialog.show();
                 break;
             case R.id.ok_twobutton:
                 callDelete();
                 deleteDialog.closeTwoButtonDialog();
                 break;
             case R.id.cancel_twobutton:
                 deleteDialog.closeTwoButtonDialog();
                 break;
         }
    }

    public void findViews(){
        listview = (ListView) findViewById(R.id.lv_attendance);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    public void initViews(){
        attendanceList = getIntent().getParcelableArrayListExtra("attendanceList");
        titleList = getTitleList();
        adapter = new AdaptInfoAttendanceList(this, R.layout.list_management_attandance, titleList, this);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    public ArrayList<AttendanceTitle> getTitleList() {
        ArrayList<AttendanceTitle> titleList = new ArrayList<AttendanceTitle>();
        boolean bfindDate;

        if(attendanceList == null) return null;

        for(int i=0; i<attendanceList.size(); i++ ) {
            bfindDate          = false;
            String attDate     = attendanceList.get(i).getAttendance_date();
            String attStatus   = attendanceList.get(i).getAttendance_state();

            //1. title에 date가 있을 경우
            if(titleList != null) {
                for (int j = 0; j < titleList.size(); j++) {
                    if (attDate.equals(titleList.get(j).getDate())){
                        titleList.get(j).setTitleCount(attStatus);
                        titleList.get(j).addAttendanceList(attendanceList.get(i));
                        bfindDate = true;
                        break;
                    }
                }
            }
            //2,title에 date가 없을 경우
            if(!bfindDate){
                AttendanceTitle tempTitle = new AttendanceTitle();
                tempTitle.setDate(attDate);
                tempTitle.setTitleCount(attStatus);
                tempTitle.addAttendanceList(attendanceList.get(i));
                titleList.add(tempTitle);
            }
        }
        return titleList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AttendanceTitle test = (AttendanceTitle)parent.getItemAtPosition(position);
        ArrayList<Attendance> list = test.getAttendanceList();
        dialog = new AttendanceCheckDialog(this,list);
        dialog.show();
    }

    @Override
    public void onListBtnClick(int position) {
        setDeletePosition(position);
        deleteDialog = new TwoButtonDialog(this);
        deleteDialog.setOkOnClickListener(this);
        deleteDialog.setCancelOnClickListener(this);
        deleteDialog.showTwoButtonDialog("출석 삭제","항목을 정말 삭제하시겠습니까?");

        /*
        RestClient.RestService service = RestClient.getClient();
        String str = "";
        Call<JsonObject> call = service.deleteAttendance(str);
        call.enqueue(this);

        Toast.makeText(this, Integer.toString(position+1) + "번 아이템 클릭", Toast.LENGTH_SHORT).show();
        */
    }

    public void setDeletePosition(int position){
        deletePos = position;
    }

    public int getDeletePosition(){
        return this.deletePos;
    }

    public void callDelete(){
        ArrayList<Attendance> list  = ((AttendanceTitle)listview.getItemAtPosition(getDeletePosition())).getAttendanceList();
        String strJson = "";
        for(int i =0; i<list.size();i++) {
            strJson = strJson + list.get(i).getJsonDelete()+ ",";
        }
        strJson = "[" + strJson.substring(0, strJson.length() - 1) + "]";

        //Log.d(TAG,">>>>>>delete return"+strJson);
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.deleteAttendance(strJson);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, "Retro Status Code = " + response.code());
        try {
            String strToast;
            String strTest = response.body().toString();
            JSONObject jsObject = new JSONObject(strTest);

            if(jsObject.getString("result").equals("1")){
                strToast = "출석이 삭제되었습니다";
            }else{
                strToast = "실패했습니다.";
            }
            Toast.makeText(getApplicationContext(),strToast, Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }
}



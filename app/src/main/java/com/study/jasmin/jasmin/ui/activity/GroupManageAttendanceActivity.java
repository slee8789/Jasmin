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

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.entity.AttendanceTitle;
import com.study.jasmin.jasmin.ui.dialog.AttendanceCheckDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class GroupManageAttendanceActivity extends AppCompatActivity implements AdaptInfoAttendanceList.ListBtnClickListener, View.OnClickListener, ListView.OnItemClickListener{
    public static final String TAG = "GMAttendanceActivity";
    Button                  btnAdd;
    ListView                listview;
    AdaptInfoAttendanceList adapter;
    AttendanceCheckDialog   dialog;
    ArrayList<Attendance> attendanceList;
    ArrayList<AttendanceTitle> titleList;

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
         }
    }

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(this, Integer.toString(position+1) + "번 아이템 클릭", Toast.LENGTH_SHORT).show();
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

        if(attendanceList == null) return null;
        ArrayList<AttendanceTitle> titleList = new ArrayList<AttendanceTitle>();
        AttendanceTitle tempTitle;
        String strDate;
        int  nStatus;
        boolean bfindDate;

        //attendanceList에서 attendanceTitleList 뽑아내기
        for(int i=0; i<attendanceList.size(); i++ ) {
            bfindDate = false;
            tempTitle = new AttendanceTitle();
            strDate = (attendanceList.get(i)).getAttendance_date();
            nStatus = (attendanceList.get(i)).getAttendance_state();

            //1. title에 date가 있을 경우
            if(titleList != null) {
                for (int j = 0; j < titleList.size(); j++) {
                    if (strDate.equals(titleList.get(j).getDate())) {
                        tempTitle = setTitleCount(nStatus, titleList.get(j));
                        tempTitle.addAttendanceList(attendanceList.get(i));
                        titleList.set(j, tempTitle);
                        bfindDate = true;
                        break;
                    }
                }
            }
            //2,title에 date가 없을 경우
            if(!bfindDate){
                tempTitle.setDate(strDate);
                tempTitle = setTitleCount(nStatus,tempTitle);
                tempTitle.addAttendanceList(attendanceList.get(i));
                titleList.add(tempTitle);
            }
        }
        return titleList;
    }

    public AttendanceTitle setTitleCount(int status, AttendanceTitle title){
        switch (status){
            case 1:
                title.countAttendType1();
                break;
            case 2:
                title.countAttendType2();
                break;
            case 3:
                title.countAttendType3();
                break;
        }
        return title;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AttendanceTitle test = (AttendanceTitle)parent.getItemAtPosition(position);
        ArrayList<Attendance> at = test.getAttendanceList();
        dialog = new AttendanceCheckDialog(this,at);
        dialog.show();
    }
}


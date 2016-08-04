package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Spinner;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceCheckList;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendanceCheck;

import java.util.ArrayList;

public class AttendanceCheckActivity extends AppCompatActivity {

    private ListView attendanceCheckList;
    private ArrayList<ListInfoAttendanceCheck> arrayListInfo = new ArrayList<ListInfoAttendanceCheck>();
    private AdaptInfoAttendanceCheckList adaptListInfo;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_check);


        ArrayList<String> AttendanceCheckColumnTest0 = new ArrayList<String>();
        AttendanceCheckColumnTest0.add("김세정");

        ArrayList<String> AttendanceCheckColumnTest1 = new ArrayList<String>();
        AttendanceCheckColumnTest1.add("김소혜");

        ArrayList<ArrayList<String>> AttendanceCheckRowTest = new ArrayList<ArrayList<String>>();

        AttendanceCheckRowTest.add(AttendanceCheckColumnTest0);
        AttendanceCheckRowTest.add(AttendanceCheckColumnTest1);

       // adaptListInfo = new AdaptInfoAttendanceCheckList(getApplicationContext(), R.layout.list_attendance_check_info, arrayListInfo,true);
        attendanceCheckList.setAdapter(adaptListInfo);
        addSelectInfo(AttendanceCheckRowTest);
       // adaptListInfo.setArraySelectInfo(arrayListInfo);
        attendanceCheckList.setAdapter(adaptListInfo);

//        attendanceCheckList = (ListView)findViewById(R.id.list_attendance_check);
//        String[] ITEMS = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ITEMS);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner = (MaterialSpinner) attendanceCheckList.findViewById(R.id.spinner);
//        spinner.setAdapter(adapter);

        }

public void addSelectInfo(ArrayList<ArrayList<String>> parseredList) {
        for (int i = 0; i < parseredList.size(); i++) {
        ListInfoAttendanceCheck selectInfo = new ListInfoAttendanceCheck();
        selectInfo.setName(parseredList.get(i).get(0));

        arrayListInfo.add(selectInfo);
        }
        }
        }

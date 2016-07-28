package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentCheckList;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceCheckList;
import com.study.jasmin.jasmin.ui.list.ListInfoAssignmentCheck;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendanceCheck;

import java.util.ArrayList;

public class AttendanceCheckActivity extends AppCompatActivity {

    private ListView attendanceCheckList;
    private ArrayList<ListInfoAttendanceCheck> arrayListInfo = new ArrayList<ListInfoAttendanceCheck>();
    private AdaptInfoAttendanceCheckList adaptListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_check);
        attendanceCheckList = (ListView)findViewById(R.id.list_attendance_check);

        ArrayList<String> AttendanceCheckColumnTest0 = new ArrayList<String>();
        AttendanceCheckColumnTest0.add("김세정");

        ArrayList<String> AttendanceCheckColumnTest1 = new ArrayList<String>();
        AttendanceCheckColumnTest1.add("김소혜");

        ArrayList<ArrayList<String>> AttendanceCheckRowTest = new ArrayList<ArrayList<String>>();

        AttendanceCheckRowTest.add(AttendanceCheckColumnTest0);
        AttendanceCheckRowTest.add(AttendanceCheckColumnTest1);

        adaptListInfo = new AdaptInfoAttendanceCheckList(getApplicationContext(), R.layout.list_attendance_check_info, arrayListInfo);
        attendanceCheckList.setAdapter(adaptListInfo);
        addSelectInfo(AttendanceCheckRowTest);
        adaptListInfo.setArraySelectInfo(arrayListInfo);
        attendanceCheckList.setAdapter(adaptListInfo);
    }

    public void addSelectInfo(ArrayList<ArrayList<String>> parseredList) {
        for (int i = 0; i < parseredList.size(); i++) {
            ListInfoAttendanceCheck selectInfo = new ListInfoAttendanceCheck();
            selectInfo.setName(parseredList.get(i).get(0));

            arrayListInfo.add(selectInfo);
        }
    }
}

package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentCheckList;
import com.study.jasmin.jasmin.ui.list.ListInfoAssignmentCheck;

import java.util.ArrayList;

public class AssignmentCheckActivity extends AppCompatActivity {

    private ListView assignmentCheckList;
    private ArrayList<ListInfoAssignmentCheck> arrayListInfo = new ArrayList<ListInfoAssignmentCheck>();
    private AdaptInfoAssignmentCheckList adaptListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_check);
        assignmentCheckList = (ListView)findViewById(R.id.list_assignment_check);


        ArrayList<String> AssignmentCheckColumnTest0 = new ArrayList<String>();
        AssignmentCheckColumnTest0.add("김세정");

        ArrayList<String> AssignmentCheckColumnTest1 = new ArrayList<String>();
        AssignmentCheckColumnTest1.add("김소혜");

        ArrayList<ArrayList<String>> AssignmentCheckRowTest = new ArrayList<ArrayList<String>>();

        AssignmentCheckRowTest.add(AssignmentCheckColumnTest0);
        AssignmentCheckRowTest.add(AssignmentCheckColumnTest1);

        adaptListInfo = new AdaptInfoAssignmentCheckList(getApplicationContext(), R.layout.list_assignment_check_info, arrayListInfo);
        assignmentCheckList.setAdapter(adaptListInfo);
        addSelectInfo(AssignmentCheckRowTest);
        adaptListInfo.setArraySelectInfo(arrayListInfo);
        assignmentCheckList.setAdapter(adaptListInfo);

    }

    public void addSelectInfo(ArrayList<ArrayList<String>> parseredList) {
        for (int i = 0; i < parseredList.size(); i++) {
            ListInfoAssignmentCheck selectInfo = new ListInfoAssignmentCheck();
            selectInfo.setName(parseredList.get(i).get(0));

            arrayListInfo.add(selectInfo);
        }
    }
}

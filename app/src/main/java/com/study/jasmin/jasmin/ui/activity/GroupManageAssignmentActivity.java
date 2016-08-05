package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.AttendanceCheckDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentList;
import com.study.jasmin.jasmin.ui.list.ListInfoAssignment;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendance;

import java.util.ArrayList;

public class GroupManageAssignmentActivity extends AppCompatActivity {

    Button btnAdd;
    ListView listView;
    View.OnClickListener listener;
    AdaptInfoAssignmentList adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_assignment);

        findViews();
        initViews();


    }

    public void findViews(){
        btnAdd = (Button)findViewById(R.id.btn_add);
        listView = (ListView)findViewById(R.id.lv_assignment);
    }

    public void initViews(){

        adapt = new AdaptInfoAssignmentList(this, R.layout.list_assignment_info, getItemFromDB());
        listView.setAdapter(adapt);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AttendanceCheckDialog dialog = new AttendanceCheckDialog(v.getContext());
                dialog.show();
            }
        });


    }

    public ArrayList<ListInfoAssignment> getItemFromDB(){
        ArrayList<ListInfoAssignment> list = new ArrayList<ListInfoAssignment>();
        ListInfoAssignment item;
        //item 입력
        for(int i=0; i<20; i++){
            item = new ListInfoAssignment();
            item.setTitle(Integer.toString(i+1)+"번째 과제입니다.");
            item.setStatus("0/0");
            list.add(item);
        }
        return list;
    }

}

package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;
import com.study.jasmin.jasmin.ui.dialog.AssignmentAddDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentList;

import java.util.ArrayList;
import java.util.Collections;

public class GroupManageAssignmentActivity extends AppCompatActivity{
    public static final String TAG = "GMAA";
    private Button btnAdd;
    private ListView listView;
    private View emptyView;
    private AdaptInfoAssignmentList adapt;
    private Assignment[] assignments;
    private ArrayList<Assignment> assignmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_assignment);


        Bundle extras = getIntent().getExtras();
        assignments = (Assignment[])extras.get("assignments");
        assignmentList = new ArrayList<Assignment>();
        Collections.addAll(assignmentList, assignments);
        findViews();
        initViews();
    }

    public void findViews(){
        btnAdd = (Button)findViewById(R.id.btn_add);
        listView = (ListView)findViewById(R.id.lv_assignment);
        emptyView = findViewById(R.id.list_empty);
    }

    public void initViews(){

        adapt = new AdaptInfoAssignmentList(this, R.layout.list_assignment_info, assignmentList);
        listView.setAdapter(adapt);
        listView.setEmptyView(emptyView);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AssignmentAddDialog dialog = new AssignmentAddDialog(v.getContext());
                dialog.show();
            }
        });
    }

}

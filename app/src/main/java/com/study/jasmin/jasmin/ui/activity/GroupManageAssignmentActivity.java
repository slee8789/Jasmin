package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;
import com.study.jasmin.jasmin.entity.AssignmentTitle;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.ui.dialog.AssignmentAddDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentList;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;
import java.util.Collections;

public class GroupManageAssignmentActivity extends AppCompatActivity{
    public static final String TAG = "GMAA";
    private Button btnAdd;
    private ListView listView;
    private View emptyView;
    private AdaptInfoAssignmentList adapt;
    private ArrayList<Assignment> assignmentList;
    private ArrayList<AssignmentTitle> assignmentTitle;
    private JasminPreference mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_assignment);
        assignmentList = new ArrayList<>();
        assignmentTitle = new ArrayList<>();
        mPref = JasminPreference.getInstance(this);

        setAssignmentList();
        findViews();
        initViews();
    }

    public void findViews(){
        btnAdd = (Button)findViewById(R.id.btn_add);
        listView = (ListView)findViewById(R.id.lv_assignment);
        emptyView = findViewById(R.id.list_empty);
    }

    public void initViews(){
        adapt = new AdaptInfoAssignmentList(this, R.layout.list_assignment_info, assignmentTitle);
        listView.setAdapter(adapt);
        listView.setEmptyView(emptyView);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                AssignmentAddDialog dialog = new AssignmentAddDialog(v.getContext());
                dialog.show();
            }
        });
    }

    public void setAssignmentList(){
        boolean bCheck;
        ArrayList<Object> objects = mPref.getListValue("assignmentList");

        for(Object obj : objects){
            bCheck = true;
            Assignment assTemp = (Assignment)obj;

            assignmentList.add(assTemp);

            if(assignmentTitle.size()>0){
                for (AssignmentTitle title : assignmentTitle) {
                    bCheck =  (bCheck == true && assTemp.getHomework_no() == title.getHomework_no())? false : true;
                }
            }

            if(bCheck)   assignmentTitle.add(getTitle(assTemp));// end for (title)
        }
    }


    public AssignmentTitle getTitle(Assignment assignment){
        AssignmentTitle title =  new AssignmentTitle(assignment.getStudy_no(),assignment.getHomework_no(),
                assignment.getHomework_title(), assignment.getHomework_content(),
                assignment.getHomework_start_date(), assignment.getHomework_end_date(),
                assignment.getHomework_money());

        return title;
    }

}

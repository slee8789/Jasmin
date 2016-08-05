package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.AssignmentAddDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentList;
import com.study.jasmin.jasmin.ui.list.ListInfoAssignment;

import java.util.ArrayList;

public class GroupManageAssignmentActivity extends AppCompatActivity{

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
                AssignmentAddDialog dialog = new AssignmentAddDialog(v.getContext());
                dialog.show();
            }
        });
    }

    public ArrayList<ListInfoAssignment> getItemFromDB(){
        ArrayList<ListInfoAssignment> list = new ArrayList<ListInfoAssignment>();
        ListInfoAssignment item;
        //item �Է�
        for(int i=0; i<20; i++){
            item = new ListInfoAssignment();
            item.setTitle(Integer.toString(i+1)+"dfdfdf");
            item.setStatus("0/0");
            list.add(item);
        }
        return list;
    }


}

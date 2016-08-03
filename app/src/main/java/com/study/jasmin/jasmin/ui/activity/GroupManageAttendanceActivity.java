package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceList;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendance;

import java.util.ArrayList;

public class GroupManageAttendanceActivity extends AppCompatActivity implements AdaptInfoAttendanceList.ListBtnClickListener, View.OnClickListener{

    Button                  btnAdd;
    ListView                listview;
    AdaptInfoAttendanceList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_attendance);

        findViews();
        initViews();

    }

    public ArrayList<ListInfoAttendance> getItemsFromDB() {

        ArrayList<ListInfoAttendance> list  = new ArrayList<ListInfoAttendance>();
        ListInfoAttendance item ;

        //item 입력
        for(int i=0; i<20; i++){
            item = new ListInfoAttendance();
            item.setDate("yy-mm-dd");
            item.setStatus("0/0/0");
            list.add(item);
        }
        return list ;
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()) {
             case R.id.btn_add:
                 Toast.makeText(this, "추가 다이얼로그", Toast.LENGTH_SHORT).show();
                 //startActivity(new Intent(this, SettingAssignmentAddActivity.class));
                 break;
         }
    }

    @Override
    public void onListBtnClick(int position) {
        //리스트의 버튼 클릭 시 실행되는 함수
        Toast.makeText(this, Integer.toString(position+1) + "번 아이템 수정", Toast.LENGTH_SHORT).show();
    }

    public void findViews(){
        listview = (ListView) findViewById(R.id.lv_attendance);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    public void initViews(){
        adapter = new AdaptInfoAttendanceList(this, R.layout.list_management_attandance, getItemsFromDB(), this);
        listview.setAdapter(adapter);
        btnAdd.setOnClickListener(this);
    }

}

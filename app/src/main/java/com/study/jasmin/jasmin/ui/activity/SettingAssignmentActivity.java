package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.item.ListViewAssignment;
import com.study.jasmin.jasmin.ui.item.ListViewBtnAdapter;

import java.util.ArrayList;

public class SettingAssignmentActivity extends AppCompatActivity implements ListViewBtnAdapter.ListBtnClickListener, View.OnClickListener{

    Button              btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_assignment);

        //list 처리
        ListView            listview;
        ListViewBtnAdapter  adapter;

        ArrayList<ListViewAssignment> items = new ArrayList<ListViewAssignment>() ;

        // items 로드.
        loadItemsFromDB(items) ;

        // Adapter 생성
        adapter = new ListViewBtnAdapter(this, R.layout.listview_setting_assignment, items, this);

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // TODO : item click
            }
        });

        // button 처리
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                startActivity(new Intent(this, SettingAssignmentAddActivity.class));
                break;
        }
    }

    public boolean loadItemsFromDB(ArrayList<ListViewAssignment> list) {

        ListViewAssignment item ;

        if (list == null) {
            list = new ArrayList<ListViewAssignment>() ;
        }
        //item 입력
          for(int i=0; i<5; i++){
              item = new ListViewAssignment();
              item.setDate("yy-mm-dd");
              item.setTitle("title");
              item.setStatus("0/0");
              list.add(item);
        }
        return true ;
    }

    @Override
    public void onListBtnClick(int position) {
        //리스트의 삭제버튼 클릭 시 실행되는 함수
       Toast.makeText(this, Integer.toString(position+1) + "번 아이템 삭제", Toast.LENGTH_SHORT).show();
    }
}

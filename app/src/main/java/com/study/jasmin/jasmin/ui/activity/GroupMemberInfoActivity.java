package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.item.ListViewAuthoritySharingAdapter;
import com.study.jasmin.jasmin.ui.item.ListViewMember;

import java.util.ArrayList;

public class GroupMemberInfoActivity extends AppCompatActivity implements View.OnClickListener{

    ListView    listview;
    Button      btnInvite;
    Button      btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_member_info);

        //add button listener
        btnInvite   = (Button)findViewById(R.id.btn_invite_member);
        btnComplete = (Button)findViewById(R.id.btn_complete);
        btnInvite.setOnClickListener(this);
        btnComplete.setOnClickListener(this);

        //add ListView adapter
        ListViewAuthoritySharingAdapter adapter;
        ArrayList<ListViewMember> items = new ArrayList<ListViewMember>() ;

        loadItemsFromDB(items);

        adapter = new ListViewAuthoritySharingAdapter(this, R.layout.listview_setting_authority, items) ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.lv_member);
        listview.setAdapter(adapter);
    }

    public boolean loadItemsFromDB(ArrayList<ListViewMember> list) {

        ListViewMember item ;
        String[] arrName = {"강미나","김도연","임나영","전소미"};
        if (list == null) {
            list = new ArrayList<ListViewMember>() ;
        }
        //item 입력
        for(int i=0; i<arrName.length; i++){
            item = new ListViewMember();
            item.setName(arrName[i]);
            item.setDwGrade(ContextCompat.getDrawable(this, R.drawable.ic_close_black_24dp));
            list.add(item);
        }
        return true ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_complete:
                finish();
                break;
            case R.id.btn_invite_member:
                startActivity(new Intent(this,GroupInviteActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
}

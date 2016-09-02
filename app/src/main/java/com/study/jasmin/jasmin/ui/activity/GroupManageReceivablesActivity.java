package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.TwoButtonDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoReceivablesList;
import com.study.jasmin.jasmin.ui.list.ListInfoReceivables;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

public class GroupManageReceivablesActivity extends AppCompatActivity implements AdaptInfoReceivablesList.ListBtnClickListener {

    ListView list;
    AdaptInfoReceivablesList adapt;
    TwoButtonDialog dialog;
    JasminPreference mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_receivables);
        mPref = JasminPreference.getInstance(this);

        findViews();
        initViews();
    }

    public void findViews(){
        list = (ListView)findViewById(R.id.lv_receivables);
    }

    public void initViews(){
        /*
            adapt = new AdaptInfoMoneybookList(this, R.layout.list_moneybook_info,  );
        list.setAdapter(adapt);
         */
        adapt = new AdaptInfoReceivablesList(this, R.layout.list_receivables_info, mPref.getListValue("penaltyList"),this);
        list.setAdapter(adapt);
    }


    @Override
    public void onListBtnClick(ListInfoReceivables listInfoReceivables ) {

        final ListInfoReceivables listInfo = listInfoReceivables ;

        String comment = listInfo.getDate()+" "+listInfo.getName()+"님의 "
                        +listInfo.getReason() +"벌금 "+listInfo.getMoney()+"원을 수령하셨습니까?";

        dialog = new TwoButtonDialog(this);
        dialog.setCancelOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.closeTwoButtonDialog();
            }
        });
        dialog.setOkOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.closeTwoButtonDialog();
            }
        });
        dialog.showTwoButtonDialog("미수금",comment);

    }

}


package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.MoneybookDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoMoneybookList;
import com.study.jasmin.jasmin.ui.list.ListInfoMoneybook;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

public class GroupManageMoneybookActivity extends AppCompatActivity{

    ListView list;
    AdaptInfoMoneybookList adapt;
    JasminPreference mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_moneybook);
        mPref = JasminPreference.getInstance(this);

        findViews();
        initViews();
    }

    public void findViews() {
        list = (ListView) findViewById(R.id.lv_moneybook);
    }

    public void initViews() {
        /*
        btnAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoneybookDialog dialog = new MoneybookDialog(v.getContext());
                dialog.show();
                dialog.setComment("머니북 입/출금","확인","취소");
            }
        });
        */

        adapt = new AdaptInfoMoneybookList(this, R.layout.list_moneybook_info,  mPref.getListValue("moneybookList"));
        list.setAdapter(adapt);

    }

}

package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.MoneyBook;
import com.study.jasmin.jasmin.entity.MoneyEct;
import com.study.jasmin.jasmin.ui.dialog.MoneybookDialog;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoMoneyEctList;
import com.study.jasmin.jasmin.ui.list.AdaptInfoReceivablesList;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;
import java.util.List;

public class GroupManageMoneyEctActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    public static final String TAG = "GMMoneyEctActivity";
    private Button          btnAdd;
    private ListView        list;
    JasminPreference          mPref;
    ProgressDialog            progress;
    MoneybookDialog           moneyBookDialog;
    ArrayList<Object>         moneyectList;
    private AdaptInfoMoneyEctList adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_money_ect);

        init();
        findViews();
        initViews();
    }

    public void init(){
        progress = new ProgressDialog(this);
        mPref = JasminPreference.getInstance(this);
        moneyectList = mPref.getListValue("moneyectList");
    }

    public void findViews(){
        btnAdd  = (Button)findViewById(R.id.btn_add);
        list    = (ListView)findViewById(R.id.lv_money_ect);
    }

    public void initViews(){
        btnAdd.setOnClickListener(this);
        adapt = new AdaptInfoMoneyEctList(this, R.layout.list_moneyect_info, moneyectList);
        list.setAdapter(adapt);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add:
                showMoneyBookDialog(null);
                break;
            case R.id.ok_twobutton:
                break;
            case R.id.cancel_twobutton:
                moneyBookDialog.dismiss();
                moneyBookDialog.cancel();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showMoneyBookDialog((MoneyEct)parent.getItemAtPosition(position));
    }

    public void showMoneyBookDialog(MoneyEct moneyEctInfo){
        moneyBookDialog = new MoneybookDialog(this,moneyEctInfo);
        moneyBookDialog.setOkOnClickListener(this);
        moneyBookDialog.setCancelOnClickListener(this);
        moneyBookDialog.show();
    }
}

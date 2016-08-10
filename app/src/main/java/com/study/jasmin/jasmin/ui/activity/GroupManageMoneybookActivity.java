package com.study.jasmin.jasmin.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.MoneybookDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoMoneybookList;
import com.study.jasmin.jasmin.ui.list.ListInfoMoneybook;

import java.util.ArrayList;

public class GroupManageMoneybookActivity extends AppCompatActivity{

    Button btnAdd;
    ListView list;
    AdaptInfoMoneybookList adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_manage_moneybook);

        findViews();
        initViews();
    }

    public void findViews() {
        btnAdd = (Button)findViewById(R.id.btn_add);
        list = (ListView) findViewById(R.id.lv_moneybook);
    }

    public void initViews() {
        btnAdd.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoneybookDialog dialog = new MoneybookDialog(v.getContext());
                dialog.show();
                dialog.setComment("머니북 입/출금","확인","취소");
            }
        });

        adapt = new AdaptInfoMoneybookList(this, R.layout.list_moneybook_info, getItemFromDB());
        list.setAdapter(adapt);

    }

    public ArrayList<ListInfoMoneybook> getItemFromDB(){

        String[] names = {"김소혜", "최유정", "전소미", "주결경", "임나영", "최유정", "유연정", "정채연", "강미나",
                "김소혜", "최유정", "전소미", "주결경", "임나영", "최유정", "유연정", "정채연", "강미나"};
        ArrayList<ListInfoMoneybook> list= new ArrayList<ListInfoMoneybook>();
        ListInfoMoneybook item;

        for (String s : names) {
            item = new ListInfoMoneybook();
            item.setName(s);
            item.setDate("16/08/05");
            item.setReason("지각");
            item.setMoney("1000");
            list.add(item);
        }
        return list;
    }


}

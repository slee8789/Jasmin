package com.study.jasmin.jasmin.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

public class MoneybookDialog extends Dialog implements View.OnClickListener{
    public static final String TAG = "MoneybookDialog";

    private Button ok;
    private Button cancel;
    private Button selectGroup;
    private Button selectMember;
    private TextView title;

    private EditText user;

    public MoneybookDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_moneybook);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        findVeiws();


    }

    public void findVeiws(){
        title = (TextView) findViewById(R.id.title);
        ok = (Button)findViewById(R.id.ok_twobutton);
        cancel = (Button)findViewById(R.id.cancel_twobutton);
        selectGroup = (Button)findViewById(R.id.btn_select_group);
        selectMember = (Button)findViewById(R.id.btn_select_member);
        user = (EditText)findViewById(R.id.et_user);
    }

    public void setComment(String title, String strBtnLeft, String strBtnRight) {
        this.title.setText(title);
        this.ok.setText(strBtnLeft);
        this.cancel.setText(strBtnRight);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ok_twobutton:
                break;
            case R.id.cancel_twobutton:
                this.cancel();
                break;
            case R.id.btn_select_group:
                user.setText("그룹명");
                v.notify();
                break;
            case R.id.btn_select_member:
                String[] names = {"김소혜", "최유정", "전소미", "주결경", "임나영", "최유정", "유연정", "정채연", "강미나"};
                //MemberListDialog dialog  = new MemberListDialog(v.getContext(),"멤버리스트",names);
                //dialog.show();
                break;
        }
    }
}


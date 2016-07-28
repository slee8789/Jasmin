package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

public class MemberListDialog extends Dialog implements View.OnClickListener{

    private RadioGroup  radioGroup;

    public MemberListDialog(Context context, String strTop, String[] arrName) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_member_list);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        this.setCanceledOnTouchOutside(false);		// 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true);                   // 백키로 다이알로그 닫기

        initComponent(context, strTop, arrName);


    }

    private void initComponent(Context context, String strTop, String[] arrName){
        ((Button)findViewById(R.id.btn_complete)).setOnClickListener(this);	// 클릭 이벤트 등록
        ((TextView) findViewById(R.id.tvText)).setText(strTop);          // 텍스트 등록

        //라디오 버튼 동적 생성
        radioGroup = (RadioGroup)findViewById(R.id.rg_member);

        for(int i=0; i<arrName.length ; i++){
            RadioButton radio_btn = new RadioButton(context);
            radio_btn.setId(i);
            radio_btn.setText(arrName[i]);
            //radio_btn[i].setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            radioGroup.addView(radio_btn);
        }

    }

    @Override
    public void show()
    {
        super.show();
    }

    @Override
    public void dismiss()
    {
        super.dismiss();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_complete:
                super.dismiss();
                break;
            default:
                break;
        }
    }
}


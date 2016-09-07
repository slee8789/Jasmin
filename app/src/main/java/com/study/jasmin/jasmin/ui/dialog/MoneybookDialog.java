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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.MoneyEct;

public class MoneybookDialog extends Dialog{
    public static final String TAG = "MoneybookDialog";
    private Button   ok, cancel;
    private TextView title;
    private EditText date, reason, money;
    private RadioGroup inout;
    private View.OnClickListener buttonOkListener, buttonCancelListener;
    private MoneyEct moneyEctInfo;

    public MoneybookDialog(Context context, MoneyEct moneyEctInfo) {
        super(context);
        this.moneyEctInfo = (moneyEctInfo!=null)? moneyEctInfo:null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_moneybook);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        findViews();
        initViews();
    }

    public void findViews(){
        title   =(TextView) findViewById(R.id.title);
        ok       =(Button)findViewById(R.id.ok_twobutton);
        cancel  =(Button)findViewById(R.id.cancel_twobutton);
        date    =(EditText)findViewById(R.id.et_date);
        reason  =(EditText)findViewById(R.id.et_comment);
        money   =(EditText)findViewById(R.id.et_money);
        inout   =(RadioGroup)findViewById(R.id.rg_inout);
    }

    public void initViews(){
        ok.setOnClickListener(buttonOkListener);
        cancel.setOnClickListener(buttonCancelListener);
        if(moneyEctInfo != null){
            date.setText(moneyEctInfo.getInout_date());
            reason.setText(moneyEctInfo.getInout_reason());
            money.setText(Integer.toString(Math.abs(moneyEctInfo.getInout_money())));
            inout.check((moneyEctInfo.getInout_money()>0)?R.id.rb_in:R.id.rb_out);
        }
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

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public void setCancelOnClickListener(View.OnClickListener listener) {
        buttonCancelListener = listener;
    }

    public MoneyEct getMoneyEctInfo(){
        return moneyEctInfo;
    }
}


package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;


/**
 * Created by leesc on 2015-09-08.
 */
public class TwoButtonDialog extends Dialog {
    private final String TAG = "TwoButtonDialog";
    private View.OnClickListener buttonOkListener;
    private View.OnClickListener buttonCancelListener;
    private Button ok;
    private Button cancel;
    private TextView title;
    private TextView comment;

    public TwoButtonDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_twobutton);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        title = (TextView) findViewById(R.id.twobutton_title);
        comment = (TextView) findViewById(R.id.comment);
        ok = (Button)findViewById(R.id.ok_twobutton);
        cancel = (Button)findViewById(R.id.cancel_twobutton);
        ok.setOnClickListener(buttonOkListener);
        cancel.setOnClickListener(buttonCancelListener);
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public void setCancelOnClickListener(View.OnClickListener listener) { buttonCancelListener = listener;    }

    public void setComment(int commentId) {
        this.comment.setText(commentId);
    }

    public void setTitle(int titleId) {
        this.title.setText(titleId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.dismiss();
    }

    public void showTwoButtonDialog(int titleId, int commentId){
        this.show();
        this.setTitle(titleId);
        this.setComment(commentId);
    }

    public void showTwoButtonDialog(String strTitle, String strComment){
        this.show();
        this.title.setText(strTitle);
        this.comment.setText(strComment);
    }

    public void showTwoButtonDialog(int titleId,  String strComment){
        this.show();
        this.setTitle(titleId);
        this.setComment(strComment);
    }


    public void closeTwoButtonDialog(){
        this.dismiss();
        this.cancel();
    }
    public void setComment(String comment) {
        this.comment.setText(comment);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}

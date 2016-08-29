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

import com.study.jasmin.jasmin.R;


/**
 * Created by leesc on 2015-09-08.
 */
public class CommentUpdateDialog extends Dialog {

    private final String TAG = "CommentUpdateDialog";
    private View.OnClickListener buttonOkListener;
    private View.OnClickListener buttonCancelListener;
    private Button ok;
    private Button cancel;
    private EditText comment;

    public CommentUpdateDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comment_update);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ok = (Button)findViewById(R.id.ok_twobutton);
        cancel = (Button)findViewById(R.id.cancel_twobutton);
        comment = (EditText) findViewById(R.id.find_email);
        ok.setOnClickListener(buttonOkListener);
        cancel.setOnClickListener(buttonCancelListener);
    }

    public void setComment(String comment) {
        this.comment.setHint(comment);
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public void setCancelOnClickListener(View.OnClickListener listener) {
        buttonCancelListener = listener;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"click onBackPressed");
        dismiss();
    }
}

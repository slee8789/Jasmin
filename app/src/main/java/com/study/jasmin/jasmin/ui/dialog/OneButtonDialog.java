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
public class OneButtonDialog extends Dialog {

    private final String TAG = "OneButtonDialog";
    private View.OnClickListener buttonOkListener;
    private TextView title;
    private Button ok;
    private TextView comment;

    public OneButtonDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_onebutton);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        title = (TextView) findViewById(R.id.onebutton_title);
        ok = (Button)findViewById(R.id.onebutton_ok);
        comment = (TextView) findViewById(R.id.onebutton_comment);
        ok.setOnClickListener(buttonOkListener);
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

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

}

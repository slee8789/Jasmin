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
        ok = (Button)findViewById(R.id.ok_onebutton);
        comment = (TextView) findViewById(R.id.comment);
        ok.setOnClickListener(buttonOkListener);
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public void setComment(String comment) {
        this.comment.setText(comment);
    }
}

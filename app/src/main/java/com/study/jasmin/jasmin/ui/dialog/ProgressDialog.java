package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

/**
 * Created by leesc on 2016-08-05.
 */
public class ProgressDialog extends Dialog {

    private final String TAG = "ProgressDialog";
    private static ProgressDialog instance;
    private TextView progress_msg;

    public ProgressDialog(Context context) {
        super(context);

    }

    public static ProgressDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (ProgressDialog.class) {
                if (instance == null) {
                    instance = new ProgressDialog(context);
                }
            }
        }
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progress_msg= (TextView) findViewById(R.id.progress_msg);
    }

    public void setProgressMsg(TextView progress_msg) {
        this.progress_msg = progress_msg;
    }
}

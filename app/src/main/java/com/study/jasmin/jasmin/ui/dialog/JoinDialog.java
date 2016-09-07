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
public class JoinDialog extends Dialog {

    private final String TAG = "JoinDialog";
    private View.OnClickListener buttonOkListener;
    private Button ok;
    private EditText code;

    public JoinDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_join_study);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ok = (Button)findViewById(R.id.join_ok);
        code = (EditText) findViewById(R.id.et_code);
        ok.setOnClickListener(buttonOkListener);
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public EditText getCode() {
        return code;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"click onBackPressed");
        dismiss();
    }
}

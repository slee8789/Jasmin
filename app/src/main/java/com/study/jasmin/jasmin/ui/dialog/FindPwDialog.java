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
public class FindPwDialog extends Dialog {

    private final String TAG = "FindPwDialog";
    private View.OnClickListener buttonOkListener;
    private Button send;
    private EditText name;
    private EditText email;

    public FindPwDialog(Context context) {
        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_find_pw);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        send = (Button)findViewById(R.id.send_button);
        name = (EditText) findViewById(R.id.find_name);
        email = (EditText) findViewById(R.id.find_email);
        send.setOnClickListener(buttonOkListener);
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG,"click onBackPressed");
        dismiss();
    }
}

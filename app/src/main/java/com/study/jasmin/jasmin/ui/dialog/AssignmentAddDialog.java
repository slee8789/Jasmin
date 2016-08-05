package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.study.jasmin.jasmin.R;

/**
 * Created by swan on 2016-08-04.
 */
public class AssignmentAddDialog extends Dialog {

    private final String TAG = "AssignmentAddDialog";
    private View.OnClickListener buttonOkListener;
    private View.OnClickListener buttonCancelListener;
    private Button btnOk;
    private Button btnCancel;
    private EditText etTitle;
    private EditText etContent;
    private DatePicker date;

    public AssignmentAddDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_assignment_add);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);        // 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true);                  // 백키로 다이알로그 닫기

        findViews();
        initViews();

    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public void setCancelOnClickListener(View.OnClickListener listener) {
        buttonCancelListener = listener;
    }

    public void findViews() {
        etTitle = (EditText) findViewById(R.id.et_name);
        etContent = (EditText) findViewById(R.id.et_msg);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
    }

    public void initViews() {
        btnOk.setOnClickListener(buttonOkListener);
        btnCancel.setOnClickListener(buttonCancelListener);
    }

}

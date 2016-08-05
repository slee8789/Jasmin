package com.study.jasmin.jasmin.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceCheckList;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendanceCheck;

import java.util.ArrayList;

/**
 * Created by swan on 2016-08-03.
 */
public class AttendanceCheckDialog extends Dialog{

    private final String TAG = "AttendanceCheckDialog";
    private View.OnClickListener buttonOkListener;
    private View.OnClickListener buttonCancelListener;
    private Button btnOk;
    private Button btnCancel;
    private ListView listView;
    private AdaptInfoAttendanceCheckList adapter;

    public AttendanceCheckDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_attendance_check);
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
        listView = (ListView) findViewById(R.id.lv_attendance_check);
        btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
    }

    public void initViews() {
        adapter = new AdaptInfoAttendanceCheckList(this.getContext(), R.layout.list_attendance_check_info, getItemsFromDB(), true);
        listView.setAdapter(adapter);
        btnOk.setOnClickListener(buttonOkListener);
        btnCancel.setOnClickListener(buttonCancelListener);
    }

    public ArrayList<ListInfoAttendanceCheck> getItemsFromDB() {

        ArrayList<ListInfoAttendanceCheck> list = new ArrayList<ListInfoAttendanceCheck>();
        ListInfoAttendanceCheck item;
        String[] names = {"김소혜", "최유정", "전소미", "주결경", "임나영", "최유정", "유연정", "정채연", "강미나"};

        for (String s : names) {
            item = new ListInfoAttendanceCheck();
            item.setName(s);
            list.add(item);
        }

        return list;
    }

}

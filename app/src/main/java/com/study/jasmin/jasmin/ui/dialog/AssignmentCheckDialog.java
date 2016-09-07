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
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentCheckList;

import java.util.ArrayList;

public class AssignmentCheckDialog extends Dialog {
    private final String TAG = "AssignmentCheckDialog";
    private View.OnClickListener buttonOkListener;
    private View.OnClickListener buttonCancelListener;
    private Button btnOk;
    private Button btnCancel;
    private TextView tvTitle;
    private TextView tvDate;
    private TextView tvContent;
    private ListView list;
    private String title;
    private ArrayList<Assignment> attendanceList;

    public AssignmentCheckDialog(Context context, String title, ArrayList<Assignment> attendanceList) {
        super(context);
        this.title = title;
        this.attendanceList = attendanceList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_assignment_check);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);        // 다이알로그 바깥영역 터치시, 다이알로그 닫히지 않기
        this.setCancelable(true);                  // 백키로 다이알로그 닫기

        findViews();
        initViews();
    }

    public void findViews() {
        tvDate     = (TextView) findViewById(R.id.tv_end_date);
        tvTitle    = (TextView) findViewById(R.id.tv_title);
        tvContent  = (TextView) findViewById(R.id.tv_content);
        list        = (ListView)findViewById(R.id.lv_assignment_check);
        btnOk       = (Button) findViewById(R.id.btn_check_ok);
        btnCancel  = (Button) findViewById(R.id.btn_check_cancel);
    }

    public void initViews() {
        tvDate.setText("마감일 : "+attendanceList.get(0).getHomework_end_date());
        tvTitle.setText("제목 : "+attendanceList.get(0).getHomework_title());
        tvContent.setText("내용 : "+attendanceList.get(0).getHomework_content());
        btnOk.setOnClickListener(buttonOkListener);
        btnCancel.setOnClickListener(buttonCancelListener);
        AdaptInfoAssignmentCheckList adapter = new AdaptInfoAssignmentCheckList(this.getContext(), R.layout.list_assignment_check_info, attendanceList);
        list.setAdapter(adapter);
    }

    public String getUpdateQuery(){
        String strQuery = "";

        for(int i=0; i<attendanceList.size(); i++){
            Assignment assignment = (Assignment)list.getItemAtPosition(i);
            //strQuery =  strQuery + "{\"homework_state\":" + assignment.getHomework_state() +
            strQuery =  strQuery + "{\"homework_state\":" + "제출" +
                                    ",\"user_no\":" + Integer.toString(assignment.getUser_no()) +
                                    ",\"homework_no\":" + Integer.toString(assignment.getHomework_no()) +
                                    "},";
        }
        strQuery = (strQuery.length()>0)? "[" + strQuery.substring(0, strQuery.length() - 1) + "]":"";

        return strQuery;
    }

    public void closeDialog(){
        cancel();
        dismiss();
    }

    public void setOkOnClickListener(View.OnClickListener listener) {
        buttonOkListener = listener;
    }

    public void setCancelOnClickListener(View.OnClickListener listener) {
        buttonCancelListener = listener;
    }
}

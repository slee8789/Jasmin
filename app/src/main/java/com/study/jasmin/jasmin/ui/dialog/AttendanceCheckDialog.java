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
import android.widget.RadioGroup;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.entity.Member;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceCheckList;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendanceCheck;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by swan on 2016-08-03.
 */
public class AttendanceCheckDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{

    private final String TAG = "AttendanceCheckDialog";
    private View.OnClickListener buttonOkListener;
    private Button btnOk;
    private ListView listView;
    private AdaptInfoAttendanceCheckList adapter;
    private ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
    private AdaptInfoAttendanceCheckList.RadioGroupClickListener rgListener;
    private TextView tvDate;

    public AttendanceCheckDialog(Context context, ArrayList<Attendance> attendanceList) {
        super(context);
        this.attendanceList = attendanceList;
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

    public void findViews() {
        listView = (ListView) findViewById(R.id.lv_attendance_check);
        btnOk = (Button) findViewById(R.id.btn_ok);
        tvDate = (TextView)findViewById(R.id.tv_date);
    }

    public void initViews() {
        setAttendanceList();
        adapter = new AdaptInfoAttendanceCheckList(this.getContext(), R.layout.list_attendance_check_info, attendanceList,rgListener);
        listView.setAdapter(adapter);

        btnOk.setOnClickListener(this);
        tvDate.setText(attendanceList.get(0).getAttendance_date());
    }

    public void setAttendanceList(){
        if(attendanceList == null){
            JasminPreference pref = JasminPreference.getInstance(getContext());
            ArrayList<Object> memberList = pref.getListValue("memberList");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
            Date currentTime = new Date ( );
            String mTime = df.format ( currentTime );
            attendanceList= new ArrayList<Attendance>();
           // attendanceList.add(new Attendance(0,1,1,"2015-20-10",1));
            for(int i=0; i<memberList.size(); i++) {
                Member mem = (Member)memberList.get(i);
                Attendance tempAtt = new Attendance(0,mem.getUser_no(), mem.getStudy_no(), mTime,1);
                attendanceList.add(tempAtt);
            }
        }
        return ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:
                this.dismiss();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Log.d(TAG, "다이얼로그 onCheckedChanged()");
    }
}

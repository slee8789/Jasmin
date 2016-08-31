package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.database.DataSetObserver;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

public class AdaptInfoAttendanceCheckList extends ArrayAdapter<Attendance> implements RadioGroup.OnCheckedChangeListener {
    private int resourceId;
    private final String TAG = "AdaptAttendCheckList";

    private TextView tvName;
    private RadioGroup  radioGroup;
    private RadioButton option1;//출석
    private RadioButton option2;//지각
    private RadioButton option3;//결석
    private Spinner spLateTime;
    private RadioClickListener radioClickListener;

    public interface  RadioClickListener{
        void onRadioChange(RadioGroup group, int checkedId, int position);
    }

    public AdaptInfoAttendanceCheckList(Context context, int resource, ArrayList<Attendance> list,RadioClickListener listener) {
        super(context, resource, list);
        this.resourceId = resource;
        this.radioClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listviewAssignment*/, parent, false);
        }

        findViews(convertView);
        initViews(position);

       return convertView;
    }

    void findViews(View rootView){
        tvName = (TextView) rootView.findViewById(R.id.tv_attendance_check_name);
        radioGroup = (RadioGroup)rootView.findViewById(R.id.rg_status);
        option1 = (RadioButton)rootView.findViewById(R.id.rb_option1);
        option2 = (RadioButton)rootView.findViewById(R.id.rb_option2);
        option3 = (RadioButton)rootView.findViewById(R.id.rb_option3);
        spLateTime = (Spinner)rootView.findViewById(R.id.sp_late_time);
    }

    //e0826_1
    void initViews(int position) {
        Attendance list = getItem(position);
        if (list == null) {
            Log.d(TAG, "AttendanceList is empty.");
        } else {
            tvName.setText(list.getUser_name());
            spLateTime.setVisibility(View.INVISIBLE);
            spLateTime.setTag(position);
            radioGroup.setClickable(true);
            radioGroup.setTag(position);
            Log.d(TAG, "spinnerID"+ Integer.toString(position)+Integer.toString(spLateTime.getId()));
            setRadioGroup(list.getAttendance_state());
            radioGroup.setOnCheckedChangeListener(this);
        }
    }

    /*// button1이 눌려졌을 때 실행되는 onClick함수.
    public void onClick(View v) {
        // ListBtnClickListener(SettingAssignmentActivity)의 onListBtnClick() 함수 호출.
        if (this.radioClickListener != null) {
            this.radioClickListener.onRadioClick((int)v.getTag()); ;
        }
    }
    */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        //RadioButton selOption = (RadioButton)findViewById(checkedId);
        //Spinner sp = (Spinner)findViewById((int)selOption.getTag());
       // Log.d(TAG, ">>>>>>>>>>>>>>>>>> SpinnerID"+Integer.toString(position)+" : "+Integer.toString(sp.getId()));
        switch (checkedId) {
            case R.id.rb_option1:
                getItem((int)group.getTag()).setAttendanceStateNew("출석");
                getItem((int)group.getTag()).setPenaltyMoney(0);

                //Log.d(TAG, ">>>>>>>>>>>>>>>>>> 1");
                //((Attendance)listView.getItemAtPosition(position)).setAttendanceStateNew("출석");
                break;
            case  R.id.rb_option2:
                getItem((int)group.getTag()).setAttendanceStateNew("지각");
                getItem((int)group.getTag()).setPenaltyMoney(1000);
                //Log.d(TAG, ">>>>>>>>>>>>>>>>>> 2");
                //((Attendance)listView.getItemAtPosition(position)).setAttendanceStateNew("지각");
               // this.spLateTime.setVisibility(View.VISIBLE);
                break;
            case  R.id.rb_option3:
                getItem((int)group.getTag()).setAttendanceStateNew("결석");
                getItem((int)group.getTag()).setPenaltyMoney(1500);
                //Log.d(TAG, ">>>>>>>>>>>>>>>>>> 3");
               // ((Attendance)listView.getItemAtPosition(position)).setAttendanceStateNew("결석");
                //this.spLateTime.setVisibility(View.INVISIBLE);
                break;
        }



        /*
        if (this.radioClickListener != null) {
            this.radioClickListener.onRadioChange(group, checkedId, (int)group.getTag());
        }
        */
    }

    void setRadioGroup(String status){
        switch (status) {
            case "출석":
                radioGroup.check(option1.getId());
                spLateTime.setVisibility(View.INVISIBLE);
                break;
            case "지각":
                radioGroup.check(option2.getId());
                spLateTime.setVisibility(View.VISIBLE);
                break;
            case "결석":
                radioGroup.check(option3.getId());
                spLateTime.setVisibility(View.INVISIBLE);
                break;
        }
    }

}

package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
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
            radioGroup.isClickable();
            radioGroup.setTag(position);
            option1.isChecked();
            option1.setTag(spLateTime.getId());
            option2.setTag(spLateTime.getId());
            option3.setTag(spLateTime.getId());
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
        if (this.radioClickListener != null) {
            this.radioClickListener.onRadioChange(group, checkedId, (int)group.getTag());
        }
    }

    void setRadioGroup(String status){
        switch (status) {
            case "출석":
                option1.isChecked();
                spLateTime.setVisibility(View.INVISIBLE);
                break;
            case "지각":
                option2.isChecked();
                spLateTime.setVisibility(View.VISIBLE);
                break;
            case "결석":
                option3.isChecked();
                spLateTime.setVisibility(View.INVISIBLE);
                break;
        }
    }

}

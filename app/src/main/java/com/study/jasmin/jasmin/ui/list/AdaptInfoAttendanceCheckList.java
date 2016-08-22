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

public class AdaptInfoAttendanceCheckList extends ArrayAdapter<Attendance> implements RadioGroup.OnCheckedChangeListener{
    private int resourceId;
    private int pos;
    private final String TAG = "AdaptAttendCheckList";

    private TextView tvName;
    private RadioGroup  radioGroup;
    private RadioButton option1;//출석
    private RadioButton option2;//지각
    private RadioButton option3;//결석
    private Spinner spLateTime;
    private RadioGroupClickListener rgClickListener;
    View context1;
    public AdaptInfoAttendanceCheckList(Context context, int resource, ArrayList<Attendance> list,RadioGroupClickListener listener) {
        super(context, resource, list);
        this.resourceId = resource;
        this.rgClickListener = listener;
    }

    public interface  RadioGroupClickListener{
        void onListItemClick(int position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pos = position;
        final Context context = parent.getContext();
        context1 = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listviewAssignment*/, parent, false);
        }

        findViews(convertView);
        initViews(position);

       return convertView;
    }

    void findViews(View rootView)
    {
        tvName = (TextView) rootView.findViewById(R.id.tv_attendance_check_name);
        radioGroup = (RadioGroup)rootView.findViewById(R.id.rg_status);
        option1 = (RadioButton)rootView.findViewById(R.id.rb_option1);
        option2 = (RadioButton)rootView.findViewById(R.id.rb_option2);
        option3 = (RadioButton)rootView.findViewById(R.id.rb_option3);
        spLateTime = (Spinner)rootView.findViewById(R.id.sp_late_time);
    }

    void initViews(int position){
        Attendance list = (Attendance)getItem(position);
        if(list ==null){
            Log.d(TAG,"AttendanceList is empty.");
        }
        tvName.setText(list.getUser_name());
        spLateTime.setVisibility(View.INVISIBLE);
        spLateTime.setTag(position);
        radioGroup.isClickable();
        setRadioGroup(list.getAttendance_state());
        radioGroup.setTag(position);
        radioGroup.setOnCheckedChangeListener(this);


    }

    void setRadioGroup(int nStatus){
        switch (nStatus) {
            case 1:
                option1.isChecked();
                spLateTime.setVisibility(View.INVISIBLE);
                break;
            case 2:
                option2.isChecked();
                spLateTime.setVisibility(View.VISIBLE);
                break;
            case 3:
                option3.isChecked();
                spLateTime.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        group.check(checkedId);
        int position = (int)group.getTag();

        switch (checkedId) {
            case R.id.rb_option1:
                Log.d(TAG, ">>>>>>>>>>>>>>>>>opriotn1선택");
                //sp.setVisibility(View.INVISIBLE);
                break;
            case R.id.rb_option2:
                //sp.setVisibility(View.VISIBLE);
                Log.d(TAG, ">>>>>>>>>>>>>>>>>opriotn2선택");
                break;
            case R.id.rb_option3:
                //sp.setVisibility(View.INVISIBLE);
                Log.d(TAG, ">>>>>>>>>>>>>>>>>opriotn3선택");
                //spLateTime.setVisibility(View.INVISIBLE);
                break;
        }
    }
}

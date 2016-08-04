package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoAttendanceCheckList extends ArrayAdapter<ListInfoAttendanceCheck> implements RadioGroup.OnCheckedChangeListener{
    private int resourceId;
    private int pos;

    private TextView tvName;
    private RadioGroup  radioGroup;
    private RadioButton option1;//출석
    private RadioButton option2;//지각
    private RadioButton option3;//결석
    private Spinner spLateTime;

    //라디오 버튼 클릭 이벤트를 위한 Listener 인터페이스 정의
    public interface  RgBtnClickListener{
        void onCheckedChanged(int position);
    }


    private RadioGroupClickListener rgClickListener;
    private Boolean bNewCheck; // True:추가  False:수정



    public AdaptInfoAttendanceCheckList(Context context, int resource, ArrayList<ListInfoAttendanceCheck> objects, Boolean bNewCheck, RadioGroupClickListener radioGroupClickListener) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.bNewCheck = bNewCheck;
        this.rgClickListener = radioGroupClickListener;
    }

    public interface  RadioGroupClickListener{
        void onBtnClick(int position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        pos = position;
        final Context context = parent.getContext();

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
        ListInfoAttendanceCheck list = (ListInfoAttendanceCheck)getItem(position);
        tvName.setText(list.getName());
        spLateTime.setVisibility(View.INVISIBLE);

        setRadioGroup(list.getStatus());
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
            default:
                option1.isChecked();
                spLateTime.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int nStatus = 0;
        if (this.rgClickListener != null) {

                this.rgClickListener.onBtnClick((int)group.getTag()); ;
            switch (checkedId) {
                case R.id.rb_option1:
                    nStatus = 1;
                    break;
                case R.id.rb_option2:
                    nStatus = 2;
                    break;
                case R.id.rb_option3:
                    nStatus = 3;
                    break;
            }
            Toast.makeText(this.getContext(), "nStatus : "+Integer.toString(nStatus) , Toast.LENGTH_SHORT).show();
            setRadioGroup(nStatus);

            }
    }
}

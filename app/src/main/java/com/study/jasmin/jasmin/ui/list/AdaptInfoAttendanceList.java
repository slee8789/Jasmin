package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.entity.AttendanceTitle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdaptInfoAttendanceList extends ArrayAdapter<AttendanceTitle> implements View.OnClickListener{
    private int resourceId;
    private TextView tvDate;
    private TextView tvStatus;
    private ImageView ivDelete;
    private ArrayList<AttendanceTitle> titleList;
    private ListImgClickListener listImageClickListener;

    public interface  ListImgClickListener{
        void onListBtnClick(int position);
    }

    public AdaptInfoAttendanceList(Context context, int resource, ArrayList<AttendanceTitle> objects,ListImgClickListener listImageClickListener) {
        super(context, resource, objects);
        this.titleList = objects;
        this.resourceId = resource;
        this.listImageClickListener = listImageClickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId, parent, false);
        }

        findView(convertView);
        initView(position);

        return convertView;
    }

/*
    // button1이 눌려졌을 때 실행되는 onClick함수.
    public void onClick(View v) {
        // ListBtnClickListener(SettingAssignmentActivity)의 onListBtnClick() 함수 호출.
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)v.getTag()) ;
            Toast.makeText(this.getContext(), "어뎁터 아이템 클릭 테스트", Toast.LENGTH_SHORT).show();
        }
    }
*/
    public void findView(View rootView) {
        tvDate = (TextView) rootView.findViewById(R.id.tv_date);
        tvStatus = (TextView) rootView.findViewById(R.id.tv_status);
        ivDelete = (ImageView) rootView.findViewById(R.id.iv_delete);
    }

    public void initView(int position){
        AttendanceTitle list = (AttendanceTitle)getItem(position);
        tvDate.setText(list.getDate());
        tvStatus.setText(list.getStatus());
        ivDelete.setTag(position);
        ivDelete.setImageDrawable(ContextCompat.getDrawable(this.getContext(), R.drawable.ic_delete_black_24dp_1));
        ivDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("aa",">>>>>>>>>>>>>>>>>test");
        if (this.listImageClickListener != null) {
            Log.d("aa",">>>>>>>>>>test");
            this.listImageClickListener.onListBtnClick((int)v.getTag()) ;
        }
    }
}

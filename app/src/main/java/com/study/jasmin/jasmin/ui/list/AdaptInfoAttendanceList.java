package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupManageAttendanceActivity;
import com.study.jasmin.jasmin.ui.item.ListViewAssignment;

import java.util.ArrayList;

public class AdaptInfoAttendanceList extends ArrayAdapter<ListInfoAttendance> implements View.OnClickListener{

    private ListBtnClickListener listBtnClickListener;
    private int resourceId;

    private TextView tvDate;
    private TextView tvStatus;
    private Button   btnEdit;

    public interface  ListBtnClickListener{
        void onListBtnClick(int position);
    }

    public AdaptInfoAttendanceList(Context context, int resource, ArrayList<ListInfoAttendance> objects, ListBtnClickListener clickListener) {
        super(context, resource, objects);

        this.resourceId = resource;
        this.listBtnClickListener = clickListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position ;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourceId/*R.layout.listviewAssignment*/, parent, false);
        }

        findView(convertView);
        initView(position);

        return convertView;
    }

    // button1이 눌려졌을 때 실행되는 onClick함수.
    public void onClick(View v) {
        // ListBtnClickListener(SettingAssignmentActivity)의 onListBtnClick() 함수 호출.
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)v.getTag()) ;
            //Toast.makeText(this.getContext(), "어뎁터 아이템 클릭 테스트", Toast.LENGTH_SHORT).show();
        }
    }

    public void findView(View rootView) {
        tvDate = (TextView) rootView.findViewById(R.id.tv_date);
        tvStatus = (TextView) rootView.findViewById(R.id.tv_status);
        btnEdit = (Button) rootView.findViewById(R.id.btn_edit);
    }

    public void initView(int position){
        ListInfoAttendance list = (ListInfoAttendance)getItem(position);
        tvDate.setText(list.getDate());
        tvStatus.setText(list.getStatus());
        btnEdit.setTag(position);
        btnEdit.setOnClickListener(this);
    }
}

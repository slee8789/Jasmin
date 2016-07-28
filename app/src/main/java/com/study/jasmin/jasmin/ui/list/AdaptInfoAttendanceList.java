package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoAttendanceList extends ArrayAdapter<ListInfoAttendance> {
    private ArrayList<ListInfoAttendance> arraySelectInfo;
    private Context context;

    public interface onButtonClickListener {
        void onAddBtnClick(ListInfoAttendance selectInfo);
        // void onSetNameText(SelectInfo selectInfo);
    }

    public AdaptInfoAttendanceList(Context context, int resource, ArrayList<ListInfoAttendance> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoAttendanceList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    private onButtonClickListener adptCallback = null;


    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ArrayList<ListInfoAttendance> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoAttendance> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_attendance_info, null);
        }
        final ListInfoAttendance listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView date = (TextView) view.findViewById(R.id.attendance_date);
            TextView status = (TextView) view.findViewById(R.id.attendance_status);
            date.setText(listInfo.getDate());
            status.setText(listInfo.getStatus());
        }
        return view;
    }

}
package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoAttendanceCheckList extends ArrayAdapter<ListInfoAttendanceCheck> {
    private ArrayList<ListInfoAttendanceCheck> arraySelectInfo;
    private Context context;

    public interface onButtonClickListener {
        void onAddBtnClick(ListInfoAttendanceCheck selectInfo);
        // void onSetNameText(SelectInfo selectInfo);
    }

    public AdaptInfoAttendanceCheckList(Context context, int resource, ArrayList<ListInfoAttendanceCheck> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoAttendanceCheckList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    private onButtonClickListener adptCallback = null;


    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ArrayList<ListInfoAttendanceCheck> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoAttendanceCheck> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_attendance_check_info, null);
        }
        final ListInfoAttendanceCheck listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView name = (TextView) view.findViewById(R.id.attendance_check_name);
            name.setText(listInfo.getName());
        }
        return view;
    }

}
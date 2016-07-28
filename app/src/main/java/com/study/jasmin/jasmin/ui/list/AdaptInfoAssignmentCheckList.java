package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoAssignmentCheckList extends ArrayAdapter<ListInfoAssignmentCheck> {
    private ArrayList<ListInfoAssignmentCheck> arraySelectInfo;
    private Context context;

    public interface onButtonClickListener {
        void onAddBtnClick(ListInfoAssignment selectInfo);
        // void onSetNameText(SelectInfo selectInfo);
    }

    public AdaptInfoAssignmentCheckList(Context context, int resource, ArrayList<ListInfoAssignmentCheck> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoAssignmentCheckList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    private onButtonClickListener adptCallback = null;


    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ArrayList<ListInfoAssignmentCheck> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoAssignmentCheck> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_assignment_check_info, null);
        }
        final ListInfoAssignmentCheck listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView name = (TextView) view.findViewById(R.id.assignment_check_name);
            Switch status = (Switch) view.findViewById(R.id.assignment_switch);
            name.setText(listInfo.getName());
        }
        return view;
    }

}
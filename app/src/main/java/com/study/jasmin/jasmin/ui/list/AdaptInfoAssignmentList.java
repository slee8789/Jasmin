package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;
import com.study.jasmin.jasmin.entity.AssignmentTitle;
import com.study.jasmin.jasmin.ui.dialog.AssignmentCheckDialog;

import java.util.ArrayList;

public class AdaptInfoAssignmentList extends ArrayAdapter<AssignmentTitle>  {
    private Context context;
    private TextView date,title,status;

    public AdaptInfoAssignmentList(Context context, int resource, ArrayList<AssignmentTitle> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_assignment_info, null);
        }

        findViews(view);
        initViews(position);

        return view;
    }

    public void findViews(View view){
        date    = (TextView) view.findViewById(R.id.assignment_date);
        title   = (TextView) view.findViewById(R.id.assignment_title);
        status  = (TextView) view.findViewById(R.id.assignment_count);
    }

    public void initViews(int position){
        AssignmentTitle listInfo = getItem(position);
        if (listInfo != null) {
            date.setText(listInfo.getDate());
            title.setText(listInfo.getTitle());
            status.setText(Integer.toString(listInfo.getSubmit_yes())+"/"+Integer.toString(listInfo.getSubmit_no()));
        }
    }

}
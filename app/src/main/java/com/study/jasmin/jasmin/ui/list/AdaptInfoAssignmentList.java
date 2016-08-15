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
import com.study.jasmin.jasmin.ui.dialog.AssignmentCheckDialog;

import java.util.ArrayList;

public class AdaptInfoAssignmentList extends ArrayAdapter<Assignment>  {
    private ArrayList<Assignment> arraySelectInfo;
    private Context context;

    public AdaptInfoAssignmentList(Context context, int resource, ArrayList<Assignment> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_assignment_info, null);
        }
        final Assignment listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView title = (TextView) view.findViewById(R.id.assignment_title);
            title.setText(listInfo.getHomework_title());
        }

        Button btnDoCheck = (Button)view.findViewById(R.id.assignment_do_check);
        btnDoCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AssignmentCheckDialog dialog = new AssignmentCheckDialog(v.getContext(),listInfo.getHomework_title());
                dialog.show();

            }
        });
        return view;
    }

}
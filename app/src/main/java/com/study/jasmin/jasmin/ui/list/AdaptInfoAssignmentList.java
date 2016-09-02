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
    private ArrayList<AssignmentTitle> assignmentList;
    private Context context;
    TextView title;
    Button   btnAdd;

    public AdaptInfoAssignmentList(Context context, int resource, ArrayList<AssignmentTitle> objects) {
        super(context, resource, objects);
        this.assignmentList = objects;
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
        title = (TextView) view.findViewById(R.id.assignment_title);
        btnAdd = (Button)view.findViewById(R.id.assignment_do_check);
    }

    public void initViews(int position){
        AssignmentTitle listInfo = getItem(position);

        if (listInfo != null) {
            title.setText(listInfo.getHomework_title());
        }
           /*
        btnDoCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AssignmentCheckDialog dialog = new AssignmentCheckDialog(v.getContext(),listInfo.getHomework_title());
                dialog.show();

            }
        });
        */
    }

}
package com.study.jasmin.jasmin.ui.list;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;

public class AdaptInfoAssignmentList extends ArrayAdapter<ListInfoAssignment> {
    private ArrayList<ListInfoAssignment> arraySelectInfo;
    private Context context;

    public interface onButtonClickListener {
        void onAddBtnClick(ListInfoAssignment selectInfo);
        // void onSetNameText(SelectInfo selectInfo);
    }

    public AdaptInfoAssignmentList(Context context, int resource, ArrayList<ListInfoAssignment> objects) {
        super(context, resource, objects);
        this.arraySelectInfo = objects;
        this.context = context;
    }

    public AdaptInfoAssignmentList(Context context, int resource) {
        super(context, resource);
        // this.arraySelectInfo =objects;
        this.context = context;
    }

    private onButtonClickListener adptCallback = null;


    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ArrayList<ListInfoAssignment> getArraySelectInfo() {
        return arraySelectInfo;
    }

    public void setArraySelectInfo(ArrayList<ListInfoAssignment> arraySelectInfo) {
        this.arraySelectInfo = arraySelectInfo;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_assignment_info, null);
        }
        final ListInfoAssignment listInfo = arraySelectInfo.get(position);

        if (listInfo != null) {
            TextView title = (TextView) view.findViewById(R.id.assignment_title);
            TextView status = (TextView) view.findViewById(R.id.assignment_status);
            title.setText(listInfo.getTitle());
            status.setText(listInfo.getStatus());
        }
        return view;
    }

}
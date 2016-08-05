package com.study.jasmin.jasmin.ui.list;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.dialog.AssignmentCheckDialog;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmAdapter;

import java.util.ArrayList;

public class AdaptInfoAssignmentList extends ArrayAdapter<ListInfoAssignment> implements View.OnClickListener {
    private ArrayList<ListInfoAssignment> arraySelectInfo;
    private Context context;
    private onButtonClickListener listButtonClickListener;

    public interface onButtonClickListener {
        //void onAddBtnClick(ListInfoAssignment selectInfo);
        void onListBtnClick(int  positon);
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

        ImageView iv = (ImageView)view.findViewById(R.id.assignment_check);
        iv.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        AssignmentCheckDialog dialog = new AssignmentCheckDialog(v.getContext());
        dialog.show();
        if (this.listButtonClickListener != null) {
            this.listButtonClickListener.onListBtnClick((int)v.getTag()) ;

        }
    }
}
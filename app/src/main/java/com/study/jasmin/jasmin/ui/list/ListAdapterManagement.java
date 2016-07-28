package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListAdapterManagement extends BaseExpandableListAdapter implements View.OnClickListener{

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, ArrayList<ArrayList<String>>> expandableListDetail;
    private onButtonClickListener adptCallback = null;

    public interface onButtonClickListener {
        void onBtnClick(View v);
    }

    public void setOnButtonClickListener(onButtonClickListener callback) {
        adptCallback = callback;
    }

    public ListAdapterManagement(Context context, List<String> expandableListTitle, HashMap<String, ArrayList<ArrayList<String>>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        Log.d("ttest",listPosition+", "+expandedListPosition+", "+isLastChild+", "+convertView+", "+parent);
        final ArrayList<String> expandedListItem = (ArrayList<String>)getChild(listPosition, expandedListPosition);
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        switch (listPosition) {
            case 0:
                convertView = layoutInflater.inflate(R.layout.list_attendance_info, null);
                TextView attendaceDate = (TextView) convertView.findViewById(R.id.attendance_date);
                TextView attendaceStatus = (TextView) convertView.findViewById(R.id.attendance_status);
                Button attendanceCheck = (Button) convertView.findViewById(R.id.attendance_do_check);

                attendaceDate.setText(expandedListItem.get(0));
                attendaceStatus.setText(expandedListItem.get(1));
                attendanceCheck.setVisibility(View.VISIBLE); //테스트
                attendanceCheck.setOnClickListener(this);
                break;

            case 1:
                convertView = layoutInflater.inflate(R.layout.list_assignment_info, null);
                TextView assignmentDate = (TextView) convertView.findViewById(R.id.assignment_title);
                TextView assignmentStatus = (TextView) convertView.findViewById(R.id.assignment_status);
                Button assignmentCheck = (Button) convertView.findViewById(R.id.assignment_do_check);

                assignmentDate.setText(expandedListItem.get(0));
                assignmentStatus.setText(expandedListItem.get(1));
                assignmentCheck.setVisibility(View.VISIBLE); //테스트
                assignmentCheck.setOnClickListener(this);
                break;

            case 2:
                convertView = layoutInflater.inflate(R.layout.list_receivables_info, null);
                TextView receivablesName = (TextView) convertView.findViewById(R.id.receivables_name);
                TextView receivablesDate = (TextView) convertView.findViewById(R.id.receivables_date);
                TextView receivablesReason = (TextView) convertView.findViewById(R.id.receivables_reason);
                TextView receivablesMoney = (TextView) convertView.findViewById(R.id.receivables_money);
                ImageView receivablesCancel = (ImageView) convertView.findViewById(R.id.receivables_cancel);

                receivablesName.setText(expandedListItem.get(0));
                receivablesDate.setText(expandedListItem.get(1));
                receivablesReason.setText(expandedListItem.get(2));
                receivablesMoney.setText(expandedListItem.get(3));
                receivablesCancel.setOnClickListener(this);
                break;

            case 3:
                convertView = layoutInflater.inflate(R.layout.list_moneybook_info, null);
                TextView moneybookName = (TextView) convertView.findViewById(R.id.moneybook_name);
                TextView moneybookDate = (TextView) convertView.findViewById(R.id.moneybook_date);
                TextView moneybookReason = (TextView) convertView.findViewById(R.id.moneybook_reason);
                TextView moneybookMoney = (TextView) convertView.findViewById(R.id.moneybook_money);
                ImageView moneybookWrite = (ImageView) convertView.findViewById(R.id.moneybook_write); //임시 다이얼로그테스트 테스트
                moneybookName.setText(expandedListItem.get(0));
                moneybookDate.setText(expandedListItem.get(1));
                moneybookReason.setText(expandedListItem.get(2));
                moneybookMoney.setText(expandedListItem.get(3));
                moneybookWrite.setOnClickListener(this);//임시 다이얼로그테스트
                break;
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_attachment, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
                adptCallback.onBtnClick(v);
        }
}
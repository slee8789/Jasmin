package com.study.jasmin.jasmin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupManageAssignmentActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageAttendanceActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageMoneybookActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageReceivablesActivity;



public class GroupManageFragment extends Fragment implements View.OnClickListener {

    private LinearLayout btnAttendance;
    private LinearLayout btnAssignment;
    private LinearLayout btnReceivables;
    private LinearLayout btnMoneybook;


    public GroupManageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_manage, container, false);
        findViews(rootView);
        initViews();

        return rootView;
    }

    private void findViews(View rootView) {
        btnAttendance = (LinearLayout)rootView.findViewById(R.id.btn_attendance);
        btnAssignment = (LinearLayout)rootView.findViewById(R.id.btn_assignment);
        btnReceivables = (LinearLayout)rootView.findViewById(R.id.btn_receivables);
        btnMoneybook = (LinearLayout)rootView.findViewById(R.id.btn_moneybook);

    }

    private void initViews() {
        btnAttendance.setOnClickListener(this);
        btnAssignment.setOnClickListener(this);
        btnReceivables.setOnClickListener(this);
        btnMoneybook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_attendance:
                startActivity(new Intent(getActivity(), GroupManageAttendanceActivity.class));
                break;

            case R.id.btn_assignment:
                startActivity(new Intent(getActivity(), GroupManageAssignmentActivity.class));
                break;

            case R.id.btn_receivables:
                startActivity(new Intent(getActivity(), GroupManageReceivablesActivity.class));
                break;

            case R.id.btn_moneybook:
                startActivity(new Intent(getActivity(), GroupManageMoneybookActivity.class));
                break;
        }
    }
}

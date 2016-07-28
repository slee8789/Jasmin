package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.AssignmentCheckActivity;
import com.study.jasmin.jasmin.ui.activity.AttendanceCheckActivity;
import com.study.jasmin.jasmin.ui.dialog.AlertDialog;
import com.study.jasmin.jasmin.ui.dialog.FindPwDialog;
import com.study.jasmin.jasmin.ui.dialog.MoneybookDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAssignmentList;
import com.study.jasmin.jasmin.ui.list.AdaptInfoAttendanceList;
import com.study.jasmin.jasmin.ui.list.AdaptInfoReceivablesList;
import com.study.jasmin.jasmin.ui.list.ListInfoAssignment;
import com.study.jasmin.jasmin.ui.list.ListInfoAttendance;
import com.study.jasmin.jasmin.ui.list.DataPumpManagement;
import com.study.jasmin.jasmin.ui.list.ListAdapterManagement;
import com.study.jasmin.jasmin.ui.list.ListInfoReceivables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupManageFragment extends Fragment implements ListAdapterManagement.onButtonClickListener {
    public static final String TAG = "GroupManageFragment";
    private ExpandableListView managementList;
    private ListAdapterManagement managementListAdapter;
    private List<String> managementListTitle;
    HashMap<String, ArrayList<ArrayList<String>>> managementListDetail;

    private ArrayList<ListInfoAttendance> arrayAttendanceListInfo = new ArrayList<ListInfoAttendance>();
    private AdaptInfoAttendanceList adaptAttendanceListInfo;
    private ArrayList<ListInfoAssignment> arrayAssignmentListInfo = new ArrayList<ListInfoAssignment>();
    private AdaptInfoAssignmentList adaptAssignmentListInfo;
    private ArrayList<ListInfoReceivables> arrayReceivablesListInfo = new ArrayList<ListInfoReceivables>();
    private AdaptInfoReceivablesList adaptReceivablesListInfo;


    public GroupManageFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_manage, container, false);
        findViews(rootView);
        initViews();

        managementListDetail = DataPumpManagement.getData();
        adaptAttendanceListInfo = new AdaptInfoAttendanceList(getContext(), R.layout.list_attendance_info, arrayAttendanceListInfo);
        adaptAssignmentListInfo = new AdaptInfoAssignmentList(getContext(), R.layout.list_assignment_info, arrayAssignmentListInfo);
        adaptReceivablesListInfo = new AdaptInfoReceivablesList(getContext(), R.layout.list_receivables_info, arrayReceivablesListInfo);
        managementListTitle = new ArrayList<String>(managementListDetail.keySet());
        managementListAdapter = new ListAdapterManagement(getContext(), managementListTitle, managementListDetail);
        managementListAdapter.setOnButtonClickListener(this);
        managementList.setAdapter(managementListAdapter);
        managementList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getContext(),
                        managementListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        managementList.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getContext(),
                        managementListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        managementList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(), managementListTitle.get(groupPosition)+ " -> "+ managementListDetail.get(managementListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return rootView;
    }

    private void findViews(View rootView) {
        managementList = (ExpandableListView) rootView.findViewById(R.id.list_management);
    }

    private void initViews() {
//        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
    }

    @Override
    public void onBtnClick(View v) {
        switch(v.getId()) {

            case R.id.receivables_cancel:
                Log.d(TAG, "receivables_cancel clicked");
                startActivity(new Intent(getContext(),AlertDialog.class));
                break;

            case R.id.assignment_do_check:
                Log.d(TAG, "assignment_do_check clicked");
                startActivity(new Intent(getContext(), AssignmentCheckActivity.class));
                break;

            case R.id.attendance_do_check:
                Log.d(TAG, "attendance_do_check clicked");
                startActivity(new Intent(getContext(), AttendanceCheckActivity.class));
                break;

            case R.id.moneybook_write:
                Log.d(TAG, "moneybook_write clicked");
                startActivity(new Intent(getContext(), MoneybookDialog.class));
                break;

        }

    }

}

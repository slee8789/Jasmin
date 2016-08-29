package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupAttendanceInfoActivity;
import com.study.jasmin.jasmin.ui.activity.GroupInviteActivity;
import com.study.jasmin.jasmin.ui.activity.GroupMemberInfoActivity;
import com.study.jasmin.jasmin.ui.activity.GroupMoneybookInfoActivity;
import com.study.jasmin.jasmin.ui.activity.GroupPenaltyActivity;
import com.study.jasmin.jasmin.ui.activity.GroupStudyInfoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupInfoFragment extends Fragment implements View.OnClickListener{

    private LinearLayout btnStudyInfo;
    private LinearLayout btnMemberInfo;
    private LinearLayout btnMemberAdd;
    private LinearLayout btnPenalty;
    private LinearLayout btnAttendance;
    private LinearLayout btnMoneyBook;

    public GroupInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_group_info, container, false);
        findViews(rootView);
        initViews();
        return rootView;
    }


    private void findViews(View rootView) {
        btnStudyInfo = (LinearLayout)rootView.findViewById(R.id.btn_info_study);
        btnMemberInfo = (LinearLayout)rootView.findViewById(R.id.btn_info_member);
        btnMemberAdd = (LinearLayout)rootView.findViewById(R.id.btn_info_member_add);
        btnPenalty = (LinearLayout)rootView.findViewById(R.id.btn_info_penalty);
        btnAttendance = (LinearLayout)rootView.findViewById(R.id.btn_info_attendance);
        btnMoneyBook = (LinearLayout)rootView.findViewById(R.id.btn_info_moneybook);
    }

    private void initViews() {
        btnStudyInfo.setOnClickListener(this);
        btnMemberInfo.setOnClickListener(this);
        btnMemberAdd.setOnClickListener(this);
        btnPenalty.setOnClickListener(this);
        btnAttendance.setOnClickListener(this);
        btnMoneyBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_info_study:
                startActivity(new Intent(getActivity(),GroupStudyInfoActivity.class));
                break;

            case R.id.btn_info_member:
                startActivity(new Intent(getActivity(),GroupMemberInfoActivity.class));
                break;

            case R.id.btn_info_member_add:
                startActivity(new Intent(getActivity(),GroupInviteActivity.class));
                break;

            case R.id.btn_info_penalty:
                startActivity(new Intent(getActivity(),GroupPenaltyActivity.class));
                break;

            case R.id.btn_info_attendance:
                startActivity(new Intent(getActivity(),GroupAttendanceInfoActivity.class));
                break;

            case R.id.btn_info_moneybook:
                startActivity(new Intent(getActivity(),GroupMoneybookInfoActivity.class));
                break;

        }
    }

}


package com.study.jasmin.jasmin.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupSettingAlarmListActivity;
import com.study.jasmin.jasmin.ui.activity.SettingAssignmentActivity;
import com.study.jasmin.jasmin.ui.activity.SettingGroupInfoActivity;
import com.study.jasmin.jasmin.ui.activity.SettingGroupPenaltyActivity;
import com.study.jasmin.jasmin.ui.dialog.MemberListDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupSettingFragment extends Fragment implements View.OnClickListener{

    Button btnAlarm;
    Button btnHomework;
    Button btnGroupSetting;
    Button btnPenaltySetting;
    Button btnSubGrant;
    Button btnGrant;
    Button btnRemoveMember;
    Button btnEndStudy;
    Button btnSecedeGroup;
    MemberListDialog memberlistDialog;

    public GroupSettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_group_setting, container, false);

        setBtnListner(rootView);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_alarm:
                //Toast.makeText(getActivity(), "btn_alarm", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), GroupSettingAlarmListActivity.class));
                break;
            case R.id.btn_homework:
                startActivity(new Intent(getActivity(), SettingAssignmentActivity.class));
                break;
            case R.id.btn_group_setting:
                startActivity(new Intent(getActivity(), SettingGroupInfoActivity.class));
                break;
            case R.id.btn_penalty_setting:
                startActivity(new Intent(getActivity(), SettingGroupPenaltyActivity.class));
                break;
            case R.id.btn_subgrant:
                memberlistDialog = new MemberListDialog(getActivity(),"관리자 권한 공유",getMemberArray(R.id.btn_subgrant));
                memberlistDialog.show();
                break;
            case R.id.btn_grant:
                memberlistDialog = new MemberListDialog(getActivity(),"관리자 권한 위임",getMemberArray(R.id.btn_grant));
                memberlistDialog.show();
                break;
            case R.id.btn_remove_member:
                memberlistDialog = new MemberListDialog(getActivity(),"멤버 강제 탈퇴",getMemberArray(R.id.btn_remove_member));
                memberlistDialog.show();
                break;
            case R.id.btn_end_study:
                //swan "Need One Button Dialog"
                //startActivity(new Intent(this, SettingAssignmentAddActivity.class));
                break;
            case R.id.btn_secede_group:
                //swan : 원버튼 다이얼로그 필요
                //startActivity(new Intent(this, SettingAssignmentAddActivity.class));
                break;
            default:
                break;
        }
    }

    public void setBtnListner(View rootView){
        Button[] arrBtn = {btnAlarm, btnHomework, btnGroupSetting,
                btnPenaltySetting, btnSubGrant, btnGrant,
                btnRemoveMember, btnEndStudy, btnSecedeGroup};
        int[] arrBtnId = {R.id.btn_alarm, R.id.btn_homework, R.id.btn_group_setting,
                R.id.btn_penalty_setting, R.id.btn_subgrant, R.id.btn_grant,
                R.id.btn_remove_member, R.id.btn_end_study, R.id.btn_secede_group};

        for(int i=0; i<9; i++){
            arrBtn[i] = (Button)rootView.findViewById(arrBtnId[i]);
            arrBtn[i].setOnClickListener(this);
        }

        return;
    }

    // return ; 멤버 리스트를 String 배열로 반환
    // Custom Dialog 생성시 필요
    public String[] getMemberArray(int id){
        String[] array ={"전소미","주결경","임나영","강미나","김소혜","최유정"};
        return array;
    }

}




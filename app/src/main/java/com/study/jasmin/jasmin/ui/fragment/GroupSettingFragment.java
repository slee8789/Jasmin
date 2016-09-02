package com.study.jasmin.jasmin.ui.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Alarm;
import com.study.jasmin.jasmin.entity.Member;
import com.study.jasmin.jasmin.entity.Study;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupSettingAlarmListActivity;
import com.study.jasmin.jasmin.ui.activity.SettingAssignmentActivity;
import com.study.jasmin.jasmin.ui.activity.SettingGroupInfoActivity;
import com.study.jasmin.jasmin.ui.activity.SettingGroupPenaltyActivity;
import com.study.jasmin.jasmin.ui.dialog.MemberListDialog;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.ui.dialog.TwoButtonDialog;
import com.study.jasmin.jasmin.ui.item.ListViewAlarmAdapter;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupSettingFragment extends Fragment implements View.OnClickListener, Callback {
    public static final String TAG = "GroupSettingFragment";
    Button btnAlarm,
            btnGroupSetting,
            btnPenaltySetting,
            btnSubGrant,
            btnGrant,
            btnSubGrantDelete,
            btnRemoveMember,
            btnEndStudy,
            btnSecedeGroup;
    MemberListDialog memberlistDialog;
    TwoButtonDialog twoButtonDialog;
    private ProgressDialog connectProgress;
    public JasminPreference mPref;
    private int mClickId;
    private int dialogType;  //1 = btn_end_study , 2 = btn_secede_group

    public GroupSettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_group_setting, container, false);

        setBtnListener(rootView);
        mPref = JasminPreference.getInstance(getActivity());
        connectProgress = new ProgressDialog(getContext());
        return rootView;
    }

    @Override
    public void onClick(View v) {
        mClickId=v.getId();
        Call<JsonObject> call =null;
        RestClient.RestService service = RestClient.getClient();

        switch (mClickId) {
            case R.id.btn_alarm:
                connectProgress.show();
                call = service.goAlarmList(mPref.getSelStudyNo());
                break;
            case R.id.btn_group_setting:
                startActivity(new Intent(getActivity(), SettingGroupInfoActivity.class));
                break;
            case R.id.btn_penalty_setting:
                startActivity(new Intent(getActivity(), SettingGroupPenaltyActivity.class));
                break;
            case R.id.btn_grade_share:
                memberlistDialog = new MemberListDialog(getActivity(),"관리자 권한 공유",1);
                memberlistDialog.show();
                break;
            case R.id.btn_grade_share_delete:
                memberlistDialog = new MemberListDialog(getActivity(),"관리자 권한 삭제",4);
                memberlistDialog.show();
                break;
            case R.id.btn_grade_delegate:
                memberlistDialog = new MemberListDialog(getActivity(),"관리자 권한 위임",2);
                memberlistDialog.show();
                break;
            case R.id.btn_member_remove:
                memberlistDialog = new MemberListDialog(getActivity(),"멤버 강제 탈퇴",3);
                memberlistDialog.show();
                break;
            case R.id.btn_end_study:
                dialogType=1;
                twoButtonDialog = new TwoButtonDialog(getActivity());
                twoButtonDialog.setOkOnClickListener(this);
                twoButtonDialog.setCancelOnClickListener(this);
                twoButtonDialog.showTwoButtonDialog("스터디 종료","스터디를 종료하시겠습니까?");
                break;
            case R.id.btn_secede_group:
                dialogType=2;
                twoButtonDialog = new TwoButtonDialog(getActivity());
                twoButtonDialog.setOkOnClickListener(this);
                twoButtonDialog.setCancelOnClickListener(this);
                twoButtonDialog.showTwoButtonDialog("스터디 탈퇴","스터디를 탈퇴하시겠습니까?");
                break;
            case R.id.ok_twobutton:
                if(dialogType==1)               callEndStudy();
                else if(dialogType==2)         callWithdrawStudy();
                break;
            case R.id.cancel_twobutton:
                twoButtonDialog.closeTwoButtonDialog();
                break;
            default:
                break;
        }

        if(call != null)        call.enqueue(this);//onResponse() 실행
    }

    public void setBtnListener(View rootView){
        Button[] arrBtn = {btnAlarm, btnGroupSetting,
                btnPenaltySetting, btnSubGrant, btnGrant, btnSubGrantDelete,
                btnRemoveMember, btnEndStudy, btnSecedeGroup};
        int[] arrBtnId = {R.id.btn_alarm, R.id.btn_group_setting,
                R.id.btn_penalty_setting, R.id.btn_grade_share, R.id.btn_grade_delegate, R.id.btn_grade_share_delete,
                R.id.btn_member_remove, R.id.btn_end_study, R.id.btn_secede_group};

        for(int i=0; i<arrBtn.length; i++){
            arrBtn[i] = (Button)rootView.findViewById(arrBtnId[i]);
            arrBtn[i].setOnClickListener(this);
        }
        return;
    }

    // return ; 멤버 리스트를 String 배열로 반환
    // Custom Dialog 생성시 필요
    public String[] getMemberArray(int id){
        ArrayList<Object> members = mPref.getListValue("memberList");
        String[] array = null;
        if(members != null){
            array = new String[members.size()];
            for(int i=0; i<members.size(); i++){
                Member mem = (Member)members.get(i);
                array[i] = mem.getUser_name();
            }
        }
        return array;
    }

    public void callWithdrawStudy(){
        if(mPref.getUserGrade()==1){
            twoButtonDialog.closeTwoButtonDialog();
            final OneButtonDialog oneButtonDialog = new OneButtonDialog(getContext());
            oneButtonDialog.setCancelable(false);
            oneButtonDialog.setOkOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    oneButtonDialog.cancel();
                    oneButtonDialog.dismiss();
                }
            });
            oneButtonDialog.show();
            oneButtonDialog.setTitle(R.string.group_setting_secede_manager_fail_title);
            oneButtonDialog.setComment(R.string.group_setting_secede_manager_fail);
        }else{
            connectProgress.show();
            RestClient.RestService service = RestClient.getClient();
            Call<JsonObject> call = service.withdrawStudy(mPref.getUserNo(), mPref.getSelectedStudyNo());
            call.enqueue(this);
        }
    }


    public void callEndStudy(){
        connectProgress.show();
        User user = (User)mPref.getObjectValue("userInfo");
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.endStudy(mPref.getSelectedStudyNo());
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Retro Status Code = " + response.code());
        Intent intent;
        Gson gson = new GsonBuilder().create();
        try {
            JSONObject jsObject = new JSONObject(gson.toJson(response.body()));
            switch (mClickId){
                case R.id.btn_alarm:
                    JSONArray attendObj = jsObject.getJSONArray("alarmList");
                    Alarm[] alarmArr = gson.fromJson(attendObj.toString(), Alarm[].class);
                    ArrayList<Alarm> alarmList = new ArrayList<Alarm>();
                    Collections.addAll(alarmList, alarmArr);
                    intent = new Intent(getActivity(),GroupSettingAlarmListActivity.class);
                    intent.putParcelableArrayListExtra("alarmList", alarmList);
                    if(intent != null) startActivity(intent);
                    break;
                case R.id.ok_twobutton:
                    if(response.body().toString().equals("{\"result\":1}")) {
                        Toast.makeText(getContext(), "성공했습니다", Toast.LENGTH_SHORT).show();
                        if(dialogType==1){
                            //스터디 종료 후 처리 필요
                        }
                        else if(dialogType==2){
                            //스터디 탈퇴 후 처리 필요
                        }
                        dialogType=0;
                        twoButtonDialog.closeTwoButtonDialog();
                    }else{
                        Toast.makeText(getContext(), "실패했습니다", Toast.LENGTH_SHORT).show();
                    }
            }
        }catch (JSONException e) {
            Log.d(TAG,"e : " + e);
            e.printStackTrace();
        }
        connectProgress.cancel();
        connectProgress.dismiss();
    }

    @Override
    public void onFailure(Call call, Throwable t) {
    }
}




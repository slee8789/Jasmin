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
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupSettingAlarmListActivity;
import com.study.jasmin.jasmin.ui.activity.SettingAssignmentActivity;
import com.study.jasmin.jasmin.ui.activity.SettingGroupInfoActivity;
import com.study.jasmin.jasmin.ui.activity.SettingGroupPenaltyActivity;
import com.study.jasmin.jasmin.ui.dialog.MemberListDialog;
import com.study.jasmin.jasmin.ui.dialog.OneButtonDialog;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.ui.dialog.TwoButtonDialog;
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
            btnSubGrant,btnGrant,
            btnRemoveMember,
            btnEndStudy,
            btnSecedeGroup;
    MemberListDialog memberlistDialog;
    TwoButtonDialog twoButtonDialog;
    private ProgressDialog connectProgress;
    public JasminPreference mPref;
    private int mClickId;

    public GroupSettingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_group_setting, container, false);

        setBtnListner(rootView);
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
            case R.id.btn_grade_delegate:
                memberlistDialog = new MemberListDialog(getActivity(),"관리자 권한 위임",2);
                memberlistDialog.show();
                break;
            case R.id.btn_member_remove:
                memberlistDialog = new MemberListDialog(getActivity(),"멤버 강제 탈퇴",3);
                memberlistDialog.show();
                break;
            case R.id.btn_end_study:
                twoButtonDialog = new TwoButtonDialog(getActivity());
                twoButtonDialog.showTwoButtonDialog("스터디 종료","스터디를 종료하시겠습니까?");
                break;
            case R.id.btn_secede_group:
                twoButtonDialog = new TwoButtonDialog(getActivity());
                twoButtonDialog.showTwoButtonDialog("스터디 탈퇴","스터디를 탈퇴하시겠습니까?");
                break;
            default:
                break;
        }

        if(call != null)        call.enqueue(this);//onResponse() 실행
    }

    public void setBtnListner(View rootView){
        Button[] arrBtn = {btnAlarm, btnGroupSetting,
                btnPenaltySetting, btnSubGrant, btnGrant,
                btnRemoveMember, btnEndStudy, btnSecedeGroup};
        int[] arrBtnId = {R.id.btn_alarm, R.id.btn_group_setting,
                R.id.btn_penalty_setting, R.id.btn_grade_share, R.id.btn_grade_delegate,
                R.id.btn_member_remove, R.id.btn_end_study, R.id.btn_secede_group};

        for(int i=0; i<8; i++){
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




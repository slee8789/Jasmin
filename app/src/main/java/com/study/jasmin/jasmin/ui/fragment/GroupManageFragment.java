package com.study.jasmin.jasmin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Assignment;
import com.study.jasmin.jasmin.entity.Attendance;
import com.study.jasmin.jasmin.entity.QnA;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupManageAssignmentActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageAttendanceActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageMoneyEctActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageMoneybookActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageReceivablesActivity;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GroupManageFragment extends Fragment implements View.OnClickListener, Callback {
    public static final String TAG = "GroupManageFragment";
    private LinearLayout btnAttendance;
    private LinearLayout btnAssignment;
    private LinearLayout btnReceivables;
    private LinearLayout btnMoneybook;
    private LinearLayout btnMoneyEct;
    private ProgressDialog connectProgress;
    public JasminPreference mPref;
    public  int mClickId;


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
        btnMoneyEct = (LinearLayout)rootView.findViewById(R.id.btn_money_ect);

    }

    private void initViews() {
        btnAttendance.setOnClickListener(this);
        btnAssignment.setOnClickListener(this);
        btnReceivables.setOnClickListener(this);
        btnMoneybook.setOnClickListener(this);
        btnMoneyEct.setOnClickListener(this);
        connectProgress = new ProgressDialog(getContext());
        mPref = JasminPreference.getInstance(getActivity());
    }

    @Override
    public void onClick(View v) {
        mClickId =v.getId();
        switch (mClickId) {
            case R.id.btn_attendance:
                callAttendance();
                break;
            case R.id.btn_assignment:
                callAssignment();
                break;
            case R.id.btn_receivables:
                callPenalty();
                break;
            case R.id.btn_moneybook:
                callMoneybook();
                break;
            case R.id.btn_money_ect:
                callMoneyEct();
                break;
        }
    }
    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Retro Status Code = " + response.code());
        Intent intent = new Intent();
        Gson gson = new GsonBuilder().create();
        if(response.body()==null){
            connectProgress.cancel();
            connectProgress.dismiss();
            Log.d(TAG,"response.body() is null");
            return;
        }
        try {
            JSONObject jsObject = new JSONObject(gson.toJson(response.body()));
            JSONArray resultObj;

            switch (mClickId){
                    case R.id.btn_attendance:
                        resultObj = jsObject.getJSONArray("result");
                        Attendance[] attendanceArr = gson.fromJson(resultObj.toString(), Attendance[].class);
                        ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
                        Collections.addAll(attendanceList, attendanceArr);
                        intent = new Intent(getActivity(),GroupManageAttendanceActivity.class);
                        intent.putParcelableArrayListExtra("attendanceList", attendanceList);
                        //Log.d(TAG,">>>>>>>attendanceList"+attendanceList.toString());
                        startActivity(intent);
                        break;
                    case R.id.btn_assignment:
                        resultObj = jsObject.getJSONArray("result");
                        mPref.putJson("assignmentList",resultObj.toString());
                        startActivity(new Intent(getActivity(),GroupManageAssignmentActivity.class));
                        break;
                    case R.id.btn_receivables:
                        resultObj = jsObject.getJSONArray("result");
                        mPref.putJson("penaltyList",resultObj.toString());
                        startActivity(new Intent(getActivity(),GroupManageReceivablesActivity.class));
                        break;
                    case R.id.btn_moneybook:
                        resultObj = jsObject.getJSONArray("MoneybookList");
                        mPref.putJson("moneybookList",resultObj.toString());
                        intent = new Intent(getActivity(),GroupManageMoneybookActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_money_ect:
                        resultObj = jsObject.getJSONArray("inoutList");
                        mPref.putJson("moneyectList",resultObj.toString());
                        intent = new Intent(getActivity(),GroupManageMoneyEctActivity.class);
                        startActivity(intent);
                        break;
                }
            connectProgress.cancel();
            connectProgress.dismiss();

        }catch (JSONException e) {
            Log.d(TAG,"e : " + e);
        }

//        if(intent != null)
    }

    public void callAttendance(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.attendanceList(mPref.getSelStudyNo());
        call.enqueue(this);
    }

    public void callAssignment(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.assignmentList(mPref.getSelStudyNo());
        call.enqueue(this);
    }


    public void callMoneybook(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.moneybookList(mPref.getSelStudyNo());
        call.enqueue(this);
    }

    public void callPenalty(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.penaltyList(mPref.getSelStudyNo());
        call.enqueue(this);
    }

    public void call(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.assignmentList(mPref.getSelStudyNo());
        call.enqueue(this);
    }
    public void callMoneyEct(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.moneyEctkList(mPref.getSelStudyNo());
        call.enqueue(this);
    }


    //public void startManageActivity(String key, ArrayList<T> listInfo, Class<?> aclass)

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG,"Retro Status onFailure  " + call);
    }
}

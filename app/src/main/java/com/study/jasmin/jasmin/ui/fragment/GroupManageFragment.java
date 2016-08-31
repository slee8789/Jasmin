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
                goAttendance();
                break;
            case R.id.btn_assignment:
                startActivity(new Intent(getActivity(), GroupManageAssignmentActivity.class));
                /*connectProgress.show();
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.getAssignmentListByStudyNo(2); //test
                call.enqueue(this);
                */
                break;

            case R.id.btn_receivables:
                startActivity(new Intent(getActivity(), GroupManageReceivablesActivity.class));
                break;
            case R.id.btn_moneybook:
                startActivity(new Intent(getActivity(), GroupManageMoneybookActivity.class));
                break;
            case R.id.btn_money_ect:
                startActivity(new Intent(getActivity(), GroupManageMoneyEctActivity.class));
                break;
        }
    }

    int id;

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Retro Status Code = " + response.code());
        Intent intent = new Intent();
        Gson gson = new GsonBuilder().create();
        try {
            JSONObject jsObject = new JSONObject(gson.toJson(response.body()));
            JSONArray attendObj = jsObject.getJSONArray("result");

            if(jsObject.get("result") == null){
                return;
            }else{

            switch (mClickId){
                    case R.id.btn_attendance:
                        Attendance[] attendanceArr = gson.fromJson(attendObj.toString(), Attendance[].class);
                        ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();
                        Collections.addAll(attendanceList, attendanceArr);
                        intent = new Intent(getActivity(),GroupManageAttendanceActivity.class);
                        intent.putParcelableArrayListExtra("attendanceList", attendanceList);
                        //Log.d(TAG,">>>>>>>attendanceList"+attendanceList.toString());
                        startActivity(intent);
                        break;
                    case R.id.btn_assignment:
    //                    Assignment[] assignments = gson.fromJson(result.toString(), Assignment[].class);
    //            List<Assignment> list = Arrays.asList(assignments);
    //            Log.d(TAG, "Retro json -> entity : " + list);
    //                    intent = new Intent(getContext(), GroupManageAssignmentActivity.class);
    //                    intent.putExtra("assignments", assignments);
    //                   startActivity(intent);
                        break;
                    case R.id.btn_receivables:
                        break;
                    case R.id.btn_moneybook:
                        break;
                    case R.id.btn_money_ect:
                        break;
                }
            }
            connectProgress.cancel();
            connectProgress.dismiss();

        }catch (JSONException e) {
            Log.d(TAG,"e : " + e);
        }

//        if(intent != null)
    }

    public void goAttendance(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.attendanceList(mPref.getSelStudyNo());
        call.enqueue(this);
    }

    public void goAssignment(){
        connectProgress.show();
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.assignmentList(mPref.getSelStudyNo());
        call.enqueue(this);
    }



    //public void startManageActivity(String key, ArrayList<T> listInfo, Class<?> aclass)

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG,"Retro Status onFailure  " + call);
    }
}

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
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupManageAssignmentActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageAttendanceActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageMoneybookActivity;
import com.study.jasmin.jasmin.ui.activity.GroupManageReceivablesActivity;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GroupManageFragment extends Fragment implements View.OnClickListener, Callback {
    public static final String TAG = "GroupManageFragment";
    private LinearLayout btnAttendance;
    private LinearLayout btnAssignment;
    private LinearLayout btnReceivables;
    private LinearLayout btnMoneybook;
    private ProgressDialog connectProgress;


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
        connectProgress = new ProgressDialog(getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_attendance:
                id = R.id.btn_attendance;
                startActivity(new Intent(getActivity(), GroupManageAttendanceActivity.class));
                break;

            case R.id.btn_assignment:
//                startActivity(new Intent(getActivity(), GroupManageAssignmentActivity.class));
                connectProgress.show();
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.getAssignmentListByStudyNo(2); //test
                call.enqueue(this);
                break;

            case R.id.btn_receivables:
                startActivity(new Intent(getActivity(), GroupManageReceivablesActivity.class));
                break;

            case R.id.btn_moneybook:
                startActivity(new Intent(getActivity(), GroupManageMoneybookActivity.class));
                break;
        }
    }

    int id;

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Retro Status Code = " + response.code());
        Gson gson = new GsonBuilder().create();
        try {
            JSONObject jsObject = new JSONObject(gson.toJson(response.body()));
            Object result = jsObject.get("result");

            Assignment[] assignments = gson.fromJson(result.toString(), Assignment[].class);
//            List<Assignment> list = Arrays.asList(assignments);
//            Log.d(TAG, "Retro json -> entity : " + list);

            Intent intent = new Intent(getActivity(), GroupManageAssignmentActivity.class);
            intent.putExtra("assignments", assignments);
            startActivity(intent);
            connectProgress.cancel();
            connectProgress.dismiss();

        }catch (JSONException e) {
            Log.d(TAG,"e : " + e);
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG,"Retro Status onFailure  " + call);
    }
}

package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupAddActivity;
import com.study.jasmin.jasmin.ui.dialog.JoinDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoGroupList;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomeFragment";
    private Button btnMakeStudy;
    private Button btnEnrollStudy;
    private RecyclerView rView;
    private AdaptInfoGroupList rcAdapter;
    private ArrayList<Object> studyList;
    private JoinDialog joinDialog;
    private JasminPreference mPref;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        findViews(rootView);
        initViews();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initWithViews();
    }

    private void init() {
        mPref = JasminPreference.getInstance(getContext());
    }

    private void findViews(View rootView) {
        btnMakeStudy = (Button) rootView.findViewById(R.id.btn_make_study);
        btnEnrollStudy = (Button) rootView.findViewById(R.id.btn_join_study);
        lLayout = new GridLayoutManager(getContext(), 2);
        rView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

    }

    private void initViews() {
        btnMakeStudy.setOnClickListener(this);
        btnEnrollStudy.setOnClickListener(this);

    }

    private void initWithViews() {
        studyList = JasminPreference.getInstance(getContext()).getListValue("studyList");
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rcAdapter = new AdaptInfoGroupList(getContext(), studyList);
        rView.setAdapter(rcAdapter);
        rView.refreshDrawableState();
    }

    private GridLayoutManager lLayout;

    public void showJoinDialog() {
        joinDialog = new JoinDialog(getContext());
        joinDialog.setOkOnClickListener(this);
        joinDialog.show();
    }

    private void codeCheck() {
        if (joinDialog.getCode().getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "코드를 입력하여 주세요.", Toast.LENGTH_SHORT).show();
        } else {
            int userNo = ((User) mPref.getObjectValue("userInfo")).getUser_no();
            RestClient.RestService service = RestClient.getClient();
            Call<JsonObject> call = service.join(userNo, Integer.parseInt((joinDialog.getCode().getText().toString())));
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d(TAG, "Retro Status Code = " + response.code());
                    //Todo: join return에 studyNo 받기
                /*RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call2 = service.gotoStudy();
                call2.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d(TAG, "Retro Status Code = " + response.code());

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });*/
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_make_study:
                Log.d(TAG, "click btn_make_study");
                Intent intent = new Intent(getActivity(), GroupAddActivity.class);
                startActivity(intent); //테스트
                break;

            case R.id.btn_join_study:
                Log.d(TAG, "click btn_join_study");
                showJoinDialog();
                break;

            case R.id.join_ok:
                Log.d(TAG, "click join_ok");
                codeCheck();
                break;

        }
    }
}

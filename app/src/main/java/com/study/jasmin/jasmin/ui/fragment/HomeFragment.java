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

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupAddActivity;
import com.study.jasmin.jasmin.ui.list.AdaptInfoGroupList;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomeFragment";
    private Button btnMakeStudy;
    private RecyclerView rView;
    private AdaptInfoGroupList rcAdapter;
    private ArrayList<Object> studyList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        studyList = JasminPreference.getInstance(getContext()).getListValue("studyList");
        findViews(rootView);
        initViews();
        return rootView;
    }

    private void findViews(View rootView) {
        btnMakeStudy = (Button) rootView.findViewById(R.id.btn_make_study);
        lLayout = new GridLayoutManager(getContext(), 2);
        rView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
    }

    private void initViews() {
        btnMakeStudy.setOnClickListener(this);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rcAdapter = new AdaptInfoGroupList(getContext(), studyList);
        rView.setAdapter(rcAdapter);
        rView.refreshDrawableState();
    }

    private GridLayoutManager lLayout;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_make_study:
                Log.d(TAG, "click btn_make_study");
                Intent intent = new Intent(getActivity(),GroupAddActivity.class);
                startActivity(intent); //테스트
                break;

        }
    }
}

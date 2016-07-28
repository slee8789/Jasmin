package com.study.jasmin.jasmin.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import com.study.jasmin.jasmin.R;


public class GroupNoticeAddFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "GroupNoticeAddFragment";

    public GroupNoticeAddFragment() {
        // Required empty public constructor
    }
    @Override
    public void onDetach() {
        Log.d(TAG, "GroupNoticeAddFragment onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "GroupNoticeAddFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "GroupNoticeAddFragment onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "GroupNoticeAddFragment onStart");
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeAddFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeAddFragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "GroupNoticeAddFragment onAttach");
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"GroupNoticeAddFragment onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_group_notice_add, container, false);
        findViews(rootView);
        initViews();
        return rootView;
    }

    private void findViews(View rootView) {
    }

    private void initViews() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}

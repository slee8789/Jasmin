package com.study.jasmin.jasmin.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupReplyActivity;
import com.study.jasmin.jasmin.ui.activity.LoginActivity;


public class GroupNoticeDetailFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "GNDetailFragment";
    private View footer;
    private ImageView btnFooterBack;
    private ImageView btnFooterWrite;
    private ImageView btnFooterFavorite;
    private ImageView btnFooterReply;
    private ExpandableListView listAttachment;

    public GroupNoticeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "GroupNoticeDetailFragment onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "GroupNoticeDetailFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "GroupNoticeDetailFragment onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "GroupNoticeDetailFragment onStart");
        super.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeDetailFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeDetailFragment onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "GroupNoticeDetailFragment onAttach");
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "GroupNoticeDetailFragment onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_group_notice_detail, container, false);
        findViews(rootView);
        initViews();
        return rootView;
    }

    private void findViews(View rootView) {
        footer = rootView.findViewById(R.id.include);
        btnFooterBack = (ImageView) footer.findViewById(R.id.btn_back);
        btnFooterWrite = (ImageView) footer.findViewById(R.id.btn_write);
        btnFooterFavorite = (ImageView) footer.findViewById(R.id.btn_favorite);
        btnFooterReply = (ImageView) footer.findViewById(R.id.btn_reply);
        listAttachment = (ExpandableListView) rootView. findViewById(R.id.detail_attachment);

    }

    private void initViews() {
        btnFooterBack.setOnClickListener(this);
        btnFooterWrite.setOnClickListener(this);
        btnFooterFavorite.setOnClickListener(this);
        btnFooterReply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Log.d(TAG, "btn_back clicked");
                break;

            case R.id.btn_write:
                Log.d(TAG, "btn_write clicked");
                break;

            case R.id.btn_favorite:
                Log.d(TAG, "btn_favorite clicked");
                break;

            case R.id.btn_reply:
                Log.d(TAG, "btn_reply clicked");
                startActivity(new Intent(getContext(), GroupReplyActivity.class));
                break;
        }
    }
}

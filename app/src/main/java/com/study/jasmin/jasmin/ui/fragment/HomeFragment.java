package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupAddActivity;
import com.study.jasmin.jasmin.ui.list.AdaptInfoGroupList;
import com.study.jasmin.jasmin.ui.list.ListInfoReply;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomeFragment";
    private Button btnMakeStudy;
    private GridLayout gridLayout;
    private int numStudy = 0;
    private ImageView studyImg;
    private Button studyBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(rootView);
        initViews();

        return rootView;
    }

    private void findViews(View rootView) {
        btnMakeStudy = (Button) rootView.findViewById(R.id.btn_make_study);
        gridLayout = (GridLayout) rootView.findViewById(R.id.gridLayout);
    }

    private void initViews() {
        btnMakeStudy.setOnClickListener(this);
    }

    private GridView gridView;

    private ListView replyList;
    private ArrayList<ListInfoReply> arrayListInfo = new ArrayList<ListInfoReply>();
    private AdaptInfoGroupList adaptListInfo;

    private void addStudy() {


//        gridLayout.addView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_make_study:
                Log.d(TAG, "click btn_make_study");
                startActivity(new Intent(getActivity(),GroupAddActivity.class)); //테스트
//                addStudy();
                break;

        }
    }
}

package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.GroupAddActivity;

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

    private void addStudy() {

        LinearLayout studyLayout = new LinearLayout(getContext());
        studyLayout.setOrientation(LinearLayout.VERTICAL);
//        studyLayout.setWeightSum(1.0f);
//        studyLayout.setBackgroundColor(Color.parseColor("#123456"));



        LinearLayout.LayoutParams paramImg = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,0.2f);
        LinearLayout.LayoutParams paramBtn = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,0.8f);

        studyImg = new ImageView(getContext());
        studyImg.setImageResource(R.drawable.ic_close_black_24dp);
        studyImg.setBackgroundColor(Color.parseColor("#34FF11"));
        studyBtn = new Button(getContext());
        studyBtn.setText("" + numStudy);
        studyBtn.setBackgroundColor(Color.parseColor("#340011"));
        studyLayout.addView(studyImg, paramImg);
        studyLayout.addView(studyBtn, paramBtn);
        studyLayout.setId(numStudy++);

//        GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();

        gridLayout.addView(studyLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_make_study:
                Log.d(TAG, "click btn_make_study");
                startActivity(new Intent(getActivity(),GroupAddActivity.class)); //테스트
                addStudy();
                break;

        }
    }
}

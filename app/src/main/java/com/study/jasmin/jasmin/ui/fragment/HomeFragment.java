package com.study.jasmin.jasmin.ui.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.study.jasmin.jasmin.util.JasminProtocol;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "HomeFragment";
    private Button btnMakeStudy;
    private RecyclerView rView;
    private AdaptInfoGroupList rcAdapter;
    private BroadcastReceiver broadcastReceiver = null;
    private ArrayList<Object> studyList;

    public HomeFragment() {
        // Required empty public constructor
    }

    private void registerReceiver() {
        if (broadcastReceiver != null) return;
        IntentFilter theFilter = new IntentFilter();
        theFilter.addAction(JasminProtocol.BROADCAST_MESSAGE);
        this.broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String result = intent.getStringExtra("groupname");
                Log.d(TAG, "registerReceiver onReceive " + result);
                switch (result) {
                    case "groupname":
//                        addStudy(result,0); //Todo: 서비스 또는 핸들러 고려
                        break;
                }
            }
        };
        getContext().registerReceiver(this.broadcastReceiver, theFilter);
    }

    private void unregisterReceiver() {
        Log.d(TAG, "unregisterReceiver");
        if (broadcastReceiver != null)
            getContext().unregisterReceiver(broadcastReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        studyList = JasminPreference.getInstance(getContext()).getListValue("studyList");
        Log.d(TAG,"studyList.size : " + studyList.size());
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
        registerReceiver();
        btnMakeStudy.setOnClickListener(this);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        Log.d(TAG,"studyList : " + studyList.toString());
        rcAdapter = new AdaptInfoGroupList(getContext(), studyList);
        Log.d(TAG,"studyList2 : " + studyList.toString());
        rView.setAdapter(rcAdapter);
        Log.d(TAG,"studyList3 : " + studyList.toString());
        rView.refreshDrawableState();
        Log.d(TAG,"studyList4 : " + studyList.toString());
    }

    private GridLayoutManager lLayout;

    /*private void addStudy(String name, int cover) {
        groupItems.add(new ListInfoGroup(0,"United States", R.drawable.one));
        rcAdapter.notifyDataSetChanged();
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_make_study:
                Log.d(TAG, "click btn_make_study");
                Intent intent = new Intent(getActivity(),GroupAddActivity.class);
                startActivity(intent); //테스트
//                addStudy();
                break;

        }
    }
}

package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.QnA;
import com.study.jasmin.jasmin.ui.activity.DeveloperActivity;
import com.study.jasmin.jasmin.ui.activity.SendCustomerCenterActivity2;
import com.study.jasmin.jasmin.ui.activity.ServiceHelpActivity;

import java.util.ArrayList;

public class ServiceFragment extends Fragment {

    public static final String TAG = "ServiceFragment";

    static final String[] LIST_MENU = {"도움말", "문의 하기", "개발자 정보"};
    private ArrayList<QnA> qnaList;

    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        final ListView listView = (ListView) view.findViewById(R.id.lv2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getActivity(), ServiceHelpActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), SendCustomerCenterActivity2.class));
                        break;
                    case 2:
                         startActivity(new Intent(getActivity(), DeveloperActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
}

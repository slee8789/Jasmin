package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.MyActActivity;
import com.study.jasmin.jasmin.ui.activity.MyPenaltyActivity;
import com.study.jasmin.jasmin.ui.activity.MyScrabActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    static final String[] LIST_MENU = {"나의 스크랩 자료", "나의 벌금", "나의 활동 내역"};

    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        final ListView listView = (ListView) view.findViewById(R.id.lv);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getActivity(),MyScrabActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), MyPenaltyActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), MyActActivity.class));
                        break;
                }
            }
        });
        return view;
    }
}

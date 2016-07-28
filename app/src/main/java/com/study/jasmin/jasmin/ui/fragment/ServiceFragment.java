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
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.activity.DeveloperActivity;
import com.study.jasmin.jasmin.ui.activity.SendCustomerCenterActivity2;
import com.study.jasmin.jasmin.ui.activity.ServiceHelpActivity;

public class ServiceFragment extends Fragment {
    static final String[] LIST_MENU = {"","도움말", "문의 하기", "개발자"};


    public ServiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        final ListView listView = (ListView) view.findViewById(R.id.lv2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(getActivity(),ServiceHelpActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), SendCustomerCenterActivity2.class));
                        break;
                    case 3:
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

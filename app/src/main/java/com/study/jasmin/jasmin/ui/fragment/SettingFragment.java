package com.study.jasmin.jasmin.ui.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
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
import com.study.jasmin.jasmin.ui.activity.EditInfoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    static final String[] LIST_MENU = {"","회원정보 수정", "로그아웃","카카오톡 연동하기"};
    private SharedPreferences mPref;

    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU);

        final ListView listView = (ListView) view.findViewById(R.id.lv3);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 1:
                        startActivity(new Intent(getActivity(),EditInfoActivity.class));
                        break;
                    case 2:
                        mPref = getContext().getSharedPreferences("userInfo", getContext().MODE_PRIVATE);
                        SharedPreferences.Editor editor = mPref.edit();
                        editor.putBoolean("autoLogin",false);
                        editor.commit();
                        //startActivity(new Intent(getActivity(), MyPenaltyActivity.class));
                        break;
                    case 3:
                        //startActivity(new Intent(getActivity(), MyPenaltyActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
}

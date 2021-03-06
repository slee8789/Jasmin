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
import com.study.jasmin.jasmin.ui.activity.EditInfoActivity;
import com.study.jasmin.jasmin.ui.activity.IntroActivity;
import com.study.jasmin.jasmin.ui.activity.LoginActivity;
import com.study.jasmin.jasmin.util.JasminPreference;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {
    public static final String TAG = "SettingFragment";
    static final String[] LIST_MENU = {"회원정보 수정", "로그아웃","카카오톡 연동하기"};
    private JasminPreference mPref = JasminPreference.getInstance(getContext());

    public SettingFragment() {
        // Required empty public constructor
    }
    @Override
    public void onDestroyView() {
        Log.d(TAG,"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.lv3);
        listView.setAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0: //회원정보수정
                        startActivity(new Intent(getActivity(),EditInfoActivity.class));
                        break;
                    case 1://로그아웃
                        mPref = JasminPreference.getInstance(getContext());
                        mPref.put("qnaList","");
                        mPref.put("userInfo","");
                        mPref.put("studyList","");
                        mPref.put("autoLogin",false);
                        getActivity().finish();
                        startActivity(new Intent(getActivity(),IntroActivity.class));
                        break;
                    case 2://카카오톡 연동하기
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

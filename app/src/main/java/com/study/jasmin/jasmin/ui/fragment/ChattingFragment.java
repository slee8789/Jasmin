package com.study.jasmin.jasmin.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.jasmin.jasmin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment {
    public static final String TAG = "ChattingFragment";

    public ChattingFragment() {
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
        return inflater.inflate(R.layout.fragment_chatting, container, false);
    }

}

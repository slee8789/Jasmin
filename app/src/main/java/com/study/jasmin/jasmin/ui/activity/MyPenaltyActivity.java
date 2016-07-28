package com.study.jasmin.jasmin.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.study.jasmin.jasmin.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MyPenaltyActivity extends Activity {
    ExpandableListView eplistView;

    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_penalty);
        eplistView = (ExpandableListView) this.findViewById(R.id.elv_penalty);
        setArrayData();
        eplistView.setAdapter(new BaseExpandableAdapter(this, arrayGroup, arrayChild));
    }

    private void setArrayData() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yy/MM/dd", Locale.KOREA);
        int penalty = 10000;
        arrayGroup.add("스터디 1");
        arrayGroup.add("스터디 2");

        ArrayList<String> study1 = new ArrayList<String>();
        study1.add(dateformat.format(new Date()) + "   |디파짓|   " + penalty);
        study1.add(dateformat.format(new Date()) + "   |지각|   " + penalty);

        ArrayList<String> study2 = new ArrayList<String>();
        study2.add(dateformat.format(new Date()) + "   |디파짓|   " + penalty);
        study2.add(dateformat.format(new Date()) + "   |지각|   " + penalty);

        arrayChild.put(arrayGroup.get(0), study1);
        arrayChild.put(arrayGroup.get(1), study2);
    }
}


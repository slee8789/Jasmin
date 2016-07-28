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

public class MyActActivity extends Activity {
    ExpandableListView listView;

    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_act);
        listView = (ExpandableListView) this.findViewById(R.id.elv2);
        setArrayData();
        listView.setAdapter(new BaseExpandableAdapter(this, arrayGroup, arrayChild));
    }

    private void setArrayData() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yy/MM/dd", Locale.KOREA);
        arrayGroup.add("스터디 1");
        arrayGroup.add("스터디 2");

        ArrayList<String> study1 = new ArrayList<String>();
        study1.add("스터디를 탈퇴했습니다" + dateformat.format(new Date()));
        study1.add("공지사항을 작성했습니다" + dateformat.format(new Date()));
        study1.add("출석했습니다" + dateformat.format(new Date()));
        study1.add("과제를 확인했습니다" + dateformat.format(new Date()));

        ArrayList<String> study2 = new ArrayList<String>();
        study2.add("자료실에 글을 삭제하였습니다" + dateformat.format(new Date()));
        study2.add("공지사항을 작성했습니다" + dateformat.format(new Date()));
        study2.add("출석했습니다" + dateformat.format(new Date()));
        study2.add("과제를 확인했습니다" + dateformat.format(new Date()));

        arrayChild.put(arrayGroup.get(0), study1);
        arrayChild.put(arrayGroup.get(1), study2);
    }
}

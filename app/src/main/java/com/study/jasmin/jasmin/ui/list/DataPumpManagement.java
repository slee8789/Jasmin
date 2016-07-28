package com.study.jasmin.jasmin.ui.list;

import java.util.ArrayList;
import java.util.HashMap;

public class DataPumpManagement {
    public static HashMap<String, ArrayList<ArrayList<String>>> getData() {
        HashMap<String, ArrayList<ArrayList<String>>> attendanceListDetail = new HashMap<String, ArrayList<ArrayList<String>>>();

        ArrayList<String> attendanceColumn0 = new ArrayList<String>();
        attendanceColumn0.add("2016/01/01");
        attendanceColumn0.add("6 0 0");

        ArrayList<String> attendanceColumn1 = new ArrayList<String>();
        attendanceColumn1.add("2016/01/02");
        attendanceColumn1.add("6 0 2");

        ArrayList<ArrayList<String>> attendance = new ArrayList<ArrayList<String>>();
        attendance.add(attendanceColumn0);
        attendance.add(attendanceColumn1);

        ArrayList<String> assignmentColumn0 = new ArrayList<String>();
        assignmentColumn0.add("첫번째 과제입니다.");
        assignmentColumn0.add("4 / 6");

        ArrayList<String> assignmentColumn1 = new ArrayList<String>();
        assignmentColumn1.add("두번째 과제입니다.");
        assignmentColumn1.add("5 / 6");

        ArrayList<String> assignmentColumn2 = new ArrayList<String>();
        assignmentColumn2.add("세번째 과제입니다.");
        assignmentColumn2.add("2 / 6");

        ArrayList<ArrayList<String>> assignment = new ArrayList<ArrayList<String>>();
        assignment.add(assignmentColumn0);
        assignment.add(assignmentColumn1);

        ArrayList<String> receivablesColumn0 = new ArrayList<String>();
        receivablesColumn0.add("주결경");
        receivablesColumn0.add("16/06/28");
        receivablesColumn0.add("지각");
        receivablesColumn0.add("-500");

        ArrayList<String> receivablesColumn1 = new ArrayList<String>();
        receivablesColumn1.add("김세정");
        receivablesColumn1.add("16/06/27");
        receivablesColumn1.add("과제");
        receivablesColumn1.add("-1500");

        ArrayList<ArrayList<String>> receivables = new ArrayList<ArrayList<String>>();
        receivables.add(receivablesColumn0);
        receivables.add(receivablesColumn1);


        ArrayList<String> moneybookColumn0 = new ArrayList<String>();
        moneybookColumn0.add("주결경");
        moneybookColumn0.add("16/06/28");
        moneybookColumn0.add("지각");
        moneybookColumn0.add("-500");

        ArrayList<String> moneybookColumn1 = new ArrayList<String>();
        moneybookColumn1.add("김세정");
        moneybookColumn1.add("16/06/27");
        moneybookColumn1.add("과제");
        moneybookColumn1.add("-1500");

        ArrayList<ArrayList<String>> moneybook = new ArrayList<ArrayList<String>>();
        moneybook.add(receivablesColumn0);
        moneybook.add(receivablesColumn1);

        attendanceListDetail.put("머니북", moneybook);
        attendanceListDetail.put("미수금", receivables);
        attendanceListDetail.put("과제", assignment);
        attendanceListDetail.put("출결", attendance);  //밑에서부터 채워짐
        return attendanceListDetail;
    }


}
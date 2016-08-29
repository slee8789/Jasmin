package com.study.jasmin.jasmin.entity;

import java.util.ArrayList;

/**
 * Created by swan on 2016-08-20.
 */
public class AttendanceTitle {
    String strDate;
    int attendType1 = 0; //출석
    int attendType2 = 0; //지각
    int attendType3 = 0; //결석
    ArrayList<Attendance> attendanceList = new ArrayList<Attendance>();


    public AttendanceTitle() {
    }

    public AttendanceTitle(String strDate, int attendType1, int attendType2, int attendType3, ArrayList<Attendance> attendanceList) {
        this.strDate = strDate;
        this.attendType1 = attendType1;
        this.attendType2 = attendType2;
        this.attendType3 = attendType3;
        this.attendanceList = attendanceList;
    }


    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void addAttendanceList(Attendance attendance){
        this.attendanceList.add(attendance);
    }
    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public int getAttendType1() {
        return attendType1;
    }

    public void setAttendType1(int attendType1) {
        this.attendType1 = attendType1;
    }

    public int getAttendType2() {
        return attendType2;
    }

    public void setAttendType2(int attendType2) {
        this.attendType2 = attendType2;
    }

    public int getAttendType3() {
        return attendType3;
    }

    public void setAttendType3(int attendType3) {
        this.attendType3 = attendType3;
    }

    public void countAttendType1() {
        this.attendType1++;
    }
    public void countAttendType2() {
        this.attendType2++;
    }

    public void countAttendType3() {
        this.attendType3++;
    }


    public String getDate() {
        return strDate;
    }

    public void setDate(String date) {
        this.strDate = date;
    }

    public String getStatus() {
        return Integer.toString(attendType1) +" / "+ Integer.toString(attendType2) +" / "+Integer.toString(attendType3);
    }

    public void setTitleCount(String status){
        switch (status){
            case "출석":
                countAttendType1();
                break;
            case "지각":
                countAttendType2();
                break;
            case "결석":
                countAttendType3();
                break;
        }
        return;
    }

}

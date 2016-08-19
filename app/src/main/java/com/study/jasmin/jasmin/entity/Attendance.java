package com.study.jasmin.jasmin.entity;


public class Attendance {
    int attendance_no;
    int user_no;
    int study_no;
    String attendance_date;
    boolean attendance_state;

    public Attendance(int attendance_no, String attendance_date, boolean attendance_state) {
        this.attendance_no = attendance_no;
        this.attendance_date = attendance_date;
        this.attendance_state = attendance_state;
    }

    public Attendance(int attendance_no, int user_no, int study_no, String attendance_date, boolean attendance_state) {

        this.attendance_no = attendance_no;
        this.user_no = user_no;
        this.study_no = study_no;
        this.attendance_date = attendance_date;
        this.attendance_state = attendance_state;
    }

    public int getAttendance_no() {
        return attendance_no;
    }

    public void setAttendance_no(int attendance_no) {
        this.attendance_no = attendance_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getStudy_no() {
        return study_no;
    }

    public void setStudy_no(int study_no) {
        this.study_no = study_no;
    }

    public String getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public boolean isAttendance_state() {
        return attendance_state;
    }

    public void setAttendance_state(boolean attendance_state) {
        this.attendance_state = attendance_state;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendance_no=" + attendance_no +
                ", user_no=" + user_no +
                ", study_no=" + study_no +
                ", attendance_date='" + attendance_date + '\'' +
                ", attendance_state=" + attendance_state +
                '}';
    }
}

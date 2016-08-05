package com.study.jasmin.jasmin.ui.list;

public class ListInfoAttendanceCheck {
    private String name;
    private int status; // 1. 출석 2. 지각 3. 결석

    public ListInfoAttendanceCheck() {
        this.name = null;
    }

    public ListInfoAttendanceCheck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStatus(String name) {
        this.status = status;
    }
}





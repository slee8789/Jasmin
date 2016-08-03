package com.study.jasmin.jasmin.ui.list;

public class ListInfoAttendance {
    private String date;
    private String status;

    public ListInfoAttendance() {
    }

    public ListInfoAttendance(String id, String content) {
        super();
        this.date = id;
        this.status = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}





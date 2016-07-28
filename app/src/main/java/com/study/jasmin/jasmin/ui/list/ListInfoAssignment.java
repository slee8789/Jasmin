package com.study.jasmin.jasmin.ui.list;

import android.widget.Button;

public class ListInfoAssignment {
    private String title;
    private String status;
    private Button check;

    public ListInfoAssignment() {
    }

    public ListInfoAssignment(String title, String status, Button check) {
        this.title = title;
        this.status = status;
        this.check = check;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Button getCheck() {

        return check;
    }

    public void setCheck(Button check) {
        this.check = check;
    }
}





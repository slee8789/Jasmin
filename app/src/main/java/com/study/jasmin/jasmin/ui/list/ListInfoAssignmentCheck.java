package com.study.jasmin.jasmin.ui.list;

import android.widget.Button;
import android.widget.Switch;

public class ListInfoAssignmentCheck {
    private String name;
    private Switch submitToggle;

    public ListInfoAssignmentCheck() {
    }

    public ListInfoAssignmentCheck(String name, Switch submitToggle) {
        this.name = name;
        this.submitToggle = submitToggle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Switch getSubmitToggle() {
        return submitToggle;
    }

    public void setSubmitToggle(Switch submitToggle) {
        this.submitToggle = submitToggle;
    }
}





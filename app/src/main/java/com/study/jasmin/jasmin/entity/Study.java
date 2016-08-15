package com.study.jasmin.jasmin.entity;

import java.io.Serializable;

/**
 * Created by leesc on 2016-08-12.
 */
public class Study implements Serializable {
    private String study_no;
    private String study_name;

    public Study(String study_no, String study_name) {
        this.study_no = study_no;
        this.study_name = study_name;
    }

    public String getStudy_no() {
        return study_no;
    }

    public void setStudy_no(String study_no) {
        this.study_no = study_no;
    }

    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }

    @Override
    public String toString() {
        return "Study{" +
                "study_no=" + study_no +
                ", study_name='" + study_name + '\'' +
                '}';
    }
}

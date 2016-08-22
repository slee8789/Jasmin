package com.study.jasmin.jasmin.entity;

import java.io.Serializable;

/**
 * Created by leesc on 2016-08-12.
 */
public class Member implements Serializable {

    private int study_no;
    private int user_no;
    private int user_grade;
    private int user_deposit;
    //private String user_name;

    public Member(int study_no, int user_no, int user_grade, int user_deposit) {
        this.study_no = study_no;
        this.user_no = user_no;
        this.user_grade = user_grade;
        this.user_deposit = user_deposit;
    }

    public int getStudy_no() {
        return study_no;
    }

    public void setStudy_no(int study_no) {
        this.study_no = study_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getUser_grade() {
        return user_grade;
    }

    public void setUser_grade(int user_grade) {
        this.user_grade = user_grade;
    }

    public int getUser_deposit() {
        return user_deposit;
    }

    public void setUser_deposit(int user_deposit) {
        this.user_deposit = user_deposit;
    }
}

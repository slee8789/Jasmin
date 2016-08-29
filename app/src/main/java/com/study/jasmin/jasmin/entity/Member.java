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
    private String user_name;
    private String user_email;

    public Member(int study_no, int user_no, int user_grade, int user_deposit, String user_name, String user_email) {
        this.study_no = study_no;
        this.user_no = user_no;
        this.user_grade = user_grade;
        this.user_deposit = user_deposit;
        this.user_name = user_name;
        this.user_email = user_email;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "study_no=" + study_no +
                ", user_no=" + user_no +
                ", user_grade=" + user_grade +
                ", user_deposit=" + user_deposit +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + user_email + '\'' +
                '}';
    }
}

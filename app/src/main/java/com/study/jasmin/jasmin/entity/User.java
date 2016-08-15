package com.study.jasmin.jasmin.entity;

import java.io.Serializable;

/**
 * Created by leesc on 2016-08-12.
 */
public class User implements Serializable {

    private int user_no;
    private int user_sex;
    private String user_email;
    private String user_name;
    private String user_kkid;

    public User(int user_no, int user_sex, String user_email, String user_name, String user_kkid) {
        this.user_no = user_no;
        this.user_sex = user_sex;
        this.user_email = user_email;
        this.user_name = user_name;
        this.user_kkid = user_kkid;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_kkid() {
        return user_kkid;
    }

    public void setUser_kkid(String user_kkid) {
        this.user_kkid = user_kkid;
    }
}

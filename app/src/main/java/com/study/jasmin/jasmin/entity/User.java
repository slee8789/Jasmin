package com.study.jasmin.jasmin.entity;

/**
 * Created by leesc on 2016-08-12.
 */
public class User {
    private String userNo;
    private String userSex;
    private String userName;
    private String userEmail;
    private String userKkid;
    private String userPassword;

    public User(String userNo, String userSex, String userName, String userEmail, String userKkid, String userPassword) {
        this.userNo = userNo;
        this.userSex = userSex;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userKkid = userKkid;
        this.userPassword = userPassword;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserKkid() {
        return userKkid;
    }

    public void setUserKkid(String userKkid) {
        this.userKkid = userKkid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}

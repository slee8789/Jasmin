package com.study.jasmin.jasmin.entity;


public class Penalty {
    private int user_no;
    private String user_name;
    private int penalty_no;
    private String penalty_date;
    private String penalty_title;
    private int penalty_money;

    public Penalty(int user_no, String user_name, int penalty_no, String penalty_date, String penalty_title, int penalty_money) {
        this.user_no = user_no;
        this.user_name = user_name;
        this.penalty_no = penalty_no;
        this.penalty_date = penalty_date;
        this.penalty_title = penalty_title;
        this.penalty_money = penalty_money;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getPenalty_no() {
        return penalty_no;
    }

    public void setPenalty_no(int penalty_no) {
        this.penalty_no = penalty_no;
    }

    public String getPenalty_date() {
        return penalty_date;
    }

    public void setPenalty_date(String penalty_date) {
        this.penalty_date = penalty_date;
    }

    public String getPenalty_title() {
        return penalty_title;
    }

    public void setPenalty_title(String penalty_title) {
        this.penalty_title = penalty_title;
    }

    public int getPenalty_money() {
        return penalty_money;
    }

    public void setPenalty_money(int penalty_money) {
        this.penalty_money = penalty_money;
    }

    @Override
    public String toString() {
        return "Penalty{" +
                "user_no=" + user_no +
                ", user_name='" + user_name + '\'' +
                ", penalty_no=" + penalty_no +
                ", penalty_date='" + penalty_date + '\'' +
                ", penalty_title='" + penalty_title + '\'' +
                ", penalty_money=" + penalty_money +
                '}';
    }
}

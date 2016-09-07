package com.study.jasmin.jasmin.entity;

public class MoneyEct {
    int inout_no;
    int inout_money;
    String inout_date;
    String inout_reason;

    public MoneyEct(int inout_no, int inout_money, String inout_date, String inout_reason) {
        this.inout_no = inout_no;
        this.inout_money = inout_money;
        this.inout_date = inout_date;
        this.inout_reason = inout_reason;
    }

    public int getInout_no() {
        return inout_no;
    }

    public void setInout_no(int inout_no) {
        this.inout_no = inout_no;
    }

    public int getInout_money() {
        return inout_money;
    }

    public void setInout_money(int inout_money) {
        this.inout_money = inout_money;
    }

    public String getInout_date() {
        return inout_date;
    }

    public void setInout_date(String inout_date) {
        this.inout_date = inout_date;
    }

    public String getInout_reason() {
        return inout_reason;
    }

    public void setInout_reason(String inout_reason) {
        this.inout_reason = inout_reason;
    }

    @Override
    public String toString() {
        return "MoneyEct{" +
                "inout_no=" + inout_no +
                ", inout_money=" + inout_money +
                ", inout_date='" + inout_date + '\'' +
                ", inout_reason='" + inout_reason + '\'' +
                '}';
    }
}

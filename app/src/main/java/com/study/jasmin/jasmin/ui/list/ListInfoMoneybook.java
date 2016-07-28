package com.study.jasmin.jasmin.ui.list;

public class ListInfoMoneybook {
    private String name;
    private String date;
    private String reason;
    private String money;

    public ListInfoMoneybook() {
    }

    public ListInfoMoneybook(String name, String date, String reason, String money) {
        this.name = name;
        this.date = date;
        this.reason = reason;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}





package com.study.jasmin.jasmin.ui.list;

import android.widget.ImageView;

public class ListInfoReceivables {
    private String name;
    private String date;
    private String reason;
    private String money;
    private ImageView cancel;

    public ListInfoReceivables() {
    }

    public ListInfoReceivables(String name, String date, String reason, String money, ImageView cancel) {
        this.name = name;
        this.date = date;
        this.reason = reason;
        this.money = money;
        this.cancel = cancel;
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

    public ImageView getCancel() {
        return cancel;
    }

    public void setCancel(ImageView cancel) {
        this.cancel = cancel;
    }
}





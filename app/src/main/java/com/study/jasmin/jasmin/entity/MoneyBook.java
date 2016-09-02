package com.study.jasmin.jasmin.entity;

public class MoneyBook {
    String  date;
    String  name;
    String  content;
    int     money;

    public MoneyBook(String date, String name, String content, int money) {
        this.date = date;
        this.name = name;
        this.content = content;
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "MoneyBook{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", money=" + money +
                '}';
    }
}

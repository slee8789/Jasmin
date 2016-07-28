package com.study.jasmin.jasmin.ui.list;

public class ListInfoNotice {
    private String no;
    private String title;
    private String date;
    private String writer;

    public ListInfoNotice() {
    }

    public ListInfoNotice(String no, String title, String date, String writer) {
        super();
        this.no = no;
        this.title = title;
        this.date = date;
        this.writer = writer;


    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}





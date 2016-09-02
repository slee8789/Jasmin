package com.study.jasmin.jasmin.entity;

public class AssignmentTitle {

    private int study_no;
    private int homework_no;
    private String homework_title;
    private String homework_content;
    private String homework_start_date;
    private String homework_end_date;
    private int homework_money;

    public AssignmentTitle() {
    }

    public AssignmentTitle(int study_no, int homework_no, String homework_title, String homework_content, String homework_start_date, String homework_end_date, int homework_money) {
        this.study_no = study_no;
        this.homework_no = homework_no;
        this.homework_title = homework_title;
        this.homework_content = homework_content;
        this.homework_start_date = homework_start_date;
        this.homework_end_date = homework_end_date;
        this.homework_money = homework_money;
    }

    public int getStudy_no() {
        return study_no;
    }

    public void setStudy_no(int study_no) {
        this.study_no = study_no;
    }

    public int getHomework_no() {
        return homework_no;
    }

    public void setHomework_no(int homework_no) {
        this.homework_no = homework_no;
    }

    public String getHomework_title() {
        return homework_title;
    }

    public void setHomework_title(String homework_title) {
        this.homework_title = homework_title;
    }

    public String getHomework_content() {
        return homework_content;
    }

    public void setHomework_content(String homework_content) {
        this.homework_content = homework_content;
    }

    public String getHomework_start_date() {
        return homework_start_date;
    }

    public void setHomework_start_date(String homework_start_date) {
        this.homework_start_date = homework_start_date;
    }

    public String getHomework_end_date() {
        return homework_end_date;
    }

    public void setHomework_end_date(String homework_end_date) {
        this.homework_end_date = homework_end_date;
    }

    public int getHomework_money() {
        return homework_money;
    }

    public void setHomework_money(int homework_money) {
        this.homework_money = homework_money;
    }

    @Override
    public String toString() {
        return "AssignmentTitle{" +
                "study_no=" + study_no +
                ", homework_no=" + homework_no +
                ", homework_title='" + homework_title + '\'' +
                ", homework_content='" + homework_content + '\'' +
                ", homework_start_date='" + homework_start_date + '\'' +
                ", homework_end_date='" + homework_end_date + '\'' +
                ", homework_money=" + homework_money +
                '}';
    }
}

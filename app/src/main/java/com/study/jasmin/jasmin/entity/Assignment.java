package com.study.jasmin.jasmin.entity;

import java.io.Serializable;

/**
 * Created by leesc on 2016-08-12.
 */
public class Assignment implements Serializable {

    private int study_no;
    private int homework_no;
    private String homework_title;
    private String homework_content;
    private String user_name;
    private int user_no;
    private String homework_old_state;
    private String homework_state;      //제출, 미제출
    private String homework_start_date;
    private String homework_end_date;
    private int homework_money;

    public Assignment(){}


    public Assignment(int homework_no, int user_no, int study_no, String homework_start_date, String homework_end_date, String homework_state, String homework_title, String homework_content, int homework_money) {
        this.homework_no = homework_no;
        this.user_no = user_no;
        this.study_no = study_no;
        this.homework_start_date = homework_start_date;
        this.homework_end_date = homework_end_date;
        this.homework_state = homework_state;
        this.homework_title = homework_title;
        this.homework_content = homework_content;
        this.homework_money = homework_money;
    }

    public Assignment(int study_no, int homework_no, String homework_title, String homework_content, String user_name, int user_no, String homework_old_state, String homework_state, String homework_start_date, String homework_end_date, int homework_money) {
        this.study_no = study_no;
        this.homework_no = homework_no;
        this.homework_title = homework_title;
        this.homework_content = homework_content;
        this.user_name = user_name;
        this.user_no = user_no;
        this.homework_old_state = homework_old_state;
        this.homework_state = homework_state;
        this.homework_start_date = homework_start_date;
        this.homework_end_date = homework_end_date;
        this.homework_money = homework_money;
    }

    public int getHomework_no() {
        return homework_no;
    }

    public void setHomework_no(int homework_no) {
        this.homework_no = homework_no;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public int getStudy_no() {
        return study_no;
    }

    public void setStudy_no(int study_no) {
        this.study_no = study_no;
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

    public String getHomework_state() {
        return homework_state;
    }

    public void setHomework_state(String homework_state) {
        this.homework_state = homework_state;
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

    public int getHomework_money() {
        return homework_money;
    }

    public void setHomework_money(int homework_money) {
        this.homework_money = homework_money;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getHomework_old_state() {
        return homework_old_state;
    }

    public void setHomework_old_state(String homework_old_state) {
        this.homework_old_state = homework_old_state;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "homework_no=" + homework_no +
                ", user_no=" + user_no +
                ", study_no=" + study_no +
                ", homework_start_date='" + homework_start_date + '\'' +
                ", homework_end_date='" + homework_end_date + '\'' +
                ", homework_state=" + homework_state +
                ", homework_title='" + homework_title + '\'' +
                ", homework_content='" + homework_content + '\'' +
                ", homework_money=" + homework_money +
                '}';
    }


    /*private int homeworkNo;
    private int userNo;
    private int studyNo;
    private String homeworkStartDate;
    private String homeworkEndDate;
    private int homeworkState;
    private String homeworkTitle;
    private String homeworkContent;
    private int homeworkMoney;

    @Override
    public String toString() {
        return "Assignment{" +
                "homeworkNo=" + homeworkNo +
                ", userNo=" + userNo +
                ", studyNo=" + studyNo +
                ", homeworkStartDate='" + homeworkStartDate + '\'' +
                ", homeworkEndDate='" + homeworkEndDate + '\'' +
                ", homeworkState=" + homeworkState +
                ", homeworkTitle='" + homeworkTitle + '\'' +
                ", homeworkContent='" + homeworkContent + '\'' +
                ", homeworkMoney=" + homeworkMoney +
                '}';
    }

    public Assignment() {
    }

    public Assignment(int homeworkNo, int userNo, int studyNo, String homeworkStartDate, String homeworkEndDate, int homeworkState, String homeworkTitle, String homeworkContent, int homeworkMoney) {
        this.homeworkNo = homeworkNo;
        this.userNo = userNo;
        this.studyNo = studyNo;
        this.homeworkStartDate = homeworkStartDate;
        this.homeworkEndDate = homeworkEndDate;
        this.homeworkState = homeworkState;
        this.homeworkTitle = homeworkTitle;
        this.homeworkContent = homeworkContent;
        this.homeworkMoney = homeworkMoney;
    }

    public int getHomeworkNo() {
        return homeworkNo;
    }

    public void setHomeworkNo(int homeworkNo) {
        this.homeworkNo = homeworkNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }

    public String getHomeworkStartDate() {
        return homeworkStartDate;
    }

    public void setHomeworkStartDate(String homeworkStartDate) {
        this.homeworkStartDate = homeworkStartDate;
    }

    public String getHomeworkEndDate() {
        return homeworkEndDate;
    }

    public void setHomeworkEndDate(String homeworkEndDate) {
        this.homeworkEndDate = homeworkEndDate;
    }

    public int getHomeworkState() {
        return homeworkState;
    }

    public void setHomeworkState(int homeworkState) {
        this.homeworkState = homeworkState;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkContent() {
        return homeworkContent;
    }

    public void setHomeworkContent(String homeworkContent) {
        this.homeworkContent = homeworkContent;
    }

    public int getHomeworkMoney() {
        return homeworkMoney;
    }

    public void setHomeworkMoney(int homeworkMoney) {
        this.homeworkMoney = homeworkMoney;
    }*/
}

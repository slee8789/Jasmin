package com.study.jasmin.jasmin.entity;

import java.io.Serializable;

/**
 * Created by leesc on 2016-08-12.
 */
public class Study implements Serializable {

    private int study_no;
    private String study_name;

    private String study_des;
    private String study_start_date;
    private String study_end_date;
    private int study_useDeposit;
    private int study_basicDeposit;
    private int study_max_late;
    private int study_late_unit;
    private int study_late_fee;
    private int study_absence_fee;

    public Study(int study_no, String study_name) {
        this.study_no = study_no;
        this.study_name = study_name;
    }

    public Study(int study_no,
                 String study_name,
                 String study_des,
                 String study_start_date,
                 String study_end_date,
                 int study_useDeposit,
                 int study_basicDeposit,
                 int study_max_late,
                 int study_late_unit,
                 int study_late_fee,
                 int study_absence_fee) {

        this.study_no = study_no;
        this.study_name = study_name;
        this.study_des = study_des;
        this.study_start_date = study_start_date;
        this.study_end_date = study_end_date;
        this.study_useDeposit = study_useDeposit;
        this.study_basicDeposit = study_basicDeposit;
        this.study_max_late = study_max_late;
        this.study_late_unit = study_late_unit;
        this.study_late_fee = study_late_fee;
        this.study_absence_fee = study_absence_fee;
    }

    public int getStudy_no() {
        return study_no;
    }

    public void setStudy_no(int study_no) {
        this.study_no = study_no;
    }

    public String getStudy_name() {
        return study_name;
    }

    public void setStudy_name(String study_name) {
        this.study_name = study_name;
    }

    public String getStudy_des() {
        return study_des;
    }

    public void setStudy_des(String study_des) {
        this.study_des = study_des;
    }

    public String getStudy_start_date() {
        return study_start_date;
    }

    public void setStudy_start_date(String study_start_date) {
        this.study_start_date = study_start_date;
    }

    public String getStudy_end_date() {
        return study_end_date;
    }

    public void setStudy_end_date(String study_end_date) {
        this.study_end_date = study_end_date;
    }

    public int getStudy_useDeposit() {
        return study_useDeposit;
    }

    public void setStudy_useDeposit(int study_useDeposit) {
        this.study_useDeposit = study_useDeposit;
    }

    public int getStudy_basicDeposit() {
        return study_basicDeposit;
    }

    public void setStudy_basicDeposit(int study_basicDeposit) {
        this.study_basicDeposit = study_basicDeposit;
    }

    public int getStudy_max_late() {
        return study_max_late;
    }

    public void setStudy_max_late(int study_max_late) {
        this.study_max_late = study_max_late;
    }

    public int getStudy_late_unit() {
        return study_late_unit;
    }

    public void setStudy_late_unit(int study_late_unit) {
        this.study_late_unit = study_late_unit;
    }

    public int getStudy_late_fee() {
        return study_late_fee;
    }

    public void setStudy_late_fee(int study_late_fee) {
        this.study_late_fee = study_late_fee;
    }

    public int getStudy_absence_fee() {
        return study_absence_fee;
    }

    public void setStudy_absence_fee(int study_absence_fee) {
        this.study_absence_fee = study_absence_fee;
    }

    @Override
    public String toString() {
        return "Study{" +
                "study_no=" + study_no +
                ", study_name='" + study_name + '\'' +
                ", study_des='" + study_des + '\'' +
                ", study_start_date=" + study_start_date +
                ", study_end_date=" + study_end_date +
                ", study_useDeposit=" + study_useDeposit +
                ", study_basicDeposit=" + study_basicDeposit +
                ", study_max_late=" + study_max_late +
                ", study_late_unit=" + study_late_unit +
                ", study_late_fee=" + study_late_fee +
                ", study_absence_fee=" + study_absence_fee +
                '}';
    }
}

package com.study.jasmin.jasmin.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class Attendance implements Parcelable{
    int attendance_no;
    int user_no;
    int study_no;
    String attendance_date;
    String attendance_state;
    String attendance_state_new;
    String user_name;
    int penaltyMoney; //생성자에서 하드로 1000으로 입력 중


    public Attendance(int attendance_no, String attendance_date, String attendance_state) {
        this.attendance_no = attendance_no;
        this.attendance_date = attendance_date;
        this.attendance_state = attendance_state;
        this.user_name="유인나";
        this.penaltyMoney = 1000;
    }

    public Attendance(int attendance_no, int user_no, String user_name, int study_no, String attendance_date, String attendance_state, String attendance_state_new) {
        this.attendance_no = attendance_no;
        this.user_no = user_no;
        this.study_no = study_no;
        this.attendance_date = attendance_date;
        this.attendance_state = attendance_state;
        this.attendance_state_new = attendance_state_new;
        this.user_name=user_name;
        this.penaltyMoney = 1000;
    }

    protected Attendance(Parcel in) {
        attendance_no = in.readInt();
        user_no = in.readInt();
        study_no = in.readInt();
        attendance_date = in.readString();
        attendance_state = in.readString();
        user_name = in.readString();
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel in) {
            return new Attendance(in);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };

    public String getAttendance_state() {
        return attendance_state;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getAttendance_no() {
        return attendance_no;
    }

    public void setAttendance_no(int attendance_no) {
        this.attendance_no = attendance_no;
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

    public String getAttendance_date() {
        return attendance_date;
    }


    public void setAttendance_date(String attendance_date) {
        this.attendance_date = attendance_date;
    }

    public void setAttendance_state(String attendance_state) {
        this.attendance_state = attendance_state;
    }

    public int getPenaltyMoney() {
        return penaltyMoney;
    }

    public void setPenaltyMoney(int penaltyMoney) {
        this.penaltyMoney = penaltyMoney;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendance_no=" + attendance_no +
                ", user_no=" + user_no +
                ", study_no=" + study_no +
                ", attendance_date='" + attendance_date + '\'' +
                ", attendance_state='" + attendance_state_new + '\'' +
                ", attendance_state_old='" + attendance_state + '\'' +
                ", user_name='" + user_name + '\'' +
                ", penaltyMoney=" + penaltyMoney +
                '}';
    }

    public String getJsonInsert(){
        return "{\"user_no\":" + user_no +
                ",\"study_no\":" + study_no +
                ",\"attendance_state\":\"" +  attendance_state_new + "\"" +
                ",\"penalty_money\":" + penaltyMoney  +
                "}";
    }

    public String getJsonUpdate(){
        return "{\"attendance_no\":" + attendance_no +
                ",\"user_no\":" + user_no +
                ",\"study_no\":" + study_no +
                ",\"attendance_old_state\":\"" +  attendance_state + "\"" +
                ",\"attendance_state\":\"" +  attendance_state_new + "\"" +
                ",\"penalty_money\":" + penaltyMoney  +
                "}";
    }

    public String getJsonDelete(){
        return "{\"attendance_no\":" + attendance_no +"}";
    }

    public boolean checkUpdate(){
        if(attendance_state_new == null) {
            return false;
        }else  if(attendance_state_new.equals(attendance_state)){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(attendance_no);
        dest.writeInt(user_no);
        dest.writeInt(study_no);
        dest.writeString(attendance_date);
        dest.writeString(attendance_state);
        dest.writeString(user_name);
    }

    public void setAttendanceStateNew(String attendance_state_new) {
        this.attendance_state_new = attendance_state_new;
    }
}

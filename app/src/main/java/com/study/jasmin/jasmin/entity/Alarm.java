package com.study.jasmin.jasmin.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Alarm implements Parcelable {
    int alarm_no;
    int study_no;
    int alarm_time;
    int alarm_hour;
    int alarm_minute;
    int alarm_repeat;
    int alarm_date=0;
    String alarm_content;
    boolean[] alarm_dates_arr;// 0~6 : mon~sun / True(1) False(0)

    public Alarm(){
        this.alarm_no = 0;
        this.study_no = 0;
        this.alarm_time = 0;
        this.alarm_repeat = 0;
        this.alarm_content = "";
        this.alarm_date = 0;
        init();
    }

    public Alarm(int alarm_no, int study_no, int alarm_time, int alarm_repeat, int alarm_date, String alarm_content) {
        this.alarm_no = alarm_no;
        this.study_no = study_no;
        this.alarm_time = alarm_time;
        this.alarm_repeat = alarm_repeat;
        this.alarm_content = alarm_content;
        this.alarm_date = alarm_date;
    }

    protected Alarm(Parcel in) {
        alarm_no = in.readInt();
        study_no = in.readInt();
        alarm_time = in.readInt();
        alarm_hour = in.readInt();
        alarm_minute = in.readInt();
        alarm_repeat = in.readInt();
        alarm_date = in.readInt();
        alarm_dates_arr = in.createBooleanArray();
        alarm_content = in.readString();
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    //custom methods
    //1,init
    public void init(){
        this.alarm_minute = alarm_time%100;
        this.alarm_hour = alarm_time/100;
        setDateIntToArr();
    }

    //2. alarm_date(int) to alarm_dates(Array)
    public void setDateArrToInt(){
        boolean[] dates = alarm_dates_arr;
        int nDate = 0;
        for(int i=0; i<dates.length; i++){
            if(dates[i]){
                nDate++;
            }
            nDate = nDate * 10;
        }
        alarm_date = nDate;
        return ;
    }

    //3.alarm_date(int) to alarm_dates(Array)
    public void setDateIntToArr(){
        alarm_dates_arr = new boolean[]{false,false,false,false,false,false,false};
        int date = this.alarm_date;
        int jari = alarm_dates_arr.length -1 ;

        while(date>0 &&jari>=0) {
            alarm_dates_arr[jari] = (date%10 == 1)? true : false; //날짜의 일의 자리의 수가 1이면 true
            date = date / 10;//입력된 숫자를 10으로 나눠서 num변수에 담음.
            jari--; //반복될때마다 자리수가 증가됨, 배열에는 6이 일요일이기 때문에 마지막 자리수인 일요일부터 월요일 순으로 값을 넣음.
        }
    }

    //4.getTime(HH:MM(String))
    public String getTimeHHMM(){
        String strHour = Integer.toString(alarm_hour);
        String strMin =Integer.toString(alarm_minute);
        if(alarm_minute == 0) strMin = "00";
        else if(alarm_minute < 10) strMin = "0"+strMin;

        return strHour +":"+ strMin;
    }

//normal getter() and setter()
    public int getAlarmDateInt(){
        return alarm_date;
    }

    public void setAlarmDateInt(int nDate){
        this.alarm_date = nDate;
        setDateIntToArr();
    }


    public boolean[] getAlarmDateArr(){
        return alarm_dates_arr;
    }


    public boolean[] getArrDates(){
        return this.alarm_dates_arr;
    }

    public int getAlarmNo() {
        return alarm_no;
    }

    public void setAlarmNo(int alarm_no) {
        this.alarm_no = alarm_no;
    }

    public int getStudyNo() {
        return study_no;
    }

    public void setStudyNo(int study_no) {
        this.study_no = study_no;
    }

    public int getAlarmTime() {
        return alarm_time;
    }

    public void setAlarmTime(int alarm_time) {
        this.alarm_time = alarm_time;
        this.alarm_minute =alarm_time%100;
        this.alarm_hour = alarm_time - this.alarm_minute;
    }

    public boolean getAlarmRepeat() {

        if(alarm_repeat==1){
            return true;
        }
        return false;
    }

    public void setAlarmRepeat(int alarm_repeat) {
        this.alarm_repeat = alarm_repeat;
    }

    public String getAlarmContent() {
        return alarm_content;
    }

    public void setAlarmContent(String alarm_content) {
        this.alarm_content = alarm_content;
    }

    public int getAlarmHour(){
        return  alarm_hour;
    }
    public int getAlarmMinute(){
        return alarm_minute;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(alarm_no);
        dest.writeInt(study_no);
        dest.writeInt(alarm_time);
        dest.writeInt(alarm_hour);
        dest.writeInt(alarm_minute);
        dest.writeInt(alarm_repeat);
        dest.writeInt(alarm_date);
        dest.writeBooleanArray(alarm_dates_arr);
        dest.writeString(alarm_content);
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "alarm_no=" + alarm_no +
                ", study_no=" + study_no +
                ", alarm_time=" + alarm_time +
                ", alarm_hour=" + alarm_hour +
                ", alarm_minute=" + alarm_minute +
                ", alarm_repeat=" + alarm_repeat +
                ", alarm_date=" + alarm_date +
                ", alarm_dates_arr=" + Arrays.toString(alarm_dates_arr) +
                ", alarm_content='" + alarm_content + '\'' +
                '}';
    }
}

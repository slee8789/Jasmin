package com.study.jasmin.jasmin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Post implements Parcelable {
    int alarm_no;
    int study_no;
    int alarm_time;
    int alarm_hour;
    int alarm_minute;
    int alarm_repeat;
    int alarm_date=0;
    String alarm_content;
    boolean[] alarm_dates;// 0~6 : mon~sun / True(1) False(0)

    public Post(int alarm_no, int study_no, int alarm_time, int alarm_repeat, int alarm_date, String alarm_content) {
        this.alarm_no = alarm_no;
        this.study_no = study_no;
        this.alarm_time = alarm_time;
        this.alarm_repeat = alarm_repeat;
        this.alarm_content = alarm_content;
        this.alarm_date = alarm_date;
    }

    public void init(){
        this.alarm_minute = alarm_time%100;
        this.alarm_hour = alarm_time/100;
        setArrDates();
    }

    public void setArrDates(){
        alarm_dates = new boolean[]{false,false,false,false,false,false,false};
        int date = this.alarm_date;
        int jari = alarm_dates.length -1 ;

        while(date>0 &&jari>=0) {
            alarm_dates[jari] = (date%10 == 1)? true : false; //날짜의 일의 자리의 수가 1이면 true
            date = date / 10;//입력된 숫자를 10으로 나눠서 num변수에 담음.
            jari--; //반복될때마다 자리수가 증가됨, 배열에는 6이 일요일이기 때문에 마지막 자리수인 일요일부터 월요일 순으로 값을 넣음.
        }
    }

    public String getTimeHHMM(){
        String strHour = Integer.toString(alarm_hour);
        String strMin =Integer.toString(alarm_minute);
        if(alarm_minute == 0) strMin = "00";
        else if(alarm_minute < 10) strMin = "0"+strMin;

        return strHour +":"+ strMin;
    }

    protected Post(Parcel in) {
        alarm_no = in.readInt();
        study_no = in.readInt();
        alarm_time = in.readInt();
        alarm_hour = in.readInt();
        alarm_minute = in.readInt();
        alarm_repeat = in.readInt();
        alarm_date = in.readInt();
        alarm_dates = in.createBooleanArray();
        alarm_content = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public boolean[] getArrDates(){
        return this.alarm_dates;
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

    public void setAlarmCountent(String alarm_countent) {
        this.alarm_content = alarm_countent;
    }

    public int getAlarmHour(){
        return  alarm_hour;
    }
    public int getAlarmMinute(){
        return alarm_minute;
    }

    public void changeAlarmState(){
        //20160824-3. 개인의 알람 상태 가져와서 바꿔야함
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
        dest.writeBooleanArray(alarm_dates);
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
                ", alarm_dates=" + Arrays.toString(alarm_dates) +
                ", alarm_content='" + alarm_content + '\'' +
                '}';
    }
}

package com.study.jasmin.jasmin.ui.item;

public class ListViewAlarmItem {
    String context;
    String time;
    boolean bRepeat;
    boolean[] arrDay; //월,화,수,목,금,토,일 : 0~6
    boolean bSetting;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setStrTime(String strTime) {
        this.time = strTime;
    }

    public boolean getRepeat() {
        return bRepeat;
    }

    public void setbRepeat(boolean bRepeat) {
        this.bRepeat = bRepeat;
    }

    public boolean[] getArrDay() {
        return arrDay;
    }

    public void setArrDay(boolean[] arrDay) {
        this.arrDay = arrDay;
    }

    public boolean getSetting() {
        return bSetting;
    }

    public void setSetting(boolean bSetting) {
        this.bSetting = bSetting;
    }
}

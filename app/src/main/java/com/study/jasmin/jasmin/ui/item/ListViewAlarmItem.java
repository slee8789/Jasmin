package com.study.jasmin.jasmin.ui.item;

public class ListViewAlarmItem {

    String strAmpm;
    String strTime;
    boolean bRepeat;
    boolean[] arrDay; //월,화,수,목,금,토,일
    boolean bSetting;

    public String getStrAmpm() {
        return strAmpm;
    }

    public void setStrAmpm(String strAmpm) {
        this.strAmpm = strAmpm;
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
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

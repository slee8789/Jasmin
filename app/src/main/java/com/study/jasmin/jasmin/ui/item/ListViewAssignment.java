package com.study.jasmin.jasmin.ui.item;
/**
 * Created by swan on 2016-07-23.
 */
public class ListViewAssignment {
    private String strDate;
    private String strTitle;
    private String strStatus ;

    //set()
    public void setDate(String text) {  strDate= text ; }

    public void setTitle(String text) {
        strTitle= text ;
    }

    public void setStatus(String text) {
        strStatus= text ;
    }

    //get()
    public String getDate() {
        return this.strDate ;
    }

    public String getTitle() {
        return this.strTitle ;
    }

    public String getStatus() {
        return this.strStatus ;
    }
}

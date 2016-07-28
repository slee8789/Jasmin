package com.study.jasmin.jasmin.ui.item;
import android.graphics.drawable.Drawable;

/**
 * Created by swan on 2016-07-24.
 */
public class ListViewMember {

    private String strName;
    private Drawable dwGrade;

    //set()
    public void setName(String text){ strName = text; }

    public void setDwGrade(Drawable dw){
        dwGrade = dw;
    }

    //get()
    public String getName(){
        return this.strName;
    }

    public Drawable getDwGrade(){
        return this.dwGrade;
    }

}

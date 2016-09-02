package com.study.jasmin.jasmin.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.jasmin.jasmin.entity.Comment;
import com.study.jasmin.jasmin.entity.Member;
import com.study.jasmin.jasmin.entity.Post;
import com.study.jasmin.jasmin.entity.QnA;
import com.study.jasmin.jasmin.entity.Study;
import com.study.jasmin.jasmin.entity.User;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by swan on 2016-08-10.
 */
public class JasminPreference {
    private final String PREF_NAME = "com.study.jasmin.jasmin";
    public final static String PREF_INTRO_USER_AGREEMENT = "PREF_USER_AGREEMENT";
    public final static String PREF_MAIN_VALUE = "PREF_MAIN_VALUE";
    private static JasminPreference instance;
    private Gson gson                   = new GsonBuilder().create();
    private Object obj                  = new Object();
    private ArrayList <Object> list     = new ArrayList<Object>();

    private User[]      users;
    private QnA[]       qnas;
    private Study[]     studies;
    private Member[]    members;
    private Post[]      posts;
    private Comment[]   comments;

    private int selStudyNo;

    static Context mContext;

    public JasminPreference(Context context) {
        mContext = context;
    }

    public static JasminPreference getInstance(Context context) {
        if (instance == null) {
            synchronized (JasminPreference.class) {
                if (instance == null) {
                    instance = new JasminPreference(context);
                }
            }
        }
        return instance;
    }

    public void remove(String key) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.clear();
        editor.commit();
    }

    public void put(String key, String value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putJson(String key, String value) {

        switch (key){
            case "qnaList":
                qnas = gson.fromJson(value, QnA[].class);
                break;

            case "userInfo":
                users = gson.fromJson(value, User[].class);
                break;

            case "studyList":
                studies = gson.fromJson(value, Study[].class);
                break;

            case "memberList":
                members = gson.fromJson(value, Member[].class);
                break;

            case "postList":
                posts = gson.fromJson(value, Post[].class);
                break;

            case "commentList":
                comments = gson.fromJson(value, Comment[].class);
                break;

            case "groupInfo":
                break;
        }
    }


    public void put(String key, boolean value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, long value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public String getValue(String key, String dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getString(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public long getValue(String key, long dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        try {
            return pref.getLong(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }
    }

    public boolean getValue(String key, boolean dftValue) {
        SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

        try {
            return pref.getBoolean(key, dftValue);
        } catch (Exception e) {
            return dftValue;
        }

    }

    public void putJsonObject(String key, int index, String value) {
        switch (key) {
            case "studyInfo":
                studies[index] = gson.fromJson(value, Study.class);
                break;

            case "studyList":
                studies = gson.fromJson(value, Study[].class);
                break;
        }
    }
    public Object getObjectValue(String key){

        switch (key){
            case "userInfo":
                obj = users[0];
                break;
            case "studyInfo":
                break;
        }
        return obj;
    }

    public ArrayList<Object> getListValue(String key) {
        list.clear();

        switch (key) {
            case "userInfo":
                Collections.addAll(list, users);
                break;

            case "studyList":
                Collections.addAll(list, studies);
                break;

            case "memberList":
                Collections.addAll(list, members);
                break;

            case "postList":
                Collections.addAll(list, posts);
                break;

            case "commentList":
                Collections.addAll(list, comments);
                break;

            case "qnaList":
                Collections.addAll(list, qnas);
                break;
        }
        return list;
    }

    public void putSelStudyNo(int studyNo){
        this.selStudyNo = studyNo;
    }

    public int getSelStudyNo(){
        return this.selStudyNo;
    }




}





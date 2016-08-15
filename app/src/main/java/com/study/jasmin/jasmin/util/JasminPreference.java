package com.study.jasmin.jasmin.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.study.jasmin.jasmin.entity.QnA;
import com.study.jasmin.jasmin.entity.Study;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by swan on 2016-08-10.
 */
public class JasminPreference {
    private final String PREF_NAME = "com.study.jasmin.jasmin";
    public final static String PREF_INTRO_USER_AGREEMENT = "PREF_USER_AGREEMENT";
    public final static String PREF_MAIN_VALUE = "PREF_MAIN_VALUE";

    static Context mContext;

    public JasminPreference(Context context) {
        mContext = context;
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

    public Object getJSONValue(String key, String dftValue) {
        Gson gson = new GsonBuilder().create();
        ArrayList <Object> list = new ArrayList<Object>();
        switch (key) {
            case "qnaList":
                Log.d("","qnaList ");
                QnA[] qnas = gson.fromJson(this.getValue(key,dftValue), QnA[].class);
                Collections.addAll(list,qnas);
                return list;

            case "studyList":
                Study[] studies = gson.fromJson(this.getValue(key,dftValue), Study[].class);
                Collections.addAll(list,studies);
                return list;
        }
        try {


        } catch (Exception e) {
            return null;
        }
        return list;
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
}




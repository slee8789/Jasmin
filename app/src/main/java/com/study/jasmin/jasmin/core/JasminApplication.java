package com.study.jasmin.jasmin.core;

import android.app.Application;
import android.util.Log;

/**
 * Created by leesc on 2016-07-30.
 */
public class JasminApplication extends Application {
    public static final String TAG = "JasminApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG,"onTerminate");
    }

}

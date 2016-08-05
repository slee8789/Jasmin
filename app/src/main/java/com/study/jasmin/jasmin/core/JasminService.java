package com.study.jasmin.jasmin.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.study.jasmin.jasmin.observer.Observer;

public class JasminService extends Service implements Observer{
    public static final String TAG = "JasminService";
    private String serverIp = "127.0.0.1"; // test
    private String serverPort = "8080"; // test

    public JasminService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}

package com.study.jasmin.jasmin.http;

import android.os.AsyncTask;
import android.util.Log;

import com.study.jasmin.jasmin.util.JasminUtil;

import java.io.File;

/**
 * Created by leesc on 2016-08-05.
 */
public class JasminGetDataTask extends AsyncTask<String,Void,String> {
    public static final String TAG = "JasminGetDataTask";

    private String url;
    private String[] valueParams;
    private String[] keyParams;  // json key로 사용할 변수
    private String[]  keyFileParams;
    private File[]  valueFileParams;
    private static JasminGetDataTask instance;
    MPHttpRequester mphttpRequester;

    public JasminGetDataTask() {

    }

    public void setExecute() {
//        System.arraycopy(keyFileParams,0,keyParams,0,keyFileParams.length);
//        Log.d(TAG,"array size : " + keyParams.length);
        this.execute(keyParams);
    }

    /** Returns singleton class instance */
    public static JasminGetDataTask getInstance() {
        if (instance == null) {
            synchronized (JasminGetDataTask.class) {
                if (instance == null) {
                    instance = new JasminGetDataTask();
                }
            }
        }
        return instance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setValueParams(String ... params) {
        this.valueParams = params;
    }

    public void setKeyFileParams(String... params) {
        this.keyFileParams = params;
    }

    public void setValueFileParams(File... params) {
        this.valueFileParams = params;
    }

    public void setKeyParams(String ... params) {
        this.keyParams = params;
    }


    public String getUrl() {
        return url;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
//        Log.d(TAG,"key : " + keyParams[i] + ", value : " + valueParams[i]);
//          mphttpRequester = new MPHttpRequester("http://54.201.72.195:8081/test/" + url);
        mphttpRequester = new MPHttpRequester("http://54.201.72.195:8081/test/api/" + url);
        for( int i=0; i < params.length; i++ ) {
            if(JasminUtil.isStringInt(valueParams[i])) {
                mphttpRequester.addParameter(keyParams[i],Integer.parseInt(valueParams[i]));
            } else {
                mphttpRequester.addParameter(keyParams[i],valueParams[i]);
            }
        }

        for( int i=0; i < keyFileParams.length; i++) {
            mphttpRequester.addFile(keyFileParams[i],valueFileParams[i]);
        }

        String result = mphttpRequester.sendMultipartPost();
        Log.d(TAG,"sendMultipartPost result : " + result);
        return result;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        try {
            Log.d(TAG, "Status Code = " + string);

        } catch (Exception e) {
            Log.d(TAG,"e : " + e);
        }

    }
}

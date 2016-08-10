package com.study.jasmin.jasmin.core;

import android.os.AsyncTask;
import android.util.Log;
import android.webkit.CookieManager;

import com.study.jasmin.jasmin.http.HttpRequester;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leesc on 2016-08-05.
 */
public class JasminGetDataTask extends AsyncTask<String,Void,String> {
    public static final String TAG = "JasminGetDataTask";

    private String url;
    private String[] valueParams;
    private String[] keyParams;  // json key로 사용할 변수

    private static JasminGetDataTask instance;

    public JasminGetDataTask() {
        CookieManager cookieManager = CookieManager.getInstance();
    }

    public void setExecute() {
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
        HttpRequester httpRequester = new HttpRequester("http://192.168.0.2:8080/jasmin/" + url);
        for( int i=0; i < params.length; i++ ) httpRequester.addParameter(keyParams[i],valueParams[i]);
        String result = httpRequester.sendPost();
        Log.d(TAG,"sendPost result : " + result);
        return result;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        try {
            JSONObject jsObject = new JSONObject(string);
            Object result = jsObject.get("result");

            Log.d(TAG,"result : " + result.toString());
//            JSONArray jsArray = jsObject.getJSONArray("result");
            /*for (int i = 0; i < jsArray.length(); i++) {
                JSONObject jobj = jsArray.getJSONObject(i);
                String mail = jobj.getString("mail");
                String pw = jobj.getString("pw");
                String name = jobj.getString("name");
                String sex = jobj.getString("sex");
                Log.d(TAG,"test mail : " + mail);
            }*/
        } catch (JSONException e) {
            Log.d(TAG,"e : " + e);
        }

    }
}

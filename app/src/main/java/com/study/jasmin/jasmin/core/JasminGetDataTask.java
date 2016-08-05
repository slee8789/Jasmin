package com.study.jasmin.jasmin.core;

import android.os.AsyncTask;
import android.util.Log;

import com.study.jasmin.jasmin.http.HttpRequester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leesc on 2016-08-05.
 */
public class JasminGetDataTask extends AsyncTask<String,Void,String> {
    public static final String TAG = "JasminGetDataTask";

    private String url;
    private String[] params;

    private static JasminGetDataTask instance;

    public JasminGetDataTask() {
    }

    public JasminGetDataTask(String url, String ... params) {
        this.url = url;
        this.params = params;
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

    public static JasminGetDataTask getInstance(String url, String ... params) {

        if (instance == null) {
            synchronized (JasminGetDataTask.class) {
                if (instance == null) {
                    instance = new JasminGetDataTask(url, params);
                }
            }
        }
        return instance;

    }

    public String getUrl() {
        return url;
    }

    public String[] getParams() {
        return params;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HttpRequester httpRequester = new HttpRequester("http://172.30.103.155:8080/jasmin/" + url);
        httpRequester.addParameter("mail", params[0]);
        httpRequester.addParameter("pw", params[1]);
        httpRequester.addParameter("name", params[2]);
        httpRequester.addParameter("sex", params[3]);
        String result = httpRequester.sendPost();

        return result;
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);

        try {
            JSONObject jsObject = new JSONObject(string);
            jsObject.get("result");
            JSONArray jsArray = jsObject.getJSONArray("result");
//            Log.d(TAG,"result : " + jsArray);
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

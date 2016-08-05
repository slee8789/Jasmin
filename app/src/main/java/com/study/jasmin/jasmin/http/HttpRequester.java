package com.study.jasmin.jasmin.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HttpRequester {
    private String url;
    private HashMap<String,String> params = new HashMap<>();

    public HttpRequester(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addParameter(String key,String value){
        params.put(key,value);
    }
    public void addParameters(Map<String,String> parameters){
        for (String key : parameters.keySet() ) {
            params.put(key,parameters.get(key));
        }
    }
    public void deleteParameter(String key){
        params.remove(key);
    }
    public void deleteAllParameter(){
        params.clear();
    }

    public String sendPost() {
        HttpURLConnection conn = null;
        String resultMsg = null;
        try {
            URL url = new URL(this.url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.setDefaultUseCaches(false);
            conn.setDoInput(true);                         // 서버에서 읽기 모드 지정
            conn.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            conn.setRequestMethod("POST");
            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded; charset=utf-8");

            StringBuffer sb = new StringBuffer(); //StringBuilder 멀티스레드 미지원, 빠르다 //StringBuffer 는 멀티스레드 안전 대신 좀 느리다
            Set key = params.keySet();
            for (Iterator iterator = key.iterator(); iterator.hasNext();) {
                String keyName = (String)iterator.next();
                String valueName = params.get(keyName);
                sb.append(URLEncoder.encode(keyName,"UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(valueName,"UTF-8"));
                if (iterator.hasNext()) {
                    sb.append("&");
                }
            }

            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");//"EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(sb.toString());
            writer.flush();
            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder builder = new StringBuilder();
                InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                }
                isr.close();
                br.close();
                resultMsg = builder.toString();
            } else {
                resultMsg = "Response Code : "+responseCode;
            }
        } catch (MalformedURLException e) {
            resultMsg = "URLExcept";
        } catch (IOException e) {
            resultMsg = "IOExcept";
        } catch (Exception e){
            resultMsg = "Except";
        } finally {
            if(conn!=null) conn.disconnect();
        }
        return resultMsg;
    }
}

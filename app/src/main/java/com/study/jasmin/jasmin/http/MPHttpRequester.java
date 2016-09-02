package com.study.jasmin.jasmin.http;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MPHttpRequester {
    private String url;
    private HashMap<String,Object> params = new HashMap<>();

    public MPHttpRequester(String url){
        this.url = url;
    }

    public void addParameter(String key, String value){
        if(key==null){
            Log.e("MPHttpRequester","null key added");
        } else if(value==null){
            Log.e("MPHttpRequester","value '"+key+"' is null");
        } else {
            params.put(key,value);
        }
    }

    public void addParameter(String key, int value){
        if(key==null){
            Log.e("MPHttpRequester","null key added");
        } else if(value== 0){
            Log.e("MPHttpRequester","value '"+key+"' is 0");
        } else {
            params.put(key,value);
        }
    }
    public void addFile(String key, File value) {
        if(key==null){
            Log.e("MPHttpRequester","null key added");
        } else if(value==null){
            params.put(key,new NullFile());
        } else {
            params.put(key,value);
        }
    }


    public String sendMultipartPost() {
        HttpURLConnection conn = null;
        String resultMsg = null;
        try {
            URL url = new URL(this.url);
            conn = (HttpURLConnection) url.openConnection();

            String delimeter = "---------------------------7d115d2a20060c";
            byte[] newLineBytes = "\r\n".getBytes();
            byte[] delimeterBytes = delimeter.getBytes();
            byte[] dispositionBytes = "Content-Disposition: form-data; name=".getBytes();
            byte[] quotationBytes = "\"".getBytes();
            byte[] contentTypeBytes = "Content-Type: application/octet-stream".getBytes();
            byte[] fileNameBytes = "; filename=".getBytes();
            byte[] twoDashBytes = "--".getBytes();

            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.setDefaultUseCaches(false);
            conn.setDoInput(true);                         // 서버에서 읽기 모드 지정
            conn.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + delimeter);

            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(conn.getOutputStream());

                Set keySet = params.keySet();
                for(Iterator iterator = keySet.iterator(); iterator.hasNext();){
                    String key = (String)iterator.next();
                    out.write(twoDashBytes);
                    out.write(delimeterBytes);
                    out.write(newLineBytes);
                    out.write(dispositionBytes);
                    out.write(quotationBytes);
                    out.write(key.getBytes());
                    out.write(quotationBytes);

                    Object value = params.get(key);
                    if(value instanceof String){
                        Log.e("string",key + " / " + (String)value);
                        out.write(newLineBytes);
                        out.write(newLineBytes);
                        out.write(((String)value).getBytes());
                        out.write(newLineBytes);
                    } else if(value instanceof Integer){
                        Log.e("Integer",key + " / " + value);
                        int intValue = (int) value;
                        out.write(newLineBytes);
                        out.write(newLineBytes);
//                        out.write(ByteUtils.toBytes(intValue));
//                        out.write(JasminUtil.intToBytes(intValue,1));
                        out.write(Integer.toString(intValue).getBytes());
                        out.write(newLineBytes);
                    } else {
                        if (value instanceof File) {
                            Log.e("file", key + " / " + "file");
                            File file = (File) value;
                            out.write(fileNameBytes);
                            out.write(quotationBytes);
                            out.write(file.getAbsolutePath().getBytes());
                            out.write(quotationBytes);
                        } else {
                            Log.e("file",key + " / " + "nullfile");
                            out.write(fileNameBytes);
                            out.write(quotationBytes);
                            out.write(quotationBytes);
                        }
                        out.write(newLineBytes);
                        out.write(contentTypeBytes);
                        out.write(newLineBytes);
                        out.write(newLineBytes);

                        if (value instanceof File) {
                            File file = (File) value;
                            BufferedInputStream is = null;
                            try {
                                is = new BufferedInputStream(new FileInputStream(file));
                                byte[] fileBuffer = new byte[1024 * 8]; // 8k
                                int len = -1;
                                while ((len = is.read(fileBuffer)) != -1) {
                                    out.write(fileBuffer, 0, len);
                                }
                            } finally {
                                if (is != null){
                                    try {
                                        is.close();
                                    } catch (IOException ex) {}
                                }
                            }
                        }
                        out.write(newLineBytes);
                    }
                    if (!iterator.hasNext()) {
                        out.write(twoDashBytes);
                        out.write(delimeterBytes);
                        out.write(twoDashBytes);
                        out.write(newLineBytes);
                    }
                }
                out.flush();
            } finally {
                if (out != null)
                    out.close();
            }
            int responseCode = conn.getResponseCode();
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
            resultMsg = "Except " + e;
        } finally {
            if(conn!=null) conn.disconnect();
        }
        return resultMsg;
    }
    class NullFile {
        @Override
        public String toString() {
            return "";
        }
    }
}

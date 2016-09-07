package com.study.jasmin.jasmin.ui.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Study;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.activity.GroupMainActivity;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolderInfoGroupList extends RecyclerView.ViewHolder implements View.OnClickListener, Callback,View.OnTouchListener{
    public static final String TAG = "HolderInfoGroupList";
    public TextView groupName;
//    public ImageView groupPhoto;
    public WebView groupPhoto;
    private Context context;
    private ArrayList<Object> studyList;
    //    private ProgressDialog Progress;
    //swan add
    private int selStudyNo;
    private static HolderInfoGroupList instance;

    public static HolderInfoGroupList getInstance(View view) {
        if (instance == null) {
            synchronized (HolderInfoGroupList.class) {
                if (instance == null) {
                    instance = new HolderInfoGroupList(view);
                }
            }
        }
        return instance;
    }
    public HolderInfoGroupList(View itemView) {
        super(itemView);
        itemView.getClass();
        itemView.setOnClickListener(this);
        context = itemView.getContext();
//        Progress = ProgressDialog.getInstance(context);
        groupName = (TextView)itemView.findViewById(R.id.group_name);
        groupPhoto = (WebView) itemView.findViewById(R.id.group_photo);
        groupPhoto.setOnTouchListener(this);
        groupPhoto.setWebViewClient(new WebViewClientClass());
        groupPhoto.setHorizontalScrollBarEnabled(false); //가로 스크롤
        groupPhoto.setVerticalScrollBarEnabled(false);   //세로 스크롤
        groupPhoto.setScrollContainer(false);
//        mWebView.loadUrl("http://54.201.72.195:8081/examples/study27/cover/testImage.jpg");
//        groupPhoto = (ImageView)itemView.findViewById(R.id.group_photo);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.group_photo && event.getAction() == MotionEvent.ACTION_UP) {
            goToStudy();
            return false;
        } else {
            return (event.getAction() == MotionEvent.ACTION_MOVE);
        }
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void goToStudy() {
        RestClient.RestService service = RestClient.getClient();
        studyList = JasminPreference.getInstance(context).getListValue("studyList");
        Call<JsonObject> call = service.gotoStudy(((Study)studyList.get(getAdapterPosition())).getStudy_no());
        call.enqueue(this);
    }

    @Override
    public void onClick(View view) {
//        Toast.makeText(view.getContext(), "Clicked group Position = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//        Progress.show(); // BadTokenException 에러
        goToStudy();
    }

    @Override
    public void onResponse(Call call, Response response) {
//        Log.d(TAG, "Retro Status Code = " + response.code());
        try {
            String strTest = response.body().toString();

            JSONObject jsObject = new JSONObject(strTest);
            JSONArray studyArr = jsObject.getJSONArray("studyInfo");
            JSONArray memberObj = jsObject.getJSONArray("memberList");
            JSONObject studyObj = studyArr.getJSONObject(0);
            JasminPreference.getInstance(context).putJsonObject("studyInfo", getAdapterPosition(), studyObj.toString());
            JasminPreference.getInstance(context).putJson("memberList",memberObj.toString());
            selStudyNo = ((Study)studyList.get(getAdapterPosition())).getStudy_no();

            //swan add
            if(studyObj != null) {
                JasminPreference.getInstance(context).putSelStudyNo(selStudyNo);
            }else{
                Log.d(TAG,"studyObj is empty!!!");
            }

            Intent intent = new Intent(context, GroupMainActivity.class);
            context.startActivity(intent);

//            Progress.cancel();
//            Progress.dismiss();

        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ResponseFail = " + call);
    }
}
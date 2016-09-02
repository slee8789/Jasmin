package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Post;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.TwoButtonDialog;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupNoticeDetailActivity extends AppCompatActivity implements View.OnClickListener, Callback {
    public static final String TAG = "GNDA";
    private TextView noticeTitle;
    private TextView noticeWriter;
    private TextView noticeDate;
    private TextView noticeViews;
    private TextView noticeContent;
    private Post post;
    private TwoButtonDialog twoButtonDialog;
    private JasminPreference mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_notice_detail);
        init();
        findViews();
        initViews();
    }

    private void init() {
        mPref = JasminPreference.getInstance(this);
        post = getIntent().getParcelableExtra("post");
        twoButtonDialog = new TwoButtonDialog(this);

    }

    private void findViews() {
        noticeTitle = (TextView) findViewById(R.id.detail_title);
        noticeWriter = (TextView) findViewById(R.id.detail_writer);
        noticeDate = (TextView) findViewById(R.id.detail_date);
        noticeViews = (TextView) findViewById(R.id.detail_views);
        noticeContent = (TextView) findViewById(R.id.detail_content);
    }

    private void initViews() {
        noticeTitle.setText(post.getPost_title());
        noticeWriter.setText(post.getUser_name());
        noticeDate.setText(post.getPost_date());
        noticeViews.setText(Integer.toString(post.getHits()));
        noticeContent.setText(post.getPost_content());
    }

    public void showTwoButtonDialog(int title, String comment) {
        twoButtonDialog = new TwoButtonDialog(this);
        twoButtonDialog.setOkOnClickListener(this);
        twoButtonDialog.setCancelOnClickListener(this);
        twoButtonDialog.show();
        twoButtonDialog.setTitle(title);
        twoButtonDialog.setComment(comment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        post.getUser_no();
        int userNo = ((User) mPref.getObjectValue("userInfo")).getUser_no();

        getMenuInflater().inflate((userNo == post.getUser_no()) ? R.menu.mydetail : R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_modify:
                Log.d(TAG, "click btn_modify");
                return true;

            case R.id.btn_delete:
                Log.d(TAG, "click btn_delete");
                showTwoButtonDialog(R.string.menu_delete, "삭제하시겠습니까?");
                return true;

            case R.id.btn_reply:
                Log.d(TAG, "click btn_reply");
                getComment(post.getPost_no());

                return true;

            case R.id.btn_favorite:
                Log.d(TAG, "click btn_favorite");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getComment(int postNo) {
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.commentList(postNo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "Retro Status Code = " + response.code());
                String strResponse = response.body().toString();
                try {
                    JSONObject jsObject = new JSONObject(strResponse);
                    JSONArray commentObj = jsObject.getJSONArray("result");
                    mPref.putJson("commentList",commentObj.toString());

                    Intent intent = new Intent(getApplicationContext(), GroupReplyActivity.class);
                    intent.putExtra("post",post);
                    startActivity(intent);
                } catch (JSONException e) {
                    Log.d(TAG, "e : " + e);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "ResponseFail = " + call);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ok_twobutton:
                Log.d(TAG, "click ok_twobutton");
                RestClient.RestService service = RestClient.getClient();
                Call<JsonObject> call = service.postDelete(post.getPost_no());
                call.enqueue(this);
                break;

            case R.id.cancel_twobutton:
                Log.d(TAG, "click cancel_twobutton");
                twoButtonDialog.cancel();
                twoButtonDialog.dismiss();
                break;
        }
    }

    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG,"Retro Status Code = " + response.code());
        try {
            String strResponse = response.body().toString();
            JSONObject jsObject = new JSONObject(strResponse);
            if (jsObject.getInt("result") == 1) {
                RestClient.RestService service = RestClient.getClient();
                call = service.postList(mPref.getSelStudyNo());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.d(TAG, "postList Code = " + response.code());

                        try {
                            String strResponse = response.body().toString();
                            JSONObject jsObject = new JSONObject(strResponse);
                            JSONArray postObj = jsObject.getJSONArray("postList");
                            mPref.putJson("postList", postObj.toString());
                            finish();
                            twoButtonDialog.cancel();
                            twoButtonDialog.dismiss();
                        } catch (JSONException e) {
                            Log.d(TAG, "e : " + e);
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        } catch (JSONException e) {
            Log.d(TAG, "e : " + e);
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Log.d(TAG, "ResponseFail = " + call);
    }
}

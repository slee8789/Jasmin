package com.study.jasmin.jasmin.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Comment;
import com.study.jasmin.jasmin.entity.Post;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.rest.RestClient;
import com.study.jasmin.jasmin.ui.dialog.CommentUpdateDialog;
import com.study.jasmin.jasmin.ui.list.AdaptInfoReplyList;
import com.study.jasmin.jasmin.util.JasminPreference;
import com.study.jasmin.jasmin.util.JasminUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupReplyActivity extends AppCompatActivity implements View.OnClickListener, AdaptInfoReplyList.onButtonClickListener {
    public static final String TAG = "GroupReplyActivity";

    private ListView replyList;
    private ArrayList<Object> commentList;
    private JasminPreference mPref;
    private AdaptInfoReplyList adaptListInfo;
    private TextView replyTitle;
    private Post post;
    private Button btnEnroll;
    private EditText inputReply;
    private CommentUpdateDialog commentUpdateDialog;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_notice_reply);

        init();
        findViews();
        initViews();
        initWithViews();

    }

    public void showCommentUpdateDialog(String comment) {
        commentUpdateDialog = new CommentUpdateDialog(this);
        commentUpdateDialog.setOkOnClickListener(this);
        commentUpdateDialog.setCancelOnClickListener(this);
        commentUpdateDialog.show();
        commentUpdateDialog.setComment(comment);
    }

    private void init() {
        post = getIntent().getParcelableExtra("post");
        mPref = JasminPreference.getInstance(this);
        commentList = mPref.getListValue("commentList");

    }

    private void findViews() {
        replyList = (ListView) findViewById(R.id.list_reply);
        replyTitle = (TextView) findViewById(R.id.reply_title);
        inputReply = (EditText) findViewById(R.id.reply_input);
        btnEnroll = (Button) findViewById(R.id.reply_enroll);
//        replyList.findViewById(R.id.)
    }

    private void initViews() {
        replyTitle.setText(post.getPost_title());
        btnEnroll.setOnClickListener(this);
    }

    private void initWithViews() {

        adaptListInfo = new AdaptInfoReplyList(this, R.layout.list_notice_info, commentList);
        adaptListInfo.setOnButtonClickListener(this);
        adaptListInfo.setArraySelectInfo(commentList);
        replyList.setAdapter(adaptListInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.exit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_exit:
                Log.d(TAG, "click btn_exit");
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reply_enroll:
                Log.d(TAG, "reply_enroll clicked");
                addReply();
                break;

            case R.id.ok_twobutton:
                Log.d(TAG, "ok_twobutton clicked");
                updateContent(selectedPosition);
                break;

            case R.id.cancel_twobutton:
                Log.d(TAG, "cancel_twobutton clicked");
                commentUpdateDialog.cancel();
                commentUpdateDialog.dismiss();
                break;
        }
    }

    private void addReply() {
        int userNo = ((User) mPref.getObjectValue("userInfo")).getUser_no();
        int postNo = post.getPost_no();
        String comment = inputReply.getText().toString();

        if (comment.isEmpty()) {
            Toast.makeText(this, "메시지를 입력 후 전송해주세요.", Toast.LENGTH_SHORT).show();
        } else {

            RestClient.RestService service = RestClient.getClient();
            Call<JsonObject> call = service.commentInsert(userNo, postNo, comment);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call call, Response response) {
                    Log.d(TAG, "Retro Status Code = " + response.code());
                    try {
                        String strResponse = response.body().toString();
                        JSONObject jsObject = new JSONObject(strResponse);
                        if (jsObject.getInt("result") == 1) {
                            Toast.makeText(getApplicationContext(), "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();

                            JasminUtil.hideSoftKeyboard(getApplicationContext(), inputReply);

                            inputReply.setText("");

                            RestClient.RestService service = RestClient.getClient();
                            call = service.commentList(post.getPost_no());
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Log.d(TAG, "commentList Code = " + response.code());

                                    try {
                                        String strResponse = response.body().toString();
                                        JSONObject jsObject = new JSONObject(strResponse);
                                        JSONArray commentObj = jsObject.getJSONArray("result");
                                        mPref.putJson("commentList", commentObj.toString());
                                        if (jsObject.getJSONArray("result") != null) {
                                            commentList = mPref.getListValue("commentList");
                                            adaptListInfo.notifyDataSetChanged();

                                        }
                                    } catch (JSONException e) {
                                        Log.d(TAG, "e : " + e);
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {

                                }
                            });
                        } else {
                            Log.d(TAG, "response Error");
                        }
                    } catch (JSONException e) {
                        Log.d(TAG, "e : " + e);
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d(TAG, "ResponseFail = " + call);
                }
            });
        }
    }

    private void updateContent(int position) {
        JasminUtil.hideSoftKeyboard(getApplicationContext(), inputReply);
        JasminUtil.hideSoftKeyboard(getApplicationContext(), commentUpdateDialog.getComment());
        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.commentUpdate(((Comment) commentList.get(position)).getComment_no(),commentUpdateDialog.getComment().getText().toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "Retro Status Code = " + response.code());
                try {
                    String strResponse = response.body().toString();
                    JSONObject jsObject = new JSONObject(strResponse);
                    if (jsObject.getInt("result") == 1) {

                        RestClient.RestService service = RestClient.getClient();
                        call = service.commentList(post.getPost_no());
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Log.d(TAG, "commentList Code = " + response.code());

                                try {
                                    String strResponse = response.body().toString();
                                    JSONObject jsObject = new JSONObject(strResponse);
                                    JSONArray commentObj = jsObject.getJSONArray("result");
                                    mPref.putJson("commentList", commentObj.toString());
                                    if (jsObject.getJSONArray("result") != null) {
                                        commentList = mPref.getListValue("commentList");
                                        adaptListInfo.notifyDataSetChanged();
                                        commentUpdateDialog.cancel();
                                        commentUpdateDialog.dismiss();

                                    }
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
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "ResponseFail = " + call);
            }
        });
    }

    @Override
    public void onBtnModify(int position, String content) {
        selectedPosition = position;
        Log.d(TAG, "onBtnModify clicked : " + position);
        showCommentUpdateDialog(content);

    }

    @Override
    public void onBtnDelete(int position) {
        Log.d(TAG, "onBtnDelete clicked : " + position);

        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.commentDelete(((Comment) commentList.get(position)).getComment_no());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "Retro Status Code = " + response.code());
                try {
                    String strResponse = response.body().toString();
                    JSONObject jsObject = new JSONObject(strResponse);
                    if (jsObject.getInt("result") == 1) {
                        RestClient.RestService service = RestClient.getClient();
                        call = service.commentList(post.getPost_no());
                        call.enqueue(new Callback<JsonObject>() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Log.d(TAG, "commentList Code = " + response.code());

                                try {
                                    String strResponse = response.body().toString();
                                    JSONObject jsObject = new JSONObject(strResponse);
                                    JSONArray commentObj = jsObject.getJSONArray("result");
                                    mPref.putJson("commentList", commentObj.toString());
                                    if (jsObject.getJSONArray("result") != null) {
                                        commentList = mPref.getListValue("commentList");
                                        adaptListInfo.notifyDataSetChanged();

                                    }
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
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d(TAG, "ResponseFail = " + call);
            }
        });
    }

}

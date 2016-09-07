package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Post;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.http.JasminGetDataTask;
import com.study.jasmin.jasmin.util.FileUtils;
import com.study.jasmin.jasmin.util.JasminPreference;
import com.study.jasmin.jasmin.util.JasminProtocol;

import java.io.File;

public class GroupNoticeUpdateActivity extends AppCompatActivity implements View.OnClickListener, JasminGetDataTask.OnResponseListener{
    public static final String TAG = "GNUA";
    private TextView noticeTitle;
    private TextView noticeWriter;
    private TextView noticeDate;
    private TextView noticeViews;
    private EditText noticeContent;
    private JasminPreference mPref;
    private Uri test;
    private Post post;
    private JasminGetDataTask mTask;
    private RelativeLayout fileList;
    private TextView fileName;
    private Button fileDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_notice_update);
        init();
        findViews();
        initViews();
    }

    private void init() {
        mPref = JasminPreference.getInstance(this);
        post = getIntent().getParcelableExtra("post");
    }

    private void findViews() {
        fileList = (RelativeLayout) findViewById(R.id.file_list);
        fileName = (TextView) findViewById(R.id.file_name);
        fileDelete = (Button) findViewById(R.id.file_delete);
        noticeTitle = (TextView) findViewById(R.id.update_title);
        noticeWriter = (TextView) findViewById(R.id.update_writer);
        noticeDate = (TextView) findViewById(R.id.update_date);
        noticeViews = (TextView) findViewById(R.id.update_views);
        noticeContent = (EditText) findViewById(R.id.update_content);
    }

    private void initViews() {
        noticeTitle.setText(post.getPost_title());
        noticeWriter.setText(post.getUser_name());
        noticeDate.setText(post.getPost_date());
        noticeViews.setText(Integer.toString(post.getHits()));
        noticeContent.setHint(post.getPost_content());
        if(!post.getPost_file().isEmpty())  {
            fileList.setVisibility(View.VISIBLE);
            fileName.setText(post.getPost_file());
        }
        fileDelete.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void uploadFile(Uri fileUri) {
        String file = FileUtils.getPath(this, fileUri);
        File test = new File(file);
        mTask = JasminGetDataTask.getInstance();
        mTask.setOnResponseListener(this);
        mTask.setUrl("postInsert");
        mTask.setKeyParams("userNo","studyNo","postTitle","postContent","postType");
        mTask.setValueParams(
                Integer.toString((((User) mPref.getObjectValue("userInfo")).getUser_no())),
                Integer.toString(mPref.getSelStudyNo()),
                noticeTitle.getText().toString(),
                noticeContent.getText().toString(),
                Integer.toString(1)
                );
        mTask.setKeyFileParams("postFile");

        mTask.setValueFileParams(test);
        mTask.setExecute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_ok:
                Log.d(TAG, "click btn_ok");
                uploadFile(test);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openFolder()
    {
        File file = new File(Environment.getExternalStorageDirectory(), "myFolder");

        Log.d("path", file.toString());

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(Uri.fromFile(file), "*/*");
        startActivityForResult(intent, JasminProtocol.REQUEST_CODE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == JasminProtocol.REQUEST_CODE_FILE && resultCode == RESULT_OK) {
            test =  data.getData();// 우선 한개의 파일만 올리는거로 테스트
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.file_delete:
                Log.d(TAG,"clicked file_delete");
                break;
        }
    }

    @Override
    public void onResponse(String result) {
        Log.d(TAG,"result : " + result);
        mTask.cancel(true);
        finish();
    }
}

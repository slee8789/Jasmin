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
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.http.JasminGetDataTask;
import com.study.jasmin.jasmin.util.FileUtils;
import com.study.jasmin.jasmin.util.JasminPreference;
import com.study.jasmin.jasmin.util.JasminProtocol;

import java.io.File;

public class GroupNoticeAddActivity extends AppCompatActivity implements View.OnClickListener, JasminGetDataTask.OnResponseListener {
    public static final String TAG = "GNAA";
    private EditText title;
    private EditText content;
    private JasminPreference mPref;
    private RelativeLayout fileList;
    private TextView fileName;
    private Button fileDelete;
    private File file;
    private JasminGetDataTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_notice_add);
        init();
        findViews();
        initViews();
    }

    private void init() {
        mPref = JasminPreference.getInstance(this);
    }

    private void findViews() {
        fileList = (RelativeLayout) findViewById(R.id.file_list);
        fileName = (TextView) findViewById(R.id.file_name);
        fileDelete = (Button) findViewById(R.id.file_delete);
        title = (EditText) findViewById(R.id.add_title);
        content = (EditText) findViewById(R.id.add_content);
    }

    private void initViews() {
        fileDelete.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void uploadFile(File file) {
        mTask = new JasminGetDataTask();
        mTask.setOnResponseListener(this);
        mTask.setUrl("postInsert");
        mTask.setKeyParams("userNo", "studyNo", "postTitle", "postContent", "postType");
        mTask.setValueParams(
                Integer.toString((((User) mPref.getObjectValue("userInfo")).getUser_no())),
                Integer.toString(mPref.getSelStudyNo()),
                title.getText().toString(),
                content.getText().toString(),
                Integer.toString(1)
        );
        mTask.setKeyFileParams("postFile");
        if (file == null) {
            mTask.setValueFileParams(null);
        } else {
            mTask.setValueFileParams(file);
        }

        mTask.setExecute();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_ok:
                Log.d(TAG, "click btn_ok");
                uploadFile(file);
                break;

            case R.id.btn_file:
                Log.d(TAG, "click btn_file");
                openFolder();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void openFolder() {
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
            Uri fileUri = data.getData();// 우선 한개의 파일만 올리는거로 테스트
            if (fileUri != null) {
                fileList.setVisibility(View.VISIBLE);
                String mFile = FileUtils.getPath(this, fileUri);
                file = new File(mFile);
                fileName.setText(file.getName());
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.file_delete:
                Log.d(TAG, "file_delete");
                fileList.setVisibility(View.GONE);
                file = null;
                break;
        }
    }

    @Override
    public void onResponse(String result) {
        Log.d(TAG, "leesc test result : " + result);
        mTask.cancel(true);
        finish();
    }
}

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

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.http.JasminGetDataTask;
import com.study.jasmin.jasmin.util.FileUtils;
import com.study.jasmin.jasmin.util.JasminPreference;
import com.study.jasmin.jasmin.util.JasminProtocol;

import java.io.File;

public class GroupNoticeAddActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "GNAA";
    private EditText title;
    private EditText content;
    private Button testButton;
    private JasminPreference mPref;
    private Uri test;
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
        title = (EditText) findViewById(R.id.add_title);
        content = (EditText) findViewById(R.id.add_content);
        testButton = (Button) findViewById(R.id.add_file);
    }

    private void initViews() {
        testButton.setOnClickListener(this);
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
        mTask.setUrl("postInsert");
        mTask.setKeyParams("userNo","studyNo","postTitle","postContent","postType");
        mTask.setValueParams(
                Integer.toString((((User) mPref.getObjectValue("userInfo")).getUser_no())),
                Integer.toString(mPref.getSelStudyNo()),
                title.getText().toString(),
                content.getText().toString(),
                Integer.toString(1)
                );
        mTask.setKeyFileParams("postFile");

        mTask.setValueFileParams(test);
        mTask.setExecute();

        /*RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), descriptionString);

        RestClient.RestService service = RestClient.getClient();
        Call<JsonObject> call = service.postInsert(
                ((User) mPref.getObjectValue("userInfo")).getUser_no(),
                mPref.getSelStudyNo(),
                title.getText().toString(),
                content.getText().toString(),
                1,
                body
                );
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {
                Log.d(TAG, "Retro Status Code = " + response.code());
//                Log.d("Upload", "success");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
            }
        });*/
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
        File file = new File(Environment.getExternalStorageDirectory(),
                "myFolder");

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
            case R.id.add_file:
                openFolder();


                break;


        }
    }
}

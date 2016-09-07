package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.User;
import com.study.jasmin.jasmin.http.JasminGetDataTask;
import com.study.jasmin.jasmin.util.JasminPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class GroupAddActivity extends AppCompatActivity implements View.OnClickListener, JasminGetDataTask.OnResponseListener{
    private static final String TAG = "GroupAddActivity";

    private Button btn_complete;
    private EditText groupName;
    private EditText groupDescription;
    private ImageView groupCover;
    private File      image;
    private String    imgPath;
    private JasminPreference mPref;
    private JasminGetDataTask mTask;
    private int userNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_add);
        init();
        findViews();
        initViews();
    }

    private void init() {
        mPref = JasminPreference.getInstance(this);
        userNo = ((User) mPref.getObjectValue("userInfo")).getUser_no();
    }

    private void findViews() {

        btn_complete = (Button) findViewById(R.id.btn_complete);
        groupName = (EditText)findViewById(R.id.add_name);
        groupDescription = (EditText)findViewById(R.id.add_introduce);
        groupCover = (ImageView) findViewById(R.id.group_cover);

    }

    private void initViews() {
        btn_complete.setOnClickListener(this);
        groupCover.setOnClickListener(this);
    }

    private void uploadFile(File file) {
        mTask = new JasminGetDataTask();
        mTask.setOnResponseListener(this);
        mTask.setUrl("addStudy");
        mTask.setKeyParams("userNo","studyName","studyDes");
        mTask.setValueParams(Integer.toString(userNo),groupName.getText().toString(),groupDescription.getText().toString());
        mTask.setKeyFileParams("studyImage");
        if(file == null) {
            mTask.setValueFileParams(null);
        }
        else {
            mTask.setValueFileParams(file);
        }

        mTask.setExecute();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_complete:
                Log.d(TAG,"btn_complete clicked");
                uploadFile(image);
                break;

            case R.id.group_cover:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 0) { //갤러리
                if (data != null && data.getData() != null) {
                    Cursor cursor = getContentResolver().query(data.getData(), new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                    if (cursor.moveToFirst()) {
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        imgPath = cursor.getString(column_index);
                    }
                    cursor.close();
                    image = new File(imgPath);
                    if (image.exists()) {
                        int degree = 0;
                        ExifInterface exif = null;
                        try {
                            exif = new ExifInterface(imgPath);
                        } catch (IOException e) {
                            Log.e("TAG", "cannot read exif");
                            e.printStackTrace();
                        }
                        if (exif != null) {
                            int orientation = exif.getAttributeInt(
                                    ExifInterface.TAG_ORIENTATION, -1);
                            if (orientation != -1) {
                                // We only recognize a subset of orientation tag values.
                                switch (orientation) {
                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        degree = 90;
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        degree = 180;
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        degree = 270;
                                        break;
                                }
                            }
                        }
                        Bitmap currentPic = BitmapFactory.decodeFile(imgPath);
                        if (currentPic.getHeight() > 1024 || currentPic.getWidth() > 1024) {
                            int resizedWidth = 1024;
                            int resizedHeight = 1024;
                            currentPic = Bitmap.createScaledBitmap(currentPic, resizedWidth, resizedHeight, false);
                        }
                        if (degree != 0) {
                            Matrix m = new Matrix();
                            m.setRotate(degree, (float) currentPic.getWidth() / 2,
                                    (float) currentPic.getHeight() / 2);
                            try {
                                Bitmap b2 = Bitmap.createBitmap(currentPic, 0, 0,
                                        currentPic.getWidth(), currentPic.getHeight(), m, true);
                                if (currentPic != b2) {
                                    currentPic.recycle();
                                    currentPic = b2;
                                }
                            } catch (OutOfMemoryError ex) {
                                // We have no memory to rotate. Return the original bitmap.
                            }
                        }
                        groupCover.setImageBitmap(currentPic);

//                        currentPic.recycle();

                    } else {
                        Toast.makeText(this, "사진을 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public void onResponse(String result) {
        //Todo : 에러 및 Entity 저장 프로세스
        Log.d(TAG,"onResponse result : " + result);
        try {
            JSONObject jsObject = new JSONObject(result);
            JSONArray studyArr = jsObject.getJSONArray("studyInfo");
            JSONArray memberObj = jsObject.getJSONArray("memberList");
            JSONObject studyObj = studyArr.getJSONObject(0);
            JasminPreference.getInstance(this).putJsonObject("studyInfo", studyObj.toString());
            JasminPreference.getInstance(this).putJson("memberList",memberObj.toString());
            JasminPreference.getInstance(this).putSelStudyNo(studyObj.getInt("study_no"));

            mTask.cancel(true);
            Intent intent = new Intent(this, GroupInviteActivity.class);
            startActivity(intent);
            finish();

        } catch (JSONException e) {
            Log.d(TAG, "onResponse e : " + e);
        }


    }
}
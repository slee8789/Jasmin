package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.entity.Post;
import com.study.jasmin.jasmin.ui.dialog.ProgressDialog;
import com.study.jasmin.jasmin.ui.fragment.GroupInfoFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupManageFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupNoticeFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupSettingFragment;
import com.study.jasmin.jasmin.util.JasminPreference;

import java.io.File;

public class GroupMainActivity extends AppCompatActivity implements GroupNoticeFragment.OnFragmentSelectedListener, OnClickListener {

    public static final String TAG = "GroupMainActivity";
//    private ImageView   coverImg;
    private WebView coverImg;
    private Fragment[]  arrFragments;
    private TabLayout   tabLayout;
    private ViewPager   viewPager;
    private MyPagerAdapter adapter;
    private ProgressDialog Progress;
    private JasminPreference mPref;
    private File      image;
    private String    imgPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);
        init();
        findViews();
        initViews();
    }

    private void init() {
        Progress = ProgressDialog.getInstance(getApplicationContext());
        mPref = JasminPreference.getInstance(getApplicationContext());
        arrFragments = new Fragment[4];
        arrFragments[0] = new GroupNoticeFragment();
        arrFragments[1] = new GroupManageFragment();
        arrFragments[2] = new GroupInfoFragment();
        arrFragments[3] = new GroupSettingFragment();
        adapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);

    }

    private void findViews() {
//        coverImg = (ImageView) findViewById(R.id.cover);
        coverImg = (WebView) findViewById(R.id.cover);
        tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pager);
    }

    private void initViews() {
//        coverImg.setOnClickListener(this);
        coverImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        coverImg.setWebViewClient(new WebViewClientClass());
        Log.d(TAG,"leesc test " + mPref.getSelStudyNo());
        coverImg.loadUrl("http://54.201.72.195:8081/examples/"+
                mPref.getSelStudyNo()+
                "/cover.jpg");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabLayout(tabLayout);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        *//*if (requestCode == JasminProtocol.REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
            coverImg.setImageURI(data.getData()); // 사진 선택한 사진URI로 연결하기
        }*//*

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
    }*/

    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dashboard_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_supervisor_account_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_info_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_settings_black_24dp);
    }

    @Override
    public void onFragmentSelected(View v) { //  글쓰기용만 쓸거면 파라메터 뺄것
        switch (v.getId()) {
            case R.id.notice_group_write:
                Log.d(TAG,"onFragmentSelectd v clicked");
                startActivity(new Intent(this, GroupNoticeAddActivity.class));

            break;
        }
    }
    @Override
    public void onFragmentSelected(int position) {
        Log.d(TAG,"onFragmentSelectd p clicked position : " + position);

        Intent intent = new Intent(this, GroupNoticeDetailActivity.class);
        intent.putExtra("post",(Post)(mPref.getListValue("postList")).get(position));
        startActivity(intent);

    }



    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            /*case R.id.cover:
                final Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, JasminProtocol.REQUEST_CODE_IMAGE);
                break;*/
        }
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private Fragment[] arrFragments;

        public MyPagerAdapter(FragmentManager fm, Fragment[] arrFragments) {
            super(fm);
            this.arrFragments = arrFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return arrFragments[position];
        }

        @Override
        public int getCount() {
            return arrFragments.length;
        }

    }
}

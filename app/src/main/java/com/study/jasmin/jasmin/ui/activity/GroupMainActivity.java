package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.util.JasminProtocol;
import com.study.jasmin.jasmin.ui.fragment.GroupInfoFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupManageFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupNoticeAddFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupNoticeDetailFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupNoticeFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupSettingFragment;

public class GroupMainActivity extends AppCompatActivity implements GroupNoticeFragment.OnFragmentSelectedListener {

    public static final String TAG = "GroupMainActivity";
    private ImageView coverImg;
    private Fragment[] arrFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);

        coverImg = (ImageView) findViewById(R.id.cover);
        coverImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, JasminProtocol.REQUEST_CODE_IMAGE);
            }
        });

        //Fragment
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_pager);

        arrFragments = new Fragment[4];
        arrFragments[0] = new GroupNoticeFragment();
        arrFragments[1] = new GroupManageFragment();
        arrFragments[2] = new GroupInfoFragment();
        arrFragments[3] = new GroupSettingFragment();

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabLayout(tabLayout);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == JasminProtocol.REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
            coverImg.setImageURI(data.getData()); // 사진 선택한 사진URI로 연결하기
        }
    }

    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_close_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_close_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_close_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_close_black_24dp);
    }

    @Override
    public void onFragmentSelected(View v) { //  글쓰기용만 쓸거면 파라메터 뺄것
        switch (v.getId()) {
            case R.id.notice_group_write:
                Log.d(TAG,"onFragmentSelectd v clicked");
                GroupNoticeAddFragment groupNoticeAddFragment = new GroupNoticeAddFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frag_container,groupNoticeAddFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            break;
        }
    }
    @Override
    public void onFragmentSelected(int position) {
        Log.d(TAG,"onFragmentSelectd p clicked position : " + position);
        GroupNoticeDetailFragment groupNoticeDetailFragment = new GroupNoticeDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container,groupNoticeDetailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    public Fragment getVisibleFragment() {
//        for (Fragment fragment: getSupportFragmentManager().getFragments()) {
//            if (fragment.isVisible()) {
//                return ((Fragment)fragment);
//            }
//        }
//        return null;
//    }


//    /**
//     * Takes a Fragment TAG and tries to find the fragment in the manager if it exists and bring it to front.
//     * if not, will return false;
//     * @param manager
//     * @param tag
//     */
//    public static boolean resurfaceFragment(FragmentManager manager, String tag ){
//        Fragment fragment = manager.findFragmentByTag(tag);
//        FragmentTransaction transaction = manager.beginTransaction();
//        if (fragment!=null){
//            for (int i = 0; i < manager.getFragments().size(); i++) {
//                Fragment f =  manager.getFragments().get(i);
//                transaction.hide(f);
//
//            }
//            transaction.show(fragment).commit();
//            return true;
//        }
//
//        return false;
//    }

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

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0 :
                    return "Notice";
                case 1 :
                    return "Manage";
                case 2 :
                    return "Info";
                case 3 :
                    return "Setting";
                default :
                    return "";
            }

        }
    }
}

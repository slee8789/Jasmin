package com.study.jasmin.jasmin.ui.activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.util.JasminProtocol;
import com.study.jasmin.jasmin.ui.fragment.ChattingFragment;
import com.study.jasmin.jasmin.ui.fragment.HomeFragment;
import com.study.jasmin.jasmin.ui.fragment.MyPageFragment;
import com.study.jasmin.jasmin.ui.fragment.ServiceFragment;
import com.study.jasmin.jasmin.ui.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    private ImageView coverImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Fragment[] arrFragments = new Fragment[5];
        arrFragments[0] = new HomeFragment();
        arrFragments[1] = new MyPageFragment();
        arrFragments[2] = new ChattingFragment();
        arrFragments[3] = new ServiceFragment();
        arrFragments[4] = new SettingFragment();

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
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_close_black_24dp);
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

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "MyPage";
                case 2:
                    return "Chatting";
                case 3:
                    return "Service";
                case 4:
                    return "Setting";
                default:
                    return "";
            }

        }
    }


}

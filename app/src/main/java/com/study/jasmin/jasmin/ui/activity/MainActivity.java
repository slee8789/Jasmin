package com.study.jasmin.jasmin.ui.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.fragment.ChattingFragment;
import com.study.jasmin.jasmin.ui.fragment.HomeFragment;
import com.study.jasmin.jasmin.ui.fragment.MyPageFragment;
import com.study.jasmin.jasmin.ui.fragment.ServiceFragment;
import com.study.jasmin.jasmin.ui.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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


    public void setupTabLayout(TabLayout tabLayout) {
            tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
            tabLayout.getTabAt(1).setIcon(R.drawable.ic_account_circle_black_24dp);
            tabLayout.getTabAt(2).setIcon(R.drawable.ic_forum_black_24dp);
            tabLayout.getTabAt(3).setIcon(R.drawable.ic_info_black_24dp);
            tabLayout.getTabAt(4).setIcon(R.drawable.ic_settings_black_24dp);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

        /*@Override
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

        }*/
    }


}

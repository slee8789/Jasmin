package com.study.jasmin.jasmin.ui.activity;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.study.jasmin.jasmin.R;
import com.study.jasmin.jasmin.ui.fragment.GroupInfoFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupManageFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupNoticeFragment;
import com.study.jasmin.jasmin.ui.fragment.GroupSettingFragment;

public class GroupMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_main);
        //Fragment
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_pager);

        Fragment[] arrFragments = new Fragment[4];
        arrFragments[0] = new GroupNoticeFragment();
        arrFragments[1] = new GroupManageFragment();
        arrFragments[2] = new GroupInfoFragment();
        arrFragments[3] = new GroupSettingFragment();

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), arrFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
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

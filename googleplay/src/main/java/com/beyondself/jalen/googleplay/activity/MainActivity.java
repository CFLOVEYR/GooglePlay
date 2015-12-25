package com.beyondself.jalen.googleplay.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.beyondself.jalen.googleplay.R;
import com.beyondself.jalen.googleplay.fragment.BaseFragment;
import com.beyondself.jalen.googleplay.fragment.FragmetFactory;
import com.beyondself.jalen.googleplay.utils.UIUtils;
import com.beyondself.jalen.googleplay.widget.PagerTab;

public class MainActivity extends BaseActivity {
    private PagerTab mTabs;
    private ViewPager mPager;
    private MainAdapter mAdapter;
    private ActionBar mActionBar;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        initAcitonBar();
        mTabs = (PagerTab) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new MainAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mTabs.setViewPager(mPager);//引导器和ViewPager联系
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当切换标签的时候,需要切换Fragment
                BaseFragment fragment = FragmetFactory.createFragment(position);
                fragment.show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initAcitonBar() {
        // 获取ActionBar
        mActionBar = getSupportActionBar();
        mActionBar.setIcon(R.mipmap.ic_launcher);// 设置应用图标
        mActionBar.setDisplayShowTitleEnabled(true);// 设置菜单 标题是否可见
        mActionBar.setDisplayShowHomeEnabled(true);// 设置应用图标是否
        mActionBar.setDisplayHomeAsUpEnabled(true);// 设置back按钮
    }


    class MainAdapter extends FragmentPagerAdapter {
        private String[] mTabDatas;

        public MainAdapter(FragmentManager fm) {
            super(fm);
            mTabDatas = UIUtils.getStringArray(R.array.tab_names);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabDatas[position];
        }

        @Override
        public Fragment getItem(int position) {
            return FragmetFactory.createFragment(position);
        }

        @Override
        public int getCount() {
            return mTabDatas.length;
        }
    }


}

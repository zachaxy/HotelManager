package com.zx.hostelmanager.ui.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by zhangxin on 2017/4/24 0024.
 * <p>
 * Description :
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;

    public OrderPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, ArrayList<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null) {
            return mTitles.get(position);
        } else {
            return "";
        }
    }
}

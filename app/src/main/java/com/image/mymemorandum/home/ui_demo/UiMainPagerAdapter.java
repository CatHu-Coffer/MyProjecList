package com.image.mymemorandum.home.ui_demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HSL on 2020/10/22.
 */

public class UiMainPagerAdapter extends FragmentPagerAdapter {
    private List<String> mTitles;
    private List<Fragment> fragments;


    public UiMainPagerAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mTitles) {
        super(fm);
        this.mTitles=mTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /*Adapter的写法非常简单,在自定义Adapter的时候需要重写里面的getPagerTitle()方法,
    实现这个方法是为了当Tablayout与ViewPager绑定的时候能够绑定Tab标签的标题
    tab里面的文字绑定
    * */
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}



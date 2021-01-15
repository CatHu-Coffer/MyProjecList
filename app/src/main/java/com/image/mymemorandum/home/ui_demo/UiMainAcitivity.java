package com.image.mymemorandum.home.ui_demo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.image.mymemorandum.R;
import com.mylibrary.BaseAppActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by HSL on 2020/10/22.
 */

public class UiMainAcitivity extends BaseAppActivity {


    @BindView(R.id.ui_main_vp)
    ViewPager uiMainVp;
    @BindView(R.id.ui_main_tb)
    TabLayout uiMainTb;


    private List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main_layout);
        ButterKnife.bind(this);
        initData();

    }

    private void initData() {
        titles.add("首页");
        titles.add("其他");
        titles.add("其他");
        titles.add("其他");

        fragments.add(new HomeFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());
        fragments.add(new OtherFragment());

        UiMainPagerAdapter adapter = new UiMainPagerAdapter(getSupportFragmentManager(), fragments, titles);
        uiMainVp.setAdapter(adapter);
        uiMainVp.setOffscreenPageLimit(fragments.size());
        uiMainTb.setupWithViewPager(uiMainVp);
    }

}

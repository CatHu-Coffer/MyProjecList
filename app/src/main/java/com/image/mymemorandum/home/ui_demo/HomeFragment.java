package com.image.mymemorandum.home.ui_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.image.mymemorandum.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HSL on 2020/10/22.
 */

public class HomeFragment extends Fragment {

    private List<String> list = new ArrayList<>();
    private HomeFragment instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);
        ButterKnife.bind(this, view);
        instance = this;
        initData();
        return view;
    }

    private void initData() {

    }
}

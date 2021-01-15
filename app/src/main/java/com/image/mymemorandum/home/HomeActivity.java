package com.image.mymemorandum.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.image.mymemorandum.DemoBaseAcitivity;
import com.image.mymemorandum.R;
import com.image.mymemorandum.home.bluetooth_demo.BluetoothActivity;
import com.image.mymemorandum.home.calculator_demo.CalculatorActivity;
import com.image.mymemorandum.home.calendar_demo.CalendarActivity;
import com.image.mymemorandum.home.mpAndroidChart_demo.GraphicsActivity;
import com.image.mymemorandum.home.puzzle_demo.PuzzleActivity;
import com.image.mymemorandum.home.video_demo.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends DemoBaseAcitivity {

    @BindView(R.id.demo_list)
    ListView demoList;

    private List<String> data = new ArrayList<>();

    private HomeAdapter adapter;
    private Intent mIntent = new Intent();
    String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        iniData();
        iniView();
    }

    private void iniView() {
        adapter = new HomeAdapter(this, data);
        demoList.setAdapter(adapter);
        demoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (data.get(i)) {
                    case "拼图游戏":
                        mIntent.setClass(HomeActivity.this, PuzzleActivity.class);
                        startActivity(mIntent);
                        break;
                    case "原生视频播放":
                        mIntent.setClass(HomeActivity.this, VideoActivity.class);
                        startActivity(mIntent);
                        break;
                    case "日历备忘闹铃":
                        mIntent.setClass(HomeActivity.this, CalendarActivity.class);
                        startActivity(mIntent);
                        break;
                    case "计算器":
                        mIntent.setClass(HomeActivity.this, CalculatorActivity.class);
                        startActivity(mIntent);
                        break;
                    case "蓝牙通讯":
                        mIntent.setClass(HomeActivity.this, BluetoothActivity.class);
                        startActivity(mIntent);
                        break;
                    case "折线图":
                        mIntent.setClass(HomeActivity.this, GraphicsActivity.class);
                        startActivity(mIntent);
                        break;
                }
            }
        });
    }

    private void iniData() {
        data.add("拼图游戏");
        data.add("原生视频播放");
        data.add("日历备忘闹铃");
        data.add("计算器");
        data.add("蓝牙通讯");
        data.add("折线图");
//        PermissionUlite.getIntance(this).getCreadPermission().getWRITEPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {//没有授权权限
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        0);
            }
        }
    }
}

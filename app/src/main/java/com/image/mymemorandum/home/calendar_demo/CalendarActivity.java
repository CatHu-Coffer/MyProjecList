package com.image.mymemorandum.home.calendar_demo;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.image.mymemorandum.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView record_gridView;//定义gridView
    private ImageView record_left;//左箭头
    private ImageView record_right;//右箭头
    private TextView record_title;//标题
    private int year;
    private int month;
    private int toDay;
    private String title;
    private List<String> days = new ArrayList<>();
    private DataCopyAdapter dateAdapters;

    private AlarmManager am;

    private PendingIntent pi;
    private Long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //初始化日期数据
        initData();
        //初始化组件
        initView();
        //组件点击监听事件
        initEvent();
    }

    private void initData() {
        year = DateUtils.getYear();
        month = DateUtils.getMonth();
        toDay = DateUtils.getCurrentDayOfMonth();
        initAlarm();
    }

    private void initEvent() {
        record_left.setOnClickListener(this);
        record_right.setOnClickListener(this);
    }

    private void initView() {
        /**
         * 以下是初始化GridView
         */
        record_gridView = (GridView) findViewById(R.id.record_gridView);
        days = DateUtils.getDayOfMonthFormats(year, month);
        //传入当前月的年
        dateAdapters = new DataCopyAdapter(this, days, year, month);
        record_gridView.setAdapter(dateAdapters);
        record_gridView.setVerticalSpacing(60);
        record_gridView.setEnabled(true);
        /**
         * 以下是初始化其他组件
         */
        record_left = (ImageView) findViewById(R.id.record_left);
        record_right = (ImageView) findViewById(R.id.record_right);
        record_title = (TextView) findViewById(R.id.record_title);
        record_title.setText(year + "年" + month + "月" + toDay + "日");

        dateAdapters.setOnItemClickDays(new DataCopyAdapter.OnItemClickDays() {
            @Override
            public void onItemClickDays(int year, int month, int day) {
                choiseTime(year, month, day);
            }
        });
    }

    private void choiseTime(final int year, final int month, final int day) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View logView = LayoutInflater.from(this).inflate(R.layout.calendar_dialog_alarm_time, null);
        dialog.setView(logView);
        final TextView dateDay = (TextView) logView.findViewById(R.id.calendar_dialog_alarm_title);
        final EditText hourEd = (EditText) logView.findViewById(R.id.houre);
        final EditText minuteEd = (EditText) logView.findViewById(R.id.minute);
        final EditText secondEd = (EditText) logView.findViewById(R.id.sencon);
        dateDay.setText(year + "年" + month + "月" + day + "日");
        dialog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String hours = hourEd.getText().toString().trim();
                String minute = minuteEd.getText().toString().trim();
                String seconde = secondEd.getText().toString().trim();
                setAlarm(year, month, day,Integer.valueOf(hours),Integer.valueOf(minute),Integer.valueOf(seconde));
            }
        });
        dialog.setPositiveButton("取消", null);
        dialog.show();
    }

    // 初始化Alarm
    private void initAlarm() {

        pi = PendingIntent.getBroadcast(this, 0, getMsgIntent(), 0);

        time = System.currentTimeMillis();

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    private Intent getMsgIntent() {
        //AlarmReceiver 为广播在下面代码中
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.setAction(AlarmReceiver.BC_ACTION);
        intent.putExtra("msg", "闹钟开启");
        return intent;

    }

    //设置定时执行的任务
    private void setAlarm(int year, int month, int day, int hours, int minute, int seconde) {
        //android Api的改变不同版本中设 置有所不同
        if (Build.VERSION.SDK_INT < 19) {
            am.set(AlarmManager.RTC_WAKEUP, getTimeDiff(year, month, day,hours,minute,seconde), pi);
        } else {
            am.setExact(AlarmManager.RTC_WAKEUP, getTimeDiff(year, month, day,hours,minute,seconde), pi);
        }

    }

    public long getTimeDiff(int year, int month, int day, int hours, int minute, int seconde) {
        //这里设置的是当天的15：55分，注意精确到秒，时间可以自由设置
        Calendar ca = Calendar.getInstance();

        ca.set(year, month, day);

        ca.set(Calendar.HOUR_OF_DAY, hours);

        ca.set(Calendar.MINUTE, minute);

        ca.set(Calendar.SECOND, seconde);

        return ca.getTimeInMillis();

    }

    //取消定时任务的执行
    private void cancelAlarm() {
        am.cancel(pi);
    }

    /**
     * 设置标题
     */
    private void setTile() {
        title = dateAdapters.getYear() + "年" + dateAdapters.getMonth() + "月";
        record_title.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_left:
                dateAdapters.prevMonth();
                setTile();
                break;
            case R.id.record_right:
                dateAdapters.nextMonth();
                setTile();
                break;
        }
    }
}

package com.image.mymemorandum.home.video_demo;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.image.mymemorandum.DemoBaseAcitivity;
import com.image.mymemorandum.R;
import com.mylibrary.ulite.ReadFileUlite;
import com.mylibrary.interface_ulite.OnReadFileListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideoActivity extends DemoBaseAcitivity {

    @BindView(R.id.video_play)
    VideoView videoPlay;
    @BindView(R.id.video_listview)
    ListView videoListview;
    @BindView(R.id.video_play_ig)
    ImageView videoPlayIg;
    @BindView(R.id.video_play_time)
    TextView videoPlayTime;
    @BindView(R.id.video_play_all_time)
    TextView videoPlayAllTime;
    @BindView(R.id.video_play_full_screen)
    ImageView videoPlayFullScreen;
    @BindView(R.id.video_play_relative)
    RelativeLayout videoPlayRelative;
    @BindView(R.id.activity_video)
    LinearLayout activityVideo;
    @BindView(R.id.video_play_lines)
    SeekBar videoPlayLines;
    @BindView(R.id.video_father_relative)
    RelativeLayout videoFatherRelative;

    private boolean isPlay = false; //判断暂停:播放

    private List<VideoData> datas = new ArrayList<>();
    private VideoDataAdapter mAdapter;
    private File file;

    private static final int ADD_DATAS = 0;   //查询文件
    private static final int PLAY_VIDEO = 1;  //更新播放时间

    private boolean isFullPlay = false; //横竖屏切换

    /**
     * 屏幕的宽高像素
     */
    private int widthPixels, heightPixels;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ADD_DATAS:
                    if (datas.size() == 0) showToast("未查找到视频!");
                    else
                        mAdapter.notifyDataSetChanged();
                    break;
                case PLAY_VIDEO:
                    //获取视频当前播放时间
                    int currentPosition = videoPlay.getCurrentPosition();
                    //获取视频总时间
                    int duration = videoPlay.getDuration();
                    updataTime(videoPlayTime, currentPosition);
                    updataTime(videoPlayAllTime, duration);

                    videoPlayLines.setMax(duration);
                    videoPlayLines.setProgress(currentPosition);
                    mHandler.sendEmptyMessageDelayed(PLAY_VIDEO, 1000);
                    break;
            }
        }
    };
    private AudioManager soundService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        iniView();
        iniData();
    }

    private void iniView() {
        soundService = (AudioManager) getSystemService(AUDIO_SERVICE);
        widthPixels = getResources().getDisplayMetrics().widthPixels;
        heightPixels = getResources().getDisplayMetrics().heightPixels;

        mAdapter = new VideoDataAdapter(this, datas);
        videoListview.setAdapter(mAdapter);
        videoListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                videoPlay.setVideoPath(datas.get(i).getPath());
                videoPlay.start();
                mHandler.sendEmptyMessage(PLAY_VIDEO);
            }
        });

        videoPlayFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFullPlay = !isFullPlay;
                if (isFullPlay) {
                    //切换成全屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    //切换成竖屏
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });
    }

    private void iniData() {
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        ReadFileUlite.getIntance().readVideoFiles(file, new OnReadFileListener() {
            @Override
            public void readVideoFiles(List<String> filePath) {
                for (String s : filePath) {
                    VideoData bean = new VideoData();
                    bean.setPath(s);
                    datas.add(bean);
                }
                if (datas.size() == 0) {
                    readSdCard();
                } else {
                    mHandler.sendEmptyMessage(ADD_DATAS);
                }
            }
        });

        videoPlayLines.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updataTime(videoPlayTime, i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeMessages(PLAY_VIDEO);

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                videoPlay.setTop(progress);
                mHandler.sendEmptyMessage(PLAY_VIDEO);

            }
        });
        /**
         * 当前设备最大音量
         */
        int streamMaxVolume = soundService.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        /**
         * 当前设备音量
         */
        int streamVolume = soundService.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    private void readSdCard() {
        file = new File("/storage/sdcard0");
        ReadFileUlite.getIntance().readVideoFiles(file, new OnReadFileListener() {
            @Override
            public void readVideoFiles(List<String> filePath) {
                for (String s : filePath) {
                    VideoData bean = new VideoData();
                    bean.setPath(s);
                    datas.add(bean);
                }
                mHandler.sendEmptyMessage(ADD_DATAS);
            }
        });
    }


    private void updataTime(TextView tv, int millisecond) {
        int seconde = millisecond / 1000;
        int hh = seconde / 3600;
        int mm = seconde % 3600 / 60;
        int ss = seconde % 60;
        String str = null;
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }
        tv.setText(str + "");
    }

    @OnClick({R.id.video_play, R.id.video_play_ig, R.id.video_play_full_screen, R.id.video_play_lines})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video_play:
                break;
            case R.id.video_play_ig:
                //控制视屏的播放和暂停
                isPlay = !isPlay;
                if (isPlay) {
                    videoPlay.pause();
                    mHandler.removeMessages(PLAY_VIDEO);
                } else {
                    videoPlay.start();
                    mHandler.sendEmptyMessage(PLAY_VIDEO);
                }
                break;
            case R.id.video_play_full_screen:
                break;
            case R.id.video_play_lines:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(PLAY_VIDEO);
    }

    /**
     * 可以监听到屏幕方向变化时
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        /**
         * 当屏幕方向为横屏的时候
         */
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setViewScale(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            setViewScale(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        }
    }

    private void setViewScale(int width, int height) {
        ViewGroup.LayoutParams layoutParams = videoPlay.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        videoPlay.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams params = videoFatherRelative.getLayoutParams();
        params.width = width;
        params.height = height;
        videoFatherRelative.setLayoutParams(params);

    }
}

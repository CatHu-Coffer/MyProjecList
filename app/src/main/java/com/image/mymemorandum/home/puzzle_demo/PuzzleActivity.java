package com.image.mymemorandum.home.puzzle_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.image.mymemorandum.DemoBaseAcitivity;
import com.image.mymemorandum.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PuzzleActivity extends DemoBaseAcitivity {
    private PintuView mGamePintu;
    private PuzzleActivity intance;
    private int ALBUM_OK = 0;
    private int CUT_OK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);
        ButterKnife.bind(this);
        intance = this;
        iniData();
    }

    private void iniData() {
        mGamePintu = ((PintuView) findViewById(R.id.game_pintu));
        mGamePintu.setPintuListener(new PintuView.GamePintuListener() {
            @Override
            public void nextLevel(int next) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(intance);
                dialog.setTitle("GAME INFO");
                dialog.setMessage("LEVE UP!!");
                dialog.setNegativeButton("增加难度", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mGamePintu.nextLeve();
                        dialogInterface.dismiss();
                    }
                });
                dialog.setPositiveButton("换图", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("没存储更多的图,即将关闭!");
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                dialog.show();
            }

            @Override
            public void timechanger(int currentTime) {

            }

            @Override
            public void gameOver() {

            }
        });
    }

    @OnClick({R.id.choice_ig})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choice_ig:
                choicePicFromAlbum();
                break;
        }
    }

    /**
     * 从相册获取图片
     */
    private void choicePicFromAlbum() {
        // 来自相册
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        /**
         * 下面这句话，与其它方式写是一样的效果，如果：
         * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         * intent.setType(""image/*");设置数据类型
         * 要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
         */
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, ALBUM_OK);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void clipPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        //可以选择图片类型，如果是*表明所有类型的图片
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop = true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        //裁剪时是否保留图片的比例，这里的比例是1:1
        intent.putExtra("scale", true);
        //是否是圆形裁剪区域，设置了也不一定有效
        //intent.putExtra("circleCrop", true);
        //设置输出的格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        //是否将数据保留在Bitmap中返回
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CUT_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ALBUM_OK) {
            if (data.getData() != null) {
                showToast("剪切无用,没有配置完成");
                clipPhoto(data.getData());
            }
        }
        if(requestCode ==CUT_OK){
            if (data.getData() != null) {
//                mGamePintu.nextLeveNewBitmap();
                showToast("没有配置完成");
            }
        }
    }
}

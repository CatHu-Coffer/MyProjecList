package com.image.mymemorandum.home.puzzle_demo;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2017/11/29.
 */

public class ImageUilt {

    /**
     * 传入图片，切块
     *
     * @param bitmap
     * @param pices
     * @return
     */
    public static List<ImagePice> splitImage(Bitmap bitmap, int pices) {
        List<ImagePice> list = new ArrayList<>();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pieceWidth = Math.min(width, height) / pices;
        for (int i = 0; i < pices; i++) {
            for (int j = 0; j < pices; j++) {
                ImagePice imagePice = new ImagePice();
                imagePice.setIndex(j + i * pices);
                int x = j * pieceWidth;
                int y = i * pieceWidth;
                imagePice.setBitmap(Bitmap.createBitmap(bitmap, x, y, pieceWidth, pieceWidth));
                list.add(imagePice);
            }
        }
        return list;
    }
}

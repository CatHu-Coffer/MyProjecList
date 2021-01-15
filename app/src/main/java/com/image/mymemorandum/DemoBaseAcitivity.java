package com.image.mymemorandum;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.image.mymemorandum.gen.response.FruitDaoUtil;
import com.mylibrary.BaseAppActivity;
import com.mylibrary.ulite.LogUtile;

/**
 * Created by 123 on 2018/1/10.
 */

public class DemoBaseAcitivity extends BaseAppActivity {


    public static FruitDaoUtil fruitDaoUtil;
    public static LogUtile myLog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fruitDaoUtil = FruitDaoUtil.getIntacen(this);
//        myLog = LogUtile.getIntance().showOrLine(true);
    }
}

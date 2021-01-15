package com.image.mymemorandum.home.bluetooth_demo;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;

/**
 * Created by 123 on 2018/7/2.
 */

public interface BluetoothListener {

    void unMatchedEquipment(BluetoothDevice device);
    void searchEnd();
}

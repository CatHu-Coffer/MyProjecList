package com.image.mymemorandum.home.bluetooth_demo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by 123 on 2018/6/29.
 */

public class BluetoothReceiver extends BroadcastReceiver {

    private BluetoothListener bluetoothListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(BluetoothDevice.ACTION_FOUND)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            //显示未配对设备
            if (bluetoothListener != null)
                bluetoothListener.unMatchedEquipment(device);
        } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
            //搜索完成
            if (bluetoothListener != null)
                bluetoothListener.searchEnd();
            Log.d("myDemo", "onReceive: 搜索完成");
        }
    }

    public void setBluetoothListener(BluetoothListener bluetoothListener) {
        this.bluetoothListener = bluetoothListener;
    }
}

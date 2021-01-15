package com.image.mymemorandum.home.bluetooth_demo;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.image.mymemorandum.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {

    private Button open, search, close;
    private BluetoothAdapter mBluetoothAdapter;

    private List<BluetoothDevice> bluetoothDevices = new ArrayList<BluetoothDevice>();
    private ListView listView;
    private MyBluetAdapter adapter;
    private BluetoothReceiver receiver = new BluetoothReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        iniView();
    }

    private void iniView() {
        open = ((Button) findViewById(R.id.open_bluetooth));
        search = ((Button) findViewById(R.id.search_bluetooth));
        close = ((Button) findViewById(R.id.close_bluetooth));
        listView = ((ListView) findViewById(R.id.bluetooth_list));
        adapter = new MyBluetAdapter(bluetoothDevices, this);
        listView.setAdapter(adapter);

        //初始化蓝牙
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        IntentFilter filter = new IntentFilter(
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receiver, filter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogInfo(bluetoothDevices.get(position));
            }
        });
        receiver.setBluetoothListener(new BluetoothListener() {

            @Override
            public void unMatchedEquipment(BluetoothDevice device) {
                bluetoothDevices.add(device);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void searchEnd() {
                Toast.makeText(BluetoothActivity.this, "搜索结束", Toast.LENGTH_SHORT).show();
            }
        });
        addMatcheBluet();
    }

    private void addMatcheBluet() {
        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
        for (BluetoothDevice bondedDevice : bondedDevices) {
            bluetoothDevices.add(bondedDevice);
        }
    }


    private void dialogInfo(final BluetoothDevice device) {
        // 判断当前是否还是正在搜索周边设备，如果是则暂停搜索
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("是否配对" + device.getName() + "?");
        dialog.setNegativeButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                device.createBond();
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("取消", null);
        dialog.show();
    }

    public void bluetOnClick(View view) {
        switch (view.getId()) {
            case R.id.open_bluetooth:
                if (mBluetoothAdapter == null) {
                    Toast.makeText(this, "该手机不支持蓝牙功能", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mBluetoothAdapter.enable();
                }
                break;
            case R.id.search_bluetooth:
                if (!mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.enable();
                }
                mBluetoothAdapter.startDiscovery();
                break;
            case R.id.close_bluetooth:
                mBluetoothAdapter.disable();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}

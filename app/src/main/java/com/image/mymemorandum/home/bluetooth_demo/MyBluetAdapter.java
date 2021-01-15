package com.image.mymemorandum.home.bluetooth_demo;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.image.mymemorandum.R;

import java.util.List;

/**
 * Created by 123 on 2018/6/29.
 */

public class MyBluetAdapter extends BaseAdapter {

    private List<BluetoothDevice> data;
    private Context context;
    private LayoutInflater inflater;

    public MyBluetAdapter(List<BluetoothDevice> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHodler hodler;
        if (convertView == null) {
            hodler = new MyHodler();
            convertView = inflater.inflate(R.layout.bluet_item, null);
            hodler.name = ((TextView) convertView.findViewById(R.id.bluet_item_name));
            convertView.setTag(hodler);
        } else {
            hodler = (MyHodler) convertView.getTag();
        }
        BluetoothDevice bluetoothDevice = data.get(position);
//        if (bluetoothDevice.getBondState() == BluetoothDevice.BOND_NONE) {    //显示已配对设备
//            hodler.name.setText("已匹配:"+bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress());
//        } else if (bluetoothDevice.getBondState() != BluetoothDevice.BOND_BONDED) {
            hodler.name.setText(bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress());
//        }
        return convertView;
    }


    private class MyHodler {
        TextView name;
    }

}

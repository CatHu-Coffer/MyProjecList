package com.image.mymemorandum.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.image.mymemorandum.R;
import com.mylibrary.list_about.BaseListAdapter;

import java.util.List;

/**
 * Created by 123 on 2018/1/8.
 */

public class HomeAdapter extends BaseListAdapter<String> {

    public HomeAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = mInflater.inflate(R.layout.home_list_item,parent,false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.home_list_item_btn);
        String text = list.get(position);
        tv.setText(text);
        return convertView;
    }

}

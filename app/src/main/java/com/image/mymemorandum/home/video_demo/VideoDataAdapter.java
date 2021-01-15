package com.image.mymemorandum.home.video_demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.image.mymemorandum.R;
import com.mylibrary.list_about.BaseListAdapter;

import java.util.List;

/**
 * Created by 123 on 2018/1/10.
 */

public class VideoDataAdapter extends BaseListAdapter<VideoData> {

    public VideoDataAdapter(Context context, List<VideoData> list) {
        super(context, list);
    }

    @Override
    public View bindView(int position, View convertView, ViewGroup parent) {
        if(convertView ==null){
            convertView = mInflater.inflate(R.layout.string_layout,parent,false);
        }
        TextView tv = ((TextView) convertView.findViewById(R.id.str_tx));
        tv.setText(list.get(position).getPath());
        return convertView;
    }
}

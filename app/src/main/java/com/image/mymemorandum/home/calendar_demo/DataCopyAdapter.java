package com.image.mymemorandum.home.calendar_demo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.image.mymemorandum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2018/6/13.
 */

public class DataCopyAdapter extends BaseAdapter {
    private List<String> listDays = new ArrayList<>();
    private Context context;
    private int year;
    private int month;
    private OnItemClickDays onItemClickDays;

    public interface OnItemClickDays {
        void onItemClickDays(int year, int month, int day);
    }

    public void setOnItemClickDays(OnItemClickDays onItemClickDays) {
        this.onItemClickDays = onItemClickDays;
    }

    public DataCopyAdapter(Context context, List<String> listDays, int year, int month) {
        this.context = context;
        this.listDays = listDays;
        this.year = year;
        this.month = month;
    }

    @Override
    public int getCount() {
        return listDays.size();
    }

    @Override
    public Object getItem(int i) {
        return listDays.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.calender_item, null);
            viewHolder = new ViewHolder();
            viewHolder.date_item = (TextView) view.findViewById(R.id.date_item);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.date_item.setText("");

        if (i < 7 && Integer.valueOf(listDays.get(i)) > 20) {
            viewHolder.date_item.setTextColor(Color.rgb(204, 204, 204));//将上个月的和下个月的设置为灰色
        } else if (i > 20 && Integer.valueOf(listDays.get(i)) < 15) {
            viewHolder.date_item.setTextColor(Color.rgb(204, 204, 204));
        } else {
            viewHolder.date_item.setTextColor(Color.WHITE);
            viewHolder.date_item.setText(listDays.get(i) + "");
        }

        viewHolder.date_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickDays.onItemClickDays(year, month, Integer.valueOf(listDays.get(i)));
            }
        });
        return view;
    }

    /**
     * 优化Adapter
     */
    class ViewHolder {
        TextView date_item;
    }

    /**
     * 下个月
     */
    public void nextMonth() {
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        listDays.clear();
        listDays.addAll(DateUtils.getDayOfMonthFormats(year, month));
        notifyDataSetChanged();
    }

    /**
     * 上一个月
     *
     * @return
     */
    public void prevMonth() {
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        listDays.clear();
        listDays.addAll(DateUtils.getDayOfMonthFormats(year, month));
        notifyDataSetChanged();
    }

    public int getYear(){
        return year;
    }

    public int getMonth(){
        return month;
    }

}

package com.image.mymemorandum.home.calendar_demo;

import java.util.List;

/**
 * Created by 123 on 2018/6/13.
 */

public class CalendarBean {

    private String year;
    private String month;
    private List<String> day;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<String> getDay() {
        return day;
    }

    public void setDay(List<String> day) {
        this.day = day;
    }
}

package com.image.mymemorandum.home.mpAndroidChart_demo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.image.mymemorandum.R;

import java.util.ArrayList;
import java.util.List;

public class GraphicsActivity extends AppCompatActivity {


    private MyLineChartView chartView;
    List<String> xValues;   //x轴数据集合
    List<Integer> yValues;  //y轴数据集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);
        chartView = ((MyLineChartView) findViewById(R.id.my_chart_view));

        xValues = new ArrayList<>();
        yValues = new ArrayList<>();
        initData();
        // xy轴集合自己添加数据
        chartView.setXValues(xValues);
        chartView.setYValues(yValues);
    }

    private void initData() {
        xValues.add(0 + "");
        xValues.add(0.5 + "");
        xValues.add(1 + "");
        xValues.add(1.5 + "");
        xValues.add(2 + "");
        xValues.add(2.5 + "");
        xValues.add(3 + "");
        xValues.add(3.5 + "");
        xValues.add(4 + "");
        xValues.add(4.5 + "");
        xValues.add(5 + "");
        xValues.add(5.5 + "");
        xValues.add(6 + "");
        xValues.add(6.5 + "");
        xValues.add(7 + "");
        xValues.add(7.5 + "");
        xValues.add(8 + "");
        xValues.add(8.5 + "");
        xValues.add(9 + "");
        xValues.add(9.5 + "");
        xValues.add(10 + "");
        xValues.add(10.5 + "");
        xValues.add(11 + "");
        xValues.add(11.5 + "");
        xValues.add(12 + "");
        xValues.add(12.5 + "");
        xValues.add(13 + "");
        xValues.add(13.5 + "");
        xValues.add(14 + "");
        xValues.add(14.5 + "");
        xValues.add(15 + "");
        xValues.add(14.5 + "");
        xValues.add(15.5 + "");
        xValues.add(16 + "");
        xValues.add(16.5 + "");
        xValues.add(17 + "");
        xValues.add(17.5 + "");
        xValues.add(18 + "");
        xValues.add(18.5 + "");
        xValues.add(19 + "");
        xValues.add(19.5 + "");
        xValues.add(20 + "");
        xValues.add(20.5 + "");
        xValues.add(21 + "");
        xValues.add(21.5 + "");
        xValues.add(22 + "");
        xValues.add(22.5 + "");
        xValues.add(23 + "");
        xValues.add(23.5 + "");
        xValues.add(24 + "");

        yValues.add(0);
        yValues.add(1888105);
        yValues.add(3550784);
        yValues.add(4550171);
        yValues.add(5080059);
        yValues.add(5498666);
        yValues.add(5772098);
        yValues.add(5972076);
        yValues.add(6140460);
        yValues.add(6274895);
        yValues.add(6415781);
        yValues.add(6524083);
        yValues.add(6685567);
        yValues.add(6978433);
        yValues.add(7366474);
        yValues.add(7898977);
        yValues.add(8607726);
        yValues.add(9491820);
        yValues.add(10422793);
        yValues.add(11388881);
        yValues.add(12306832);
        yValues.add(13225349);
        yValues.add(13989818);
        yValues.add(14673323);
        yValues.add(15329132);
        yValues.add(15831604);
        yValues.add(16296706);
        yValues.add(16802538);
        yValues.add(17257047);
        yValues.add(17652860);
        yValues.add(18020979);
        yValues.add(18339078);
        yValues.add(18651084);
        yValues.add(18983671);
        yValues.add(19250928);
        yValues.add(19540631);
        yValues.add(19790401);
        yValues.add(20089696);
        yValues.add(20317685);
        yValues.add(20570820);
        yValues.add(20840012);
        yValues.add(21167090);
        yValues.add(21473488);
        yValues.add(21900953);
        yValues.add(22415336);
        yValues.add(23132341);
        yValues.add(23436514);
        yValues.add(23777127);

//        yValues.add(1888105);
//        yValues.add(1888105);
//        yValues.add(24491532);
//        yValues.add(24534582);
//        yValues.add(24551113);
//        yValues.add(24562083);
//        yValues.add(24567495);
//        yValues.add(24572047);
//        yValues.add(24579534);
//        yValues.add(24588589);
//        yValues.add(24590743);
//        yValues.add(24592602);
//        yValues.add(24595253);
//        yValues.add(24599488);
//
//        yValues.add(24605182);
//        yValues.add(24614345);
//        yValues.add(24629737);
//        yValues.add(24647754);
//        yValues.add(24674868);
//        yValues.add(24707940);
//        yValues.add(24750876);
//        yValues.add(24790507);
    }

}

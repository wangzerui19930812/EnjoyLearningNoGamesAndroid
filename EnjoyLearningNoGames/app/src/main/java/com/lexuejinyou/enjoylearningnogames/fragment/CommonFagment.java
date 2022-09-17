package com.lexuejinyou.enjoylearningnogames.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lexuejinyou.enjoylearningnogames.base.BaseFragment;

public class CommonFagment extends BaseFragment {

    private TextView textView;

    @Override
    protected View initView() {
        System.out.println("CommonFagment initView");
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("常用框架页面");
    }
}

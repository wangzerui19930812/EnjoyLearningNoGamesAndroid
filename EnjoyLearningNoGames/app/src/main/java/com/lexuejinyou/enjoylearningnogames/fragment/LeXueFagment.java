package com.lexuejinyou.enjoylearningnogames.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lexuejinyou.enjoylearningnogames.base.BaseFragment;

public class LeXueFagment extends BaseFragment {

    private TextView textView;

    @Override
    protected View initView() {
        System.out.println("LeXueFagment initView");
        textView = new TextView(mContext);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("乐学框架页面");
    }
}

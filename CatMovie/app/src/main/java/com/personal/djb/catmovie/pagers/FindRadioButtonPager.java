package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.personal.djb.catmovie.base.BasePager;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class FindRadioButtonPager extends BasePager {

    private TextView view;

    public FindRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton发现页面initView");
        view = new TextView(context);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "radiobutton发现页面initData");

        isInitData = true;
        view.setText("发现");
        super.initData();
    }
}

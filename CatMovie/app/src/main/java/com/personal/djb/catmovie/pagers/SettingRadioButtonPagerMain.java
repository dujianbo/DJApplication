package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.personal.djb.catmovie.base.MainBasePager;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SettingRadioButtonPagerMain extends MainBasePager {

    private TextView view;

    public SettingRadioButtonPagerMain(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton我的设置页面initView");
        view = new TextView(context);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "radiobutton我的设置页面initData");

        isInitData = true;
        view.setText("我的");
        super.initData();
    }
}

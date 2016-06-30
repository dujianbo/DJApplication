package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.base.BasePager;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SettingRadioButtonPager extends BasePager {

    private View view;

    public SettingRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton我的设置页面initView");
        view = View.inflate(context, R.layout.mysetting_page,null);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "radiobutton我的设置页面initData");
        isInitData = true;
        super.initData();


    }
}

package com.personal.djb.catmovie.pagers.moviepagers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.personal.djb.catmovie.base.BasePager;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class OutMoviePager extends BasePager {

    private TextView view;

    public OutMoviePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "电影页面的海外initView");
        view = new TextView(context);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "电影页面的海外initData");
        super.initData();
        isInitData = true;

        view.setText("海外");
    }
}

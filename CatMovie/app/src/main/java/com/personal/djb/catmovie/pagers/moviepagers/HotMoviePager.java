package com.personal.djb.catmovie.pagers.moviepagers;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.personal.djb.catmovie.base.moviebase.MovieBasePager;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class HotMoviePager extends MovieBasePager {

    private TextView view;

    public HotMoviePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "电影页面的热映initView");
        view = new TextView(context);
        return view;
    }

    @Override
    public void initData() {
        Log.e("TAG", "电影页面的热映initData");
        super.initData();

        view.setText("热映");
    }
}

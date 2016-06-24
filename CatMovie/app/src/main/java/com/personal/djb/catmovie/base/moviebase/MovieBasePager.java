package com.personal.djb.catmovie.base.moviebase;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public abstract class MovieBasePager {

    public Context context;
    /**
     * 根view
     */
    public View rootView;

    public MovieBasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    /**
     * 是否填充过数据
     */
    public boolean isInitData = false;

    public abstract View initView();

    public void initData(){

    }
}

package com.personal.djb.catmovie.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public abstract class BasePager {

    public Context context;
    /**
     * 根view
     */
    public View rootView;

    public BasePager(Context context) {
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

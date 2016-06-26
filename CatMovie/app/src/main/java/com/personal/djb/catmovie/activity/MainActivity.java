package com.personal.djb.catmovie.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.pagers.CinemaRadioButtonPager;
import com.personal.djb.catmovie.pagers.FindRadioButtonPager;
import com.personal.djb.catmovie.pagers.MovieRadioButtonPager;
import com.personal.djb.catmovie.pagers.SettingRadioButtonPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private FrameLayout mFrameContent;
    private RadioGroup mRadioGroup;

//    private RadioButton mRBMovie;
//    private RadioButton mRBCinema;
//    private RadioButton mRBFind;
//    private RadioButton mRBSetting;

    /**
     * 基础页面集合
     */
    private List<BasePager> datas;

    /**
     * 记录RadioButton点击的位置
     */
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initView();
        initData();
        setListener();
    }

    private void initData() {
        initFragment();

    }

    private void initFragment() {
        datas = new ArrayList<>();
        datas.add(new MovieRadioButtonPager(this));
        datas.add(new CinemaRadioButtonPager(this));
        datas.add(new FindRadioButtonPager(this));
        datas.add(new SettingRadioButtonPager(this));
    }

    private void setListener() {

        mRadioGroup.setOnCheckedChangeListener(new MyOncheckedChangeListener());
        mRadioGroup.check(R.id.rb_main_movie);
        setPager(0);
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        mFrameContent = (FrameLayout) findViewById(R.id.fl_main);

//        mRBMovie = (RadioButton) findViewById(R.id.rb_main_movie);
//        mRBCinema = (RadioButton) findViewById(R.id.rb_main_cinema);
//        mRBFind = (RadioButton) findViewById(R.id.rb_main_find);
//        mRBSetting = (RadioButton) findViewById(R.id.rb_main_setting);

    }

    /**
     * RadioGroup的点击改变监听，切换页面
     */
    private class MyOncheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_main_movie :
                    position = 0;
                    break;
                case R.id.rb_main_cinema:
                    position = 1;
                    break;
                case R.id.rb_main_find:
                    position = 2;
                    break;
                case R.id.rb_main_setting:
                    position = 3;
                    break;
            }

            setPager(position);
        }
    }

    /**
     * 设置fragment以前先判断是否初始化数据过
     * @param position
     */
    private void setPager(int position) {
        BasePager mbp = datas.get(position);
        if (!mbp.isInitData){
            mbp.initData();
        }
        mFrameContent.removeAllViews();
        mFrameContent.addView(mbp.rootView);
    }

}

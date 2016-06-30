package com.personal.djb.catmovie.pagers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.SearchActivity;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.pagers.moviepagers.HotMoviePager;
import com.personal.djb.catmovie.pagers.moviepagers.OutMoviePager;
import com.personal.djb.catmovie.pagers.moviepagers.WaitMoviePager;
import com.personal.djb.catmovie.view.MyMovieButtonViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class MovieRadioButtonPager extends BasePager {

    private View view;

    private MyMovieButtonViewPager mMoviePager;
    private RadioGroup mTitleRadioGroup;
    private ImageView mIVPoint;
    private MyAdapter adapter;
    private List<BasePager> datas;
    private int leftMargin;
    private int width;
    private int height;

    private boolean isMeasure = false;
    private RelativeLayout mRGGroup;
    private RelativeLayout mRlChoiceCity;

    /**
     * 选择城市的按钮
     */
    private Button mBtnChoiceCity;
    /**
     * 搜索的按钮
     */
    private RelativeLayout mBtnSearchRl;
    private ImageView mBtnSearch;

    private int preChecked = 0;
    private int prePosition = 0;

    public MovieRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton电影页面initView");

        view = View.inflate(context, R.layout.movie_main_pager,null);

        findView();
        return view;
    }

    private void findView() {

        mTitleRadioGroup = (RadioGroup) view.findViewById(R.id.common_title_rg);
        mIVPoint = (ImageView) view.findViewById(R.id.iv_title_selector_bg);
        mMoviePager = (MyMovieButtonViewPager) view.findViewById(R.id.vp_main_movie);

        mBtnChoiceCity = (Button) view.findViewById(R.id.common_title_city);
        mBtnSearchRl = (RelativeLayout) view.findViewById(R.id.common_title_search);
        mBtnSearch = (ImageView) view.findViewById(R.id.common_title_search_search);
        mRGGroup = (RelativeLayout) view.findViewById(R.id.rg_group_group);
        mRlChoiceCity = (RelativeLayout) view.findViewById(R.id.rl_choice_city);

        mRlChoiceCity.setVisibility(View.VISIBLE);

        mTitleRadioGroup.setVisibility(View.VISIBLE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mBtnChoiceCity.setVisibility(View.VISIBLE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mRGGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        Log.e("TAG", "radiobutton电影页面initData");
        isInitData = true;
        super.initData();

        //  准备页面
        initPager();

        adapter = new MyAdapter();
        mMoviePager.setAdapter(adapter);

        setListener();
    }

    /**
     * 顶部导航栏的监听 等
     */
    private void setListener() {
        mTitleRadioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //  设定默认选中
        mTitleRadioGroup.check(R.id.common_title_rg_hot);
        setPager(0);
//          viewpager的监听
        mMoviePager.addOnPageChangeListener(new MyOnPageChangeListener());
        //控件创建到显示--测量-布局-绘制
        mIVPoint.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());


        mBtnChoiceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "选择城市", Toast.LENGTH_SHORT).show();
            }
        });

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SearchActivity.class);
                context.startActivity(intent);
            }
        });

    }

    private void initPager() {
        datas = new ArrayList<>();
        datas.add(new HotMoviePager(context));
        datas.add(new WaitMoviePager(context));
        datas.add(new OutMoviePager(context));
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View rootView = datas.get(position).rootView;
            container.addView(rootView);
            setPager(position);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView(datas.get(position).rootView);
        }
    }

    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (group.getCheckedRadioButtonId() == preChecked) {
                return;
            }
            preChecked = checkedId;
            switch (checkedId) {
                case R.id.common_title_rg_hot :
                    mMoviePager.setCurrentItem(0);
                    break;
                case R.id.common_title_rg_wait:
                    mMoviePager.setCurrentItem(1);
                    break;
                case R.id.common_title_rg_out:
                    mMoviePager.setCurrentItem(2);
                    break;
            }
            Log.e("dj", "调用radioButton改变的监听");
        }
    }

    private void setPager(int position) {
        BasePager basePager = datas.get(position);

        if (!basePager.isInitData){
            basePager.initData();
        }
    }

    //  viewpager的监听实现
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            //  测量过才进行此操作
            if (isMeasure) {
                //红点移动的距离 = 间距*屏幕滑动百分比
//            float reddistance = leftMargin*positionOffset;

                //红点在屏幕上的坐标 = 起始坐标 +  红点移动的距离；
                float reddistance =leftMargin* (position + positionOffset);

                RelativeLayout.LayoutParams params  = new RelativeLayout.LayoutParams(width,height);
                params.leftMargin  = (int) reddistance;
                mIVPoint.setLayoutParams(params);
            }

        }

        @Override
        public void onPageSelected(int position) {
            int id = mTitleRadioGroup.getChildAt(position).getId();
//            Log.e("test", "id===="+id);
            mTitleRadioGroup.check(id);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 一个监听，用来测量控件宽高
     */
    private class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            //取出注册
            mIVPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            width = mIVPoint.getWidth();
            height = mIVPoint.getHeight();

            Log.e("test", "width==="+width + "  ,height==="+height+"  ,leftMargin==="+leftMargin);
            //间距 = 每个控件的宽
            leftMargin = mTitleRadioGroup.getChildAt(1).getLeft() - mTitleRadioGroup.getChildAt(0).getLeft();;
            isMeasure = true;
        }
    }
}

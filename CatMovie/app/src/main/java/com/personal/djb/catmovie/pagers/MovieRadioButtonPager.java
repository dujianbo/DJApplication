package com.personal.djb.catmovie.pagers;

import android.annotation.TargetApi;
import android.content.Context;
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
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.pagers.moviepagers.HotMoviePager;
import com.personal.djb.catmovie.pagers.moviepagers.OutMoviePager;
import com.personal.djb.catmovie.pagers.moviepagers.WaitMoviePager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class MovieRadioButtonPager extends BasePager {

    private View view;

    private ViewPager mMoviePager;
    private RadioGroup mTitleRadioGroup;
    private ImageView mIVPoint;
    private MyAdapter adapter;
    //  记录点击位置
    private int position;
    private List<BasePager> datas;
    private int leftMargin;
    private int width;
    private int height;

    private boolean isMeasure = false;

    /**
     * 选择城市的按钮
     */
    private Button mBtnChoiceCity;
    /**
     * 搜索的按钮
     */
    private RelativeLayout mBtnSearch;

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
        mMoviePager = (ViewPager) view.findViewById(R.id.vp_main_movie);

        mBtnChoiceCity = (Button) view.findViewById(R.id.common_title_city);
        mBtnSearch = (RelativeLayout) view.findViewById(R.id.common_title_search);
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
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
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
            switch (checkedId) {
                case R.id.common_title_rg_hot :
                    position = 0;
                    break;
                case R.id.common_title_rg_wait:
                    position = 1;
                    break;
                case R.id.common_title_rg_out:
                    position = 2;
                    break;
            }

            setPager(position);
            choicePager(position);
        }
    }

    private void choicePager(int position) {
        mMoviePager.setCurrentItem(position);
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

                //  设置字体的透明度
//                float alpha = (reddistance/leftMargin)*2;
////                mTitleRadioGroup.getChildAt(position).setAlpha((1- position + positionOffset)*2);
////                mTitleRadioGroup.getChildAt(prePosition).setAlpha((1 - position + positionOffset)*2);
//
//                mTitleRadioGroup.getChildAt(position).setAlpha(alpha);
//                mTitleRadioGroup.getChildAt(prePosition).setAlpha(alpha);

                RelativeLayout.LayoutParams params  = new RelativeLayout.LayoutParams(width,height);
                params.leftMargin  = (int) reddistance;
                mIVPoint.setLayoutParams(params);
            }

        }

        @Override
        public void onPageSelected(int position) {
//            mTitleRadioGroup.getChildAt(prePosition).setAlpha(1);
//            mTitleRadioGroup.getChildAt(position).setAlpha(1);

//            Log.e("test", "prePosition" + prePosition + "    position" + position);

            //  可以省略
//            setPager(position);
            int id = mTitleRadioGroup.getChildAt(position).getId();
//            Log.e("test", "id===="+id);
            mTitleRadioGroup.check(id);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onGlobalLayout() {
            //取出注册
            mIVPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            width = mTitleRadioGroup.getChildAt(0).getWidth();
            height = mTitleRadioGroup.getHeight();

            Log.e("test", "width==="+width + "  ,height==="+height+"  ,leftMargin==="+leftMargin);
            //间距 = 每个控件的宽
            leftMargin = mTitleRadioGroup.getChildAt(1).getLeft() - mTitleRadioGroup.getChildAt(0).getLeft();;
//            System.out.println("leftMargin==" + leftMargin);
            isMeasure = true;
        }
    }
}

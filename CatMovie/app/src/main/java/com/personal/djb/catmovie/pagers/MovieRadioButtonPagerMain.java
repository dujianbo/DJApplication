package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.base.MainBasePager;
import com.personal.djb.catmovie.base.moviebase.MovieBasePager;
import com.personal.djb.catmovie.pagers.moviepagers.HotMoviePager;
import com.personal.djb.catmovie.pagers.moviepagers.OutMoviePager;
import com.personal.djb.catmovie.pagers.moviepagers.WaitMoviePager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class MovieRadioButtonPagerMain extends MainBasePager {

    private View view;

    private ViewPager mMoviePager;
    private RadioGroup mTitleRadioGroup;
    private ImageView mIVPoint;
    private MyAdapter adapter;

    private List<MovieBasePager> datas;

    public MovieRadioButtonPagerMain(Context context) {
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
}

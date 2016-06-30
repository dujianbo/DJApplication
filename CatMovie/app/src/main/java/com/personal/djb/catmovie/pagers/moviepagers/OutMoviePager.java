package com.personal.djb.catmovie.pagers.moviepagers;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.pagers.moviepagers.outmoviepagers.AmericaPager;
import com.personal.djb.catmovie.pagers.moviepagers.outmoviepagers.JapanPager;
import com.personal.djb.catmovie.pagers.moviepagers.outmoviepagers.KoreaPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class OutMoviePager extends BasePager {

    private View view;

    private TabLayout mTabLayout;
    private ViewPager mOutMoviePager;

    private static final String[] countrys = {"美国","韩国","日本"};
    private MyPagerAdapter adapter;

    private List<BasePager> datas;

    public OutMoviePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "电影页面的海外initView");

        view = View.inflate(context, R.layout.outmovie_pager, null);

        findView();
        return view;
    }

    private void findView() {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabout_movie_pager);
        mOutMoviePager = (ViewPager) view.findViewById(R.id.out_movie_pager);
    }

    @Override
    public void initData() {
        Log.e("TAG", "电影页面的海外initData");
        super.initData();

        isInitData = true;
        datas = new ArrayList<>();
        datas.add(new AmericaPager(context));
        datas.add(new KoreaPager(context));
        datas.add(new JapanPager(context));


        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        TabLayout.Tab tab1 = mTabLayout.newTab().setText(countrys[0]);
        tab1.setTag(0);
        mTabLayout.addTab(tab1);
        TabLayout.Tab tab2 = mTabLayout.newTab().setText(countrys[1]);
        tab1.setTag(1);
        mTabLayout.addTab(tab2);
        TabLayout.Tab tab3 = mTabLayout.newTab().setText(countrys[2]);
        tab1.setTag(2);
        mTabLayout.addTab(tab3);

        adapter = new MyPagerAdapter();
        mOutMoviePager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mOutMoviePager);

    }

    /**
     * viewPager的适配器
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View rootView = datas.get(position).rootView;
            container.addView(rootView);
            if (!datas.get(position).isInitData) {
                datas.get(position).initData();
            }
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return countrys.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return countrys[position];
        }
    }

}

package com.personal.djb.catmovie.pagers.moviepagers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.adapter.HotMovieAdapter;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.bean.movies.HotMovieBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class HotMoviePager extends BasePager {

    private final String HOT_MOVIE_URL = "http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000";

    private RecyclerView mHotMovieRecyclerView;
    /**
     * 热映电影的集合
     */
    private List<HotMovieBean.DataBean.MoviesBean> datas;
    /**
     * 没网的页面
     */
    private RelativeLayout mNoNetPager;
    private ImageView mNoNetButton;
    /**
     * 缓冲的页面
     */
    private RelativeLayout mLoadingPager;

    /**
     * 下拉刷新控件
     */
    private MaterialRefreshLayout mRefreshLayout;

    private View view;
    /**
     * 适配器
     */
    private HotMovieAdapter adapter;
    private boolean isRefreshing = false;

    public HotMoviePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "电影页面的热映initView");

        view = View.inflate(context, R.layout.hotmovie_pager, null);

        findView();
        return view;
    }

    private void findView() {
        mHotMovieRecyclerView = (RecyclerView) view.findViewById(R.id.hotmovie_recyclerview);
        mNoNetPager = (RelativeLayout) view.findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) view.findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) view.findViewById(R.id.iv_netload_nonet);
        mHotMovieRecyclerView.setVisibility(View.GONE);

        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        mRefreshLayout.setSunStyle(true);

        setListener();
    }

    @Override
    public void initData() {
        Log.e("TAG", "电影页面的热映initData");
        super.initData();
        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.GONE);

        //  检查网络连接
        if (!NetUtils.getInstance().checkNetworkState()) {
            mNoNetPager.setVisibility(View.VISIBLE);
            return;
        }
        isInitData = true;
        mLoadingPager.setVisibility(View.VISIBLE);
        getDataFromNet();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mNoNetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoNetPager.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.VISIBLE);
                getDataFromNet();
            }
        });

        mRefreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());
    }

    /**
     * 1.联网请求热映页面的列表数据
     */
    private void getDataFromNet() {
        if (isInitData == false) {
            isInitData = true;
        }

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.VISIBLE);

        RequestCall call = OkHttpUtils.get().url(HOT_MOVIE_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mNoNetPager.setVisibility(View.VISIBLE);
                mHotMovieRecyclerView.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.GONE);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });

    }

    /**
     * 2.处理请求成功的数据
     * @param json
     */
    private void processData(String json) {
        HotMovieBean hotMovieBean = new Gson().fromJson(json, HotMovieBean.class);
        datas = hotMovieBean.getData().getMovies();

        setView();
    }

    /**
     * 3.配置recyclerView的详细内容
     */
    private void setView() {

        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mHotMovieRecyclerView.setVisibility(View.VISIBLE);

        adapter = new HotMovieAdapter(context,datas);
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mHotMovieRecyclerView.setLayoutManager(manager);
        //设置adapter
        mHotMovieRecyclerView.setAdapter(adapter);
    }


    private class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onfinish() {
            super.onfinish();
            isRefreshing = false;
            Toast.makeText(context, "刷新完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);

            materialRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRefreshLayout.finishRefreshLoadMore();
                    Toast.makeText(context, "加载完成", Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        }

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }
}

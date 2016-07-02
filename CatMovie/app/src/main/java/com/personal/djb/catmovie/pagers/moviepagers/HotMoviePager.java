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
import com.personal.djb.catmovie.adapter.MovieAdapter;
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

//    private final String HOT_MOVIE_URL = "http://api.meituan.com/mmdb/movie/v3/list/hot.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=864394100811969&net=255&dModel=VPhone&uuid=34E86498880DD102B3AE536CBD0B91E18FE1C6AF0DF103828C211090ED4196FD&lat=39.906899375649395&lng=116.39723909965588&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1467009899799&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=628150cd-6c6e-40b9-8dee-218d138f24e7&__skcy=tjWgaZc35IaYrtk3AzYwTLnS0gI%3D";
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
    private MovieAdapter adapter;
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

        NetUtils.getInstance().setHotMovies(datas);
        setView();
    }

    /**
     * 3.配置recyclerView的详细内容
     */
    private void setView() {

        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mHotMovieRecyclerView.setVisibility(View.VISIBLE);

        adapter = new MovieAdapter(context,datas, MovieAdapter.HOT_MOVIE);
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
                    Toast.makeText(context, "抱歉，没有更多数据..", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        }

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }
}

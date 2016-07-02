package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.adapter.FindAdapter;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.bean.findbean.FindBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class FindRadioButtonPager extends BasePager {

    //    private final String HOT_MOVIE_URL = "http://api.meituan.com/mmdb/movie/v3/list/hot.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=864394100811969&net=255&dModel=VPhone&uuid=34E86498880DD102B3AE536CBD0B91E18FE1C6AF0DF103828C211090ED4196FD&lat=39.906899375649395&lng=116.39723909965588&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1467009899799&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=628150cd-6c6e-40b9-8dee-218d138f24e7&__skcy=tjWgaZc35IaYrtk3AzYwTLnS0gI%3D";
    private final String FIND_URL = "http://api.meituan.com/sns/v2/feed.json?offset=0&limit=10&timestamp=0&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=baidumobile1&utm_medium=android&utm_term=6.8.0&utm_content=863777020121611&ci=1&net=255";

    private final String FIND_URL_PRE = "http://api.meituan.com/sns/v2/feed.json?offset=10&limit=10&timestamp=1467265117771&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=864394100811969&ci=55&net=255&dModel=VPhone&uuid=34E86498880DD102B3AE536CBD0B91E18FE1C6AF0DF103828C211090ED4196FD&lat=39.906899375649395&lng=116.39723909965588&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1467282243019&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=85726a51-79d9-418b-8c70-d9c6af6b25a6&__skcy=Nr5J9OY%2BryPO2q1pgLr60zSR4YY%3D";

    private RecyclerView mHotMovieRecyclerView;
    private int count = 0;
    /**
     * 热映电影的集合
     */
    private FindBean.DataBean datas;
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
    private FindAdapter adapter;
    private boolean isRefreshing = false;
    private StickyRecyclerHeadersDecoration headersDecor;

    public FindRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {

        view = View.inflate(context, R.layout.find_pager, null);

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

        RequestCall call = OkHttpUtils.get().url(FIND_URL).build();
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
        FindBean findBean = new Gson().fromJson(json, FindBean.class);
        datas = findBean.getData();

        setView();
    }

    /**
     * 3.配置recyclerView的详细内容
     */
    private void setView() {

        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mHotMovieRecyclerView.setVisibility(View.VISIBLE);

        //  如果是刷新走这里
        if (isRefreshing) {
            mRefreshLayout.finishRefresh();
            adapter.setDatas(datas);

            return;
        }

        adapter = new FindAdapter(context,datas);
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mHotMovieRecyclerView.setLayoutManager(manager);
        //设置adapter
        headersDecor = new StickyRecyclerHeadersDecoration(adapter); //绑定之前的adapter

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });  //刷新数据的时候回刷新头部

        mHotMovieRecyclerView.addItemDecoration(headersDecor);
        headersDecor.invalidateHeaders();

        mHotMovieRecyclerView.setAdapter(adapter);
    }


    private class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onfinish() {
            super.onfinish();
            isRefreshing = false;
            count = 0;
            Toast.makeText(context, "刷新完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);

            materialRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getMoreDataFromNet();
                }
            }, 1200);
        }

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }

    /**
     * 加载更多数据
     */
    private void getMoreDataFromNet() {
        if (count > 2) {
            mRefreshLayout.finishRefreshLoadMore();
            Toast.makeText(context, "没有更多数据了，很遗憾", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils.get().url(FIND_URL_PRE).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(context, "加载失败，很抱歉！", Toast.LENGTH_SHORT).show();
                mRefreshLayout.finishRefreshLoadMore();
            }

            @Override
            public void onResponse(String response, int id) {
                parseMoreData(response);
            }
        });
    }

    /**
     * 加载更多以后处理数据
     * @param json
     */
    private void parseMoreData(String json) {
        count++;
        FindBean findBean = new Gson().fromJson(json, FindBean.class);
        FindBean.DataBean dataMore = findBean.getData();
        adapter.setMoreDatas(dataMore);
        mRefreshLayout.finishRefreshLoadMore();
    }
}

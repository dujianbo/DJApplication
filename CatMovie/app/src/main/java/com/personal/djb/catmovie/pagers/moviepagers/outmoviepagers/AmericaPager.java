package com.personal.djb.catmovie.pagers.moviepagers.outmoviepagers;

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
import com.personal.djb.catmovie.adapter.outmovieadapter.AmericaMovieAdapter;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.bean.movies.outmoviebean.AmericaMovieBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class AmericaPager extends BasePager {

    private final String AMERICA_MOVIE_URL = "http://api.meituan.com/mmdb/movie/oversea/coming.json?area=NA&offset=0&limit=10&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099337&lng=116.379463&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463979002816&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=ff7d7258-bf35-49b1-8350-438616853e0b&__skcy=oNlmWR0dK8QqRyoPuJ9SQPBnvk0%3D";

    private RecyclerView mHotMovieRecyclerView;
    /**
     * 美国电影电影的集合
     */
    private AmericaMovieBean datas;
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
    private AmericaMovieAdapter adapter;
    private boolean isRefreshing = false;

    private StickyRecyclerHeadersDecoration headersDecor;

    public AmericaPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "电影页面的美国电影initView");

        view = View.inflate(context, R.layout.out_common_movie_pager, null);

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
        Log.e("TAG", "电影页面的美国电影initData");
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
     * 1.联网请求美国电影页面的列表数据
     */
    private void getDataFromNet() {
        if (isInitData == false) {
            isInitData = true;
        }

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.VISIBLE);

        RequestCall call = OkHttpUtils.get().url(AMERICA_MOVIE_URL).build();
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
//                Log.e("xxx", AMERICA_MOVIE_URL);
                processData(response);
            }
        });

    }

    /**
     * 2.处理请求成功的数据
     * @param json
     */
    private void processData(String json) {
        AmericaMovieBean americaMovieBean = new Gson().fromJson(json, AmericaMovieBean.class);
        datas = americaMovieBean;

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

        adapter = new AmericaMovieAdapter(context,datas);
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        mHotMovieRecyclerView.setLayoutManager(manager);

        headersDecor = new StickyRecyclerHeadersDecoration(adapter); //绑定之前的adapter

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });  //刷新数据的时候回刷新头部

        mHotMovieRecyclerView.addItemDecoration(headersDecor);
        headersDecor.invalidateHeaders();

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
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }
}

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
import com.personal.djb.catmovie.bean.movies.WaitMovieBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/24 0024.
 */
public class WaitMoviePager extends BasePager {

    private final String WAIT_MOVIE_URL = "http://api.meituan.com/mmdb/movie/v2/list/rt/order/coming.json?ci=1&limit=12&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content=868030022327462&net=255&dModel=MI%205&uuid=0894DE03C76F6045D55977B6D4E32B7F3C6AAB02F9CEA042987B380EC5687C43&lat=40.100673&lng=116.378619&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463704714271&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1a0b4a9b-44ec-42fc-b110-ead68bcc2824&__skcy=sXcDKbGi20CGXQPPZvhCU3%2FkzdE%3D";

    private RecyclerView mWAITMovieRecyclerView;
    /**
     * 待映电影的集合
     */
    private List<WaitMovieBean.DataBean.ComingBean> datas;
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
    private StickyRecyclerHeadersDecoration headersDecor;

    public WaitMoviePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "电影页面的待映initView");

        view = View.inflate(context, R.layout.hotmovie_pager, null);

        findView();
        return view;
    }

    private void findView() {
        mWAITMovieRecyclerView = (RecyclerView) view.findViewById(R.id.hotmovie_recyclerview);
        mNoNetPager = (RelativeLayout) view.findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) view.findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) view.findViewById(R.id.iv_netload_nonet);
        mWAITMovieRecyclerView.setVisibility(View.GONE);

        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        mRefreshLayout.setSunStyle(true);

        setListener();
    }

    @Override
    public void initData() {
        Log.e("TAG", "电影页面的待映initData");
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
     * 1.联网请求待映页面的列表数据
     */
    private void getDataFromNet() {
        if (isInitData == false) {
            isInitData = true;
        }

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.VISIBLE);

        RequestCall call = OkHttpUtils.get().url(WAIT_MOVIE_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mNoNetPager.setVisibility(View.VISIBLE);
                mWAITMovieRecyclerView.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.GONE);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });

    }

    /**
     * 2.处理请求成功的数据
     * @param json
     */
    private void processData(String json) {
        WaitMovieBean waitMovieBean = new Gson().fromJson(json, WaitMovieBean.class);
        datas = waitMovieBean.getData().getComing();

        setView();
    }

    /**
     * 3.配置recyclerView的详细内容
     */
    private void setView() {

        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mWAITMovieRecyclerView.setVisibility(View.VISIBLE);

        if (isRefreshing) {
            mRefreshLayout.finishRefresh();
            adapter.setDatas(datas);

            return;
        }

        adapter = new MovieAdapter(context,datas,MovieAdapter.WAIT_MOVIE);
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);

        headersDecor = new StickyRecyclerHeadersDecoration(adapter); //绑定之前的adapter

        mWAITMovieRecyclerView.setLayoutManager(manager);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });  //刷新数据的时候回刷新头部

        mWAITMovieRecyclerView.addItemDecoration(headersDecor);
        headersDecor.invalidateHeaders();

        //设置adapter
        mWAITMovieRecyclerView.setAdapter(adapter);
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

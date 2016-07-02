package com.personal.djb.catmovie.pagers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.SearchActivity;
import com.personal.djb.catmovie.adapter.CinemaAdapter;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.bean.cinema.CinemaBean;
import com.personal.djb.catmovie.bean.cinema.PlaceBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class CinemaRadioButtonPager extends BasePager {

    private String CINEMA_URL = "http://m.maoyan.com/cinemas.json";

    private String PLACE_NET_URL = "http://api.meituan.com/group/v1/city/latlng/40.100168,116.379677?tag=0&__vhost=api.mobile.meituan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6901&utm_source=xiaomi&utm_medium=android&utm_term=6.9.0&utm_content=867515025248252&ci=1&net=255&dModel=Redmi%20Note%202&uuid=6EB9FAAFA10A6C010EBB1A7B00F74A501A378CDE3EBF6261A49B9727ECF79B4D&lat=40.100168&lng=116.379677&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1466660891503&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=62d6ceed-3363-4657-b44c-360020ba3fa6&__skcy=hdKur%2FrsWbF0B085kZFa%2FLMiSrU%3D";

    private View view;

    private ImageButton mBtnChoicePlace;

    private List<CinemaBean.BaseBean> data;

    /**
     * 我的当前位置
     */
    private TextView mCurPlace;

    /**
     * 没网的页面
     */
    private RelativeLayout mNoNetPager;
    private ImageView mNoNetButton;
    private TextView mTitleCinemaName;
    /**
     * 缓冲的页面
     */
    private RelativeLayout mLoadingPager;

    /**
     * 下拉刷新控件
     */
    private MaterialRefreshLayout mRefreshLayout;

    /**
     * 选择城市的按钮
     */
    private Button mBtnChoiceCity;
    /**
     * 搜索的按钮
     */
    private RelativeLayout mBtnSearchRl;
    private ImageButton mBtnSearch;

    private RecyclerView mCinemaRecyclerView;
    private boolean isRefreshing = false;
    private CinemaAdapter adapter;

    public CinemaRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton电影页面initView");

        view = View.inflate(context, R.layout.cinerma_main_pager,null);

        findView();
        return view;
    }

    private void findView() {
        mNoNetPager = (RelativeLayout) view.findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) view.findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) view.findViewById(R.id.iv_netload_nonet);

        mBtnChoiceCity = (Button) view.findViewById(R.id.common_title_city);
        mBtnSearchRl = (RelativeLayout) view.findViewById(R.id.common_title_search);
        mBtnSearch = (ImageButton) view.findViewById(R.id.common_title_search_search);
        mBtnChoicePlace = (ImageButton) view.findViewById(R.id.btn_cinema_choiceplace);
        mTitleCinemaName = (TextView) view.findViewById(R.id.tv_title_cinema_name);
        mCurPlace = (TextView) view.findViewById(R.id.tv_mycurrplace);

        mTitleCinemaName.setVisibility(View.VISIBLE);
        mBtnSearchRl.setVisibility(View.VISIBLE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mBtnChoiceCity.setVisibility(View.VISIBLE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mBtnChoicePlace.setVisibility(View.VISIBLE);

        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        mRefreshLayout.setSunStyle(true);


        mCinemaRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_main_cinema);
        mCinemaRecyclerView.setVisibility(View.GONE);
        mCurPlace.setVisibility(View.GONE);

        setListener();
    }

    @Override
    public void initData() {
        Log.e("TAG", "radiobutton电影页面initData");
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

        getMyPlaceFromNet();
    }

    /**
     * 联网获取自己当前位置
     */
    private void getMyPlaceFromNet() {
        RequestCall call = OkHttpUtils.get().url(PLACE_NET_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                processPlaceData(response);
            }
        });
    }

    /**
     * 联网动态获取地址
     * @param json
     */
    private void processPlaceData(String json) {
        PlaceBean placeBean = new Gson().fromJson(json, PlaceBean.class);
        String detail = placeBean.getData().getDetail();
        mCurPlace.setText(detail);
    }

    /**
     * 1.联网请求影院数据
     */
    private void getDataFromNet() {
        if (isInitData == false) {
            isInitData = true;
        }

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.VISIBLE);

        RequestCall call = OkHttpUtils.get().url(CINEMA_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mNoNetPager.setVisibility(View.VISIBLE);
                mCinemaRecyclerView.setVisibility(View.GONE);
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
     * 解析影院数据
     * @param json
     */
    private void processData(String json) {
        Log.e("du", json);

        CinemaBean cinemaBean = new Gson().fromJson(json, CinemaBean.class);

        data = new ArrayList<>();
        data.addAll(cinemaBean.getData().getChangping());
        data.addAll(cinemaBean.getData().getChaoyang());
        data.addAll(cinemaBean.getData().getDaxing());
        data.addAll(cinemaBean.getData().getDongcheng());
        data.addAll(cinemaBean.getData().getFangshan());
        data.addAll(cinemaBean.getData().getFengtai());
        data.addAll(cinemaBean.getData().getHaidian());
        data.addAll(cinemaBean.getData().getHuairou());
        data.addAll(cinemaBean.getData().getMentougou());
        data.addAll(cinemaBean.getData().getMiyun());
        data.addAll(cinemaBean.getData().getPinggu());
        data.addAll(cinemaBean.getData().getShijingshan());
        data.addAll(cinemaBean.getData().getShunyi());
        data.addAll(cinemaBean.getData().getTongzhou());
        data.addAll(cinemaBean.getData().getXicheng());
        data.addAll(cinemaBean.getData().getYanqin());

        Log.e("ceshi", "长度" + data.size() + "条" + data.toString());
        setView();
    }

    private void setView() {
        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mCinemaRecyclerView.setVisibility(View.VISIBLE);
        mCurPlace.setVisibility(View.VISIBLE);

        //  如果是刷新走这里
        if (isRefreshing) {
            mRefreshLayout.finishRefresh();
            adapter.setDatas(data);

            return;
        }

        adapter = new CinemaAdapter(context,data);

        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mCinemaRecyclerView.setLayoutManager(manager);

        mCinemaRecyclerView.setAdapter(adapter);
    }

    /**
     * 顶部导航栏的监听 等
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void setListener() {

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

        mBtnChoicePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "选择行政区等", Toast.LENGTH_SHORT).show();
            }
        });

        mNoNetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoNetPager.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.VISIBLE);
                getDataFromNet();
            }
        });

        mRefreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());

        mCinemaRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mCurPlace.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPlace.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        mCurPlace.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }

        });

        mCurPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG1111", "选择地址条被点击");
                getMyPlaceFromNet();
            }
        });
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

package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.findbean.YKTopBean;
import com.personal.djb.catmovie.bean.findbean.yingku.BoxBean;
import com.personal.djb.catmovie.bean.findbean.yingku.PraiseBean;
import com.personal.djb.catmovie.bean.findbean.yingku.TopOneBean;
import com.personal.djb.catmovie.bean.findbean.yingku.WishBean;
import com.personal.djb.catmovie.utils.CacheUtils;
import com.personal.djb.catmovie.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class CelluloideActivity extends Activity implements View.OnClickListener {

    private RelativeLayout titleYk;
    private ImageButton ibYkForback;
    private RelativeLayout rlYkTopBg;
    private TextView tvYkTopDay;
    private TextView tvYkTopMouth;
    private TextView tvYkTopDesc;
    private TextView tvYkTopPerson;
    private ImageView ivYkTopIcon;
    private ImageView topBgIv;

    /**
     * 口碑电影数据集合
     */
    private PraiseBean.DataBean mPraise;
    /**
     * 期待数据集合
     */
    private WishBean.DataBean mWish;
    /**
     * 票房数据集合
     */
    private BoxBean.DataBean mBox;
    /**
     * top100数据集合
     */
    private TopOneBean.DataBean mTop100;

    private List<YKTopBean.DataBean> topDatas;

    private final String YK_TOP_URL = "http://api.maoyan.com/mmdb/daily/recommend/latest/list.json?utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=xiaomi&utm_medium=android&utm_term=6.8.0&utm_content";

    private final String YK_PRAISE_URL = "http://api.meituan.com/mmdb/movieboard/fixedboard/7.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463727372645&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=26a19547-c4ad-438f-96af-0feadadba2b0&__skcy=S9pqtpXxrUm%2BgC2THRjoEuUzJoY%3D";

    private final String YK_WISH_URL = "http://api.meituan.com/mmdb/movieboard/fixedboard/6.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463727423837&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=bbd36610-f22a-4523-bcfb-59658c25056e&__skcy=Phj0IYaOnVVHPXIHPumsV6vBBrg%3D";

    private final String YK_BOX_URL = "http://api.meituan.com/mmdb/movieboard/fixedboard/2.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463712271641&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=0002470d-9e88-412f-a300-2a6e651a7e3e&__skcy=FN%2Ff1xBS0AohUxjhP7FKLe2JNlo%3D";

    private final String YK_TOP100_URL = "http://api.meituan.com/mmdb/movieboard/fixedboard/4.json?offset=0&limit=10&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AC98AD8183EC9108E7D92853C8D3EF972&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463712334518&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=7a1d1ed9-473e-49e9-ad52-b1b7bbd5a5b7&__skcy=nXjScwb2c0H8dO0LxNXZGfODzoI%3D";

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
    private boolean isRefreshing;
    private TextView tvYkTopPubdate;



    private RelativeLayout rlKoubei;
    private TextView tvYkNumber1;
    private ImageView ivYkSecondicon1;
    private RelativeLayout rlQidai;
    private TextView tvYkNumber2;
    private ImageView ivYkSecondicon2;
    private RelativeLayout rlPiaofang;
    private TextView tvYkNumber3;
    private ImageView ivYkSecondicon3;
    private RelativeLayout rlTop100;
    private TextView tvYkNumber4;
    private ImageView ivYkSecondicon4;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-06-30 21:12:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        titleYk = (RelativeLayout)findViewById( R.id.title_yk );
        ibYkForback = (ImageButton)findViewById( R.id.ib_yk_forback );
        rlYkTopBg = (RelativeLayout)findViewById( R.id.rl_yk_top_bg );
        tvYkTopDay = (TextView)findViewById( R.id.tv_yk_top_day );
        tvYkTopMouth = (TextView)findViewById( R.id.tv_yk_top_mouth );
        tvYkTopDesc = (TextView)findViewById( R.id.tv_yk_top_desc );
        tvYkTopPerson = (TextView)findViewById( R.id.tv_yk_top_person );
        ivYkTopIcon = (ImageView)findViewById(R.id.iv_yk_top_icon);
        tvYkTopPubdate = (TextView) findViewById(R.id.tv_yk_top_pubdate);
        topBgIv = (ImageView) findViewById(R.id.iv_top_bg);

        mNoNetPager = (RelativeLayout) findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) findViewById(R.id.iv_netload_nonet);

        rlKoubei = (RelativeLayout)findViewById( R.id.rl_koubei );
        tvYkNumber1 = (TextView)findViewById( R.id.tv_yk_number1 );
        ivYkSecondicon1 = (ImageView)findViewById( R.id.iv_yk_secondicon1 );
        rlQidai = (RelativeLayout)findViewById( R.id.rl_qidai );
        tvYkNumber2 = (TextView)findViewById( R.id.tv_yk_number2 );
        ivYkSecondicon2 = (ImageView)findViewById( R.id.iv_yk_secondicon2 );
        rlPiaofang = (RelativeLayout)findViewById( R.id.rl_piaofang );
        tvYkNumber3 = (TextView)findViewById( R.id.tv_yk_number3 );
        ivYkSecondicon3 = (ImageView)findViewById( R.id.iv_yk_secondicon3 );
        rlTop100 = (RelativeLayout)findViewById( R.id.rl_top100 );
        tvYkNumber4 = (TextView)findViewById( R.id.tv_yk_number4 );
        ivYkSecondicon4 = (ImageView)findViewById( R.id.iv_yk_secondicon4 );

        mRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mRefreshLayout.setSunStyle(true);

        setListener();
    }

    /**
     * 设置各种监听
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


        ibYkForback.setOnClickListener(this);
        rlTop100.setOnClickListener(this);
        rlKoubei.setOnClickListener(this);
        rlPiaofang.setOnClickListener(this);
        rlQidai.setOnClickListener(this);
        mRefreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());
    }

    /**
     * 总的联网方法，在此分发不同请求
     */
    private void getDataFromNet() {

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.GONE);

        //  检查网络连接
        if (!NetUtils.getInstance().checkNetworkState()) {
            mNoNetPager.setVisibility(View.VISIBLE);
            return;
        }
        mLoadingPager.setVisibility(View.VISIBLE);
        getTopDataFromNet();
        getSecoundDataFromNet();
    }

    /**
     * 第二部分的联网数据请求
     */
    private void getSecoundDataFromNet() {
        getKouBeiData();
        getQiDaiData();
        getPiaoFangData();
        getTop100Data();
    }

    /**
     * top100
     */
    private void getTop100Data() {
        RequestCall call = OkHttpUtils.get().url(YK_TOP100_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("xxx", AMERICA_MOVIE_URL);
                processTop100Data(response);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });
    }

    /**
     * top100数据处理
     * @param json
     */
    private void processTop100Data(String json) {
        TopOneBean topOneBean = new Gson().fromJson(json, TopOneBean.class);
        mTop100 = topOneBean.getData();

        tvYkNumber4.setText(mTop100.getMovies().get(0).getNm());
        String change = CacheUtils.change(mTop100.getMovies().get(0).getImg());
        Glide.with(this).load(change).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.splash_2).error(R.drawable.splash_2)
                .into(ivYkSecondicon4);
    }

    /**
     * 票房数据
     */
    private void getPiaoFangData() {
        RequestCall call = OkHttpUtils.get().url(YK_BOX_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("xxx", AMERICA_MOVIE_URL);
                processPiaoFangData(response);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });
    }

    /**
     * 票房数据
     * @param json
     */
    private void processPiaoFangData(String json) {
        BoxBean boxBean = new Gson().fromJson(json, BoxBean.class);
        mBox = boxBean.getData();

        tvYkNumber3.setText(mBox.getMovies().get(0).getNm());
        String change = CacheUtils.change(mBox.getMovies().get(0).getImg());
        Glide.with(this).load(change).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.splash_2).error(R.drawable.splash_2)
                .into(ivYkSecondicon3);
    }

    /**
     * 期待的数据
     */
    private void getQiDaiData() {
        RequestCall call = OkHttpUtils.get().url(YK_WISH_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("xxx", AMERICA_MOVIE_URL);
                processQiDaiData(response);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });
    }

    /**
     * 期待数据处理
     * @param json
     */
    private void processQiDaiData(String json) {
        WishBean wishBean = new Gson().fromJson(json, WishBean.class);
        mWish = wishBean.getData();

        tvYkNumber2.setText(mWish.getMovies().get(0).getNm());
        String change = CacheUtils.change(mWish.getMovies().get(0).getImg());
        Glide.with(this).load(change).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.splash_2).error(R.drawable.splash_2)
                .into(ivYkSecondicon2);
    }

    /**
     * 口碑数据
     */
    private void getKouBeiData() {
        RequestCall call = OkHttpUtils.get().url(YK_PRAISE_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("xxx", AMERICA_MOVIE_URL);
                processKouBeiData(response);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });
    }

    /**
     * 口碑数据
     * @param json
     */
    private void processKouBeiData(String json) {
        PraiseBean praiseBean = new Gson().fromJson(json, PraiseBean.class);
        mPraise = praiseBean.getData();

        tvYkNumber1.setText(mPraise.getMovies().get(0).getNm());
        String change = CacheUtils.change(mPraise.getMovies().get(0).getImg());
        Glide.with(this).load(change).diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.splash_2).error(R.drawable.splash_2)
                .into(ivYkSecondicon1);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-06-30 21:12:38 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == ibYkForback) {
            finish();
        } else if (v == ivYkTopIcon) {
            Toast.makeText(CelluloideActivity.this, "小图标被点击", Toast.LENGTH_SHORT).show();
        } else if (v == rlKoubei) {
            Intent intent = new Intent(this, BillboardActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",mPraise);
            intent.putExtras(bundle);
            startActivity(intent);
            Toast.makeText(CelluloideActivity.this, "口碑被点击", Toast.LENGTH_SHORT).show();
        } else if (v == rlQidai) {
            Toast.makeText(CelluloideActivity.this, "期待被点击", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BillboardActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",mWish);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (v == rlPiaofang) {
            Toast.makeText(CelluloideActivity.this, "票房被点击", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BillboardActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",mBox);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (v == rlTop100) {
            Toast.makeText(CelluloideActivity.this, "Top100被点击", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BillboardActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",mTop100);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    /**
     * activity生成
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_celluloide);

        findViews();

        getDataFromNet();
    }

    /**
     * 头部数据的请求
     */
    private void getTopDataFromNet() {

        RequestCall call = OkHttpUtils.get().url(YK_TOP_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
//                Log.e("xxx", AMERICA_MOVIE_URL);
                processTopData(response);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });
    }

    /**
     * 解析头部数据的
     * @param json
     */
    private void processTopData(String json) {

        YKTopBean ykTopBean = new Gson().fromJson(json, YKTopBean.class);
        topDatas = ykTopBean.getData();

        Glide.with(this).load(topDatas.get(0).getHorImg()).error(R.drawable.default_bg)
                .placeholder(R.drawable.default_bg)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(topBgIv);

        String topIconUrl = topDatas.get(0).getImg();
        topIconUrl = topIconUrl.replace("w.h","165.220");
        Glide.with(this).load(topIconUrl).error(R.drawable.splash_2)
                .placeholder(R.drawable.splash_2)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivYkTopIcon);
        setView();
    }

    private void setView() {
        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);

        setTopView();
    }

    /**
     * 设置头部数据
     */
    private void setTopView() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM月");
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        Log.e("jianbo", "时间=====" + format);
        String regularExpression = "/";
        String[] split = format.split(regularExpression);

        tvYkTopDay.setText(split[0]);
        tvYkTopMouth.setText(" / "+split[1]);

        tvYkTopDesc.setText(topDatas.get(0).getContent());
        tvYkTopPerson.setText(topDatas.get(0).getObjectName());
        tvYkTopPubdate.setText(topDatas.get(0).getPubDate()+"");
    }

    private class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onfinish() {
            super.onfinish();
            isRefreshing = false;
            Toast.makeText(CelluloideActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }

}

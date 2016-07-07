package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
import com.personal.djb.catmovie.bean.FunnyBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MostTypeActivity extends Activity {

    private Button mBtnForBack;
//    private RecyclerView mTypeRec;
//    private RecyclerView mPlaceRec;
    private RecyclerView mFunnyRec;
    private boolean isRefreshing = false;

    private String[] types = {"全部","剧情","喜剧","爱情","动画","动作","恐怖","惊悚","悬疑","冒险","科幻","犯罪","战争","纪录片","其他",};
    private String[] places = {"全部","大陆","美国","法国","英国","日本","韩国","印度","泰国","港台","德国","其他",};
    private Context me;
    private MyCommonAdapter typeAdapter;
    private MyCommonAdapter placeAdapter;
    private int tpyeposition;
    private int placeposition;

    public static final int TYPE_TYPE = 1;
    public static final int PLACE_TYPE = 2;
    private RecyclerView.LayoutManager typeManager;
    private RecyclerView.LayoutManager placeManager;

    private final String FUNNY_URL = "http://api.meituan.com/mmdb/search/integrated/keyword/list.json?almtype=1&keyword=%E7%9A%84&stype=-1&refer=1&iscorrected=false&limit=10&offset=0&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=qihu360-dy&utm_medium=android&utm_term=6.8.0&utm_content=351670061374943&ci=1&net=255&dModel=GT-I9507V&uuid=2712263ED4FA063021DC526C3CD1B2761F241E61575350470EA2532AD93026A0&lat=40.09955&lng=116.379719&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1464023832122&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=1499602e-5b7a-4f74-b147-46b7a37e5a10&__skcy=SeQjBqKkNc9kO64%2FTFMql%2BkGkWw%3D";

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
    private List<FunnyBean.DataBean.ListBean> funnyMovies;
    private MySearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_most_type);

        me = this;
        findView();

        tpyeposition = getIntent().getIntExtra("tpyeposition", 0);
        placeposition = getIntent().getIntExtra("placeposition", 0);

        setListener();

        setView();
    }

    private void setView() {

        //  类型
//        typeAdapter = new MyCommonAdapter(types,tpyeposition,TYPE_TYPE);
//        typeManager = new LinearLayoutManager(me,LinearLayoutManager.HORIZONTAL,false);
//        mTypeRec.setLayoutManager(typeManager);
//        mTypeRec.setAdapter(typeAdapter);
//
//        //  地区
//        placeAdapter = new MyCommonAdapter(places,placeposition,PLACE_TYPE);
//        placeManager = new LinearLayoutManager(me,LinearLayoutManager.HORIZONTAL,false);
//        mPlaceRec.setLayoutManager(placeManager);
//        mPlaceRec.setAdapter(placeAdapter);

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.GONE);

        //  检查网络连接
        if (!NetUtils.getInstance().checkNetworkState()) {
            mNoNetPager.setVisibility(View.VISIBLE);
            return;
        }
        mLoadingPager.setVisibility(View.VISIBLE);


        getDataFromNet();

    }

    private void getDataFromNet() {
        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.VISIBLE);

        RequestCall call = OkHttpUtils.get().url(FUNNY_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mNoNetPager.setVisibility(View.VISIBLE);
                mFunnyRec.setVisibility(View.GONE);
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
     * 处理喜剧类型数据
     * @param json
     */
    private void processData(String json) {
        FunnyBean funnyBean = new Gson().fromJson(json, FunnyBean.class);
        funnyMovies = funnyBean.getData().get(0).getList();
        List<FunnyBean.DataBean.ListBean> temp = new ArrayList<>();
        temp.addAll(funnyMovies);
        funnyMovies.addAll(temp);

        setFunnyView();
    }

    //  设置喜剧页
    private void setFunnyView() {
//  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mFunnyRec.setVisibility(View.VISIBLE);

        //  如果是刷新走这里
        if (isRefreshing) {
            mRefreshLayout.finishRefresh();
            adapter.notifyDataSetChanged();

            return;
        }

        adapter = new MySearchAdapter();

        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(me,LinearLayoutManager.VERTICAL,false);
        mFunnyRec.setLayoutManager(manager);

        mFunnyRec.setAdapter(adapter);
    }

    private void setListener() {
        mBtnForBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRefreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());
    }

    private void findView() {
        mBtnForBack = (Button) findViewById(R.id.btn_mosttype_back);

//        mTypeRec = (RecyclerView) findViewById(R.id.type_rec);
//        mPlaceRec = (RecyclerView) findViewById(R.id.place_rec);
        mFunnyRec = (RecyclerView) findViewById(R.id.recy_funny);

        mNoNetPager = (RelativeLayout) findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) findViewById(R.id.iv_netload_nonet);

        mRefreshLayout = (MaterialRefreshLayout) findViewById(R.id.refresh);
        mRefreshLayout.setSunStyle(true);


        mFunnyRec.setVisibility(View.GONE);
    }


    /**
     * 类型和地区的适配器  切换按钮等
     */
    public class MyCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private int currType;
        private boolean first = true;
        private int myPosition;
        private String[] commonDatas;

        private int clickPosition = -1;
        private boolean isFirstClick = false;

        public MyCommonAdapter(String[] datas,int myPosition,int type){
            this.commonDatas = datas;
            this.myPosition = myPosition;
            this.currType = type;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(me).
                    inflate(R.layout.common_mosttype_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyCommonAdapter.ViewHolder)holder).setData(position);
        }

        @Override
        public int getItemCount() {
            return commonDatas.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            private TextView mCommonItem;

            public ViewHolder(final View itemView) {
                super(itemView);

                mCommonItem = (TextView) itemView.findViewById(R.id.tv_type_most);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickPosition = getLayoutPosition();
                        if (!isFirstClick) {
                            isFirstClick = true;
                        }
//                        switch (currType) {
//                            case TYPE_TYPE :
//                                typeAdapter.notifyDataSetChanged();
//                                break;
//                            case PLACE_TYPE:
//                                placeAdapter.notifyDataSetChanged();
//                                break;
//                        }
                        switch (currType) {
                            case TYPE_TYPE:
                                Toast.makeText(me, types[clickPosition], Toast.LENGTH_SHORT).show();
                                break;
                            case PLACE_TYPE:
                                Toast.makeText(me, places[clickPosition], Toast.LENGTH_SHORT).show();
                                break;
                        }
                        notifyDataSetChanged();

                    }
                });


            }

            public void setData(int position) {
                mCommonItem.setText(commonDatas[position]);

                mCommonItem.setEnabled(false);
                if (clickPosition != -1 && clickPosition == position) {
                    mCommonItem.setEnabled(true);
                }

                //  如果进行过一次点击才把first置为false
                if (position == myPosition && first) {
                    if(isFirstClick) {
                        first = false;
                        return;
                    }
                    mCommonItem.setEnabled(true);
                }

            }
        }
    }

    /**
     * 用于实时搜索
     */
    private class MySearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == 0 || viewType ==1) {
                MostHeadViewHolder holder = new MostHeadViewHolder(LayoutInflater.from(me).inflate(
                        R.layout.search_most_type_item, parent, false
                ));
                return holder;
            }

            SearchViewHolder holder = new SearchViewHolder(LayoutInflater.from(me).inflate(
                    R.layout.search_funny_item, parent, false
            ));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(position == 0) {
                ((MostHeadViewHolder)holder).setData1();
                return;
            }

            if(position == 1) {
                ((MostHeadViewHolder)holder).setData2();
                return;
            }
            ((SearchViewHolder)holder).setData(position - 2);
        }

        @Override
        public int getItemCount() {
            return funnyMovies.size() + 2;
        }

        @Override
        public int getItemViewType(int position) {
            if(position == 0) {
                return 0;
            } else if (position == 1) {
                return 1;
            } else {
                return 2;
            }
        }

        public class SearchViewHolder extends RecyclerView.ViewHolder{

            private ImageView ivItemHotmovieIcon;
            private TextView tvNumber;
            private TextView tvTimeMovieName;
            private TextView tvItemHotmovieScore;
            private TextView tvTimeMovieStar;
            private TextView tvItemHotmovieDesc;



            private TextView tvEnm;

            public SearchViewHolder(View itemView) {
                super(itemView);
                ivItemHotmovieIcon = (ImageView)itemView.findViewById(R.id.iv_item_hotmovie_icon);
                tvNumber = (TextView)itemView.findViewById(R.id.tv_number);
                tvTimeMovieName = (TextView)itemView.findViewById(R.id.tv_time_movie_name);
                tvItemHotmovieScore = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_score);
                tvTimeMovieStar = (TextView)itemView.findViewById(R.id.tv_time_movie_star);
                tvItemHotmovieDesc = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_desc);
                tvEnm = (TextView) itemView.findViewById(R.id.tv_enm);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(me, WebActivity.class);
                        int funnyId = funnyMovies.get(getLayoutPosition()).getId();
                        String funnyUrl = "http://m.maoyan.com/movie/" + funnyId + "?_v_=yes";
                        intent.putExtra("url", funnyUrl);
                        startActivity(intent);
                    }
                });
            }

            public void setData(int position) {
                FunnyBean.DataBean.ListBean moviesBean = funnyMovies.get(position);

                String imgUrl = moviesBean.getImg();
                imgUrl = imgUrl.replace("w.h", "165.220");
                Glide.with(me).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.splash_2).error(R.drawable.splash_2).into(ivItemHotmovieIcon);

                tvTimeMovieName.setText(moviesBean.getNm());
                tvEnm.setText(moviesBean.getEnm());

                tvTimeMovieStar.setText(moviesBean.getCat());
                tvItemHotmovieDesc.setText(moviesBean.getPubDesc());

//                llItemHotmovieScore.setVisibility(View.GONE);
//                rlItemHotmovieWish.setVisibility(View.GONE);
//                llItemHotmovieScore.setVisibility(View.VISIBLE);
                tvItemHotmovieScore.setText(moviesBean.getSc()+"");

                tvNumber.setText((position + 1) + "");
                tvNumber.setEnabled(false);
                if (position < 3) {
                    tvNumber.setEnabled(true);
                }
            }
        }

        public class MostHeadViewHolder extends RecyclerView.ViewHolder{

            private RecyclerView recyclerView;
            private TextView my_tv_test;

            public MostHeadViewHolder(View itemView) {
                super(itemView);

                recyclerView = (RecyclerView) itemView.findViewById(R.id.type_rec);
                my_tv_test = (TextView) itemView.findViewById(R.id.my_tv_test);
            }

            public void setData1() {
                //  类型
                typeAdapter = new MyCommonAdapter(types,tpyeposition,TYPE_TYPE);
                typeManager = new LinearLayoutManager(me,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(typeManager);
                recyclerView.setAdapter(typeAdapter);


            }

            public void setData2() {
                my_tv_test.setVisibility(View.VISIBLE);
                //  地区
                placeAdapter = new MyCommonAdapter(places,placeposition,PLACE_TYPE);
                placeManager = new LinearLayoutManager(me,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(placeManager);
                recyclerView.setAdapter(placeAdapter);

            }
        }
    }


    private class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onfinish() {
            super.onfinish();
            isRefreshing = false;
            Toast.makeText(me, "刷新完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }
}

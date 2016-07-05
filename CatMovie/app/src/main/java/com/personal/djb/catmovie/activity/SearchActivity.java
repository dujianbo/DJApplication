package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.HotSearchBean;
import com.personal.djb.catmovie.bean.movies.HotMovieBean;
import com.personal.djb.catmovie.utils.CacheUtils;
import com.personal.djb.catmovie.utils.NetUtils;
import com.personal.djb.catmovie.utils.PinyinTool;
import com.personal.djb.catmovie.view.DividerGridItemDecoration;
import com.personal.djb.catmovie.view.DividerItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

public class SearchActivity extends Activity {

    private RelativeLayout mRlForSearch;
    private EditText mEtForSerach;
    private Button mTvCancel;
    private RecyclerView mSearchRecyclerView;
    private List<HotSearchBean.DataBean> datas;
    private RecyclerView mTypeSearchRecyclerView;
    private RecyclerView mPlaceSearchRecyclerView;

    private String[] types = {"剧情","喜剧","爱情","动画","动作","恐怖","惊悚","悬疑","冒险","科幻","犯罪","战争","纪录片","其他"};
    private String[] places = {"大陆","美国","法国","英国","日本","韩国","印度","泰国","港台","德国","其他"};

    private String HOT_SEARCH_URL = "http://api.meituan.com/mmdb/search/movie/hotword/list.json?token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099064&lng=116.379351&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463657899016&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=23e3e90e-007e-4607-a43f-b3d609838bb8&__skcy=2MBnuWHEtyH9WJldBe5hskKYlgM%3D";
    private MyAdapter adapter;

    private Context me;
    private MyCommonAdapter typeAdapter;
    private MyCommonAdapter placeAdapter;
    private ImageView mIvDeleteText;
    private List<String> names;
    private HashMap<String, Integer> index;
    //  实时搜索结果
    private RecyclerView mTimeSearchView;
    //  实时搜索布局
    private RelativeLayout mRlTime;
    private MySearchAdapter timeAdapter;
    private TextView mSearchNoFind;
    private RecyclerView mHistoryRecyclerView;
    private MyHistoryAdapter historyAdapter;

    private List<String> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        me = this;
        findView();
        initView();

        setListener();
    }

    private void initView() {

        typeAdapter = new MyCommonAdapter(types,MostTypeActivity.TYPE_TYPE);
        RecyclerView.LayoutManager typeManager = new GridLayoutManager(me,4);
        mTypeSearchRecyclerView.setLayoutManager(typeManager);

        mTypeSearchRecyclerView.addItemDecoration(new DividerGridItemDecoration(me));
        mTypeSearchRecyclerView.setAdapter(typeAdapter);


        placeAdapter = new MyCommonAdapter(places,MostTypeActivity.PLACE_TYPE);
        RecyclerView.LayoutManager placeManager = new GridLayoutManager(me,4);
        mPlaceSearchRecyclerView.setLayoutManager(placeManager);

        mPlaceSearchRecyclerView.addItemDecoration(new DividerGridItemDecoration(me));
        mPlaceSearchRecyclerView.setAdapter(placeAdapter);
        //  实时搜索显示的库
        timeMovies = new ArrayList<>();
        //  历史记录
        history = new ArrayList<>();

        hotMovies = NetUtils.getInstance().getHotMovies();
        index = new HashMap<>();

        names = new ArrayList<>();
        if(hotMovies != null && hotMovies.size() > 0) {
            for(int i = 0; i < hotMovies.size(); i++) {
                String nm = hotMovies.get(i).getNm();
                names.add(nm);
                index.put(nm, i);
            }
        }

        Log.e("TAG2", names.toString());
        try {
            PinyinTool.setData(names);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        //  实时搜索
        timeAdapter = new MySearchAdapter();
        RecyclerView.LayoutManager timManager = new LinearLayoutManager(me,LinearLayoutManager.VERTICAL,false);
        mTimeSearchView.setLayoutManager(timManager);
        mTimeSearchView.setAdapter(timeAdapter);

        //  历史记录
        historyAdapter = new MyHistoryAdapter();
        RecyclerView.LayoutManager hisManager = new LinearLayoutManager(me,LinearLayoutManager.VERTICAL,false);
        mHistoryRecyclerView.setLayoutManager(hisManager);
        mHistoryRecyclerView.addItemDecoration(new DividerItemDecoration(me, DividerItemDecoration.VERTICAL_LIST));
        mHistoryRecyclerView.setAdapter(historyAdapter);

        //  获取本地保存的历史记录
        parseHistoryData();

        getDataFromNet();
    }

    private void parseHistoryData() {

        Log.e("Hi", "测试1");
        String cacheHistory = CacheUtils.getString(me,"history");
        if (!TextUtils.isEmpty(cacheHistory)) {
            String[] split = cacheHistory.split("&");
            List<String> strings = Arrays.asList(split);

            Log.e("Hi", strings.toString());
//            if(strings.size() > 2) {
//                strings.remove(strings.size()-1);
//            }
            history.clear();
            history.addAll(strings);

            Log.e("Hi", history.toString());
            Collections.reverse(history);

            historyAdapter.notifyDataSetChanged();
        }

    }

    /**
     * 根据名字找电影
     */
    private List<HotMovieBean.DataBean.MoviesBean> timeMovies;
    public List<HotMovieBean.DataBean.MoviesBean> toListFromName(List<String> strings) {
        timeMovies.clear();
        for(int i = 0; i < strings.size(); i++) {
            timeMovies.add(hotMovies.get(index.get(strings.get(i))));
        }
        return timeMovies;
    }

    private void getDataFromNet() {

        OkHttpUtils.get().url(HOT_SEARCH_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    private void processData(String json) {
        HotSearchBean hotSearchBean = new Gson().fromJson(json, HotSearchBean.class);
        datas = hotSearchBean.getData();

        adapter = new MyAdapter();
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mSearchRecyclerView.setLayoutManager(manager);
        mSearchRecyclerView.setAdapter(adapter);

    }

    private void setListener() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mIvDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtForSerach.setText("");
            }
        });

        mEtForSerach.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchMovie(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mIvDeleteText.setVisibility(View.GONE);
                    mRlTime.setVisibility(View.GONE);
                } else {
                    mIvDeleteText.setVisibility(View.VISIBLE);
                    mRlTime.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 实现搜索功能
     * @param text
     */
    private void searchMovie(String text) {
        final String str=text;
        new Thread(){

            @Override
            public void run() {
                String tt = PinyinTool.getSimple(str);
                names = PinyinTool.search(tt);
                Message message=new Message();
                message.what=0;
                mHandler.sendMessage(message);
            }


        }.start();
    }

    Handler mHandler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0) {
                Log.e("TAG", names.toString());
                timeMovies = toListFromName(names);
                if(timeMovies.size() > 0) {
                    mSearchNoFind.setVisibility(View.GONE);
                } else {
                    mSearchNoFind.setVisibility(View.VISIBLE);
                }
                timeAdapter.notifyDataSetChanged();
            }
        }

    };

    private List<HotMovieBean.DataBean.MoviesBean> hotMovies;

    private void findView() {
        mRlForSearch = (RelativeLayout) findViewById(R.id.common_title_rl_etandot);
        mEtForSerach = (EditText) findViewById(R.id.common_title_et_input);
        mTvCancel = (Button) findViewById(R.id.common_title_search_cancel);
        mSearchRecyclerView = (RecyclerView) findViewById(R.id.hot_search_recyclerview);

        mTypeSearchRecyclerView = (RecyclerView) findViewById(R.id.type_search_recyclerview);
        mPlaceSearchRecyclerView = (RecyclerView) findViewById(R.id.place_search_recyclerview);
        mIvDeleteText = (ImageView) findViewById(R.id.ivDeleteText);

        mHistoryRecyclerView = (RecyclerView) findViewById(R.id.history_recyclerview);

        mTimeSearchView = (RecyclerView) findViewById(R.id.serch_recy);
        mRlTime = (RelativeLayout) findViewById(R.id.rl_search);
        
        mSearchNoFind = (TextView) findViewById(R.id.tv_nofind);

        mRlForSearch.setVisibility(View.VISIBLE);
        mEtForSerach.setVisibility(View.VISIBLE);
        mTvCancel.setVisibility(View.VISIBLE);

    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    SearchActivity.this).inflate(R.layout.hot_search_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyAdapter.ViewHolder)holder).setData(datas.get(position).getValue());
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder{

            private TextView mTvHotSearch;

            public ViewHolder(View itemView) {
                super(itemView);

                mTvHotSearch = (TextView) itemView.findViewById(R.id.tv_hot_search);
            }

            public void setData(String value) {
                mTvHotSearch.setText(value);
            }
        }
    }

    private class MyCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private String[] commonDatas;
        private int currType;

        public MyCommonAdapter(String[] datas, int type){
            this.commonDatas = datas;
            this.currType = type;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(me).
            inflate(R.layout.common_search_item, parent, false));
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

            public ViewHolder(View itemView) {
                super(itemView);

                mCommonItem = (TextView) itemView.findViewById(R.id.tv_common_search);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(me, MostTypeActivity.class);
                        switch (currType) {
                            case MostTypeActivity.TYPE_TYPE :
                                intent.putExtra("tpyeposition",getLayoutPosition()+1);
                                break;
                            case MostTypeActivity.PLACE_TYPE :
                                intent.putExtra("placeposition",getLayoutPosition()+1);
                                break;
                        }
                        startActivity(intent);
                    }
                });
            }

            public void setData(int position) {
                mCommonItem.setText(commonDatas[position]);
            }
        }
    }

    /**
     * 用于实时搜索
     */
    private class MySearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            SearchViewHolder holder = new SearchViewHolder(LayoutInflater.from(me).inflate(
                    R.layout.search_time_item,parent,false
            ));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((SearchViewHolder)holder).setData(position);
        }

        @Override
        public int getItemCount() {
            return timeMovies.size();
        }

        public class SearchViewHolder extends RecyclerView.ViewHolder{

            private RelativeLayout ivItemHotmovieIconRl;
            private ImageView ivItemHotmovieIcon;
            private TextView tvNumber;
            private TextView tvTimeMovieName;
            private LinearLayout llItemHotmovieScore;
            private TextView tvItemHotmovieScore;
            private RelativeLayout rlItemHotmovieWish;
            private RelativeLayout rlWantgroup;
            private TextView tvDingwei;
            private TextView tvItemHotmovieWish1;
            private TextView tvTimeMovieStar;
            private TextView tvItemHotmovieDesc;
            private Button btnWantWatch;

            public SearchViewHolder(View itemView) {
                super(itemView);
                ivItemHotmovieIconRl = (RelativeLayout)itemView.findViewById(R.id.iv_item_hotmovie_icon_rl);
                ivItemHotmovieIcon = (ImageView)itemView.findViewById(R.id.iv_item_hotmovie_icon);
                tvNumber = (TextView)itemView.findViewById(R.id.tv_number);
                tvTimeMovieName = (TextView)itemView.findViewById(R.id.tv_time_movie_name);
                llItemHotmovieScore = (LinearLayout)itemView.findViewById(R.id.ll_item_hotmovie_score);
                tvItemHotmovieScore = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_score);
                rlItemHotmovieWish = (RelativeLayout)itemView.findViewById(R.id.rl_item_hotmovie_wish);
                rlWantgroup = (RelativeLayout)itemView.findViewById(R.id.rl_wantgroup);
                tvDingwei = (TextView)itemView.findViewById(R.id.tv_dingwei);
                tvItemHotmovieWish1 = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_wish1);
                tvTimeMovieStar = (TextView)itemView.findViewById(R.id.tv_time_movie_star);
                tvItemHotmovieDesc = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_desc);
                btnWantWatch = (Button)itemView.findViewById(R.id.btn_want_watch);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  将点击的电影名称传入本地历史记录
                        if (!history.contains(timeMovies.get(getLayoutPosition()).getNm())) {
                            String str1 = CacheUtils.getString(me, "history");
                            str1 = str1 + timeMovies.get(getLayoutPosition()).getNm() + "&";
                            Log.e("Hi", "测试");
                            Intent intent = new Intent(me, WebActivity.class);
                            int sarchId = timeMovies.get(getLayoutPosition()).getId();
                            String urlHot = "http://m.maoyan.com/movie/" + sarchId + "?_v_=yes";
                            intent.putExtra("url", urlHot);
                            startActivity(intent);
                            CacheUtils.putString(me, "history", str1);
                            parseHistoryData();
                        }
                    }
                });
            }

            public void setData(int position) {
                HotMovieBean.DataBean.MoviesBean moviesBean = timeMovies.get(position);

                Glide.with(me).load(moviesBean.getImg()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.splash_2).error(R.drawable.splash_2).into(ivItemHotmovieIcon);

                tvTimeMovieName.setText(moviesBean.getNm());

                tvTimeMovieStar.setText(moviesBean.getStar());
                tvItemHotmovieDesc.setText(moviesBean.getRt());

                llItemHotmovieScore.setVisibility(View.GONE);
                rlItemHotmovieWish.setVisibility(View.GONE);
                switch (moviesBean.getPreSale()) {
                    case 0 :
                        llItemHotmovieScore.setVisibility(View.VISIBLE);
                        tvItemHotmovieScore.setText(moviesBean.getSc()+"");
                        break;
                    case 1:
                        rlItemHotmovieWish.setVisibility(View.VISIBLE);
                        tvItemHotmovieWish1.setText(moviesBean.getWish()+"");
                        break;
                }

                tvNumber.setText((position + 1) + "");
                tvNumber.setEnabled(false);
                if (position < 3) {
                    tvNumber.setEnabled(true);
                }
            }
        }
    }

    /**
     * 历史记录recyclerView
     */
    public class MyHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HisViewHolder holder = new HisViewHolder(LayoutInflater.from(me).inflate(
                    R.layout.search_history_item,parent,false
            ));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((HisViewHolder)holder).setData(position);
        }

        @Override
        public int getItemCount() {
            return history.size();
        }

        public class HisViewHolder extends RecyclerView.ViewHolder {

            private TextView historyName;
            private ImageView deleteHistory;

            public HisViewHolder(View itemView) {
                super(itemView);

                historyName = (TextView) itemView.findViewById(R.id.history_name);
                deleteHistory = (ImageView) itemView.findViewById(R.id.iv_delete_history);

                deleteHistory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        history.remove(getLayoutPosition());
                        saveLocal(history);
                        historyAdapter.notifyDataSetChanged();
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String st = history.get(getLayoutPosition());
                        history.remove(st);
                        history.add(st);
                        Collections.reverse(history);
                        historyAdapter.notifyDataSetChanged();
                        mEtForSerach.setText(st);
                    }
                });
            }

            public void setData(int position) {
                historyName.setText(history.get(position));
            }
        }
    }

    private void saveLocal(List<String> afterHistory) {
        String aftStr = "";
        for(int i = 0; i < afterHistory.size(); i++) {
            aftStr += afterHistory.get(i)+"&";
        }

        CacheUtils.putString(me,"history",aftStr);
    }

}

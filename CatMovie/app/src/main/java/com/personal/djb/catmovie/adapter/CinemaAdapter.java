package com.personal.djb.catmovie.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.cinema.CinemaBean;
import com.personal.djb.catmovie.bean.movies.HotMovieHeadPagerBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class CinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;

    private List<CinemaBean.BaseBean> data;
    private boolean isSearch;

    public CinemaAdapter(Context context,List<CinemaBean.BaseBean> data,boolean isSearch) {
        this.context = context;
        this.data = data;
        this.isSearch = isSearch;
    }

    public void setDatas(List<CinemaBean.BaseBean> data) {
        this.data.clear();
        this.data.addAll(data);
    }

//    private final String HOTMOVIE_HEADPAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=11&version=6.8.0&new=0&app=movie&clienttp=android&uuid=FCFAB9D8DD339645D629C8372A29A2C6AD16F9C9E87AF9AC0D656B29DD5AC6DE&devid=866641027400542&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=qq&utm_medium=android&utm_term=6.8.0&utm_content=866641027400542&ci=1&net=255&dModel=HM%20NOTE%201LTETD&lat=40.100855&lng=116.378273&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463730432992&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=01f9c5c0-eb56-4e19-92fb-b86b16ad79da&__skcy=5K8wRR%2FKYAZDTgmAzbhrXi%2FomzU%3D";
    private final String URL_CINEMA_HEADER = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=12&version=6.8.0&new=0&app=movie&clienttp=android&uuid=2C2C0ECD557F366849954BEF88D0017AE8F745F113619A0301F4E4E123A4CD7E&devid=000000000000000&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBpushC110189035496448D-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Google%20Nexus%204%20-%204.3%20-%20API%2018%20-%20768x1280_1&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1466950481658&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=376da951-e975-440d-82b9-f4ac14788679&__skcy=a51B0teaxlLzzW5QEnyfCrcq2S0%3D";

    //  头部类型
    private static final int HEAD_TYPE = 0;
    //  默认类型
    private static final int DEFAULT_TYPE = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            //  头部
            HeadViewHolder headHolder = new HeadViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_hot_movie_head, parent, false));
            return headHolder;
        }

        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.cinema_item, parent, false));
        return holder;
    }

    /**
     * 给ViewHolder绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeadViewHolder) {
            ((CinemaAdapter.HeadViewHolder)holder).setData();
        } else if (holder instanceof ViewHolder) {
            ((CinemaAdapter.ViewHolder)holder).setData(position - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        //  分类型的加载view
        if (position == 0) {
            return HEAD_TYPE;
        }
        return DEFAULT_TYPE;
    }

    /**
     * 根据类型返回数目
     * @return
     */
    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    /**
     * 这个holder用来显示头部的viewpager
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder {

        private ViewPager mHotMovieHeadPager;
        private HeadPageAdapter adapter;
        //  热映页面的资源集合
        private List<HotMovieHeadPagerBean.DataBean> pageDatas;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mHotMovieHeadPager = (ViewPager) itemView.findViewById(R.id.vp_item_hot_movie_head);
        }

        public void setData() {
            if(isSearch) {
                mHotMovieHeadPager.setVisibility(View.GONE);
                return;
            }

            //  联网获取数据
            OkHttpUtils.get().url(URL_CINEMA_HEADER).build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    mHotMovieHeadPager.setVisibility(View.GONE);
                }

                @Override
                public void onResponse(String response, int id) {
                    processData(response);
                }
            });
        }

        private void processData(String json) {
            HotMovieHeadPagerBean hotMovieHeadPagerBean = new Gson().fromJson(json, HotMovieHeadPagerBean.class);
            pageDatas = hotMovieHeadPagerBean.getData();

            mHotMovieHeadPager.setVisibility(View.VISIBLE);
            adapter = new HeadPageAdapter();
            mHotMovieHeadPager.setAdapter(adapter);
            mHotMovieHeadPager.setCurrentItem(500);
            mHotMovieHeadPager.addOnPageChangeListener(new MyOnPageChangeListener());

            //  每3秒轮播图滚动一次
            handler.sendEmptyMessageDelayed(0,3000);
        }

        /**
         * 广告轮播图 通过handler实现
         */
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mHotMovieHeadPager.setCurrentItem(mHotMovieHeadPager.getCurrentItem() + 1);
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,3000);
            }
        };


        /**
         * 热映页面头部pager的适配器
         */
        private class HeadPageAdapter extends PagerAdapter {
            @Override
            public int getCount() {
                return 1000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(context).load(pageDatas.get(position%pageDatas.size()).getImgUrl())
                        .placeholder(R.drawable.lh).error(R.drawable.lh)
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
                container.addView(imageView);
                //  图片设置touch事件监听
                imageView.setOnTouchListener(new MyOnTouchListener());
                //  图片点击的时候链接到指定页面
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                return imageView;
            }

            /**
             * 图片触摸事件监听 保证顶部轮播图的自动播放
             */
            private class MyOnTouchListener implements View.OnTouchListener {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN://按下
                            //移除所有的消息和任务
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0,3000);
                            break;
                        case MotionEvent.ACTION_UP://离开
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0,3000);
                    }
                    return true;
                }
            }
        }

        /**
         * 重写方法 使得轮播图抓取的时候没有bug
         */
        private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            private boolean isDragging = false;
            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isDragging = true;
                    handler.removeCallbacksAndMessages(null);
                }else if(state == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                    isDragging = false;
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(0,3000);
                }else if(state == ViewPager.SCROLL_STATE_SETTLING&&isDragging){
                    isDragging = false;
                    handler.removeCallbacksAndMessages(null);
                    handler.sendEmptyMessageDelayed(0,3000);
                }
            }
        }
    }

    /**
     * 这个holder用来显示默认的列表
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        //  影院登录
        private RelativeLayout mCineaTitle;
        //  登录按钮
        private Button mCineaLogin;
        //  影院名称
        private TextView mCineaName;
        //  影院地址
        private TextView mCineaPlace;
        //  退，团，改的布局
        private LinearLayout mLLEatAndTuaGroup;
        private TextView mTui;
        private TextView mTuan;
        private TextView mGai;
        private TextView mQuan;
        private TextView mEat;
        private TextView mZuo;
        //  影院价格
        private TextView mCineaPrice;

        //  显示特惠的布局
        private RelativeLayout mRLForfoot;
        private RelativeLayout mRLForfoot1;
        //  特惠的文字显示
        private TextView mGood;
        private TextView mSales;
        private TextView mCinemaLength;

        public ViewHolder(View itemView) {
            super(itemView);

            findView(itemView);
        }

        /**
         * 实例化item的view
         * @param itemView
         */
        private void findView(View itemView) {
            mCineaName = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_name);
            mCineaPlace = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_desc);

            mCineaLogin = (Button) itemView.findViewById(R.id.btn_item_login);
            mLLEatAndTuaGroup = (LinearLayout) itemView.findViewById(R.id.ll_eat_tuan_group);

            mTui = (TextView) itemView.findViewById(R.id.tui);
            mTuan = (TextView) itemView.findViewById(R.id.tuan);
            mGai = (TextView) itemView.findViewById(R.id.gai);
            mQuan = (TextView) itemView.findViewById(R.id.quanjing);
            mEat = (TextView) itemView.findViewById(R.id.eat);
            mZuo = (TextView) itemView.findViewById(R.id.zuo);

            mRLForfoot = (RelativeLayout) itemView.findViewById(R.id.ll_item_fordingwei);
            mRLForfoot1 = (RelativeLayout) itemView.findViewById(R.id.ll_item_fordingwei1);

            mCineaPrice = (TextView) itemView.findViewById(R.id.iv_imax2d);

            mCineaTitle = (RelativeLayout) itemView.findViewById(R.id.tv_item_title_desc);

            mGood = (TextView) itemView.findViewById(R.id.tv_item_desc1);
            mSales = (TextView) itemView.findViewById(R.id.tv_item_desc2);
            mCinemaLength = (TextView) itemView.findViewById(R.id.cinema_length);

        }

        /**
         * 绑定每个item的数据
         * @param position
         */
        public void setData(int position) {
            CinemaBean.BaseBean baseBean = data.get(position);

            mCineaTitle.setVisibility(View.GONE);
            if (getLayoutPosition() == 1) {
                mCineaTitle.setVisibility(View.VISIBLE);
            }

            mCineaName.setText(baseBean.getNm());
            mCineaPlace.setText(baseBean.getAddr());
            mCineaPrice.setText(baseBean.getSellPrice() + "");

            mLLEatAndTuaGroup.setVisibility(View.VISIBLE);

            int value = (int) (Math.random() * 6);
            mTui.setVisibility(View.GONE);
            mTuan.setVisibility(View.GONE);
            mGai.setVisibility(View.GONE);
            mQuan.setVisibility(View.GONE);
            mEat.setVisibility(View.GONE);
            mCinemaLength.setText((Math.round(baseBean.getDjbDistance()*100)/100000.0)+"km");

            mRLForfoot1.setVisibility(View.GONE);
            if (baseBean.getDealPrice() != 0) {
                mRLForfoot1.setVisibility(View.VISIBLE);
                mSales.setText(baseBean.getSellPrice()+"元/影票，大促！！");
            }

            for(int i = 0; i < Math.random()*5; i++) {
                mLLEatAndTuaGroup.getChildAt((int) (Math.random() * 5) + 1).setVisibility(View.VISIBLE);
            }

            mRLForfoot.setVisibility(View.GONE);
            if (value == 5) {
                mRLForfoot.setVisibility(View.VISIBLE);
                mGood.setText(str[(int)(Math.random ()*4)]);
            }
        }
    }

    private String[] str = {"大海鱼棠等2部电影特惠","赏金猎人特惠","赏金猎人等2部电影特惠","大海鱼棠特惠"};
}

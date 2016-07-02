package com.personal.djb.catmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.personal.djb.catmovie.activity.WebActivity;
import com.personal.djb.catmovie.bean.movies.HotMovieBean;
import com.personal.djb.catmovie.bean.movies.HotMovieHeadPagerBean;
import com.personal.djb.catmovie.bean.movies.WaitMovieBean;
import com.personal.djb.catmovie.view.MyRecyclerView;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter{

    //  电影类型
    public static final int HOT_MOVIE = 10;
    public static final int WAIT_MOVIE = 11;

    //  头部类型
    public static final int HEAD_TYPE_TOP = 20;
    public static final int HEAD_TYPE_BOTTOM = 21;

    private int currType;
    //  热映电影的列表
    private List<HotMovieBean.DataBean.MoviesBean> hotMovieDatas;
    //  待映电影的列表
    private List<WaitMovieBean.DataBean.ComingBean> waitMovieDatas;
    private Context context;

    private final String HOTMOVIE_HEADPAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=11&version=6.8.0&new=0&app=movie&clienttp=android&uuid=FCFAB9D8DD339645D629C8372A29A2C6AD16F9C9E87AF9AC0D656B29DD5AC6DE&devid=866641027400542&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=qq&utm_medium=android&utm_term=6.8.0&utm_content=866641027400542&ci=1&net=255&dModel=HM%20NOTE%201LTETD&lat=40.100855&lng=116.378273&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463730432992&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=01f9c5c0-eb56-4e19-92fb-b86b16ad79da&__skcy=5K8wRR%2FKYAZDTgmAzbhrXi%2FomzU%3D";

    //  头部类型
    private static final int HEAD_TYPE = 0;
    //  默认类型
    private static final int DEFAULT_TYPE = 1;

    public MovieAdapter(Context context, List datas, int type){
        this.context = context;
        switch (type) {
            case HOT_MOVIE :
                hotMovieDatas = datas;
                break;
            case WAIT_MOVIE :
                waitMovieDatas = datas;
                break;
        }

        currType = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            //  头部
            HeadViewHolder headHolder = new HeadViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_hot_movie_head, parent, false));
            return headHolder;
        }

        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_hot_movie, parent, false));
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
            ((MovieAdapter.HeadViewHolder)holder).setData();
        } else if (holder instanceof ViewHolder) {
            ((MovieAdapter.ViewHolder)holder).setData(position - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
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
        switch (currType) {
            case HOT_MOVIE :
                return hotMovieDatas.size() + 1;
            case WAIT_MOVIE :
                return waitMovieDatas.size() + 1;
        }
        return -1;
    }

    public void setDatas(List<WaitMovieBean.DataBean.ComingBean> datas) {
        this.waitMovieDatas.clear();
        this.waitMovieDatas.addAll(datas);
    }

    /**
     * 这个holder用来显示头部的viewpager
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder {

        //  待映头部的布局
        private RelativeLayout mHotMovieHeadRl;
        //  待映头部的recyclerView1
        private MyRecyclerView  mHotMovieRecyclerView1;
        //  待映头部的recyclerView2
        private MyRecyclerView  mHotMovieRecyclerView2;




        private ViewPager mHotMovieHeadPager;
        private HeadPageAdapter adapter;
        //  热映页面的资源集合
        private List<HotMovieHeadPagerBean.DataBean> pageDatas;

        public HeadViewHolder(View itemView) {
            super(itemView);
            switch (currType) {
                case HOT_MOVIE :
                    mHotMovieHeadPager = (ViewPager) itemView.findViewById(R.id.vp_item_hot_movie_head);
                    break;
                case WAIT_MOVIE :
                    mHotMovieHeadRl = (RelativeLayout) itemView.findViewById(R.id.rl_head_waitmovie);
                    mHotMovieRecyclerView1 = (MyRecyclerView) itemView.findViewById(R.id.recyclerview_head_1);
                    mHotMovieRecyclerView2 = (MyRecyclerView) itemView.findViewById(R.id.recyclerview_head_2);
                    mHotMovieHeadRl.setVisibility(View.VISIBLE);
                    break;
            }

        }

        public void setData() {

            switch (currType) {
                case HOT_MOVIE :
                    //  联网获取数据
                    OkHttpUtils.get().url(HOTMOVIE_HEADPAGE_URL).build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            mHotMovieHeadPager.setVisibility(View.GONE);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            processData(response);
                        }
                    });
                    break;
                case WAIT_MOVIE:
                    setWaitMovieHeadData();
                    break;
            }
        }

        private WaitMovieHeadAdapter waitMovieHeadAdapterTop;
        private WaitMovieHeadAdapter waitMovieHeadAdapterBottom;

        /**
         * 设置待映页面头部的数据
         */
        private void setWaitMovieHeadData() {

            List<Integer> datas = new ArrayList<>();
            for(int i = 0; i < 20; i++) {
                datas.add(R.drawable.splash_image);
            }

            //设置布局管理器
            RecyclerView.LayoutManager manager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            RecyclerView.LayoutManager manager2 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            mHotMovieRecyclerView1.setLayoutManager(manager1);
            mHotMovieRecyclerView2.setLayoutManager(manager2);
            //设置adapter
            waitMovieHeadAdapterTop = new WaitMovieHeadAdapter(context,datas,HEAD_TYPE_TOP);
            mHotMovieRecyclerView1.setAdapter(waitMovieHeadAdapterTop);
            waitMovieHeadAdapterBottom = new WaitMovieHeadAdapter(context,datas,HEAD_TYPE_BOTTOM);
            mHotMovieRecyclerView2.setAdapter(waitMovieHeadAdapterBottom);
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
        private class HeadPageAdapter extends PagerAdapter{
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
        //  电影图标
        private ImageView mHotMovieIcon;
        //  电影名称
        private TextView mHotMovieName;
        //  电影评分
        private TextView mHotMovieScore;
        //  电影期待人数
        private TextView mHotMovieWish;
        //  电影描述
        private TextView mHotMovieDesc;
        //  电影场次
        private TextView mHotMovieCount;
        //  购票按钮
        private Button mHotMovieBtnBuy;
        //  预约按钮
        private Button mHotMovieBtnOrder;
        //  想看按钮
        private Button mHotMovieBtnWant;

        //  电影评分布局
        private LinearLayout mHotMovieScoreLinear;
        //  电影想看人数布局
        private LinearLayout mHotMovieWishLinear;

        //  电影上映时间
        private TextView mHotMovieDate;
        //  电影屏幕3d 2d等
        private ImageView mMaxScreen2D;
        private ImageView mMaxScreen3D;
        private ImageView mScreen3D;

        //  用来显示小新闻的布局
        private RelativeLayout mHotMovieSmallNews1;
        private RelativeLayout mHotMovieSmallNews2;

        //  小新闻的图标
        private TextView mHotMovieSmallNewsIcon1;
        private TextView mHotMovieSmallNewsIcon2;

        //  小新闻的描述
        private TextView mHotMovieSmallNewsDesc1;
        private TextView mHotMovieSmallNewsDesc2;

        //  item头部的描述信息
//        private TextView mHotMovieTitleDesc;

        public ViewHolder(View itemView) {
            super(itemView);

            findView(itemView);
        }

        /**
         * 实例化item的view
         * @param itemView
         */
        private void findView(View itemView) {
            mHotMovieIcon = (ImageView) itemView.findViewById(R.id.iv_item_hotmovie_icon);
            mHotMovieName = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_name);
            mHotMovieScore = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_score);
            mHotMovieWish = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_wish);
            mHotMovieDesc = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_desc);
            mHotMovieCount = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_count);

            mHotMovieBtnBuy = (Button) itemView.findViewById(R.id.btn_item_hotmovie_buy);
            mHotMovieBtnOrder = (Button) itemView.findViewById(R.id.btn_item_hotmovie_order);

            mHotMovieScoreLinear = (LinearLayout) itemView.findViewById(R.id.ll_item_hotmovie_score);
            mHotMovieWishLinear = (LinearLayout) itemView.findViewById(R.id.ll_item_hotmovie_wish);

            mHotMovieDate = (TextView) itemView.findViewById(R.id.tv_item_hotmovie_date);

            mMaxScreen2D = (ImageView) itemView.findViewById(R.id.iv_imax2d);
            mMaxScreen3D = (ImageView) itemView.findViewById(R.id.iv_imax3d);
            mScreen3D = (ImageView) itemView.findViewById(R.id.iv_3d);

            mHotMovieSmallNews1 = (RelativeLayout) itemView.findViewById(R.id.rl_item_news1);
            mHotMovieSmallNews2 = (RelativeLayout) itemView.findViewById(R.id.rl_item_news2);

            mHotMovieSmallNewsIcon1 = (TextView) itemView.findViewById(R.id.iv_item_small_icon1);
            mHotMovieSmallNewsIcon2 = (TextView) itemView.findViewById(R.id.iv_item_small_icon2);

            mHotMovieSmallNewsDesc1 = (TextView) itemView.findViewById(R.id.tv_item_desc1);
            mHotMovieSmallNewsDesc2 = (TextView) itemView.findViewById(R.id.tv_item_desc2);

//            mHotMovieTitleDesc = (TextView) itemView.findViewById(R.id.tv_item_title_desc);

            mHotMovieBtnWant = (Button) itemView.findViewById(R.id.btn_item_hotmovie_want);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, WebActivity.class);
                    switch (currType) {
                        case HOT_MOVIE :
//                            Toast.makeText(context, "被点击", Toast.LENGTH_SHORT).show();
                            int idHot = hotMovieDatas.get(getLayoutPosition() - 1).getId();
                            String urlHot = "http://m.maoyan.com/movie/" + idHot + "?_v_=yes";
                                    intent.putExtra("url",urlHot);
                            context.startActivity(intent);
                            break;
                        case WAIT_MOVIE:
//                            Toast.makeText(context, "很可惜，没有数据..", Toast.LENGTH_SHORT).show();
                            int idWait = waitMovieDatas.get(getLayoutPosition() - 1).getId();
                            String urlWait = "http://m.maoyan.com/movie/" + idWait + "?_v_=yes";
                            intent.putExtra("url",urlWait);
                            context.startActivity(intent);
                            break;
                    }
                }
            });
        }

        /**
         * 绑定每个item的数据
         * @param position
         */
        public void setData(int position) {
            
            switch (currType) {
                case  HOT_MOVIE:
                    setHotMovieData(hotMovieDatas.get(position));
                    break;
                case  WAIT_MOVIE:
                    setWaitMovieData(waitMovieDatas.get(position));
            }

        }

        /**
         * 绑定待映界面数据
         * @param comingBean
         */
        private void setWaitMovieData(WaitMovieBean.DataBean.ComingBean comingBean) {
            //  替换为可以访问的图片
            String imgUrl = comingBean.getImg();
            imgUrl = imgUrl.replace("w.h", "165.220");

            //  设置公共的view 图片 标题等
            Glide.with(context).load(imgUrl).placeholder(R.drawable.backgroud_logo02)
                    .error(R.drawable.backgroud_logo02).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mHotMovieIcon);

            mHotMovieName.setText(comingBean.getNm());
            mHotMovieDesc.setText(comingBean.getScm());
            mHotMovieCount.setText(comingBean.getDesc());

            mHotMovieWishLinear.setVisibility(View.VISIBLE);
            mHotMovieWish.setText(comingBean.getWish() + "");

            mScreen3D.setVisibility(View.GONE);
            mMaxScreen2D.setVisibility(View.GONE);
            mMaxScreen3D.setVisibility(View.GONE);
            //  1.判断3d
            if (comingBean.getVer().length()>2) {
                mScreen3D.setVisibility(View.VISIBLE);
                if(comingBean.getVer().length()>5) {
                    mScreen3D.setVisibility(View.GONE);
                    mMaxScreen3D.setVisibility(View.VISIBLE);
                }
            }

            //  判断是否显示小新闻
            mHotMovieSmallNews1.setVisibility(View.GONE);
            mHotMovieSmallNews2.setVisibility(View.GONE);
            List<WaitMovieBean.DataBean.ComingBean.SmallNews> headLinesVO = comingBean.getHeadLinesVO();
            if (headLinesVO != null && headLinesVO.size() > 0) {
                mHotMovieSmallNews1.setVisibility(View.VISIBLE);
                mHotMovieSmallNewsDesc1.setText(headLinesVO.get(0).getTitle());
                if (headLinesVO.size() > 1) {
                    mHotMovieSmallNews2.setVisibility(View.VISIBLE);
                    mHotMovieSmallNewsDesc2.setText(headLinesVO.get(1).getTitle());
                }
            }

//            mHotMovieTitleDesc.setVisibility(View.GONE);

            //  判断头部日期
//            showTitleDate(comingBean);

            //  2.判断是否上映
            if (comingBean.getShowst() == 4) {
                //  预售
                setWaitNoPlayMovie(comingBean);
            } else if (comingBean.getShowst() == 1){
                //  想看
                setWantPlayMovie(comingBean);
            }
        }

        /**
         * 用显示头部日期
         * @param comingBean
         */
        private void showTitleDate(WaitMovieBean.DataBean.ComingBean comingBean) {
            String predate = comingBean.getRt();
            for(int i = 0; i < waitMovieDatas.indexOf(comingBean); i++) {
                if (predate.equals(waitMovieDatas.get(i).getRt())) {
//                    mHotMovieTitleDesc.setVisibility(View.GONE);
                    break;
                }
//                mHotMovieTitleDesc.setVisibility(View.VISIBLE);
            }

            if (waitMovieDatas.indexOf(comingBean) == 0) {
//                mHotMovieTitleDesc.setVisibility(View.VISIBLE);
            }

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 E");
            SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
            Date date = null;//提取格式中的日期
            try {
                date = sdf1.parse(predate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            predate = sdf2.format(date);


            String strDate = sdf3.format(date);
            int intDate = Integer.parseInt(strDate);
            Log.e("TAG", "intDate====" + intDate);
//            mHotMovieTitleDesc.setText(predate);
        }

        //  预售
        private void setWaitNoPlayMovie(WaitMovieBean.DataBean.ComingBean comingBean) {
            mHotMovieBtnOrder.setVisibility(View.VISIBLE);
            mHotMovieBtnWant.setVisibility(View.GONE);
        }

        //  想看
        private void setWantPlayMovie(WaitMovieBean.DataBean.ComingBean comingBean) {
            mHotMovieBtnOrder.setVisibility(View.GONE);
            mHotMovieBtnWant.setVisibility(View.VISIBLE);
        }

        /**
         * 绑定热映界面数据
         * @param moviesBean
         */
        private void setHotMovieData(HotMovieBean.DataBean.MoviesBean moviesBean) {
            //  设置公共的view 图片 标题等
            Glide.with(context).load(moviesBean.getImg()).placeholder(R.drawable.backgroud_logo02)
                    .error(R.drawable.backgroud_logo02).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mHotMovieIcon);

            mHotMovieName.setText(moviesBean.getNm());
            mHotMovieDesc.setText(moviesBean.getScm());


            mHotMovieSmallNews1.setVisibility(View.GONE);
            mHotMovieSmallNews2.setVisibility(View.GONE);

            if (getLayoutPosition()==1) {
                mHotMovieSmallNews1.setVisibility(View.VISIBLE);
                mHotMovieSmallNews2.setVisibility(View.VISIBLE);
                mHotMovieSmallNewsIcon1.setText("影评");
                mHotMovieSmallNewsIcon2.setText("影评");
                mHotMovieSmallNewsDesc1.setText("节奏和悬念设计超出前作一大截");
                mHotMovieSmallNewsDesc2.setText("魔术如魔幻，主角近乎超级英雄");

            }

            //  判断类型显示相应的内容
            mMaxScreen2D.setVisibility(View.GONE);
            mMaxScreen3D.setVisibility(View.GONE);
            mScreen3D.setVisibility(View.GONE);
            //  1.判断3d
            if (moviesBean.isValue3d()) {
                if(moviesBean.isImax()) {
                    mMaxScreen3D.setVisibility(View.VISIBLE);

                }
                mScreen3D.setVisibility(View.VISIBLE);
            } else {
                if(moviesBean.isImax()) {
                    mMaxScreen2D.setVisibility(View.VISIBLE);
                }
            }

//            //  判断是否显示小新闻
//            mHotMovieSmallNews1.setVisibility(View.GONE);
//            mHotMovieSmallNews2.setVisibility(View.GONE);
//            List<HotMovieBean.DataBean.HotBean.HeadLinesVOBean> headLinesVO = moviesBean.getHeadLinesVO();
//            if (headLinesVO != null && headLinesVO.size() > 0) {
//                mHotMovieSmallNews1.setVisibility(View.VISIBLE);
//                mHotMovieSmallNewsDesc1.setText(headLinesVO.get(0).getTitle());
//                if (headLinesVO.size() > 1) {
//                    mHotMovieSmallNews2.setVisibility(View.VISIBLE);
//                    mHotMovieSmallNewsDesc2.setText(headLinesVO.get(1).getTitle());
//                }
//            }

//            //  1.判断3d
//            if (moviesBean.getVer().length()>2) {
//                mScreen3D.setVisibility(View.VISIBLE);
//                if(moviesBean.getVer().length()>5) {
//                    mScreen3D.setVisibility(View.GONE);
//                    mMaxScreen3D.setVisibility(View.VISIBLE);
//                }
//            }

            //  2.判断是否上映
            if (moviesBean.getPreSale() == 0) {
                //  上映过
                setHasPlayMovie(moviesBean);

            } else if (moviesBean.getPreSale() == 1){
                //  未上映
                setNoPlayMovie(moviesBean);
            }

            //  绑定item点击事件

        }

        //  未上映
        private void setNoPlayMovie(HotMovieBean.DataBean.MoviesBean moviesBean) {
            mHotMovieWishLinear.setVisibility(View.VISIBLE);
            mHotMovieScoreLinear.setVisibility(View.GONE);
            mHotMovieWish.setText(moviesBean.getWish() + "");
            mHotMovieBtnOrder.setVisibility(View.VISIBLE);
            mHotMovieBtnBuy.setVisibility(View.GONE);
            mHotMovieDate.setVisibility(View.VISIBLE);
            mHotMovieCount.setVisibility(View.GONE);
            mHotMovieDate.setText(moviesBean.getShowInfo());
        }
        //  上映过
        private void setHasPlayMovie(HotMovieBean.DataBean.MoviesBean moviesBean) {
            mHotMovieScoreLinear.setVisibility(View.VISIBLE);
            mHotMovieWishLinear.setVisibility(View.GONE);
            mHotMovieScore.setText(moviesBean.getSc() + "");
            mHotMovieBtnBuy.setVisibility(View.VISIBLE);
            mHotMovieBtnOrder.setVisibility(View.GONE);
            mHotMovieCount.setVisibility(View.VISIBLE);
            mHotMovieDate.setVisibility(View.GONE);
            mHotMovieCount.setText(moviesBean.getShowInfo());
        }
    }



    /**
     * 以下代码实现recyclerView粘性头部控件
     */
    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
            commonTitle = (TextView) view;
        }
    }

    private TextView commonTitle;

    @Override
    public long getHeaderId(int position) {
        if (currType == WAIT_MOVIE) {
            if (position == 0) {
                return -1;
            }
            return parseDate(waitMovieDatas.get(position - 1).getRt());
        }
        return -1;
    }

    private int parseDate(String strDate) {

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 E");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
        Date date = null;//提取格式中的日期
        try {
            date = sdf1.parse(strDate);
            String strDate1 = sdf3.format(date);
            int intDate = Integer.parseInt(strDate1);
            testDate = sdf2.format(date);
            return intDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String testDate;

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_for_test, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getHeaderId(position) == parseDate(waitMovieDatas.get(position - 1).getRt())) {
            commonTitle.setText(testDate);
        } else if (getHeaderId(position) == -1) {
            commonTitle.setVisibility(View.GONE);
        }
    }

}

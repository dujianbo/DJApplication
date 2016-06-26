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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.movies.HotMovieBean;
import com.personal.djb.catmovie.bean.movies.HotMovieHeadPagerBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class HotMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HotMovieBean.DataBean.MoviesBean> datas;
    private Context context;

    private final String HOTMOVIE_HEADPAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=11&version=6.8.0&new=0&app=movie&clienttp=android&uuid=FCFAB9D8DD339645D629C8372A29A2C6AD16F9C9E87AF9AC0D656B29DD5AC6DE&devid=866641027400542&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=qq&utm_medium=android&utm_term=6.8.0&utm_content=866641027400542&ci=1&net=255&dModel=HM%20NOTE%201LTETD&lat=40.100855&lng=116.378273&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463730432992&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=01f9c5c0-eb56-4e19-92fb-b86b16ad79da&__skcy=5K8wRR%2FKYAZDTgmAzbhrXi%2FomzU%3D";

    //  头部类型
    public static final int HEAD_TYPE = 0;
    //  第2行类型
    public static final int SECOUND_TYPE = 1;
    //  默认类型
    public static final int DEFAULT_TYPE = 2;

    public HotMovieAdapter(Context context,List<HotMovieBean.DataBean.MoviesBean> datas){
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            //  头部
            HeadViewHolder headHolder = new HeadViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_hot_movie_head, parent, false));
            return headHolder;
        } else if (viewType == DEFAULT_TYPE){
            //  默认类型
            ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_hot_movie, parent, false));
            return holder;
        } else if (viewType == SECOUND_TYPE){
            //  第2条的数据
            SecoundViewHolder secoundViewHolder = new SecoundViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_hot_movie_secound, parent, false));
            return secoundViewHolder;
        }
        return null;
    }

    /**
     * 给ViewHolder绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeadViewHolder) {
            ((HotMovieAdapter.HeadViewHolder)holder).setData();
        } else if (holder instanceof ViewHolder) {
            if (position == 1) {
                ((HotMovieAdapter.ViewHolder)holder).setData(datas.get(position - 1));
                return;
            }
            ((HotMovieAdapter.ViewHolder)holder).setData(datas.get(position - 2));
        } else if (holder instanceof SecoundViewHolder) {
            ((HotMovieAdapter.SecoundViewHolder)holder).setData();
        }
    }

    @Override
    public int getItemViewType(int position) {
        //  分类型的加载view
        if (position == 0) {
            return HEAD_TYPE;
        }

        if (position == 2) {
            return SECOUND_TYPE;
        }
        return DEFAULT_TYPE;
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    /**
     * 这个holder用来显示头部的viewpager
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder {

        private ViewPager mHotMovieHeadPager;
        private HeadPageAdapter adapter;
        //  热映页面的资源集合
        private List<HotMovieHeadPagerBean.DataBean> datas;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mHotMovieHeadPager = (ViewPager) itemView.findViewById(R.id.vp_item_hot_movie_head);
            mHotMovieHeadPager.setVisibility(View.GONE);
        }

        public void setData() {
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

        }

        private void processData(String json) {
            HotMovieHeadPagerBean hotMovieHeadPagerBean = new Gson().fromJson(json, HotMovieHeadPagerBean.class);
            datas = hotMovieHeadPagerBean.getData();

            mHotMovieHeadPager.setVisibility(View.VISIBLE);
            adapter = new HeadPageAdapter();
            mHotMovieHeadPager.setAdapter(adapter);
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
                mHotMovieHeadPager.setCurrentItem((mHotMovieHeadPager.getCurrentItem() + 1) % datas.size());
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
                return datas.size();
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
                Glide.with(context).load(datas.get(position).getImgUrl())
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
     * 这个holder用来显示第二个列表
     */
    public class SecoundViewHolder extends RecyclerView.ViewHolder {


        public SecoundViewHolder(View itemView) {
            super(itemView);

        }

        public void setData() {

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
        }

        /**
         * 绑定每个item的数据
         * @param moviesBean
         */
        public void setData(HotMovieBean.DataBean.MoviesBean moviesBean) {
            //  设置公共的view 图片 标题等
            Glide.with(context).load(moviesBean.getImg()).placeholder(R.drawable.backgroud_logo02)
                    .error(R.drawable.backgroud_logo02).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mHotMovieIcon);

            mHotMovieName.setText(moviesBean.getNm());
            mHotMovieDesc.setText(moviesBean.getScm());

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

            //  2.判断是否上映
            if (moviesBean.getPreSale() == 0) {
                //  上映过
                setHasPlayMovie(moviesBean);

            } else if (moviesBean.getPreSale() == 1){
                //  未上映
                setNoPlayMovie(moviesBean);
            }
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

}

package com.personal.djb.catmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.WebActivity;
import com.personal.djb.catmovie.bean.findbean.FindBean;
import com.personal.djb.catmovie.bean.findbean.FindViewPagerBean;
import com.personal.djb.catmovie.bean.movies.WaitMovieBean;
import com.personal.djb.catmovie.utils.DensityUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class FindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //  数据列表
    private List<FindBean.DataBean.FeedsBean> datas;
    //  待映电影的列表
    private List<WaitMovieBean.DataBean.ComingBean> waitMovieDatas;
    private Context context;

//    private final String HOTMOVIE_HEADPAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=11&version=6.8.0&new=0&app=movie&clienttp=android&uuid=FCFAB9D8DD339645D629C8372A29A2C6AD16F9C9E87AF9AC0D656B29DD5AC6DE&devid=866641027400542&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=qq&utm_medium=android&utm_term=6.8.0&utm_content=866641027400542&ci=1&net=255&dModel=HM%20NOTE%201LTETD&lat=40.100855&lng=116.378273&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463730432992&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=01f9c5c0-eb56-4e19-92fb-b86b16ad79da&__skcy=5K8wRR%2FKYAZDTgmAzbhrXi%2FomzU%3D";
    private final String FIND_HEADPAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=14&version=6.8.0&new=0&app=movie&clienttp=android&uuid=22C117E01E15F95B04242F2BFCC4575F5F5EA8EDB0193ED973FED7FA7B2829BD&devid=353918059801163&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=353918059801163&ci=1&net=255&dModel=Nexus%204&lat=40.100654&lng=116.37805&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463655698510&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=d4f1e217-fd38-4cda-8d82-ef52b331a71a&__skcy=PL%2BjGyr5ANh3wbigOteOnFr7PLU%3D";

    //  头部类型
    private static final int HEAD_TYPE = 0;
    //  默认类型
    private static final int DEFAULT_TYPE = 1;

    //  话题链接
    private String TOPIC_URL = "http://m.maoyan.com/groups?_v_=yes&f=android&userid=-1&pushToken=7e6bd5fe73912116ed3ca24d473265cc37ad738879f590efaf0a436f905b945b103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1";
    //  资讯链接
    private String INFORMATION_URL = "http://m.maoyan.com/groups?_v_=yes&f=android&userid=-1&pushToken=7e6bd5fe73912116ed3ca24d473265cc37ad738879f590efaf0a436f905b945b103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1";
    //  影库链接

    //  票房链接
    private String BOOKING_OFFICE_URL = "http://m.maoyan.com/newGuide/maoyanpiaofang?f=nohdft";


    public FindAdapter(Context context, List<FindBean.DataBean.FeedsBean> datas){
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            //  头部
            HeadViewHolder headHolder = new HeadViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.find_head_item, parent, false));
            return headHolder;
        }

        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_find, parent, false));
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
            ((FindAdapter.HeadViewHolder)holder).setData();
        } else if (holder instanceof ViewHolder) {
            ((FindAdapter.ViewHolder)holder).setData(position - 1);
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
        return datas.size() + 1;
    }

    public void setDatas(List<FindBean.DataBean.FeedsBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
    }

    /**
     * 这个holder用来显示头部的viewpager
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder {

        private ViewPager mHotMovieHeadPager;
        private HeadPageAdapter adapter;
        //  热映页面的资源集合
        private List<FindViewPagerBean.DataBean> pageDatas;
        private TextView mCurrTitle;
        private LinearLayout mLLPointGroup;
        private boolean flag = false;
        private int preSize;

        public HeadViewHolder(View itemView) {
            super(itemView);
            mHotMovieHeadPager = (ViewPager) itemView.findViewById(R.id.vp_item_hot_movie_head);
            mCurrTitle = (TextView) itemView.findViewById(R.id.tv_currtitle);
            mLLPointGroup = (LinearLayout) itemView.findViewById(R.id.ll_point_group);
        }

        public void setData() {

                    //  联网获取数据
                    OkHttpUtils.get().url(FIND_HEADPAGE_URL).build().execute(new StringCallback() {
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
            FindViewPagerBean findViewPagerBean = new Gson().fromJson(json, FindViewPagerBean.class);
            pageDatas = findViewPagerBean.getData();

            mHotMovieHeadPager.setVisibility(View.VISIBLE);

//            mCurrTitle.setText(pageDatas.get(getLayoutPosition()).getCommonTitle());
            adapter = new HeadPageAdapter();
            mHotMovieHeadPager.setAdapter(adapter);
            mHotMovieHeadPager.setCurrentItem(500);
            mHotMovieHeadPager.addOnPageChangeListener(new MyOnPageChangeListener());

            //  每3秒轮播图滚动一次
            handler.sendEmptyMessageDelayed(0, 3000);

            if (!flag) {
                addPoint();
                flag = true;
                Log.e("du", "执行一次这个");
                preSize = pageDatas.size();
            }

            if (flag) {
                if(preSize != pageDatas.size()) {
                    addPoint();
                    preSize = pageDatas.size();
                }
            }

        }

        /**
         * 动态添加点
         */
        private void addPoint() {
            for(int i = 0; i < pageDatas.size(); i++) {

                ImageView imageView = new ImageView(context);
                imageView.setEnabled(false);
                imageView.setBackgroundResource(R.drawable.point_selector);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(context,8),DensityUtil.dip2px(context,8));
                if (i != 0) {
                    params.leftMargin = DensityUtil.dip2px(context,5);
                }

                if (i == 0) {
                    imageView.setEnabled(true);
                }
                imageView.setLayoutParams(params);
                mLLPointGroup.addView(imageView);
            }
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
                int realPosition = position%pageDatas.size();

                mCurrTitle.setText(pageDatas.get(realPosition).getCommonTitle());
                for(int i = 0; i < pageDatas.size(); i++) {
                    mLLPointGroup.getChildAt(i).setEnabled(false);
                    if(i == realPosition) {
                        mLLPointGroup.getChildAt(i).setEnabled(true);
                    }
                }
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

        private TextView tvFindTitleDate;
        private TextView tvFindTitleDesc;
        private RelativeLayout rlKuaixun;
        private ImageView ivFindKuaixunImage1;
        private ImageView ivFindKuaixunImage2;
        private ImageView ivFindKuaixunImage3;
        private TextView findImageCountKuaixun;
        private RelativeLayout rlYugao;
        private ImageView ivFindYugaoImage;
        private TextView tvFindYugaoName;
        private RelativeLayout rlFindPinglun;
        private ImageView pinglunImage1;
        private ImageView pinglunImage2;
        private ImageView pinglunImage3;
        private RelativeLayout rlFindBottom;
        private ImageView ivFindBottomIcon;
        private TextView tvFindUsername;
        private TextView tvFindZan;
        private TextView tvFindPinlun;
        private TextView tvYingPing;
        private LinearLayout llBottom;
        private LinearLayout ll3images;

        //  话题 资讯 影库 票房
        private Button mBtnHT;
        private Button mBtnZX;
        private Button mBtnYK;
        private Button mBtnPF;
        private LinearLayout mBtnGroup;
        private MyOnClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);

            findView(itemView);
        }

        /**
         * 实例化item的view
         * @param itemView
         */
        private void findView(View itemView) {

            //  日期  今天明天
            tvFindTitleDate = (TextView)itemView.findViewById(R.id.tv_find_title_date);
            //  标题，主要标题
            tvFindTitleDesc = (TextView)itemView.findViewById(R.id.tv_find_title_desc);
            //  电影快讯类型
            rlKuaixun = (RelativeLayout)itemView.findViewById(R.id.rl_kuaixun);
            //  快讯里的图片123
            ivFindKuaixunImage1 = (ImageView)itemView.findViewById(R.id.iv_find_kuaixun_image1);
            ivFindKuaixunImage2 = (ImageView)itemView.findViewById(R.id.iv_find_kuaixun_image2);
            ivFindKuaixunImage3 = (ImageView)itemView.findViewById(R.id.iv_find_kuaixun_image3);
            //  电影快讯总图片数
            findImageCountKuaixun = (TextView)itemView.findViewById(R.id.find_image_count_kuaixun);
            ll3images = (LinearLayout) itemView.findViewById(R.id.lllllllll);

            //  电影预告类型
            rlYugao = (RelativeLayout)itemView.findViewById(R.id.rl_yugao);
            ivFindYugaoImage = (ImageView)itemView.findViewById(R.id.iv_find_yugao_image);
            tvFindYugaoName = (TextView)itemView.findViewById(R.id.tv_find_yugao_name);

            //  电影评论类型3张图待深度影片
            rlFindPinglun = (RelativeLayout)itemView.findViewById(R.id.rl_find_pinglun);
            pinglunImage1 = (ImageView)itemView.findViewById(R.id.pinglun_image1);
            pinglunImage2 = (ImageView)itemView.findViewById(R.id.pinglun_image2);
            pinglunImage3 = (ImageView)itemView.findViewById(R.id.pinglun_image3);
            tvYingPing = (TextView) itemView.findViewById(R.id.tv_yingping);

            //  电影底部
            rlFindBottom = (RelativeLayout)itemView.findViewById(R.id.rl_find_bottom);
            ivFindBottomIcon = (ImageView)itemView.findViewById(R.id.iv_find_bottom_icon);
            tvFindUsername = (TextView)itemView.findViewById(R.id.tv_find_username);
            tvFindZan = (TextView)itemView.findViewById(R.id.tv_find_zan);
            tvFindPinlun = (TextView)itemView.findViewById(R.id.tv_find_pinlun);
            llBottom = (LinearLayout) itemView.findViewById(R.id.lllllllll);
            mBtnHT = (Button) itemView.findViewById(R.id.huati);
            mBtnZX = (Button) itemView.findViewById(R.id.zixun);
            mBtnYK = (Button) itemView.findViewById(R.id.yingku);
            mBtnPF = (Button) itemView.findViewById(R.id.piaofang);
            mBtnGroup = (LinearLayout) itemView.findViewById(R.id.find_head_ll_group);
            listener = new MyOnClickListener();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "被点击", Toast.LENGTH_SHORT).show();
                }
            });

        }

        /**
         * 绑定每个item的数据
         * @param position
         */
        public void setData(int position) {
            FindBean.DataBean.FeedsBean feedsBean = datas.get(position);
            int feedType = feedsBean.getFeedType();

            rlKuaixun.setVisibility(View.GONE);
            rlYugao.setVisibility(View.GONE);
            rlFindPinglun.setVisibility(View.GONE);
            tvFindTitleDate.setVisibility(View.GONE);
            tvFindTitleDesc.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);

            tvFindUsername.setText(feedsBean.getUser().getNickName());

            //  头部点击事件
            mBtnGroup.setVisibility(View.GONE);
            if(position == 0) {
                mBtnGroup.setVisibility(View.VISIBLE);
                mBtnHT.setOnClickListener(listener);
                mBtnZX.setOnClickListener(listener);
                mBtnYK.setOnClickListener(listener);
                mBtnPF.setOnClickListener(listener);
            }

            Glide.with(context).load(feedsBean.getUser().getAvatarurl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(ivFindBottomIcon) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivFindBottomIcon.setImageDrawable(circularBitmapDrawable);
                }
            });

            if(position == 0) {
                tvFindTitleDate.setVisibility(View.VISIBLE);
                tvFindTitleDate.setText("今天");
            }

            int count = (int)(Math.random()*50)+1;
            int count1 = (int)(Math.random()*50)+1;

            switch (feedType) {
                case 4 :
                    rlYugao.setVisibility(View.VISIBLE);

                    Glide.with(context).load(feedsBean.getImages().get(0).getUrl()).into(ivFindYugaoImage);
                    tvFindYugaoName.setText(feedsBean.getTitle());

                    break;

                case 8 :
                    rlKuaixun.setVisibility(View.VISIBLE);
                    List<FindBean.DataBean.FeedsBean.ImagesBean> images = feedsBean.getImages();
                    Glide.with(context).load(images.get(0).getUrl()).error(R.drawable.lh).into(ivFindKuaixunImage1);
                    Glide.with(context).load(images.get(1).getUrl()).error(R.drawable.lh).into(ivFindKuaixunImage2);
                    Glide.with(context).load(images.get(2).getUrl()).error(R.drawable.lh).into(ivFindKuaixunImage3);
                    tvFindTitleDesc.setVisibility(View.VISIBLE);
                    tvFindTitleDesc.setText(feedsBean.getTitle());
                    findImageCountKuaixun.setText("共" + feedsBean.getImageCount() + "张");
                    llBottom.setVisibility(View.VISIBLE);
                    tvFindZan.setText(count + "");
                    tvFindPinlun.setText(count1 + "");
                    break;
                
                case 7:
                    rlYugao.setVisibility(View.VISIBLE);
                    Glide.with(context).load(feedsBean.getImages().get(0).getUrl()).into(ivFindYugaoImage);
                    tvFindYugaoName.setText(feedsBean.getTitle());
                    llBottom.setVisibility(View.VISIBLE);
                    tvFindZan.setText(count + "");
                    tvFindPinlun.setText(count1 + "");
                    break;
                case 2:
                    //  电影评论类型3张图待深度影片
                    rlFindPinglun.setVisibility(View.VISIBLE);
                    ll3images.setVisibility(View.VISIBLE);
                    List<FindBean.DataBean.FeedsBean.ImagesBean> images1 = feedsBean.getImages();
                    Glide.with(context).load(images1.get(0).getUrl()).error(R.drawable.lh).into(pinglunImage1);
                    Glide.with(context).load(images1.get(1).getUrl()).error(R.drawable.lh).into(pinglunImage2);
                    Glide.with(context).load(images1.get(2).getUrl()).error(R.drawable.lh).into(pinglunImage3);
                    tvYingPing.setText(feedsBean.getGroupName());
                    tvFindTitleDesc.setVisibility(View.VISIBLE);
                    tvFindTitleDesc.setText(feedsBean.getTitle());
                    tvFindZan.setText(count + "");
                    tvFindPinlun.setText(count1 + "");
                    break;
            }

        }

        /**
         * 点击事件处理
         */
        private class MyOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                switch (v.getId()) {
                    case R.id.huati :
                        intent.putExtra("url",TOPIC_URL);
                        intent.putExtra("webname","话题");
                        context.startActivity(intent);
                        break;
                    case R.id.zixun :
                        intent.putExtra("url",INFORMATION_URL);
                        intent.putExtra("webname","资讯");
                        context.startActivity(intent);
                        break;
                    case R.id.yingku :

                        break;
                    case R.id.piaofang :
                        intent.putExtra("url",BOOKING_OFFICE_URL);
                        intent.putExtra("webname","票房");
                        context.startActivity(intent);
                        break;
                }
            }
        }
    }

}

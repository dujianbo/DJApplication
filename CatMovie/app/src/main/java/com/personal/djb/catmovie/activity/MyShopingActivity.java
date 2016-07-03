package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.ShopTopPageBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class MyShopingActivity extends Activity {

    private ViewPager mShopTopPager;
    private RadioGroup mShopGroupTop;
    private RadioGroup mShopGroupBottom;
    private MyChangeListener changeListener;

    private final String SHOP_TOP_PAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=15&version=6.8.0&new=0&app=movie&clienttp=android&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&devid=000000000000000&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463727759502&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=c5c010cf-df8d-4cf3-b201-ff77d589a7d8&__skcy=aGeWnSUqiW22CW4JJ7%2FVZzJVKtI%3D";
    private MyTopPagerAdapter pageAdapter;
    private List<ShopTopPageBean.DataBean> topPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shoping);

        findView();
        initData();
    }

    private void initData() {
        getPageDataFromNet();
    }

    private void getPageDataFromNet() {
        OkHttpUtils.get().url(SHOP_TOP_PAGE_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                processPageData(response);
            }
        });
    }

    /**
     * 广告轮播图 通过handler实现
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mShopTopPager.setCurrentItem(mShopTopPager.getCurrentItem() + 1);
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };

    private void processPageData(String json) {
        ShopTopPageBean shopTopPageBean = new Gson().fromJson(json, ShopTopPageBean.class);
        topPages = shopTopPageBean.getData();
        pageAdapter = new MyTopPagerAdapter();
        mShopTopPager.setAdapter(pageAdapter);

        mShopTopPager.setCurrentItem(500);
        mShopTopPager.addOnPageChangeListener(new MyOnPageChangeListener());

        //  每3秒轮播图滚动一次
        handler.sendEmptyMessageDelayed(0,3000);
    }

    private void findView() {
        mShopTopPager = (ViewPager) findViewById(R.id.vp_my_setting_top);
        mShopGroupTop = (RadioGroup) findViewById(R.id.rg_shop_top);
        mShopGroupBottom = (RadioGroup) findViewById(R.id.rg_shop_bottom);

        setListener();
    }

    private void setListener() {
        mShopGroupTop.setOnCheckedChangeListener(changeListener);
        mShopGroupBottom.setOnCheckedChangeListener(changeListener);
    }

    private class MyChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rg_shop_top1 :

                    break;
                case R.id.rg_shop_top2 :

                    break;
                case R.id.rg_shop_top3 :

                    break;
                case R.id.rg_shop_top4 :

                    break;
                case R.id.rg_shop_top5 :

                    break;
                case R.id.rg_shop_bottom1 :

                    break;
                case R.id.rg_shop_bottom2 :

                    break;
                case R.id.rg_shop_bottom3 :

                    break;
                case R.id.rg_shop_bottom4 :

                    break;
                case R.id.rg_shop_bottom5 :

                    break;
            }
        }
    }

    private class MyTopPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return 1000;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String imageUrl = topPages.get(position%topPages.size()).getImgUrl();
            ImageView imageView = new ImageView(MyShopingActivity.this);
            Glide.with(MyShopingActivity.this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.splash3).error(R.drawable.splash3).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
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

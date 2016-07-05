package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.ShopBean;
import com.personal.djb.catmovie.bean.ShopTopPageBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MyShopingActivity extends Activity {

    private final String SHOP_TOP_PAGE_URL = "http://advert.mobile.meituan.com/api/v3/adverts?cityid=1&category=15&version=6.8.0&new=0&app=movie&clienttp=android&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&devid=000000000000000&uid=&movieid=&partner=1&apptype=1&smId=&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463727759502&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=c5c010cf-df8d-4cf3-b201-ff77d589a7d8&__skcy=aGeWnSUqiW22CW4JJ7%2FVZzJVKtI%3D";
    private List<ShopTopPageBean.DataBean> topPages;

    private RecyclerView mRecyMayLike;
    private SpecialAdapter specialAdapter;
    private Context me;

    private List<ShopBean.DataBean.ListBean> likeDatas;
    private final String LIKE_SHOP_URL = "http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=all&category=%E6%95%B0%E7%A0%81&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463728005079&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=eae20294-3b53-45f5-999c-183a4b4dfbb9&__skcy=1lxN6MHul9F0PHcMKJwMDPVLT9I%3D";

    private int[] specials = {1,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shoping);
        me = this;

        findView();
        initData();
    }

    private void initData() {

        getLikeDataFromNet();
    }

    private void getLikeDataFromNet() {
        OkHttpUtils.get().url(LIKE_SHOP_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                processLikeData(response);
            }
        });
    }

    private void processLikeData(String response) {
        ShopBean shopBean = new Gson().fromJson(response, ShopBean.class);
        likeDatas = shopBean.getData().getList();

        likeData1 = new ArrayList<>();
        likeData2 = new ArrayList<>();
        for(int i = 0; i < likeDatas.size(); i++) {
            if (i%2 == 0) {
                likeData1.add(likeDatas.get(i));
                continue;
            }
            likeData2.add(likeDatas.get(i));
        }

        specialAdapter = new SpecialAdapter();
        RecyclerView.LayoutManager specialManager = new LinearLayoutManager(me,LinearLayoutManager.VERTICAL,false);
        mRecyMayLike.setLayoutManager(specialManager);

        mRecyMayLike.setAdapter(specialAdapter);

    }

    private List<ShopBean.DataBean.ListBean> likeData1;
    private List<ShopBean.DataBean.ListBean> likeData2;

    private void findView() {


//        mRecySpecial = (RecyclerView) findViewById(R.id.my_shop_special);
        mRecyMayLike = (RecyclerView) findViewById(R.id.my_shop_like);

        setListener();
    }

    private void setListener() {
//        mShopGroupTop.setOnCheckedChangeListener(changeListener);
//        mShopGroupBottom.setOnCheckedChangeListener(changeListener);
    }

    private class SpecialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == 50) {
                SpecialAdapter.HeadShopViewHolder holder = new HeadShopViewHolder(
                        LayoutInflater.from(me).inflate(R.layout.item_shop_head,parent,false)
                );
                return holder;
            }


            if(viewType == 100) {
                SpecialAdapter.SpecialViewHolder holder = new SpecialAdapter.SpecialViewHolder(
                        LayoutInflater.from(me).inflate(R.layout.item_shop_special,parent,false)
                );
                return holder;
            }
            SpecialAdapter.RecViewHolder holder = new SpecialAdapter.RecViewHolder(
                    LayoutInflater.from(me).inflate(R.layout.item_shop_like,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position) == 50) {
                ((HeadShopViewHolder)holder).setData(position);
                return;
            }
            if(getItemViewType(position) == 100) {
                ((SpecialViewHolder)holder).setData(position);
                return;
            }

            ((RecViewHolder)holder).setData(position - 3);
        }

        @Override
        public int getItemCount() {
            return likeData1.size() + 3 > likeData2.size() + 3 ? likeData1.size() + 3 : likeData2.size() + 3;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 50;
            }

            if(position == 1 || position == 2) {
                return 100;
            }
            return 200;
        }

        public class SpecialViewHolder extends RecyclerView.ViewHolder {

            private TextView tvShopName1;
            private TextView tvShopName2;
            private TextView tvShopName3;
            private TextView tvShopDesc1;
            private TextView tvShopDesc2;
            private TextView tvShopDesc3;
            private ImageView ivShopIcon1;
            private ImageView ivShopIcon2;
            private ImageView ivShopIcon3;

            private LinearLayout llTest;

            public SpecialViewHolder(View itemView) {
                super(itemView);

                tvShopName1 = (TextView) itemView.findViewById(R.id.tv_shop_special_name1);
                tvShopName2 = (TextView) itemView.findViewById(R.id.tv_shop_special_name2);
                tvShopName3 = (TextView) itemView.findViewById(R.id.tv_shop_special_name3);

                tvShopDesc1 = (TextView) itemView.findViewById(R.id.tv_shop_special_desc1);
                tvShopDesc2 = (TextView) itemView.findViewById(R.id.tv_shop_special_desc2);
                tvShopDesc3 = (TextView) itemView.findViewById(R.id.tv_shop_special_desc3);
                
                ivShopIcon1 = (ImageView) itemView.findViewById(R.id.tv_shop_special_icon1);
                ivShopIcon2 = (ImageView) itemView.findViewById(R.id.tv_shop_special_icon2);
                ivShopIcon3 = (ImageView) itemView.findViewById(R.id.tv_shop_special_icon3);

                llTest = (LinearLayout) itemView.findViewById(R.id.ll_test);

                ivShopIcon1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(me,WebActivity.class);
                        switch (specials[getLayoutPosition()-1]) {
                            case 1 :
                                intent.putExtra("url","http://m.maoyan.com/store/topicDetail/300000056?_v_=yes&f=android&userid=-1&pushToken=8568be797789152f60d193cec511b9d2c01ebd1e6da38efbd62611e87b213090103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1");
                                startActivity(intent);
                                break;
                            case 2 :
                                intent.putExtra("url","http://i.meituan.com/firework/CivilWarcat?f=android&userid=-1&pushToken=8568be797789152f60d193cec511b9d2c01ebd1e6da38efbd62611e87b213090103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1");
                                startActivity(intent);
                                break;
                        }
                    }
                });

                ivShopIcon2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(me,WebActivity.class);
                        switch (specials[getLayoutPosition()-1]) {
                            case 1 :
                                intent.putExtra("rul","http://m.maoyan.com/store/topicDetail/200000039?_v_=yes&f=android&userid=-1&pushToken=8568be797789152f60d193cec511b9d2c01ebd1e6da38efbd62611e87b213090103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1");
                                startActivity(intent);
                                break;
                            case 2 :
                                intent.putExtra("url","http://m.maoyan.com/store/topicDetail/200000045?_v_=yes&f=android&userid=-1&pushToken=8568be797789152f60d193cec511b9d2c01ebd1e6da38efbd62611e87b213090103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1");
                                startActivity(intent);
                                break;
                        }
                    }
                });

                ivShopIcon3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(me,WebActivity.class);
                        switch (specials[getLayoutPosition()-1]) {
                            case 1 :
                                intent.putExtra("url","http://m.maoyan.com/store/topicDetail/200000043?_v_=yes&f=android&userid=-1&pushToken=8568be797789152f60d193cec511b9d2c01ebd1e6da38efbd62611e87b213090103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1");
                                startActivity(intent);
                                break;
                            case 2 :
                                intent.putExtra("url","http://m.maoyan.com/store/topicDetail/300000057?_v_=yes&f=android&userid=-1&pushToken=8568be797789152f60d193cec511b9d2c01ebd1e6da38efbd62611e87b213090103b4d6d4a8cb7a6cd70f822f26ffff9&cityId=1");
                                startActivity(intent);
                                break;
                        }
                    }
                });

            }

            public void setData(int position) {
                llTest.setVisibility(View.GONE);
                if(position == 1) {
                    tvShopName1.setText("魔兽趣味小物");
                    tvShopName2.setText("魔兽T恤");
                    tvShopName3.setText("儿童节玩具模型");
                    tvShopDesc1.setText("小魔兽");
                    tvShopDesc2.setText("为了部落");
                    tvShopDesc3.setText("送给我家小王子");
                    ivShopIcon1.setBackgroundResource(R.drawable.moshou1);
                    ivShopIcon2.setBackgroundResource(R.drawable.moshou2);
                    ivShopIcon3.setBackgroundResource(R.drawable.wanju);
                } else if (position == 2) {
                    tvShopName1.setText("酷炫周边");
                    tvShopName2.setText("激萌小鸟正版周边");
                    tvShopName3.setText("X-Man");
                    tvShopDesc1.setText("英雄内战");
                    tvShopDesc2.setText("愤怒的小鸟");
                    tvShopDesc3.setText("周边");
                    ivShopIcon1.setBackgroundResource(R.drawable.meidui);
                    ivShopIcon2.setBackgroundResource(R.drawable.xiaoniao);
                    ivShopIcon3.setBackgroundResource(R.drawable.zhanjing);
                    llTest.setVisibility(View.VISIBLE);
                }
            }
        }

        /**
         * 头holder
         */
        private class HeadShopViewHolder extends RecyclerView.ViewHolder{

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

            private MyTopPagerAdapter pageAdapter;

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

            private ViewPager mShopTopPager;
            private RadioGroup mShopGroupTop;
            private RadioGroup mShopGroupBottom;

            public HeadShopViewHolder(View itemView) {
                super(itemView);

                mShopTopPager = (ViewPager) itemView.findViewById(R.id.vp_my_setting_top);
                mShopGroupTop = (RadioGroup) itemView.findViewById(R.id.rg_shop_top);
                mShopGroupBottom = (RadioGroup) itemView.findViewById(R.id.rg_shop_bottom);

                mShopGroupTop.setOnCheckedChangeListener(changeListener);
                mShopGroupBottom.setOnCheckedChangeListener(changeListener);
            }

            private MyChangeListener changeListener = new MyChangeListener();

            private class MyChangeListener implements RadioGroup.OnCheckedChangeListener{
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    Intent intent = new Intent(me,ShowShopActivity.class);
                    switch (checkedId) {
                        case R.id.rg_shop_top1 :
//                            Toast.makeText(me, "数码", Toast.LENGTH_SHORT).show();
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=all&category=%E6%95%B0%E7%A0%81&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463728005079&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=eae20294-3b53-45f5-999c-183a4b4dfbb9&__skcy=1lxN6MHul9F0PHcMKJwMDPVLT9I%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_top2 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=all&category=%E9%AB%98%E7%8E%A9%E4%B8%93%E5%8C%BA&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463728087947&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=0104909f-73f2-4d80-b37b-c41ff3cdca40&__skcy=9CJqOiRx4qDxK5MR9C8XlCekfkA%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_top3 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=all&category=%E7%8E%A9%E5%85%B7&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463728117971&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=9340c88a-b6db-42bc-8f22-7b392cded40c&__skcy=j4AV126GTWQqc%2Bvk0lSEtmuBRwo%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_top4 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=all&category=%E7%94%9F%E6%B4%BB&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463733286575&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=b713a8a4-48bd-4d31-be2b-0c98fd87a5cc&__skcy=e%2FNTuoHnH%2FNxbjKKTu5dczCxEMw%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_top5 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=all&category=%E6%9C%8D%E9%A5%B0&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463728561480&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=ab1ca51e-b66f-4b29-b31a-3eea1fec7553&__skcy=GpuqasJkM5Q0Y8l47mbqCK%2Br7i4%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_bottom1 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=%E8%B6%85%E8%9D%99&category=all&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463728706833&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=fd889724-9a4a-4943-ba70-94fdd6bbe656&__skcy=7Ye6muAOHkQ2PGHjw%2FvTK44Rczg%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_bottom2 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=%E6%9C%BA%E5%99%A8%E7%8C%AB&category=all&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463732086359&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=7299a89e-b4a6-4d86-ad6d-fbff9332091f&__skcy=2bzKri7PTKPff33eC16q29l%2FJwk%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_bottom3 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=%E5%B0%8F%E9%BB%84%E4%BA%BA&category=all&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463732224154&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=69c556da-7759-4abc-8f04-048079c5e72a&__skcy=MGKUwMH7rmz2Mqt7x6EO%2FbRCq%2Fw%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_bottom4 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=%E7%BE%8E%E9%98%9F&category=all&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463732268611&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=6d385c6b-9767-49c8-b8fd-5806489aee6a&__skcy=9ZNJ6yqY31PpX9jqO%2FdSvSYj8SU%3D");
                            startActivity(intent);
                            break;
                        case R.id.rg_shop_bottom5 :
                            intent.putExtra("shopurl","http://api.meituan.com/mallpro/query.json?movieId=0&offset=0&limit=10&theme=%E6%98%9F%E7%90%83%E5%A4%A7%E6%88%98&category=all&sort=default&query=&token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieC110189035496448D-1&movieBundleVersion=6801&utm_source=goapk&utm_medium=android&utm_term=6.8.0&utm_content=000000000000000&ci=1&net=255&dModel=Custom%20Phone%20-%204.3%20-%20API%2018%20-%20768x1280&uuid=2C2C0ECD557F366849954BEF88D0017AB4515C9A1D62BD4BDCDAFCB624C971FB&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463732303511&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=3183aae6-b4f2-44f3-9b65-e6341a3b555d&__skcy=MAr2EfIpIvN3Zz21Hy7ri%2FnD3Ss%3D");
                            startActivity(intent);
                            break;
                    }
                }
            }

            public void setData(int position) {
                getPageDataFromNet();
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
                    imageView.setOnTouchListener(new MyOnTouchListener());
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

                @Override
                public void destroyItem(ViewGroup container, int position, Object object) {
                //            super.destroyItem(container, position, object);
                    container.removeView((View) object);
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

        private class RecViewHolder extends RecyclerView.ViewHolder{

            private ImageView likeIcon;
            private TextView likeTv1;
            private TextView likeTv2;
            private TextView likeTv3;
            private ImageView likeIcon1;
            private TextView likeTv4;
            private TextView likeTv5;
            private TextView likeTv6;

            public RecViewHolder(View itemView) {
                super(itemView);

                likeIcon = (ImageView) itemView.findViewById(R.id.shop_likeicon);
                likeTv1 = (TextView) itemView.findViewById(R.id.tv_shop_like1);
                likeTv2 = (TextView) itemView.findViewById(R.id.tv_shop_like2);
                likeTv3 = (TextView) itemView.findViewById(R.id.tv_shop_like3);

                likeIcon1 = (ImageView) itemView.findViewById(R.id.shop_likeicon1);
                likeTv4 = (TextView) itemView.findViewById(R.id.tv_shop_like4);
                likeTv5 = (TextView) itemView.findViewById(R.id.tv_shop_like5);
                likeTv6 = (TextView) itemView.findViewById(R.id.tv_shop_like6);
            }

            public void setData(int i) {
                ShopBean.DataBean.ListBean listBean = likeData1.get(i);

                Glide.with(me).load(listBean.getPic()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.splash_2).error(R.drawable.splash_2).into(likeIcon);

                likeTv1.setText(listBean.getTitle());
                likeTv2.setText(listBean.getPrice() + "");
                likeTv3.setText(listBean.getValue() + "元");
                likeTv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                ShopBean.DataBean.ListBean listBean1 = likeData2.get(i);

                Glide.with(me).load(listBean1.getPic()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.splash_2).error(R.drawable.splash_2).into(likeIcon1);

                likeTv4.setText(listBean1.getTitle());
                likeTv5.setText(listBean1.getPrice() + "");
                likeTv6.setText(listBean1.getValue() + "元");
                likeTv6.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }

}

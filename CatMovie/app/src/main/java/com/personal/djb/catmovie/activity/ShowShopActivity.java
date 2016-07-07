package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.ShopBean;
import com.personal.djb.catmovie.utils.CartProvider;
import com.personal.djb.catmovie.utils.ScreenUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ShowShopActivity extends Activity {

    private RecyclerView mShowShopRecy;

    private String COMMON_URL = "";

    private List<ShopBean.DataBean.ListBean> datas;

    private CommonAdapterForShop adapter;
    private Context me;

    private ImageButton mBtnBack;
    private TextView mTvBtn1;
    private TextView mTvBtn2;
    private TextView mTvBtn3;
    private PopupWindow popupWindow;
    private ListView leftLV;
    private ShowAdapter showAdapter;

    private CartProvider cartProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_shop);
        me = this;
        cartProvider = new CartProvider(me);

        mShowShopRecy = (RecyclerView) findViewById(R.id.show_shop_recy);
        mBtnBack = (ImageButton) findViewById(R.id.title_show_shop_back);
        mTvBtn1 = (TextView) findViewById(R.id.tv_show_shop1);
        mTvBtn2 = (TextView) findViewById(R.id.tv_show_shop2);
        mTvBtn3 = (TextView) findViewById(R.id.tv_show_shop3);

        //  是背景变暗
        darkView = findViewById(R.id.main_darkview);
        //  两个动画
        animIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(this, R.anim.fade_out_anim);

        initPop();
        setListener();
        COMMON_URL = getIntent().getStringExtra("shopurl");

        getDataFromNet();
    }

    /**
     * 以下实现popwindow多级菜单显示
     */
    public void showPuPWindow() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(mTvBtn1);
//            popupWindow.setAnimationStyle(-1);
            //背景变暗
            darkView.startAnimation(animIn);
            darkView.setVisibility(View.VISIBLE);
        }
    }

    //弹出PopupWindow时背景变暗
    private View darkView;
    //弹出PopupWindow时，背景变暗的动画
    private Animation animIn, animOut;

    private String[] data1 = {"全部/未分类","超蝠","魔兽","美队","机器猫","星球大战","功夫熊猫","变形金刚","其他主题","超人","迪士尼","钢铁侠","史努比","大圣","日漫","大白","小黄人"};
    private String[] data2 = {"全部/未分类","数码","高玩专区","玩具","生活","服饰","3C配件","箱包","手办公仔","电影原著","配饰","家纺","文具","毛绒公仔"};
    private String[] data3 = {"默认排序","价格从低到高","价格从高到低","销量最多","销量最少","最近更新","最远更新"};


    private List<String> list;

    private void initPop() {
        popupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_layout_for_shopshow, null);
        leftLV = (ListView) view.findViewById(R.id.pop_listview_left);

        list = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        initList();

        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
//        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);

        popupWindow.setHeight(ScreenUtils.getScreenH(this) * 3 / 5);
        popupWindow.setWidth(ScreenUtils.getScreenW(this));

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkView.startAnimation(animOut);
                darkView.setVisibility(View.GONE);

                leftLV.setSelection(0);
                flag1 = false;
                flag2 = false;
                flag3 = false;
                mTvBtn1.setSelected(false);
                mTvBtn3.setSelected(false);
                mTvBtn2.setSelected(false);
            }
        });

        showAdapter = new ShowAdapter();
        leftLV.setAdapter(showAdapter);

        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭popupWindow，显示用户选择的分类
                popupWindow.dismiss();


                Toast.makeText(me, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && popupWindow.isShowing()) {
            popupWindow.dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private List<String> list1;
    private List<String> list2;
    private List<String> list3;

    public void initList() {
        for(int i = 0; i < data1.length; i++) {
            list1.add(data1[i]);
        }
        for(int i = 0; i < data2.length; i++) {
            list2.add(data2[i]);
        }
        for(int i = 0; i < data3.length; i++) {
            list3.add(data3[i]);
        }
    }

    private boolean flag1;
    private boolean flag2;
    private boolean flag3;

    private void setListener() {
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    return;
                }
                finish();
            }
        });

        mTvBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag1 && popupWindow.isShowing()) {
                    list.clear();
                    list.addAll(list1);
                    showAdapter.notifyDataSetChanged();
//                    showPuPWindow();
                    flag1 = true;
                    mTvBtn1.setSelected(flag1);
                    mTvBtn2.setSelected(false);
                    mTvBtn3.setSelected(false);
                } else if (!flag1 && !popupWindow.isShowing()) {
                    list.clear();
                    list.addAll(list1);
                    showAdapter.notifyDataSetChanged();
                    showPuPWindow();
                    flag1 = true;
                    mTvBtn1.setSelected(flag1);
                    mTvBtn2.setSelected(false);
                    mTvBtn3.setSelected(false);
                } else if (flag1) {
                    mTvBtn1.setSelected(false);
                    flag1 = false;
                    popupWindow.dismiss();
                }

            }
        });

        mTvBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag2 && popupWindow.isShowing()) {
                    list.clear();
                    list.addAll(list2);
                    showAdapter.notifyDataSetChanged();
//                    showPuPWindow();
                    flag2 = true;
                    mTvBtn2.setSelected(flag2);
                    mTvBtn1.setSelected(false);
                    mTvBtn3.setSelected(false);
                } else if (!flag2 && !popupWindow.isShowing()) {
                    list.clear();
                    list.addAll(list2);
                    showAdapter.notifyDataSetChanged();
                    showPuPWindow();
                    flag2 = true;
                    mTvBtn2.setSelected(flag2);
                    mTvBtn1.setSelected(false);
                    mTvBtn3.setSelected(false);
                } else if (flag2) {
                    mTvBtn2.setSelected(false);
                    flag2 = false;
                    popupWindow.dismiss();
                }
            }
        });

        mTvBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag3 && popupWindow.isShowing()) {
                    list.clear();
                    list.addAll(list3);
                    showAdapter.notifyDataSetChanged();
//                    showPuPWindow();
                    flag3 = true;
                    mTvBtn3.setSelected(flag3);
                    mTvBtn2.setSelected(false);
                    mTvBtn1.setSelected(false);
                } else if (!flag3 && !popupWindow.isShowing()) {
                    list.clear();
                    list.addAll(list3);
                    showAdapter.notifyDataSetChanged();
                    showPuPWindow();
                    flag3 = true;
                    mTvBtn3.setSelected(flag3);
                    mTvBtn2.setSelected(false);
                    mTvBtn1.setSelected(false);
                } else if (flag3) {
                    mTvBtn1.setSelected(false);
                    flag3 = false;
                    popupWindow.dismiss();
                }
            }
        });
    }

    private void getDataFromNet() {
        if ("".equals(COMMON_URL)) {
            Toast.makeText(ShowShopActivity.this, "没有链接", Toast.LENGTH_SHORT).show();
            return;
        }

        OkHttpUtils.get().url(COMMON_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                processComData(response);
            }
        });
    }

    private void processComData(String response) {
        ShopBean shopBean = new Gson().fromJson(response, ShopBean.class);
        datas = shopBean.getData().getList();

        adapter = new CommonAdapterForShop();
        RecyclerView.LayoutManager comManager = new GridLayoutManager(this,2);
        mShowShopRecy.setLayoutManager(comManager);
        mShowShopRecy.setAdapter(adapter);
    }

    private class CommonAdapterForShop extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CommonViewHolder holder = new CommonViewHolder(
                    LayoutInflater.from(me).inflate(R.layout.shop_common_item,parent,false)
            );
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((CommonViewHolder)holder).setData(position);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class CommonViewHolder extends RecyclerView.ViewHolder{
            private ImageView likeIcon;
            private TextView likeTv1;
            private TextView likeTv2;
            private TextView likeTv3;

            private ImageButton ibForBuy;

            public CommonViewHolder(View itemView) {
                super(itemView);

                likeIcon = (ImageView) itemView.findViewById(R.id.shop_likeicon);
                likeTv1 = (TextView) itemView.findViewById(R.id.tv_shop_like1);
                likeTv2 = (TextView) itemView.findViewById(R.id.tv_shop_like2);
                likeTv3 = (TextView) itemView.findViewById(R.id.tv_shop_like3);

                ibForBuy = (ImageButton) itemView.findViewById(R.id.ib_add_cart);

                ibForBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("TAG", "添加到购物车");
                        cartProvider.update(cartProvider.conversion(datas.get(getLayoutPosition())));
                    }
                });

            }

            public void setData(int i) {
                ShopBean.DataBean.ListBean listBean = datas.get(i);

                Glide.with(me).load(listBean.getPic()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.splash_2).error(R.drawable.splash_2).into(likeIcon);

                likeTv1.setText(listBean.getTitle());
                likeTv2.setText(listBean.getPrice() + "");
                likeTv3.setText(listBean.getValue() + "元");
                likeTv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            }

        }
    }

    private class ShowAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(me).inflate(R.layout.right_listview_item_forshow, null);
                holder.nameTV = (TextView) convertView.findViewById(R.id.right_item_name);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nameTV.setEnabled(false);
            holder.nameTV.setText(list.get(position));

            return convertView;
        }

        private class ViewHolder{
            TextView nameTV;
        }
    }
}

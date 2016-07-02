package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.findbean.yingku.BaseDataBean;
import com.personal.djb.catmovie.utils.CacheUtils;

import java.util.List;

public class BillboardActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<BaseDataBean.DataBean.MoviesBean> datas;
    private MyAdapter adapter;

    private ImageButton ivBack;

    /**
     * title名字
     */
    private TextView mBillTitle;
    private int boardtype;
    private TextView mContent;
    private TextView mUpDatedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_billboard);

        BaseDataBean.DataBean dataBean = (BaseDataBean.DataBean) getIntent().getSerializableExtra("data");
        if (dataBean != null) {
            datas = dataBean.getMovies();
        } else {
            Toast.makeText(BillboardActivity.this, "未找到数据", Toast.LENGTH_SHORT).show();
            finish();
        }

        boardtype = dataBean.getBoardtype();

        ivBack = (ImageButton) findViewById(R.id.ib_yk_forback);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBillTitle = (TextView) findViewById(R.id.bill_title);
        mContent = (TextView) findViewById(R.id.up_content);
        mUpDatedate = (TextView) findViewById(R.id.update_date);
        mContent.setText(dataBean.getContent());
        mUpDatedate.setText(dataBean.getCreated());
        setTitleName();

        mRecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
//        Log.e("du", dataBean.toString());
        adapter = new MyAdapter();

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

    }

    private void setTitleName() {
        switch (boardtype) {
            case 7 :
                mBillTitle.setText("热映口碑榜单");
                break;
            case 6 :
                mBillTitle.setText("最受期待榜单");
                break;
            case 4 :
                mBillTitle.setText("北美票房榜单");
                break;
            case 1 :
                mBillTitle.setText("TOP100榜单");
                break;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    BillboardActivity.this).inflate(R.layout.item_billboard, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((ViewHolder)holder).setData(position);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            //  票房的布局
            private RelativeLayout rlPfGroup;
            //  图标
            private ImageView ivItemHotmovieIcon;
            //  电影名称
            private TextView tvItemHotmovieName;
            //  电影评分布局
            private LinearLayout llItemHotmovieScore;
            //  电影评分
            private TextView tvItemHotmovieScore;
            //  新增想看人数
            private TextView tvItemHotmovieWish;
            //  演员
            private TextView tvItemHotmovieCount;
            //  上映日期或者没有
            private TextView tvItemHotmovieDesc;
            //  上映日期或者总想看人数
            private TextView tvItemHotmovieDate;
            //  想看的按钮
            private Button btnItemHotmovieWant;
            //  名次
            private TextView tvNumber;
            //  票房金额
            private TextView tvPFPrice;
            //  票房的
            private RelativeLayout rlWantGroup;

            public ViewHolder(View itemView) {
                super(itemView);

                ivItemHotmovieIcon = (ImageView)itemView.findViewById(R.id.iv_item_hotmovie_icon);
                tvItemHotmovieName = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_name);
                llItemHotmovieScore = (LinearLayout)itemView.findViewById(R.id.ll_item_hotmovie_score);
                tvItemHotmovieScore = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_score);
                tvItemHotmovieWish = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_wish1);
                tvItemHotmovieCount = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_count);
                tvItemHotmovieDesc = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_desc);
                tvItemHotmovieDate = (TextView)itemView.findViewById(R.id.tv_item_hotmovie_date);
                btnItemHotmovieWant = (Button)itemView.findViewById(R.id.btn_item_hotmovie_want );
                tvNumber = (TextView) itemView.findViewById(R.id.tv_number);

                tvPFPrice = (TextView) itemView.findViewById(R.id.tv_pf_price);
                rlWantGroup = (RelativeLayout) itemView.findViewById(R.id.rl_wantgroup);
                rlPfGroup = (RelativeLayout) itemView.findViewById(R.id.pf_group);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = datas.get(getLayoutPosition()).getId();
                        String url = "http://m.maoyan.com/movie/" + id + "?_v_=yes";
                        Intent intent = new Intent(BillboardActivity.this,WebActivity.class);
                        intent.putExtra("url",url);
                        BillboardActivity.this.startActivity(intent);
                    }
                });
            }

            public void setData(int position) {
                BaseDataBean.DataBean.MoviesBean moviesBean = datas.get(position);
                String imgUrl = CacheUtils.change(moviesBean.getImg());
                //  先设置公共的东西
                Glide.with(BillboardActivity.this).load(imgUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.splash_2).placeholder(R.drawable.splash_2).into(ivItemHotmovieIcon);

                tvItemHotmovieDesc.setText(moviesBean.getRt());
                tvItemHotmovieCount.setText(moviesBean.getStar());
                tvItemHotmovieName.setText(moviesBean.getNm());

                tvNumber.setText((position + 1) + "");
                tvNumber.setEnabled(false);
                if(position < 3) {
                    tvNumber.setEnabled(true);
                }
                
                //  分类型设置
                switch (boardtype) {
                    //  热映榜单
                    case 1 :
                    case 7 :
                        llItemHotmovieScore.setVisibility(View.VISIBLE);
                        tvItemHotmovieScore.setText(moviesBean.getSc()+"");
                        break;

                    case 6 :
                        tvItemHotmovieDate.setVisibility(View.VISIBLE);
                        tvItemHotmovieDate.setText("总想看：" + moviesBean.getWish() + "人");
                        rlWantGroup.setVisibility(View.VISIBLE);
                        tvItemHotmovieWish.setText(moviesBean.getMonthWish()+"");
                        break;
                    case 4 :
                        tvItemHotmovieDate.setVisibility(View.VISIBLE);
                        tvItemHotmovieDate.setText("总票房：" + moviesBean.getSumBoxOffice()+"万美元");
                        rlPfGroup.setVisibility(View.VISIBLE);
                        tvPFPrice.setText(moviesBean.getWeekBoxOffice()+"");
                        break;
                }
            }
        }
    }
}

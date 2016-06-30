package com.personal.djb.catmovie.adapter.outmovieadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.movies.outmoviebean.JapanMovieBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class JapanMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private JapanMovieBean bean;
    //  美国电影的列表
    private List<JapanMovieBean.DataBean.HotBean> datas;
    private Context context;

    //  头部类型
    private static final int HEAD_TYPE = 0;
    //  默认类型
    private static final int DEFAULT_TYPE = 1;

    public JapanMovieAdapter(Context context, JapanMovieBean datas){
        this.context = context;
        this.bean = datas;
        this.datas = datas.getData().getHot();
        Log.e("xxxxx", ""+this.datas.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_TYPE) {
            //  头部
            TextView textView = new TextView(context);
            HeadViewHolder headHolder = new HeadViewHolder(textView);
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
            ((JapanMovieAdapter.HeadViewHolder)holder).setData();
        } else if (holder instanceof ViewHolder) {
            ((JapanMovieAdapter.ViewHolder)holder).setData(position - 1);
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

    /**
     * 这个holder用来显示头部的viewpager
     */
    public class HeadViewHolder extends RecyclerView.ViewHolder {

        private TextView view;
        public HeadViewHolder(View itemView) {
            super(itemView);

            view = (TextView) itemView;
            view.setWidth(1);
            view.setHeight(1);
            view.setVisibility(View.GONE);
        }

        public void setData() {
            view.setVisibility(View.GONE);
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
        private TextView mHotMovieTitleDesc;

        //  查看全部影片
        private TextView mHotMovieCheckAll;
        private RelativeLayout mHotMovieCheckAllRl;

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

            mHotMovieTitleDesc = (TextView) itemView.findViewById(R.id.tv_item_title_desc);

            mHotMovieBtnWant = (Button) itemView.findViewById(R.id.btn_item_hotmovie_want);

            mHotMovieCheckAll = (TextView) itemView.findViewById(R.id.tv_item_movie_checkall);
            mHotMovieCheckAllRl = (RelativeLayout) itemView.findViewById(R.id.tv_item_movie_checkall_rl);
        }

        /**
         * 绑定每个item的数据
         * @param position
         */
        public void setData(int position) {
            JapanMovieBean.DataBean.HotBean comingBean = datas.get(position);
            //  替换为可以访问的图片
            String imgUrl = comingBean.getImg();
            imgUrl = imgUrl.replace("w.h", "165.220");

            //  设置公共的view 图片 标题等
            Glide.with(context).load(imgUrl).placeholder(R.drawable.backgroud_logo02)
                    .error(R.drawable.backgroud_logo02).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mHotMovieIcon);

            mHotMovieName.setText(comingBean.getNm());
            mHotMovieDesc.setText(comingBean.getCat());
            mHotMovieCount.setText(comingBean.getDesc());

            mHotMovieWishLinear.setVisibility(View.VISIBLE);
            mHotMovieWish.setText(comingBean.getWish() + "");

            mMaxScreen3D.setVisibility(View.GONE);
            mScreen3D.setVisibility(View.GONE);
            //  1.判断3d
            if (comingBean.getVer().length()>2) {
                if(comingBean.getVer().length()>5) {
                    mMaxScreen3D.setVisibility(View.VISIBLE);
                }
                mScreen3D.setVisibility(View.VISIBLE);
            }

            //  显示item头部小条
            mHotMovieTitleDesc.setVisibility(View.GONE);
            if (getLayoutPosition() == 1) {
                mHotMovieTitleDesc.setText("日本热映");
                mHotMovieTitleDesc.setVisibility(View.VISIBLE);
            }

            //  判断是否显示小新闻
            mHotMovieSmallNews1.setVisibility(View.GONE);
            mHotMovieSmallNews2.setVisibility(View.GONE);
            List<JapanMovieBean.DataBean.HotBean.HeadLinesVOBean> headLinesVO = comingBean.getHeadLinesVO();
            if (headLinesVO != null && headLinesVO.size() > 0) {
                mHotMovieSmallNews1.setVisibility(View.VISIBLE);
                mHotMovieSmallNewsIcon1.setText(headLinesVO.get(0).getType());
                mHotMovieSmallNewsDesc1.setText(headLinesVO.get(0).getTitle());
                if (headLinesVO.size() > 1) {
                    mHotMovieSmallNews2.setVisibility(View.VISIBLE);
                    mHotMovieSmallNewsIcon2.setText(headLinesVO.get(1).getType());
                    mHotMovieSmallNewsDesc2.setText(headLinesVO.get(1).getTitle());
                }
            }


            //  2.判断是否上映
            if (comingBean.getShowst() == 4) {
                //  预售
                setWaitNoPlayMovie(comingBean);
            } else if (comingBean.getShowst() == 1) {
                //  想看
                setWantPlayMovie(comingBean);
            } else if (comingBean.getShowst() == 2){
                //  想看
                setWantPlayMovie(comingBean);
            }

            mHotMovieCheckAllRl.setVisibility(View.GONE);

            if (getLayoutPosition() == datas.size() && bean.getData().getPaging().isHasMore()) {
                mHotMovieCheckAllRl.setVisibility(View.VISIBLE);

                mHotMovieCheckAll.setText("查看全部热映影片");

                mHotMovieCheckAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "加载更多，但没数据~~", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        //  预售
        private void setWaitNoPlayMovie(JapanMovieBean.DataBean.HotBean comingBean) {
            mHotMovieBtnOrder.setVisibility(View.VISIBLE);
            mHotMovieBtnWant.setVisibility(View.GONE);
        }

        //  想看
        private void setWantPlayMovie(JapanMovieBean.DataBean.HotBean comingBean) {
            mHotMovieBtnOrder.setVisibility(View.GONE);
            mHotMovieBtnWant.setVisibility(View.VISIBLE);
        }

    }

}

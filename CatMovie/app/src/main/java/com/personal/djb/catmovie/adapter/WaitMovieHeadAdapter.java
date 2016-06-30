package com.personal.djb.catmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.personal.djb.catmovie.R;

import java.util.List;

/**
 * Created by Administrator on 2016/6/26 0026.
 */
public class WaitMovieHeadAdapter extends RecyclerView.Adapter<WaitMovieHeadAdapter.ViewHolder> {
    
    private int currType;

    private List<Integer> topDatas;
    private List<Integer> bottomDatas;
    private Context context;

    public WaitMovieHeadAdapter(Context context, List<Integer> datas, int headType) {
        this.context = context;
        switch (headType) {
            case MovieAdapter.HEAD_TYPE_TOP :
                topDatas = datas;
                break;
            case MovieAdapter.HEAD_TYPE_BOTTOM :
                bottomDatas = datas;
                break;
        }

        currType = headType;
    }

    @Override
    public WaitMovieHeadAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_wait_movie_head, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(WaitMovieHeadAdapter.ViewHolder holder, int position) {
        switch (currType) {
            case MovieAdapter.HEAD_TYPE_TOP :
                holder.setTopData(position);
                break;
            case MovieAdapter.HEAD_TYPE_BOTTOM :
                holder.setBottomData(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        switch (currType) {
            case MovieAdapter.HEAD_TYPE_TOP :
                return topDatas.size();
            case MovieAdapter.HEAD_TYPE_BOTTOM :
                return bottomDatas.size();
        }
        return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mWaitMovieHeadRlTop;
        private RelativeLayout mWaitMovieHeadRlBottom;

        private ImageView mWaitMovieHeadBottomIcon;
        private ImageView mWaitMovieHeadTopIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            switch (currType) {
                case MovieAdapter.HEAD_TYPE_TOP :
                    mWaitMovieHeadRlTop = (RelativeLayout) itemView.findViewById(R.id.rl_waitmovie_head_top);
                    mWaitMovieHeadTopIcon = (ImageView) itemView.findViewById(R.id.iv_waitmovie_head_top_icon);
                    mWaitMovieHeadRlTop.setVisibility(View.VISIBLE);
                    break;

                case MovieAdapter.HEAD_TYPE_BOTTOM :
                    mWaitMovieHeadRlBottom = (RelativeLayout) itemView.findViewById(R.id.rl_waitmovie_head_bottom);
                    mWaitMovieHeadBottomIcon = (ImageView) itemView.findViewById(R.id.iv_waitmovie_head_bottom_icon);
                    mWaitMovieHeadRlBottom.setVisibility(View.VISIBLE);
                    break;
            }

        }

        public void setTopData(int position) {
            mWaitMovieHeadTopIcon.setImageResource(topDatas.get(position));
        }

        public void setBottomData(int position) {
            mWaitMovieHeadBottomIcon.setImageResource(bottomDatas.get(position));
        }
    }

}

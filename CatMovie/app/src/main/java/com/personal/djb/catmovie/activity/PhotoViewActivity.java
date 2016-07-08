package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.findbean.YKTopBean;
import com.personal.djb.catmovie.utils.ScreenUtils;
import com.personal.djb.catmovie.view.MyPhotoViewPager;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends Activity {

    private PhotoViewAttacher mAttacher;
    private List<YKTopBean.DataBean> topDatas;
    private MyPhotoViewPager mVpForPhotoView;
    private MyPageAdapter vpAdapter;
    private int screenH;
    private int screenW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo_view);

        mVpForPhotoView = (MyPhotoViewPager) findViewById(R.id.vp_for_photoview);

        topDatas = (List<YKTopBean.DataBean>) getIntent().getSerializableExtra("imgurl");

        screenH = ScreenUtils.getScreenH(this);
        screenW = ScreenUtils.getScreenW(this);

        Log.e("TAG111", topDatas.toString());

        vpAdapter = new MyPageAdapter();
        mVpForPhotoView.setAdapter(vpAdapter);
    }

    private class MyPageAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return topDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView myPhotoView = new ImageView(PhotoViewActivity.this);
            myPhotoView.setScaleType(ImageView.ScaleType.FIT_XY);

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(screenW, screenH);
            myPhotoView.setLayoutParams(layoutParams);

            Glide.with(PhotoViewActivity.this).load(topDatas.get(position).getBgImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.splash_2)
                    .error(R.drawable.splash_2).into(myPhotoView);

            // Any implementation of ImageView can be used!

            // Set the Drawable displayed

            // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
            mAttacher = new PhotoViewAttacher(myPhotoView);
            container.addView(myPhotoView);
            return myPhotoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}

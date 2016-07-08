package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.gallery.CustomGallery;
import com.personal.djb.catmovie.bean.movies.HotMovieBean;

import java.util.List;

public class GalleryActivity extends Activity {

    private List<HotMovieBean.DataBean.MoviesBean> datas;
    private TextView tv_gallery_title;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        datas = (List<HotMovieBean.DataBean.MoviesBean>) getIntent().getSerializableExtra("imgs");
        tv_gallery_title = (TextView) findViewById(R.id.tv_gallery_title);

        CustomGallery customGallery = (CustomGallery) findViewById(R.id.customgallery);
        adapter = new ImageAdapter();
        customGallery.setAdapter(adapter);
        customGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_gallery_title.setText(datas.get(position).getNm());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class ImageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ImageView imageView;
            if (convertView != null) {
                imageView = (ImageView) convertView;
            } else {
                imageView = new ImageView(GalleryActivity.this);
            }

            Glide.with(GalleryActivity.this).load(datas.get(position).getImg()).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.splash_2).error(R.drawable.splash_2).into(imageView);
            Gallery.LayoutParams params = new Gallery.LayoutParams(240,320);
            imageView.setLayoutParams(params);
            return imageView;
        }
    }
}

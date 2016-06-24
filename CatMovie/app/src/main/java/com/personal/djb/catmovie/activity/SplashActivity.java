package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.personal.djb.catmovie.MainActivity;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.SplashImageBean;
import com.personal.djb.catmovie.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.List;

import okhttp3.Call;

public class SplashActivity extends Activity {

    private String mSplashUrl = "http://api.meituan.com/dianying/posters.json?city_id=1&poster_type=1&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=0.0&lng=0.0&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463656383990&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=09cbe8d3-3cb0-4e54-bc41-8f0b3387cdf2&__skcy=RbOZVh%2BK%2Bf86K3WlGJYg4ls7DIU%3D";

    private ImageView mSplashImage;
    private Drawable drawable;

    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0 :
                    //  检查网络连接
                    if (NetUtils.getInstance().checkNetworkState()) {
                        loadImageFromNet();
                    } else {
                        startMain();
                    }
                    break;
                case 1 :
                    if (mSplashImage.getDrawable() != drawable) {
                        setAnimation();
                    } else {
                        startMain();
                    }
                    break;
            }

        }
    };

    /**
     * 加载网络广告
     */
    private void loadImageFromNet() {
        RequestCall call = OkHttpUtils.get().url(mSplashUrl).build();
        call.connTimeOut(1000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "splash联网失败");
                startMain();
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    /**
     * 设置动画
     */
    private void setAnimation() {
        ScaleAnimation sa = new ScaleAnimation(1.0f, 1.15f, 1.0f, 1.15f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(3000);
        sa.setFillAfter(true);
        mSplashImage.startAnimation(sa);

        sa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 启动主页面
     */
    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_splash);

        mSplashImage = (ImageView) findViewById(R.id.iv_splash);

//        Log.e("TAG", "是否为空"+(mSplashNetImage.getDrawable() == null));
        drawable = mSplashImage.getDrawable();
//        Log.e("TAG", "mSplashImage  "+(mSplashImage.getDrawable()==drawable));

        handler.sendEmptyMessageDelayed(0, 1200);

    }

    /**
     * 处理联网取得的数据
     * @param json
     */
    private void processData(String json) {
        SplashImageBean sib = new Gson().fromJson(json, SplashImageBean.class);
        List<SplashImageBean.PostersBean> posters = sib.getPosters();

        if (posters != null && posters.size() > 0) {
            String imageUrl = posters.get(posters.size()-1).getPic();
            Glide.with(this).load(imageUrl).placeholder(R.drawable.splash_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(mSplashImage);
            handler.sendEmptyMessageDelayed(1,1000);
        } else {
            startMain();
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);

        Glide.clear(mSplashImage);
        RequestCall call = OkHttpUtils.get().url(mSplashUrl).build();
        call.cancel();
    }
}

package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.ScreenBean;
import com.personal.djb.catmovie.utils.DensityUtil;
import com.personal.djb.catmovie.utils.LocUtil;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends Activity {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.e("TAG", "宽" + screenBean.getsWidth());
        Log.e("TAG", "高" + screenBean.getsHeight());

        switch (newConfig.orientation) {
            //更改为LANDSCAPE
            case (Configuration.ORIENTATION_LANDSCAPE):
                //如果转换为横向屏时，有要做的事，请写在这里
                RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(screenBean.getsHeight(),screenBean.getsWidth());

                mVideoView.setLayoutParams(params1);
                VideoActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);

                Toast.makeText(getApplicationContext(), "横屏", Toast.LENGTH_SHORT).show();
                break;
            //更改为PORTRAIT
            case (Configuration.ORIENTATION_PORTRAIT):
                //如果转换为竖向屏时，有要做的事，请写在这里
                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(screenBean.getsWidth(), DensityUtil.dip2px(this, 200));
                mVideoView.setLayoutParams(params2);
                VideoActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                Toast.makeText(getApplicationContext(), "竖屏", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 重力感应监听者
     */
    public class OrientationSensorListener implements SensorEventListener {
        private static final int _DATA_X = 0;
        private static final int _DATA_Y = 1;
        private static final int _DATA_Z = 2;

        public static final int ORIENTATION_UNKNOWN = -1;

        private Handler rotateHandler;

        public OrientationSensorListener(Handler handler) {
            rotateHandler = handler;
        }

        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub

        }

        public void onSensorChanged(SensorEvent event) {

                float[] values = event.values;
                int orientation = ORIENTATION_UNKNOWN;
                float X = -values[_DATA_X];
                float Y = -values[_DATA_Y];
                float Z = -values[_DATA_Z];
                float magnitude = X*X + Y*Y;
                // Don't trust the angle if the magnitude is small compared to the y value
                if (magnitude * 4 >= Z*Z) {
                    //屏幕旋转时
                    float OneEightyOverPi = 57.29577957855f;
                    float angle = (float)Math.atan2(-Y, X) * OneEightyOverPi;
                    orientation = 90 - (int)Math.round(angle);
                    // normalize to 0 - 359 range
                    while (orientation >= 360) {
                        orientation -= 360;
                    }
                    while (orientation < 0) {
                        orientation += 360;
                    }
                }
                if (rotateHandler!=null) {
                    rotateHandler.obtainMessage(888, orientation, 0).sendToTarget();
                }

        }
    }




    /** 当前视频路径 */
    //    private String path = Environment.getExternalStorageDirectory() + "/hd.mp4";
    private String path;
    /** 当前声音 */
    private int mVolume = -1;
    /** 最大音量 */
    private int mMaxVolume;
    /** 当前亮度 */
    private float mBrightness = -1f;
    /** 手势数目 */
    private int finNum = 0;
    /** 当前进度 */

    private View mVolumeBrightnessLayout;
    private ImageView mOperationBg;
    private ImageView mOperationPercent;
    private GestureDetector gestDetector;
    private ScaleGestureDetector scaleDetector;

    private ScreenBean screenBean;

    private void init() {
        mVideoView = (VideoView) findViewById(R.id.surface_view);
        mVolumeBrightnessLayout = findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView) findViewById(R.id.operation_bg);
        mOperationPercent = (ImageView) findViewById(R.id.operation_percent);

        mMaxVolume = LocUtil.getMaxVolume(this);
        gestDetector = new GestureDetector(this, new SingleGestureListener());
        scaleDetector = new ScaleGestureDetector(this, new MultiGestureListener());

        screenBean = LocUtil.getScreenPix(this);

        path = getIntent().getStringExtra("videoUrl");

        if (path == "") {
            Toast.makeText(VideoActivity.this, "视频路径无效", Toast.LENGTH_SHORT).show();
            return;
        } else {
            mVideoView.setVideoPath(path);
            mVideoView.setMediaController(new MediaController(this));
            mVideoView.requestFocus();

            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
//                            mediaPlayer.setPlaybackSpeed(1.0f);
                    mediaPlayer1 = mediaPlayer;
                    mediaPlayer.start();
                }
            });
        }
    }

    private MediaPlayer mediaPlayer1;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mediaPlayer1 != null) {
//            mediaPlayer1.reset();
//            mediaPlayer1.release();
//            mediaPlayer1.stop();
//        }
        mVideoView.stopPlayback();
    }

    private io.vov.vitamio.widget.VideoView mVideoView;


    private SensorManager sm;
    private OrientationSensorListener listener;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);


        init();


        //注册重力感应器  屏幕旋转
        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new OrientationSensorListener(mDismissHandler);
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);

    }


    /** 定时隐藏 */
    private Handler mDismissHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 888:
                    int orientation = msg.arg1;
                    if (orientation>45&&orientation<135) {

                    }else if (orientation>135&&orientation<225){

                    }else if (orientation>225&&orientation<315){
                        System.out.println("切换成横屏");
                        VideoActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    }else if ((orientation>315&&orientation<360)||(orientation>0&&orientation<45)){
                        System.out.println("切换成竖屏");
                        VideoActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }
                    break;
                case 0 :
                    mVolumeBrightnessLayout.setVisibility(View.GONE);
                    break;
            }

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        finNum=event.getPointerCount();

        if (1 == finNum) {
            gestDetector.onTouchEvent(event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    endGesture();
            }
        } else if (2 ==finNum) {
            scaleDetector.onTouchEvent(event);
        }
        return true;
    }

    /** 手势结束 */
    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;

        // 隐藏
        mDismissHandler.removeMessages(0);
        mDismissHandler.sendEmptyMessageDelayed(0, 500);
    }

    /**
     * 视频缩放
     */
    public void changeLayout(int size) {
        mVideoView.setVideoLayout(size, 0);
    }

    /**
     * 声音大小
     *
     * @param percent
     */
    public void changeVolume(float percent) {
        if (mVolume == -1) {
            mVolume = LocUtil.getCurVolume(this);
            if (mVolume < 0)
                mVolume = 0;
            // 显示
            mOperationBg.setImageResource(R.drawable.video_volumn_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }
        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;

        // 变更声音
        LocUtil.setCurVolume(this, index);

        // 变更进度条
        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = findViewById(R.id.operation_full).getLayoutParams().width
                * index / mMaxVolume;
        mOperationPercent.setLayoutParams(lp);
    }

    /**
     * 亮度大小
     *
     * @param percent
     */
    public void changeBrightness(float percent) {
        if (mBrightness < 0) {
            mBrightness = getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;
            // 显示
            mOperationBg.setImageResource(R.drawable.video_brightness_bg);
            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
        }

        WindowManager.LayoutParams lpa = getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        getWindow().setAttributes(lpa);

        ViewGroup.LayoutParams lp = mOperationPercent.getLayoutParams();
        lp.width = (int) (findViewById(R.id.operation_full).getLayoutParams().width * lpa.screenBrightness);
        mOperationPercent.setLayoutParams(lp);
    }

    /**
     * 单点触屏
     *
     * @author jin
     *
     */
    private class SingleGestureListener implements
            android.view.GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            if(2==finNum){
                return false;
            }

            float moldX = e1.getX();
            float moldY = e1.getY();
            float y = e2.getY();
            if (moldX > screenBean.getsWidth() * 9.0 / 10)// 右边滑动
                changeVolume((moldY - y) / screenBean.getsHeight());
            else if (moldX < screenBean.getsWidth() / 10.0)// 左边滑动
                changeBrightness((moldY - y) / screenBean.getsHeight());
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            // TODO Auto-generated method stub
            return false;
        }
    }

    /**
     * 多点缩放
     *
     * @author jin
     *
     */
    private class MultiGestureListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            // 返回true ，才能进入onscale()函数
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            float oldDis = detector.getPreviousSpan();
            float curDis = detector.getCurrentSpan();
            if (oldDis - curDis > 50) {
                // 缩小
                changeLayout(0);
                Toast.makeText(VideoActivity.this, "缩小", Toast.LENGTH_SHORT).show();
            } else if (oldDis - curDis < -50) {
                // 放大
                changeLayout(1);
                Toast.makeText(VideoActivity.this, "放大", Toast.LENGTH_SHORT).show();
            }
        }

    }

}

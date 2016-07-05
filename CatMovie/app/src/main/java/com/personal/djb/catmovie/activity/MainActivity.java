package com.personal.djb.catmovie.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.pagers.CinemaRadioButtonPager;
import com.personal.djb.catmovie.pagers.FindRadioButtonPager;
import com.personal.djb.catmovie.pagers.MovieRadioButtonPager;
import com.personal.djb.catmovie.pagers.SettingRadioButtonPager;
import com.personal.djb.catmovie.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();



    private FrameLayout mFrameContent;
    private RadioGroup mRadioGroup;

//    private RadioButton mRBMovie;
//    private RadioButton mRBCinema;
//    private RadioButton mRBFind;
//    private RadioButton mRBSetting;

    /**
     * 基础页面集合
     */
    private List<BasePager> datas;

    /**
     * 记录RadioButton点击的位置
     */
    private int position;
    private String currentCity = "";
    private boolean isFirshShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数

        initLocation();
        mLocationClient.start();

        initView();
        initData();
        setListener();
    }

    private void initData() {
        initFragment();

        currentCity = CacheUtils.getString(this, "currcity");
        if(!TextUtils.isEmpty(currentCity)) {
            ((CinemaRadioButtonPager)datas.get(1)).setCityName(currentCity);
            ((MovieRadioButtonPager)datas.get(0)).setCityName(currentCity);
        }
    }

    private void initFragment() {
        datas = new ArrayList<>();
        datas.add(new MovieRadioButtonPager(this));
        datas.add(new CinemaRadioButtonPager(this));
        datas.add(new FindRadioButtonPager(this));
        datas.add(new SettingRadioButtonPager(this));
    }

    private void setListener() {

        mRadioGroup.setOnCheckedChangeListener(new MyOncheckedChangeListener());
        mRadioGroup.check(R.id.rb_main_movie);
        setPager(0);
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        mFrameContent = (FrameLayout) findViewById(R.id.fl_main);

//        mRBMovie = (RadioButton) findViewById(R.id.rb_main_movie);
//        mRBCinema = (RadioButton) findViewById(R.id.rb_main_cinema);
//        mRBFind = (RadioButton) findViewById(R.id.rb_main_find);
//        mRBSetting = (RadioButton) findViewById(R.id.rb_main_setting);

    }

    /**
     * RadioGroup的点击改变监听，切换页面
     */
    private class MyOncheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_main_movie :
                    position = 0;
                    break;
                case R.id.rb_main_cinema:
                    position = 1;
                    break;
                case R.id.rb_main_find:
                    position = 2;
                    break;
                case R.id.rb_main_setting:
                    position = 3;
                    break;
            }

            setPager(position);
        }
    }

    /**
     * 设置fragment以前先判断是否初始化数据过
     * @param position
     */
    private void setPager(int position) {
        BasePager mbp = datas.get(position);
        if (!mbp.isInitData){
            mbp.initData();
        }
        mFrameContent.removeAllViews();
        mFrameContent.addView(mbp.rootView);
    }


    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
//        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {

            Log.e("info", "city = " + location.getCity());
            String posCity = location.getCity().substring(0,
                    location.getCity().length() - 1);

            if(!currentCity.equals(posCity) && isFirshShow) {
                isFirshShow = false;
                showChoiceCityDialog(posCity);
            }

            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.e("dujian", sb.toString());

        }
    }

    //  弹出城市定位提示
    private void showChoiceCityDialog(final String posCity) {

        new AlertDialog.Builder(this).setTitle("切换当前城市")
                .setMessage("检测到当前定位城市为"+posCity+",是否切换")
                .setPositiveButton("切换", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentCity = posCity;
                        CacheUtils.putString(MainActivity.this,"currcity",currentCity);
                        ((CinemaRadioButtonPager)datas.get(1)).setCityName(currentCity);
                        ((MovieRadioButtonPager)datas.get(0)).setCityName(currentCity);
                    }
                })
                .setNegativeButton("取消",null)
                .show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (onAtivityResult != null && data != null) {
            currentCity = data.getStringExtra("cityname");
            ((CinemaRadioButtonPager)datas.get(1)).setCityName(currentCity);
            ((MovieRadioButtonPager)datas.get(0)).setCityName(currentCity);
            onAtivityResult.onResult(requestCode,resultCode,data);
        }
    }

    public interface OnAtivityResult{
        void onResult(int requestCode, int resultCode, Intent data);
    }

    private OnAtivityResult onAtivityResult;

    public void setOnAtivityResult(OnAtivityResult onAtivityResult) {
        this.onAtivityResult = onAtivityResult;
    }

    @Override
    protected void onPause() {
        super.onPause();
        CacheUtils.putString(this, "currcity", currentCity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheUtils.putString(this,"currcity",currentCity);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(myOnKeyDown != null) {
            if(!myOnKeyDown.onKeyDown(keyCode,event)) {
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public interface MyOnKeyDown {
        boolean onKeyDown(int keyCode, KeyEvent event);
    }

    private MyOnKeyDown myOnKeyDown;

    public void setMyOnKeyDown(MyOnKeyDown myOnKeyDown) {
        this.myOnKeyDown = myOnKeyDown;
    }
}

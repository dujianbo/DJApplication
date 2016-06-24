package com.personal.djb.catmovie.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.personal.djb.catmovie.R;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class NetUtils {

    private static NetUtils instance;

    public static NetUtils getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public static void initInstance(Context context) {
        if (instance == null) {
            instance = new NetUtils(context);
        }
    }

    private Context context;
    private ConnectivityManager manager;

    private NetUtils(Context context){
        this.context = context;
        //得到网络连接信息
        manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    };

    /**
     * 检测网络是否连接
     * @return
     */
    public boolean checkNetworkState() {
        boolean flag = false;
        //去进行判断网络是否连接
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
//            setNetwork();
        } else {
//            isNetworkAvailable();
        }

        return flag;
    }


    /**
     * 网络未连接时，调用设置方法
     */
    private void setNetwork(){
        Toast.makeText(context, "wifi is closed!", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.btn_orange_unpressed);
        builder.setTitle("网络提示信息");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                /**
                 * 判断手机系统的版本！如果API大于10 就是3.0+
                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
                 */
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 网络已经连接，然后去判断是wifi连接还是GPRS连接
     * 设置一些自己的逻辑调用
     */
    private void isNetworkAvailable(){

        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING){
            Toast.makeText(context, "wifi is open! gprs", Toast.LENGTH_SHORT).show();
        }
        //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！
        if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING){
            Toast.makeText(context, "wifi is open! wifi", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 在wifi状态下 加载admob广告
     */
    private void loadAdmob(){
    }

//    /**
//     * 检测网络是否可用
//     * @return
//     */
//    public boolean isNetworkConnected() {
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//        return ni != null && ni.isConnectedOrConnecting();
//    }

//    /**
//     * 获取当前网络类型
//     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
//     */
//
//    public static final int NETTYPE_WIFI = 0x01;
//    public static final int NETTYPE_CMWAP = 0x02;
//    public static final int NETTYPE_CMNET = 0x03;
//    public int getNetworkType() {
//        int netType = 0;
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        if (networkInfo == null) {
//            return netType;
//        }
//        int nType = networkInfo.getType();
//        if (nType == ConnectivityManager.TYPE_MOBILE) {
//            String extraInfo = networkInfo.getExtraInfo();
//            if(!TextUtils.isEmpty(extraInfo)){
//                if (extraInfo.toLowerCase().equals("cmnet")) {
//                    netType = NETTYPE_CMNET;
//                } else {
//                    netType = NETTYPE_CMWAP;
//                }
//            }
//        } else if (nType == ConnectivityManager.TYPE_WIFI) {
//            netType = NETTYPE_WIFI;
//        }
//        return netType;
//    }
}

package com.personal.djb.catmovie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.personal.djb.catmovie.bean.movies.HotMovieBean;

import java.util.List;

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
//        NetworkInfo ni = manager.getActiveNetworkInfo();
//        return ni != null && ni.isConnectedOrConnecting();
        return flag;
    }


    /**
     * 网络未连接时，调用此方法
     */
    private void showNoNetPage(){
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

    private List<HotMovieBean.DataBean.MoviesBean> hotMovies;

    public List<HotMovieBean.DataBean.MoviesBean> getHotMovies(){
        return hotMovies;
    }

    public void setHotMovies(List<HotMovieBean.DataBean.MoviesBean> hotMovies){
        this.hotMovies = hotMovies;
    }
}

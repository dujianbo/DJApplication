package com.personal.djb.catmovie;

import android.app.Application;

import com.personal.djb.catmovie.utils.NetUtils;
import com.umeng.socialize.PlatformConfig;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class CatMovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        NetUtils.initInstance(this);

        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //微信 appid appsecret
        PlatformConfig.setSinaWeibo("2794591820", "b45989fb4a0372d64b652068d7e306dd");
        //新浪微博 appkey appsecret
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        // QQ和Qzone appid appkey

    }

    /**
     *　　　　　　　　┏┓　　　┏┓+ +
     *　　　　　　　┏┛┻━━━┛┻┓ + +
     *　　　　　　　┃　　　　　　　┃ 　
     *　　　　　　　┃　　　━　　　┃ ++ + + +
     *　　　　　　 ████━████ ┃+
     *　　　　　　　┃　　　　　　　┃ +
     *　　　　　　　┃　　　┻　　　┃
     *　　　　　　　┃　　　　　　　┃ + +
     *　　　　　　　┗━┓　　　┏━┛
     *　　　　　　　　　┃　　　┃　　　　　　　　　　　
     *　　　　　　　　　┃　　　┃ + + + +
     *　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting　　　　　　　
     *　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug　　
     *　　　　　　　　　┃　　　┃
     *　　　　　　　　　┃　　　┃　　+　　　　　　　　　
     *　　　　　　　　　┃　 　　┗━━━┓ + +
     *　　　　　　　　　┃ 　　　　　　　┣┓
     *　　　　　　　　　┃ 　　　　　　　┏┛
     *　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
     *　　　　　　　　　　┃┫┫　┃┫┫
     *　　　　　　　　　　┗┻┛　┗┻┛+ + + +
     */


    //                            _ooOoo_
    //                           o8888888o
    //                           88" . "88
    //                           (| -_- |)
    //                            O\ = /O
    //                        ____/`---'\____
    //                      .   ' \\| |// `.
    //                       / \\||| : |||// \
    //                     / _||||| -:- |||||- \
    //                       | | \\\ - /// | |
    //                     | \_| ''\---/'' | |
    //                      \ .-\__ `-` ___/-. /
    //                   ___`. .' /--.--\ `. . __
    //                ."" '< `.___\_<|>_/___.' >'"".
    //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
    //                 \ \ `-. \_ __\ /__ _/ .-` / /
    //         ======`-.____`-.___\_____/___.-`____.-'======
    //                            `=---='
    //
    //         .............................................
    //                  佛祖保佑             永无BUG
    //          佛曰:
    //                  写字楼里写字间，写字间里程序员；
    //                  程序人员写程序，又拿程序换酒钱。
    //                  酒醒只在网上坐，酒醉还来网下眠；
    //                  酒醉酒醒日复日，网上网下年复年。
    //                  但愿老死电脑间，不愿鞠躬老板前；
    //                  奔驰宝马贵者趣，公交自行程序员。
    //                  别人笑我忒疯癫，我笑自己命太贱；
    //                  不见满街漂亮妹，哪个归得程序员？

}


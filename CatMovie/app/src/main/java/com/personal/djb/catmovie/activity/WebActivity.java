package com.personal.djb.catmovie.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.utils.NetUtils;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

public class WebActivity extends Activity {

    private WebView mWebView;

    /**
     * 没网的页面
     */
    private RelativeLayout mNoNetPager;
    private ImageView mNoNetButton;
    /**
     * 缓冲的页面
     */
    private RelativeLayout mLoadingPager;
    private String url;

    private ImageButton mBtnForBack;
    private TextView mTvWebTitle;
    private boolean flag = false;
    private ImageButton mBtnShare;

    private final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[] {
                    SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.SINA,
                    SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE,SHARE_MEDIA.DOUBAN
    };

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(WebActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(WebActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(WebActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult( requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        mBtnForBack = (ImageButton) findViewById(R.id.ib_web_forback);
        mTvWebTitle = (TextView) findViewById(R.id.tv_web_title);

        mBtnForBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mNoNetPager = (RelativeLayout) findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) findViewById(R.id.iv_netload_nonet);
        mBtnShare = (ImageButton) findViewById(R.id.ib_web_share);

        mNoNetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoNetPager.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.VISIBLE);
                mWebView.loadUrl(url);
            }
        });

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.GONE);

        //  检查网络连接
        if (!NetUtils.getInstance().checkNetworkState()) {
            mNoNetPager.setVisibility(View.VISIBLE);
            return;
        }
        mLoadingPager.setVisibility(View.VISIBLE);


        String titleName = getIntent().getStringExtra("webname");
        if (titleName != null && !TextUtils.isEmpty(titleName)) {
            mTvWebTitle.setText(titleName);
            flag = true;
        }

        url = getIntent().getStringExtra("url");

        mWebView = (WebView) findViewById(R.id.web_view);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);

        mWebView.loadUrl(url);

        MyWebViewClient myWebView=new MyWebViewClient();
        mWebView.setWebViewClient(myWebView);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if(flag) {
                    return;
                }
                mTvWebTitle.setText(title);//a textview
            }
        });



        //可以将一下代码加到你的MainActivity中，或者在任意一个需要调用分享功能的activity当中
        String[] mPermissionList = new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS};
        ActivityCompat.requestPermissions(WebActivity.this, mPermissionList, 100);
        //  图片url
        final UMImage image = new UMImage(WebActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");

//        final UMImage image = new UMImage(WebActivity.this, url);

        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  分享按钮
//                showShare();
                new ShareAction(WebActivity.this).setDisplayList( displaylist )
                        .withText( "你懂的" )
                        .withTitle("东方好傻啊")
                        .withTargetUrl(url)
                        .withMedia( image )
                        .setListenerList(umShareListener)
                        .open();
            }
        });

    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mNoNetPager.setVisibility(View.GONE);
            mLoadingPager.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            mLoadingPager.setVisibility(View.GONE);
//            mWebView.setVisibility(View.VISIBLE);

        }
        @Override
        public void onReceivedError(WebView view, int errorCode,
                                    String description, String failingUrl) {
            // TODO Auto-generated method stub
            super.onReceivedError(view, errorCode, description, failingUrl);
            mNoNetPager.setVisibility(View.VISIBLE);
            mLoadingPager.setVisibility(View.GONE);

        }
    }

    /**
     * 页面不会整体back
     * @param keyCode
     * @param event
     * @return
     */
    public  boolean  onKeyDown(int  keyCode, KeyEvent event) {
        if  ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack() ) {
            mWebView.goBack();
            return  true ;
        }
        return  super .onKeyDown(keyCode, event);
    }

}

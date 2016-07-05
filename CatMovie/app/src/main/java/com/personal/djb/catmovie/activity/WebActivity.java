package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.os.Bundle;
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

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.utils.NetUtils;

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

        mBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  分享按钮
//                showShare();
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

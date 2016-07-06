package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.utils.CacheUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener{

    private UMShareAPI mShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mShareAPI = UMShareAPI.get(this);

        findViews();
    }

    private RelativeLayout titleYk;
    private ImageButton ibCartForback;
    private Button btnNoPassword;
    private EditText etLoginUsername;
    private EditText etLoginPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private TextView tvNumberLogin;
    private ImageView weixin;
    private ImageView weibo;
    private ImageView qq;
    private ImageView qqzone;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-07-05 20:41:41 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        titleYk = (RelativeLayout)findViewById( R.id.title_yk );
        ibCartForback = (ImageButton)findViewById( R.id.ib_cart_forback );
        btnNoPassword = (Button)findViewById( R.id.btn_no_password );
        etLoginUsername = (EditText)findViewById( R.id.et_login_username );
        etLoginPassword = (EditText)findViewById( R.id.et_login_password );
        btnLogin = (Button)findViewById( R.id.btn_login );
        tvRegister = (TextView)findViewById( R.id.tv_register );
        tvNumberLogin = (TextView)findViewById( R.id.tv_number_login );
        weixin = (ImageView)findViewById( R.id.weixin );
        weibo = (ImageView)findViewById( R.id.weibo );
        qq = (ImageView)findViewById( R.id.qq );
        qqzone = (ImageView)findViewById( R.id.qqzone );

        ibCartForback.setOnClickListener( this );
        btnNoPassword.setOnClickListener( this );
        btnLogin.setOnClickListener( this );
        qq.setOnClickListener(this);
        weibo.setOnClickListener(this);
        weixin.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-07-05 20:41:41 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibCartForback ) {
            finish();
        } else if ( v == btnNoPassword ) {
            Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
        } else if ( v == btnLogin ) {
            Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
        } else if ( v == qq) {
            SHARE_MEDIA platform = SHARE_MEDIA.QQ;
            mShareAPI.doOauthVerify(this, platform, umAuthListener);
        } else if ( v == weibo) {
            SHARE_MEDIA platform = SHARE_MEDIA.SINA;
            mShareAPI.doOauthVerify(this, platform, umAuthListener);
        } else if ( v == weixin) {
            SHARE_MEDIA platform = SHARE_MEDIA.WEIXIN;
            mShareAPI.doOauthVerify(this, platform, umAuthListener);
        }
    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText( getApplicationContext(), "授权成功", Toast.LENGTH_SHORT).show();
            if (onShouQuanBack != null) {
                onShouQuanBack.onSuccess();
            }
            CacheUtils.putBoolen(LoginActivity.this,"islogin",true);
            setResult(MainActivity.LOGIN_STATE_SUCCESS);
            finish();
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText( getApplicationContext(), "授权失败", Toast.LENGTH_SHORT).show();
            if (onShouQuanBack != null) {
                onShouQuanBack.onError();
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText( getApplicationContext(), "授权取消", Toast.LENGTH_SHORT).show();
            if (onShouQuanBack != null) {
                onShouQuanBack.onCancel();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
    }

    public interface OnShouQuanBack{
        void onSuccess();
        void onError();
        void onCancel();
    }

    private OnShouQuanBack onShouQuanBack;

    public void setOnShouQuanBack(OnShouQuanBack onShouQuanBack) {
        this.onShouQuanBack = onShouQuanBack;
    }
}

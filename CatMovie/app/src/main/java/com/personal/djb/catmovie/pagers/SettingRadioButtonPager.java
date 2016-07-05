package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.LoginActivity;
import com.personal.djb.catmovie.activity.MainActivity;
import com.personal.djb.catmovie.activity.MyShopCartActivity;
import com.personal.djb.catmovie.activity.MyShopingActivity;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.utils.CacheUtils;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SettingRadioButtonPager extends BasePager {

    private View view;

    private RelativeLayout rlNologiin;
    private MyClickListener listener;

    private ScrollView mySettingPageIslogin;
    private RelativeLayout myHead;
    private ImageView ivMyUserIcon;
    private RelativeLayout llMyUserIslogin;
    private TextView tvMyIsloginUsername;
    private TextView tvMyIsloginLevel;
    private RelativeLayout rlIslogin;
    private RadioGroup myIsloginRg;
    private RadioButton myRg11;
    private RadioButton myRg12;
    private RadioButton myRg13;
    private RadioButton myRg14;
    private LinearLayout llMyLlOrder;
    private TextView myMyOrder;
    private TextView myMyOrderRight;
    private RadioGroup myIsloginOrderRg;
    private RadioButton myRg21;
    private RadioButton myRg22;
    private RadioButton myRg23;
    private RadioButton myRg24;
    private LinearLayout llMyLlYou;
    private TextView myYou;
    private TextView myYouRight;
    private TextView myMonpack;
    private TextView myMonpackRight;
    private TextView myHuiycard;
    private TextView myHuiycardRight;
    private TextView myShoping;
    private TextView myShopingRight;
    private TextView mySetting;
    private TextView mySettingRight;
    private LinearLayout llMyLlMess;
    private TextView myMessage;
    private TextView myMessageRight;
    private TextView myHuiyuan;
    private TextView myHuiyuanRight;
    private TextView myChengj;
    private TextView myChengjRight;
    private LinearLayout mySettingPageNologin;
    private RelativeLayout myHeadNologin;
    private ImageView ivMyUserIconNologin;
    private TextView fastLoginNologin;
    private LinearLayout myBody;
    private Button xiangkan;
    private Button kanguo;
    private Button yingping;
    private Button huati;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-07-05 22:52:06 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        mySettingPageIslogin = (ScrollView)view.findViewById(R.id.my_setting_page_islogin);
        myHead = (RelativeLayout)view.findViewById(R.id.my_head);
        ivMyUserIcon = (ImageView)view.findViewById(R.id.iv_my_user_icon);
        llMyUserIslogin = (RelativeLayout)view.findViewById(R.id.ll_my_user_islogin);
        tvMyIsloginUsername = (TextView)view.findViewById(R.id.tv_my_islogin_username);
        tvMyIsloginLevel = (TextView)view.findViewById(R.id.tv_my_islogin_level);
        rlIslogin = (RelativeLayout)view.findViewById(R.id.rl_islogin);
        myIsloginRg = (RadioGroup)view.findViewById(R.id.my_islogin_rg);
        myRg11 = (RadioButton)view.findViewById(R.id.my_rg1_1);
        myRg12 = (RadioButton)view.findViewById(R.id.my_rg1_2);
        myRg13 = (RadioButton)view.findViewById(R.id.my_rg1_3);
        myRg14 = (RadioButton)view.findViewById(R.id.my_rg1_4);
        llMyLlOrder = (LinearLayout)view.findViewById(R.id.ll_my_ll_order);
        myMyOrder = (TextView)view.findViewById(R.id.my_my_order);
        myMyOrderRight = (TextView)view.findViewById(R.id.my_my_order_right);
        myIsloginOrderRg = (RadioGroup)view.findViewById(R.id.my_islogin_order_rg);
        myRg21 = (RadioButton)view.findViewById(R.id.my_rg2_1);
        myRg22 = (RadioButton)view.findViewById(R.id.my_rg2_2);
        myRg23 = (RadioButton)view.findViewById(R.id.my_rg2_3);
        myRg24 = (RadioButton)view.findViewById(R.id.my_rg2_4);
        llMyLlYou = (LinearLayout)view.findViewById(R.id.ll_my_ll_you);
        myYou = (TextView)view.findViewById(R.id.my_you);
        myYouRight = (TextView)view.findViewById(R.id.my_you_right);
        myMonpack = (TextView)view.findViewById(R.id.my_monpack);
        myMonpackRight = (TextView)view.findViewById(R.id.my_monpack_right);
        myHuiycard = (TextView)view.findViewById(R.id.my_huiycard);
        myHuiycardRight = (TextView)view.findViewById(R.id.my_huiycard_right);
        myShoping = (TextView)view.findViewById(R.id.my_shoping);
        myShopingRight = (TextView)view.findViewById(R.id.my_shoping_right);
        mySetting = (TextView)view.findViewById(R.id.my_setting);
        mySettingRight = (TextView)view.findViewById(R.id.my_setting_right);
        llMyLlMess = (LinearLayout)view.findViewById(R.id.ll_my_ll_mess);
        myMessage = (TextView)view.findViewById(R.id.my_message);
        myMessageRight = (TextView)view.findViewById(R.id.my_message_right);
        myHuiyuan = (TextView)view.findViewById(R.id.my_huiyuan);
        myHuiyuanRight = (TextView)view.findViewById(R.id.my_huiyuan_right);
        myChengj = (TextView)view.findViewById(R.id.my_chengj);
        myChengjRight = (TextView)view.findViewById(R.id.my_chengj_right);
        mySettingPageNologin = (LinearLayout)view.findViewById(R.id.my_setting_page_nologin);
        myHeadNologin = (RelativeLayout)view.findViewById(R.id.my_head_nologin);
        ivMyUserIconNologin = (ImageView)view.findViewById(R.id.iv_my_user_icon_nologin);
        fastLoginNologin = (TextView)view.findViewById(R.id.fast_login_nologin);
        myBody = (LinearLayout)view.findViewById( R.id.my_body );

        xiangkan = (Button)view.findViewById(R.id.xiangkan);
        kanguo = (Button)view.findViewById(R.id.kanguo);
        yingping = (Button)view.findViewById(R.id.yingping);
        huati = (Button)view.findViewById(R.id.huati_setting);

        //  未登录显示的布局
        fast_login =(TextView) view.findViewById(R.id.fast_login_nologin);



        listener = new MyClickListener();

        fast_login.setOnClickListener(listener);

        myShoping.setOnClickListener( listener );
        myShopingRight.setOnClickListener( listener );
        myHuiycard.setOnClickListener(listener);
        myHuiycardRight.setOnClickListener(listener);
        xiangkan.setOnClickListener(listener);
        kanguo.setOnClickListener(listener);
        yingping.setOnClickListener(listener);
        huati.setOnClickListener(listener);

    }


    public SettingRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton我的设置页面initView");

        view = View.inflate(context, R.layout.mysetting_page,null);

        findViews();

        return view;
    }


    @Override
    public void initData() {
        Log.e("TAG", "radiobutton我的设置页面initData");
        isInitData = true;
        super.initData();

        mySettingPageIslogin.setVisibility(View.GONE);
        mySettingPageNologin.setVisibility(View.GONE);
        if(CacheUtils.getBoolean(context, "islogin")){
            mySettingPageIslogin.setVisibility(View.VISIBLE);
        } else {
            mySettingPageNologin.setVisibility(View.VISIBLE);
        }

    }

    private TextView fast_login;
    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == myShoping || v == myShopingRight) {
                context.startActivity(new Intent(context, MyShopingActivity.class));
            } else if (v == myHuiycard || v == myHuiycardRight) {
                Log.e("TAG", "跳转购物车");
                context.startActivity(new Intent(context, MyShopCartActivity.class));
            } else if (v == fast_login) {
                ((MainActivity)context).startActivityForResult(new Intent(context, LoginActivity.class), 300);
            } else if (v == xiangkan) {
                Toast.makeText(context, "查看想看的内容请先登录", Toast.LENGTH_SHORT).show();
                ((MainActivity)context).startActivityForResult(new Intent(context, LoginActivity.class), 300);
            }else if (v == kanguo) {
                Toast.makeText(context, "查看看过的内容请先登录", Toast.LENGTH_SHORT).show();
                ((MainActivity)context).startActivityForResult(new Intent(context, LoginActivity.class), 300);
            }else if (v == yingping) {
                Toast.makeText(context, "查看影评请先登录", Toast.LENGTH_SHORT).show();
                ((MainActivity)context).startActivityForResult(new Intent(context, LoginActivity.class), 300);
            }else if (v == huati) {
                Toast.makeText(context, "查看话题请先登录", Toast.LENGTH_SHORT).show();
                ((MainActivity)context).startActivityForResult(new Intent(context, LoginActivity.class), 300);
            }
        }
    }

}

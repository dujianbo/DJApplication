package com.personal.djb.catmovie.pagers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.MyShopCartActivity;
import com.personal.djb.catmovie.activity.MyShopingActivity;
import com.personal.djb.catmovie.base.BasePager;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SettingRadioButtonPager extends BasePager {

    private View view;


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
    private RelativeLayout rlNologiin;
    private LinearLayout myBody;
    private MyClickListener listener;


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


    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-07-03 17:19:16 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        myHead = (RelativeLayout) view.findViewById(R.id.my_head);
        ivMyUserIcon = (ImageView) view.findViewById(R.id.iv_my_user_icon);
        llMyUserIslogin = (RelativeLayout) view.findViewById(R.id.ll_my_user_islogin);
        tvMyIsloginUsername = (TextView) view.findViewById(R.id.tv_my_islogin_username);
        tvMyIsloginLevel = (TextView) view.findViewById(R.id.tv_my_islogin_level);
        rlIslogin = (RelativeLayout) view.findViewById(R.id.rl_islogin);
        myIsloginRg = (RadioGroup) view.findViewById(R.id.my_islogin_rg);
        myRg11 = (RadioButton) view.findViewById(R.id.my_rg1_1);
        myRg12 = (RadioButton) view.findViewById(R.id.my_rg1_2);
        myRg13 = (RadioButton) view.findViewById(R.id.my_rg1_3);
        myRg14 = (RadioButton) view.findViewById(R.id.my_rg1_4);
        llMyLlOrder = (LinearLayout) view.findViewById(R.id.ll_my_ll_order);
        myMyOrder = (TextView) view.findViewById(R.id.my_my_order);
        myMyOrderRight = (TextView) view.findViewById(R.id.my_my_order_right);
        myIsloginOrderRg = (RadioGroup) view.findViewById(R.id.my_islogin_order_rg);
        myRg21 = (RadioButton) view.findViewById(R.id.my_rg2_1);
        myRg22 = (RadioButton) view.findViewById(R.id.my_rg2_2);
        myRg23 = (RadioButton) view.findViewById(R.id.my_rg2_3);
        myRg24 = (RadioButton) view.findViewById(R.id.my_rg2_4);
        llMyLlYou = (LinearLayout) view.findViewById(R.id.ll_my_ll_you);
        myYou = (TextView) view.findViewById(R.id.my_you);
        myYouRight = (TextView) view.findViewById(R.id.my_you_right);
        myMonpack = (TextView) view.findViewById(R.id.my_monpack);
        myMonpackRight = (TextView) view.findViewById(R.id.my_monpack_right);
        myHuiycard = (TextView) view.findViewById(R.id.my_huiycard);
        myHuiycardRight = (TextView) view.findViewById(R.id.my_huiycard_right);
        myShoping = (TextView) view.findViewById(R.id.my_shoping);
        myShopingRight = (TextView) view.findViewById(R.id.my_shoping_right);
        mySetting = (TextView) view.findViewById(R.id.my_setting);
        mySettingRight = (TextView) view.findViewById(R.id.my_setting_right);
        llMyLlMess = (LinearLayout) view.findViewById(R.id.ll_my_ll_mess);
        myMessage = (TextView) view.findViewById(R.id.my_message);
        myMessageRight = (TextView) view.findViewById(R.id.my_message_right);
        myHuiyuan = (TextView) view.findViewById(R.id.my_huiyuan);
        myHuiyuanRight = (TextView) view.findViewById(R.id.my_huiyuan_right);
        myChengj = (TextView) view.findViewById(R.id.my_chengj);
        myChengjRight = (TextView) view.findViewById(R.id.my_chengj_right);
        rlNologiin = (RelativeLayout) view.findViewById(R.id.rl_nologiin);
        myBody = (LinearLayout) view.findViewById(R.id.my_body);

        listener = new MyClickListener();

        myShoping.setOnClickListener( listener );
        myShopingRight.setOnClickListener( listener );
        myHuiycard.setOnClickListener(listener);
        myHuiycardRight.setOnClickListener(listener);
    }

    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == myShoping || v == myShopingRight) {
                context.startActivity(new Intent(context, MyShopingActivity.class));
            } else if (v == myHuiycard || v == myHuiycardRight) {
                Log.e("TAG", "跳转购物车");
                context.startActivity(new Intent(context, MyShopCartActivity.class));
            }
        }
    }
}

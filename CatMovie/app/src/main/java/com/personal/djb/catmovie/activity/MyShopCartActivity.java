package com.personal.djb.catmovie.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.adapter.ShopCartAdapter;
import com.personal.djb.catmovie.bean.ShoppingCart;
import com.personal.djb.catmovie.pay.H5PayDemoActivity;
import com.personal.djb.catmovie.pay.PayResult;
import com.personal.djb.catmovie.pay.SignUtils;
import com.personal.djb.catmovie.utils.CartProvider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MyShopCartActivity extends Activity {

    private CartProvider cartProvider;
    private MyShopCartActivity me;
    private ImageButton forBack;
    private RecyclerView mRecyCart;
    private Button mBtnEditOrDel;
    private CheckBox checkbox_all;
    private TextView tv_total_price;
    private Button btn_delete;
    private Button btn_order;

    private ShopCartAdapter adapter;

    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 1;
    /**
     * 完成状态
     */
    private static final int ACITON_COMPLETE = 2;

    /**
     * 购物车的数据
     */
    private List<ShoppingCart> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop_cart);
        me = this;
        cartProvider = new CartProvider(me);

        findView();
        setListener();

        initData();

    }

    /**
     * 设置适配器
     */
    private void initData() {
        datas = cartProvider.getAllData();
        adapter = new ShopCartAdapter(this,datas,checkbox_all,tv_total_price);
        mRecyCart.setAdapter(adapter);

        //设置布局管理器
        mRecyCart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void setListener() {
        forBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = (int) mBtnEditOrDel.getTag();
                if(a == ACITON_COMPLETE) {
                    hideDeleteButton();
                    return;
                }
                finish();
            }
        });

        mBtnEditOrDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) mBtnEditOrDel.getTag();

                if (action == ACTION_EDIT) {
                    //显示删除按钮
                    showDeleteButton();
                } else if (action == ACITON_COMPLETE) {
                    //隐藏删除按钮
                    hideDeleteButton();
                    adapter.showTotalPrice();
                }
            }
        });

        //设置删除按钮的点击事件
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.deleteData();
            }
        });

        //设置去结算的点击事件
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pay(v);
            }
        });
    }

    private void findView() {
        forBack = (ImageButton) findViewById(R.id.ib_cart_forback);
        mRecyCart = (RecyclerView) findViewById(R.id.cart_recy);

        mBtnEditOrDel = (Button) findViewById(R.id.btn_cart);

        checkbox_all = (CheckBox) findViewById(R.id.checkbox_all);
        tv_total_price = (TextView) findViewById(R.id.tv_total_price);
        btn_order = (Button) findViewById(R.id.btn_order);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        mBtnEditOrDel.setTag(ACTION_EDIT);
    }

    private void hideDeleteButton() {
        //1.设置文本为编辑
        mBtnEditOrDel.setText("编辑");
        //2.设置全部为 勾选和全选按钮为 勾选
        adapter.all_none(true);
        checkbox_all.setChecked(true);
        //3.显示价格
        tv_total_price.setVisibility(View.VISIBLE);
        //4.隐藏删除按钮
        btn_delete.setVisibility(View.GONE);
        //5.显示结算按钮
        btn_order.setVisibility(View.VISIBLE);
        //6.设置状态 ACTION_EDIT
        mBtnEditOrDel.setTag(ACTION_EDIT);
    }

    private void showDeleteButton() {
        //1.设置文本为完成
        mBtnEditOrDel.setText("完成");
        //2.设置全部为非勾选和全选按钮为非勾选
        adapter.all_none(false);
        checkbox_all.setChecked(false);
        //3.隐藏价格
        tv_total_price.setVisibility(View.GONE);
        //4.显示删除按钮
        btn_delete.setVisibility(View.VISIBLE);
        //5.隐藏结算按钮
        btn_order.setVisibility(View.GONE);
        //6.设置状态 ACTION_COMPLETE
        mBtnEditOrDel.setTag(ACITON_COMPLETE);

    }



    // 商户PID
    public static final String PARTNER = "2088911876712776";
    // 商户收款账号
    public static final String SELLER = "chenlei@atguigu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOwV2Ke7aIkS5Ja8\n" +
            "v5UGlUy3vZNB7ZKDW6XsbRzsrpw1JBhMhR7M3wnuDj0DBZ71tGs9BPqJ3R3DPIjc\n" +
            "QCd658dMwGYZ/5abYIC+QlHKxPgE9PYG6o0yFvlw5eIW+vrQ+R5wyFbe5hArRXAW\n" +
            "XE8ShkK7CgFIJatgme7Mfmv4ZowdAgMBAAECgYEA0s7PHzdhKMUOCOuRxuiR+Gc5\n" +
            "iHyePu28sHRlGibd4+4vxOLPG6mkn6FKZYCFTGPqLrfV8kGYiDMFos/JGXfN7Zlj\n" +
            "pjCMKhQ3lG/9uEjo45gv+GKBg0UZIvkDDuddy5RGXbIW0yAAhNKGhOSIQJWZQjP6\n" +
            "ODJ7hRQx0MBqginKW6ECQQD/iwHdfD+xwUFqE/UDBOmuzjkQ0Nr089DY8rKl2V/8\n" +
            "Meids2659+GkXV2Wng90rxxJ3VOSQqSplIx/xy5m4hWjAkEA7IHuUIVSpC58S3Uf\n" +
            "0n0xXQexk5yX24M5UAFt5nRJ8ev7qIGMY2sZM2Pbk3Vu6AZucTCvVbMwFQbVCzAb\n" +
            "f3lzPwJAPitLYN8Bf/VMPMpuKTXgxC5Xo8er/Tvz61PgMokX2PsrZHJq1cOAmcQ9\n" +
            "KI97ox+W+kFmO4cLGFaTd+K1X0JRkQJBAJpBsKdSHfG55BXvQZ8vSpgL4ZWOiDmD\n" +
            "YEk/dWffJy+kJBLm1CMwAlLTaa7G5FxvfW9UOrfh1JWSTDgs23KMS08CQAZnWRSC\n" +
            "kvjSsFtNoP3AQxpLDR+3tsneBfuYkIy+HJqqN1WVUb5xDedip6M8j6AhC+B8ZtLX\n" +
            "uBdV56mLobPrtsA=";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDsFdinu2iJEuSWvL+VBpVMt72T\n" +
            "Qe2Sg1ul7G0c7K6cNSQYTIUezN8J7g49AwWe9bRrPQT6id0dwzyI3EAneufHTMBm\n" +
            "Gf+Wm2CAvkJRysT4BPT2BuqNMhb5cOXiFvr60PkecMhW3uYQK0VwFlxPEoZCuwoB\n" +
            "SCWrYJnuzH5r+GaMHQIDAQAB";
    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(me, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(me, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(me, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay(View v) {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        String str = "";
        for(int i = 0; i < datas.size(); i++) {
            if(datas.get(i).isCheck()) {
                str += datas.get(i).getTitle() + ",";
            }
        }

        String orderInfo = getOrderInfo(str, str, adapter.getTotalPrice()+"");

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(me);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
     *
     * @param v
     */
    public void h5Pay(View v) {
        Intent intent = new Intent(this, H5PayDemoActivity.class);
        Bundle extras = new Bundle();
        /**
         * url是测试的网站，在app内部打开页面是基于webview打开的，demo中的webview是H5PayDemoActivity，
         * demo中拦截url进行支付的逻辑是在H5PayDemoActivity中shouldOverrideUrlLoading方法实现，
         * 商户可以根据自己的需求来实现
         */
        String url = "http://m.taobao.com";
        // url可以是一号店或者淘宝等第三方的购物wap站点，在该网站的支付过程中，支付宝sdk完成拦截支付
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}

package com.personal.djb.catmovie.pagers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.activity.MainActivity;
import com.personal.djb.catmovie.activity.citypackage.CityActivity;
import com.personal.djb.catmovie.adapter.CinemaAdapter;
import com.personal.djb.catmovie.adapter.popadapter.FirstClassAdapter;
import com.personal.djb.catmovie.adapter.popadapter.SecondClassAdapter;
import com.personal.djb.catmovie.adapter.popadapter.ThirdClassAdapter;
import com.personal.djb.catmovie.base.BasePager;
import com.personal.djb.catmovie.bean.cinema.CinemaBean;
import com.personal.djb.catmovie.bean.cinema.PlaceBean;
import com.personal.djb.catmovie.bean.cinema.cinemamenu.FirstClassItem;
import com.personal.djb.catmovie.bean.cinema.cinemamenu.SecondClassItem;
import com.personal.djb.catmovie.bean.cinema.cinemamenu.ThirdClassItem;
import com.personal.djb.catmovie.utils.JingWei2Distance;
import com.personal.djb.catmovie.utils.NetUtils;
import com.personal.djb.catmovie.utils.PinyinTool;
import com.personal.djb.catmovie.utils.ScreenUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class CinemaRadioButtonPager extends BasePager {

    public static final int REQUEST_CODE = 100;
    private String CINEMA_URL = "http://m.maoyan.com/cinemas.json";

    private String PLACE_NET_URL = "http://api.meituan.com/group/v1/city/latlng/40.100168,116.379677?tag=0&__vhost=api.mobile.meituan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6901&utm_source=xiaomi&utm_medium=android&utm_term=6.9.0&utm_content=867515025248252&ci=1&net=255&dModel=Redmi%20Note%202&uuid=6EB9FAAFA10A6C010EBB1A7B00F74A501A378CDE3EBF6261A49B9727ECF79B4D&lat=40.100168&lng=116.379677&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1466660891503&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=62d6ceed-3363-4657-b44c-360020ba3fa6&__skcy=hdKur%2FrsWbF0B085kZFa%2FLMiSrU%3D";

    private View view;

    private ImageButton mBtnChoicePlace;

    private List<CinemaBean.BaseBean> data;

    private List<CinemaBean.BaseBean> timeDatas;
    //  影院名字的集合
    private List<String> cinemaNames;
    private HashMap<String, Integer> index;
    private boolean isSearch = false;

    /**
     * 我的当前位置
     */
    private TextView mCurPlace;

    /**
     * 没网的页面
     */
    private RelativeLayout mNoNetPager;
    private ImageView mNoNetButton;
    private TextView mTitleCinemaName;
    /**
     * 缓冲的页面
     */
    private RelativeLayout mLoadingPager;

    /**
     * 下拉刷新控件
     */
    private MaterialRefreshLayout mRefreshLayout;

    /**
     * 选择城市的按钮
     */
    private Button mBtnChoiceCity;
    /**
     * 搜索的按钮
     */
    private RelativeLayout mBtnSearchRl;
    private ImageButton mBtnSearch;

    private RecyclerView mCinemaRecyclerView;
    private boolean isRefreshing = false;
    private CinemaAdapter adapter;
    //  点搜索时候的布局
    private LinearLayout mRlForSearch;
    //  点击取消
    private TextView mTvSearchCancle;
    //  找影院的输入框
    private EditText mSeaCinemaInput;
    //  清空输入框
    private ImageView mIvDeleteInput;
    //  实时搜索的列表
    private RecyclerView mRecyTimeSearch;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0) {
                Log.e("TAG", cinemaNames.toString());
                timeDatas = toListFromName(cinemaNames);
                if(timeDatas.size() > 0) {
                    mSearchNoFind.setVisibility(View.GONE);
                } else {
                    mSearchNoFind.setVisibility(View.VISIBLE);
                }
                timeAdapter.notifyDataSetChanged();
            }
        }
    };
    private TextView mSearchNoFind;
    private CinemaAdapter timeAdapter;
    private boolean isPlace = false;

    private List<CinemaBean.BaseBean> toListFromName(List<String> cinemaNames) {
        timeDatas.clear();
        for(int i = 0; i < cinemaNames.size(); i++) {
            timeDatas.add(data.get(index.get(cinemaNames.get(i))));
        }
        return timeDatas;
    }

    public CinemaRadioButtonPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        Log.e("TAG", "radiobutton电影页面initView");

        view = View.inflate(context, R.layout.cinerma_main_pager,null);

        findView();
        return view;
    }

    private void findView() {
        mNoNetPager = (RelativeLayout) view.findViewById(R.id.netload_nonet);
        mLoadingPager = (RelativeLayout) view.findViewById(R.id.netload_load);
        mNoNetButton = (ImageView) view.findViewById(R.id.iv_netload_nonet);

        mBtnChoiceCity = (Button) view.findViewById(R.id.common_title_city);
        mBtnSearchRl = (RelativeLayout) view.findViewById(R.id.common_title_search);
        mBtnSearch = (ImageButton) view.findViewById(R.id.common_title_search_search);
        mBtnChoicePlace = (ImageButton) view.findViewById(R.id.btn_cinema_choiceplace);
        mTitleCinemaName = (TextView) view.findViewById(R.id.tv_title_cinema_name);
        mCurPlace = (TextView) view.findViewById(R.id.tv_mycurrplace);
        //  是背景变暗
        darkView = view.findViewById(R.id.main_darkview);
        //  两个动画
        animIn = AnimationUtils.loadAnimation(context, R.anim.fade_in_anim);
        animOut = AnimationUtils.loadAnimation(context, R.anim.fade_out_anim);

        mSearchNoFind = (TextView) view.findViewById(R.id.searchnofound);

        mTitleCinemaName.setVisibility(View.VISIBLE);
        mBtnSearchRl.setVisibility(View.VISIBLE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mBtnChoiceCity.setVisibility(View.VISIBLE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mBtnChoicePlace.setVisibility(View.VISIBLE);

        mRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        mRefreshLayout.setSunStyle(true);


        mCinemaRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_main_cinema);
        mCinemaRecyclerView.setVisibility(View.GONE);
        mCurPlace.setVisibility(View.GONE);

        mRlForSearch = (LinearLayout) view.findViewById(R.id.rl_search_cinema);
        mTvSearchCancle = (TextView) view.findViewById(R.id.cinema_my_search_cancel);
        mSeaCinemaInput = (EditText) view.findViewById(R.id.cinema_et_input);
        mIvDeleteInput = (ImageView) view.findViewById(R.id.ivDeleteText_cinema);

        mRecyTimeSearch = (RecyclerView) view.findViewById(R.id.search_city_recy);

        setListener();
    }

    @Override
    public void initData() {
        Log.e("TAG", "radiobutton电影页面initData");
        super.initData();

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.GONE);

        cinemaNames = new ArrayList<>();
        index = new HashMap<>();
        timeDatas = new ArrayList<>();

        //  检查网络连接
        if (!NetUtils.getInstance().checkNetworkState()) {
            mNoNetPager.setVisibility(View.VISIBLE);
            return;
        }
        isInitData = true;
        mLoadingPager.setVisibility(View.VISIBLE);

        getMyPlaceFromNet();
    }

    public void setCityName(String currCity){
        mBtnChoiceCity.setText(currCity);
    }

    /**
     * 联网获取自己当前位置
     */
    private void getMyPlaceFromNet() {
        RequestCall call = OkHttpUtils.get().url(PLACE_NET_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                processPlaceData(response);
            }
        });
    }

    //  我的经纬度
    private double myLat;
    private double myLng;
    //  目标经纬度
    private double targetLat;
    private double targetLng;

    /**
     * 联网动态获取地址
     * @param json
     */
    private void processPlaceData(String json) {
        PlaceBean placeBean = new Gson().fromJson(json, PlaceBean.class);
        String detail = placeBean.getData().getDetail();

        mCurPlace.setText(detail);

        if(isPlace) {
            isPlace = false;
            return;
        }

        myLat = placeBean.getData().getLat();
        myLng = placeBean.getData().getLng();

        getDataFromNet();
    }

    /**
     * 1.联网请求影院数据
     */
    private void getDataFromNet() {
        if (isInitData == false) {
            isInitData = true;
        }

        mNoNetPager.setVisibility(View.GONE);
        mLoadingPager.setVisibility(View.VISIBLE);

        RequestCall call = OkHttpUtils.get().url(CINEMA_URL).build();
        call.connTimeOut(10000);
        call.execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mNoNetPager.setVisibility(View.VISIBLE);
                mCinemaRecyclerView.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.GONE);

                if (isRefreshing) {
                    mRefreshLayout.finishRefresh();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    /**
     * 解析影院数据
     * @param json
     */
    private void processData(String json) {
        Log.e("du", json);

        CinemaBean cinemaBean = new Gson().fromJson(json, CinemaBean.class);

        data = new ArrayList<>();
        data.addAll(cinemaBean.getData().getChangping());
        data.addAll(cinemaBean.getData().getChaoyang());
        data.addAll(cinemaBean.getData().getDaxing());
        data.addAll(cinemaBean.getData().getDongcheng());
        data.addAll(cinemaBean.getData().getFangshan());
        data.addAll(cinemaBean.getData().getFengtai());
        data.addAll(cinemaBean.getData().getHaidian());
        data.addAll(cinemaBean.getData().getHuairou());
        data.addAll(cinemaBean.getData().getMentougou());
        data.addAll(cinemaBean.getData().getMiyun());
        data.addAll(cinemaBean.getData().getPinggu());
        data.addAll(cinemaBean.getData().getShijingshan());
        data.addAll(cinemaBean.getData().getShunyi());
        data.addAll(cinemaBean.getData().getTongzhou());
        data.addAll(cinemaBean.getData().getXicheng());
        data.addAll(cinemaBean.getData().getYanqin());

        for(int i = 0; i < data.size(); i++) {
            double distanceOfMeter = JingWei2Distance.getDistanceOfMeter(myLat, myLng, data.get(i).getLat(), data.get(i).getLng());
            data.get(i).setDjbDistance(distanceOfMeter);
        }

        //  排序
        Collections.sort(data, new Comparator<CinemaBean.BaseBean>() {
            @Override
            public int compare(CinemaBean.BaseBean lhs, CinemaBean.BaseBean rhs) {
                if (lhs.getDjbDistance() > rhs.getDjbDistance()) {
                    return 1;
                } else if (lhs.getDjbDistance() < rhs.getDjbDistance()){
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        for(int i = 0; i < data.size(); i++) {
            cinemaNames.add(data.get(i).getNm());
            index.put(data.get(i).getNm(), i);
        }

        Log.e("TAG2", cinemaNames.toString());
        try {
            PinyinTool.setData(cinemaNames);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }

        Log.e("ceshi", "长度" + data.size() + "条" + data.toString());

        //  初始化popwindow数据
        initPopData();
        initPop();
        setView();
    }

    private void initPopData() {
        firstList = new ArrayList<FirstClassItem>();
        int count = 102;
        //1
        ArrayList<SecondClassItem> secondList1 = new ArrayList<SecondClassItem>();
        secondList1.add(new SecondClassItem(101,"全部"));
        List<String> str1 = new ArrayList<>();
        for(int i = 0; i < data.size(); i++) {
            if (!str1.contains(data.get(i).getArea())) {
                str1.add(data.get(i).getArea());
            }
        }
        for(int i = 0; i < str1.size(); i++) {
            secondList1.add(new SecondClassItem(count++,str1.get(i)));
        }

        firstList.add(new FirstClassItem(1, "行政区", secondList1));

        List<ThirdClassItem> thirdList1 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            thirdList1.add(new ThirdClassItem(i,"地铁假数据"));
        }

        //2
        ArrayList<SecondClassItem> secondList2 = new ArrayList<SecondClassItem>();
        secondList2.add(new SecondClassItem(201, "1号线",thirdList1));
        secondList2.add(new SecondClassItem(202, "2号线",thirdList1));
        secondList2.add(new SecondClassItem(203, "4号线",thirdList1));
        secondList2.add(new SecondClassItem(204, "5号线",thirdList1));
        secondList2.add(new SecondClassItem(205, "6号线",thirdList1));
        secondList2.add(new SecondClassItem(206, "7号线",thirdList1));
        secondList2.add(new SecondClassItem(207, "8号线",thirdList1));
        secondList2.add(new SecondClassItem(208, "9号线",thirdList1));
        secondList2.add(new SecondClassItem(209, "10号线",thirdList1));
        secondList2.add(new SecondClassItem(210, "13号线",thirdList1));
        secondList2.add(new SecondClassItem(211, "14号线",thirdList1));
        secondList2.add(new SecondClassItem(212, "15号线",thirdList1));
        secondList2.add(new SecondClassItem(212, "八通线",thirdList1));
        secondList2.add(new SecondClassItem(212, "昌平线",thirdList1));
        secondList2.add(new SecondClassItem(212, "大兴线",thirdList1));
        secondList2.add(new SecondClassItem(212, "房山线",thirdList1));
        secondList2.add(new SecondClassItem(212, "机场线",thirdList1));
        secondList2.add(new SecondClassItem(212, "亦庄线",thirdList1));

        firstList.add(new FirstClassItem(2, "地铁", secondList2));

        //3
        ArrayList<SecondClassItem> secondList3 = new ArrayList<SecondClassItem>();
        secondList3.add(new SecondClassItem(301, "全部"));
        secondList3.add(new SecondClassItem(302, "杜比全景声厅"));
        secondList3.add(new SecondClassItem(303, "IMAX厅"));
        secondList3.add(new SecondClassItem(304, "4K厅"));
        secondList3.add(new SecondClassItem(305, "中国巨幕厅"));
        secondList3.add(new SecondClassItem(305, "4DZ厅"));
        secondList3.add(new SecondClassItem(305, "双机3D厅"));
        secondList3.add(new SecondClassItem(305, "巨幕厅"));
        firstList.add(new FirstClassItem(3, "特殊厅", secondList3));

        List<SecondClassItem> secondList4 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            secondList4.add(new SecondClassItem(i,"品牌假数据"));
        }
        List<SecondClassItem> secondList5 = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            secondList5.add(new SecondClassItem(i,"服务假数据"));
        }
        firstList.add(new FirstClassItem(4,"品牌",secondList4));
        firstList.add(new FirstClassItem(5,"服务",secondList5));
    }

    private void setView() {
        //  取消加载界面
        mLoadingPager.setVisibility(View.GONE);
        mCinemaRecyclerView.setVisibility(View.VISIBLE);
        mCurPlace.setVisibility(View.VISIBLE);

        //  如果是刷新走这里
        if (isRefreshing) {
            mRefreshLayout.finishRefresh();
            adapter.setDatas(data);

            return;
        }

        adapter = new CinemaAdapter(context,data,false);

        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mCinemaRecyclerView.setLayoutManager(manager);

        mCinemaRecyclerView.setAdapter(adapter);


        timeAdapter = new CinemaAdapter(context,timeDatas,true);
        RecyclerView.LayoutManager timManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        mRecyTimeSearch.setLayoutManager(timManager);
        mRecyTimeSearch.setAdapter(timeAdapter);

        ((MainActivity)context).setOnAtivityResult(new MainActivity.OnAtivityResult() {
            @Override
            public void onResult(int requestCode, int resultCode, Intent data) {
                if(requestCode == REQUEST_CODE && resultCode == CityActivity.RECUALT_CITY_CODE) {
                    String cityname = data.getStringExtra("cityname");
                    mBtnChoiceCity.setText(cityname);
                }
            }
        });
    }

    /**
     * 顶部导航栏的监听 等
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void setListener() {

        mBtnChoiceCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "选择城市", Toast.LENGTH_SHORT).show();
//                context.startActivity(new Intent(context, CityActivity.class));
                Intent intent = new Intent(context,CityActivity.class);
                ((MainActivity) context).startActivityForResult(intent,REQUEST_CODE);
            }
        });

        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, SearchActivity.class);
//                context.startActivity(intent);
                if(data == null || data.size() < 1) {
                    return;
                }
                Toast.makeText(context, "搜索", Toast.LENGTH_SHORT).show();
                mRlForSearch.setVisibility(View.VISIBLE);
                isSearch = true;
            }
        });

        mBtnChoicePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data == null || data.size() < 1) {
                    return;
                }
                Toast.makeText(context, "选择行政区等", Toast.LENGTH_SHORT).show();
                showPuPWindow();
            }
        });

        mNoNetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoNetPager.setVisibility(View.GONE);
                mLoadingPager.setVisibility(View.VISIBLE);
                getDataFromNet();
            }
        });

        mRefreshLayout.setMaterialRefreshListener(new MyMaterialRefreshListener());

        mCinemaRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mCurPlace.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurPlace.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        mCurPlace.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }

        });

        mCurPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG1111", "选择地址条被点击");
                isPlace = true;
                getMyPlaceFromNet();
            }
        });

        mTvSearchCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                mRlForSearch.setVisibility(View.GONE);
                isSearch = false;
            }
        });

        mIvDeleteInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchNoFind.setVisibility(View.GONE);
                mSeaCinemaInput.setText("");
            }
        });

        mSeaCinemaInput.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchCinema(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mIvDeleteInput.setVisibility(View.GONE);
                    mRecyTimeSearch.setVisibility(View.GONE);
                } else {
                    mRecyTimeSearch.setVisibility(View.VISIBLE);
                    mIvDeleteInput.setVisibility(View.VISIBLE);
                }
            }
        });

        ((MainActivity)context).setMyOnKeyDown(new MainActivity.MyOnKeyDown() {
            @Override
            public boolean onKeyDown(int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && isSearch) {
                    isSearch = false;
                    mRlForSearch.setVisibility(View.GONE);
                    hideKeyBoard();
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * 搜索影院
     * @param keyword
     */
    private void searchCinema(String keyword) {
        final String str=keyword;
        new Thread(){
            @Override
            public void run() {
                String tt = PinyinTool.getSimple(str);
                cinemaNames = PinyinTool.search(tt);
                Message message=new Message();
                message.what=0;
                mHandler.sendMessage(message);
            }

        }.start();
    }

    private class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onfinish() {
            super.onfinish();
            isRefreshing = false;
            Toast.makeText(context, "刷新完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            isRefreshing = true;
            getDataFromNet();
        }
    }

    public void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSeaCinemaInput.getWindowToken(),0);
    }

    /**左侧一级分类的数据*/
    private List<FirstClassItem> firstList;
    /**右侧二级分类的数据*/
    private List<SecondClassItem> secondList;
    /**最右侧的分类数据*/
    private List<ThirdClassItem> thirdList;

    /**使用PopupWindow显示一级分类和二级分类*/
    private PopupWindow popupWindow;

    /**左侧和右侧两个ListView*/
    private ListView leftLV, rightLV;
    //弹出PopupWindow时背景变暗
    private View darkView;

    //弹出PopupWindow时，背景变暗的动画
    private Animation animIn, animOut;

    /**
     * 以下实现popwindow多级菜单显示
     */
    public void showPuPWindow() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(mBtnChoicePlace);
            popupWindow.setAnimationStyle(-1);
            //背景变暗
            darkView.startAnimation(animIn);
            darkView.setVisibility(View.VISIBLE);
        }
    }


    private ListView maxLv;
    private void initPop() {
        popupWindow = new PopupWindow(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_layout, null);
        leftLV = (ListView) view.findViewById(R.id.pop_listview_left);
        rightLV = (ListView) view.findViewById(R.id.pop_listview_right);
        maxLv = (ListView) view.findViewById(R.id.pop_listview_maxright);

        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new PaintDrawable());
        popupWindow.setFocusable(true);

        popupWindow.setHeight(ScreenUtils.getScreenH((MainActivity)context) / 2);
        popupWindow.setWidth(ScreenUtils.getScreenW((MainActivity)context));

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkView.startAnimation(animOut);
                darkView.setVisibility(View.GONE);

                leftLV.setSelection(0);
                rightLV.setSelection(0);
                maxLv.setSelection(0);
            }
        });


        //为了方便扩展，这里的两个ListView均使用BaseAdapter.如果分类名称只显示一个字符串，建议改为ArrayAdapter.
        //加载一级分类
        final FirstClassAdapter firstAdapter = new FirstClassAdapter(context, firstList);
        leftLV.setAdapter(firstAdapter);

        //加载左侧第一行对应右侧二级分类
        secondList = new ArrayList<SecondClassItem>();
        secondList.addAll(firstList.get(0).getSecondList());
        thirdList = new ArrayList<ThirdClassItem>();
        thirdList.addAll(firstList.get(1).getSecondList().get(0).getThirdList());


        final SecondClassAdapter secondAdapter = new SecondClassAdapter(context, secondList);
        rightLV.setAdapter(secondAdapter);

        final ThirdClassAdapter thirdClassAdapter = new ThirdClassAdapter(context,thirdList);
        maxLv.setAdapter(thirdClassAdapter);

        //左侧ListView点击事件
        leftLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //二级数据
                List<SecondClassItem> list2 = firstList.get(position).getSecondList();

                List<ThirdClassItem> thirdList = list2.get(position).getThirdList();
                if(thirdList == null || thirdList.size() == 0) {
                    maxLv.setVisibility(View.GONE);
                } else {
                    maxLv.setVisibility(View.VISIBLE);
                }

                //如果没有二级类，则直接跳转
                if (list2 == null || list2.size() == 0) {
                    popupWindow.dismiss();

                    firstS = firstList.get(position).getName();
                    handleResult(firstS, "", "");
                    return;
                }

                FirstClassAdapter adapter = (FirstClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }

                //根据左侧一级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                //显示右侧二级分类
                updateSecondListView(list2, secondAdapter);
            }
        });

        //右侧ListView点击事件
        rightLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //三级数据
                List<ThirdClassItem> list3 = secondList.get(position).getThirdList();
                //如果没有三级类，则直接跳转
                if (list3 == null || list3.size() == 0) {
                    popupWindow.dismiss();

                    firstS = firstList.get(firstAdapter.getSelectedPosition()).getName();
                    secondS = secondList.get(position).getName();
                    handleResult(firstS, secondS, "");
                    return;
                }

                SecondClassAdapter adapter = (SecondClassAdapter) (parent.getAdapter());
                //如果上次点击的就是这一个item，则不进行任何操作
                if (adapter.getSelectedPosition() == position) {
                    return;
                }

                //根据左侧二级分类选中情况，更新背景色
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                //显示右侧三级分类
                updateThirdListView(list3, thirdClassAdapter);
            }
        });

        maxLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //关闭popupWindow，显示用户选择的分类
                popupWindow.dismiss();

                int firstPosition = firstAdapter.getSelectedPosition();
                firstS = firstList.get(firstPosition).getName();
                int secondPosition = secondAdapter.getSelectedPosition();
                secondS = secondList.get(secondPosition).getName();
                String thirdS = thirdList.get(position).getName();
                handleResult(firstS, secondS, thirdS);
            }
        });
    }

    private String firstS;
    private String secondS;

    private void updateThirdListView(List<ThirdClassItem> list3, ThirdClassAdapter thirdClassAdapter) {
        thirdList.clear();
        thirdList.addAll(list3);
        thirdClassAdapter.notifyDataSetChanged();
    }

    //刷新右侧ListView
    private void updateSecondListView(List<SecondClassItem> list2, SecondClassAdapter secondAdapter) {
        secondList.clear();
        secondList.addAll(list2);
        secondAdapter.notifyDataSetChanged();
    }

    //处理点击结果
    private void handleResult(String firstS, String secondS, String thirdS){
        String text = "一级：" + firstS + ",二级：" + secondS + ",三级：" + thirdS;
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }

}

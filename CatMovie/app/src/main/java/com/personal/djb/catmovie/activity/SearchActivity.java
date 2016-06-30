package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.personal.djb.catmovie.R;
import com.personal.djb.catmovie.bean.HotSearchBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class SearchActivity extends Activity {

    private RelativeLayout mRlForSearch;
    private EditText mEtForSerach;
    private Button mTvCancel;
    private RecyclerView mSearchRecyclerView;
    private List<HotSearchBean.DataBean> datas;
    private RadioGroup mRgTest;

    private String HOT_SEARCH_URL = "http://api.meituan.com/mmdb/search/movie/hotword/list.json?token=&__vhost=api.maoyan.com&utm_campaign=AmovieBmovieCD-1&movieBundleVersion=6801&utm_source=hiapk&utm_medium=android&utm_term=6.8.0&utm_content=860311023305964&ci=1&net=255&dModel=MI%203&uuid=F096369352E4004DD3758BF83FE24AC312445F9B51AD3D8FCE2A0CD57754F6E4&lat=40.099064&lng=116.379351&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1463657899016&__skua=7e01cf8dd30a179800a7a93979b430b2&__skno=23e3e90e-007e-4607-a43f-b3d609838bb8&__skcy=2MBnuWHEtyH9WJldBe5hskKYlgM%3D";
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        findView();
        initView();

        setListener();
    }

    private void initView() {

        getDataFromNet();
    }

    private void getDataFromNet() {

        OkHttpUtils.get().url(HOT_SEARCH_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                processData(response);
            }
        });
    }

    private void processData(String json) {
        HotSearchBean hotSearchBean = new Gson().fromJson(json, HotSearchBean.class);
        datas = hotSearchBean.getData();

        adapter = new MyAdapter();
        //设置布局管理器
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mSearchRecyclerView.setLayoutManager(manager);
        mSearchRecyclerView.setAdapter(adapter);

    }

    private void setListener() {
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRgTest.setOnCheckedChangeListener(new MyOnCheckChangeListener());
    }

    private void findView() {
        mRlForSearch = (RelativeLayout) findViewById(R.id.common_title_rl_etandot);
        mEtForSerach = (EditText) findViewById(R.id.common_title_et_input);
        mTvCancel = (Button) findViewById(R.id.common_title_search_cancel);
        mSearchRecyclerView = (RecyclerView) findViewById(R.id.hot_search_recyclerview);
        mRgTest = (RadioGroup) findViewById(R.id.rg_test);

        mRlForSearch.setVisibility(View.VISIBLE);
        mEtForSerach.setVisibility(View.VISIBLE);
        mTvCancel.setVisibility(View.VISIBLE);


    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = new ViewHolder(LayoutInflater.from(
                    SearchActivity.this).inflate(R.layout.hot_search_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyAdapter.ViewHolder)holder).setData(datas.get(position).getValue());
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder{

            private TextView mTvHotSearch;

            public ViewHolder(View itemView) {
                super(itemView);

                mTvHotSearch = (TextView) itemView.findViewById(R.id.tv_hot_search);
            }

            public void setData(String value) {
                mTvHotSearch.setText(value);
            }
        }
    }

    private class MyOnCheckChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case  R.id.type_funny:
                    Intent intent = new Intent(SearchActivity.this, MostTypeActivity.class);
                    startActivity(intent);
                    break;
                default:
                    Toast.makeText(SearchActivity.this, "没有数据，很遗憾~", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

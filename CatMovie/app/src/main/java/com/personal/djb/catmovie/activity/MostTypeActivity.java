package com.personal.djb.catmovie.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.personal.djb.catmovie.R;

public class MostTypeActivity extends Activity {

    private Button mBtnForBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_most_type);

        findView();

        setListener();
    }

    private void setListener() {
        mBtnForBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findView() {
        mBtnForBack = (Button) findViewById(R.id.btn_mosttype_back);
    }
}

package com.personal.djb.catmovie.activity;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.google.zxing.Result;

import cn.itguy.zxingportrait.CaptureActivity;

public class ERCodeActivity extends CaptureActivity {

    @Override
    public void handleDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
//        super.handleDecode(rawResult, barcode, scaleFactor);
        //  震动响铃
        beepManager.playBeepSoundAndVibrate();
        
        Toast.makeText(ERCodeActivity.this, rawResult.getText(), Toast.LENGTH_SHORT).show();

        //  添加下列语句可以连续扫描
//        restartPreviewAfterDelay(0L);
    }
}

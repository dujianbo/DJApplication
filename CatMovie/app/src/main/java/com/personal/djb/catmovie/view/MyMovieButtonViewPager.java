package com.personal.djb.catmovie.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class MyMovieButtonViewPager extends ViewPager {
    
    public MyMovieButtonViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(getCurrentItem() == 2) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = ev.getX();
                    startY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float endY = ev.getY();
                    float endX = ev.getX();

                    float distanceX = endX - startX;
                    float distanceY = endY - startY;

                    if (Math.abs(distanceX) > Math.abs(distanceY)) {
                        if(distanceX > 10) {
                            return true;
                        }
                    } else {
                        return false;
                    }

                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    private float startX;
    private float startY;

}

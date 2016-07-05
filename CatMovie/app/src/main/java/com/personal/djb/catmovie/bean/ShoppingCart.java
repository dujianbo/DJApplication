package com.personal.djb.catmovie.bean;

/**
 * 作者：杨光福 on 2016/6/14 15:25
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：购物车类
 * 某条数据在购物车中的状态，用于要买多少个，是否选中状态等等
 *
 */
public class ShoppingCart extends ShopBean.DataBean.ListBean {
    private int count = 1;
    private boolean isCheck = true;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "count=" + count +
                ", isCheck=" + isCheck +
                '}';
    }
}

package com.personal.djb.catmovie.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class ShopBean {


    /**
     * list : [{"dealid":33527771,"pic":"http://p1.meituan.net/348.348/movie/017b5251479cb4157236af746c077533132848.jpg","price":15.9,"shortTitle":"","title":"GetD格多维 3D偏光眼镜 近视夹片式","value":68},{"dealid":29251108,"pic":"http://p0.meituan.net/348.348/movie/fa9c6639db2761ea8f2eeeaece3cf2f9284099.jpg","price":379,"shortTitle":"","title":"美国队长盾牌移动电源","value":399},{"dealid":33412921,"pic":"http://p1.meituan.net/348.348/movie/876f599135fad5412778b4d31263653a629209.png","price":29.9,"shortTitle":"","title":"QQ游戏创意拉链耳机","value":108},{"dealid":36513561,"pic":"http://p0.meituan.net/348.348/movie/88738639d74c4bc31030f535f8803243465188.jpg@60q","price":58,"shortTitle":"","title":"超蝙正版6sPlus手机壳","value":78},{"dealid":37948178,"pic":"http://p1.meituan.net/348.348/movie/30fdf3c5295755c9d46fba0a1e91d3b1165779.jpg@60q","price":198,"shortTitle":"","title":"正版美队线控入耳式运动耳机","value":259},{"dealid":37948183,"pic":"http://p0.meituan.net/348.348/movie/49298f3476b33f398590571d2adc1095226498.jpg@60q","price":168,"shortTitle":"","title":"美队3内战IOS苹果数据线","value":268},{"dealid":37323443,"pic":"http://p1.meituan.net/348.348/movie/196c2d88eb9021ba30a04cdc65b3229d96139.jpg@60q","price":199,"shortTitle":"","title":"钢铁侠蓝牙耳机EBT945","value":299},{"dealid":38839680,"pic":"http://p0.meituan.net/348.348/movie/edb721e313bbeaf6192bf540f638798b229915.jpg@60q","price":16.8,"shortTitle":"","title":"《我叫MT》正版授权游戏鼠标垫","value":35},{"dealid":33433435,"pic":"http://p1.meituan.net/348.348.60/movie/5895cf62a945089088ce8153c70df42a97204.jpg","price":69,"shortTitle":"","title":"《哆啦A梦》萌睡机器猫手机架","value":89},{"dealid":36089501,"pic":"http://p1.meituan.net/348.348/movie/a3e746c155b81f95870086d4a56e2e0643744.jpg@60q","price":88,"shortTitle":"","title":"漫威正版美国队长USB游戏鼠标","value":118}]
     * total : 87
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int total;
        /**
         * dealid : 33527771
         * pic : http://p1.meituan.net/348.348/movie/017b5251479cb4157236af746c077533132848.jpg
         * price : 15.9
         * shortTitle :
         * title : GetD格多维 3D偏光眼镜 近视夹片式
         * value : 68
         */

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int dealid;
            private String pic;
            private double price;
            private String shortTitle;
            private String title;
            private int value;

            public int getDealid() {
                return dealid;
            }

            public void setDealid(int dealid) {
                this.dealid = dealid;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public String getShortTitle() {
                return shortTitle;
            }

            public void setShortTitle(String shortTitle) {
                this.shortTitle = shortTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}

package com.personal.djb.catmovie.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class SearchBean {

    /**
     * data : [{"list":[{"cat":"喜剧,爱情","dur":134,"enm":"Our Times","fra":"中国台湾,中国香港,新加坡,韩国","frt":"2015-08-13,2015-10-15,2015-10-22,2016-05-12","globalReleased":true,"id":341569,"img":"http://p1.meituan.net/w.h/movie/19e87124de0929c5df86cb760776afc5655939.jpg","movieType":0,"nm":"我的少女时代","onlinePlay":false,"pubDesc":"2015-11-19大陆上映","rt":"2015-11-19","sc":9.2,"show":"","showst":2,"type":0,"ver":"2D","wish":54553,"wishst":0},{"cat":"动画,奇幻,冒险","dur":97,"enm":"Angry Birds","fra":"美国,韩国","frt":"2016-05-20,2016-05-19","globalReleased":true,"id":246188,"img":"http://p0.meituan.net/w.h/movie/b721e73740601799c065affb619d837e151622.jpg","movieType":0,"nm":"愤怒的小鸟","onlinePlay":false,"pubDesc":"2016-05-20大陆上映","rt":"2016-05-20","sc":8.9,"show":"","showst":3,"type":0,"ver":"3D/中国巨幕","wish":123045,"wishst":0},{"cat":"喜剧,爱情","dur":98,"enm":"Meet Miss Anxiety","fra":"新加坡,日本","frt":"2015-02-05,2016-04-08","globalReleased":true,"id":78991,"img":"http://p1.meituan.net/w.h/movie/6f7d5be7aa398e583b4f752fce7e24b0413982.jpg","movieType":0,"nm":"我的早更女友","onlinePlay":true,"pubDesc":"2014-12-12大陆上映","rt":"2014-12-12","sc":8.2,"show":"","showst":2,"type":0,"ver":"2D","wish":34203,"wishst":0},{"cat":"爱情,剧情","dur":108,"enm":"Yesterday Once More","globalReleased":true,"id":246582,"img":"http://p1.meituan.net/w.h/movie/702e327012a1e8d82c644690f2a9d827196818.jpg","movieType":0,"nm":"谁的青春不迷茫","onlinePlay":false,"pubDesc":"2016-04-22大陆上映","rt":"2016-04-22","sc":8.7,"show":"","showst":2,"type":0,"ver":"2D","wish":94492,"wishst":0},{"cat":"动作,剧情,冒险","dur":99,"enm":"My Beloved Bodyguard","globalReleased":true,"id":246152,"img":"http://p0.meituan.net/w.h/movie/89d790d8f8524dc1f15465420d4857a5380818.jpg","movieType":0,"nm":"我的特工爷爷","onlinePlay":false,"pubDesc":"2016-04-01大陆上映","rt":"2016-04-01","sc":8,"show":"","showst":2,"type":0,"ver":"2D/中国巨幕","wish":201507,"wishst":0}],"total":33583,"type":0}]
     * correction :
     * algotype : 1
     */

    private String correction;
    private int algotype;
    /**
     * list : [{"cat":"喜剧,爱情","dur":134,"enm":"Our Times","fra":"中国台湾,中国香港,新加坡,韩国","frt":"2015-08-13,2015-10-15,2015-10-22,2016-05-12","globalReleased":true,"id":341569,"img":"http://p1.meituan.net/w.h/movie/19e87124de0929c5df86cb760776afc5655939.jpg","movieType":0,"nm":"我的少女时代","onlinePlay":false,"pubDesc":"2015-11-19大陆上映","rt":"2015-11-19","sc":9.2,"show":"","showst":2,"type":0,"ver":"2D","wish":54553,"wishst":0},{"cat":"动画,奇幻,冒险","dur":97,"enm":"Angry Birds","fra":"美国,韩国","frt":"2016-05-20,2016-05-19","globalReleased":true,"id":246188,"img":"http://p0.meituan.net/w.h/movie/b721e73740601799c065affb619d837e151622.jpg","movieType":0,"nm":"愤怒的小鸟","onlinePlay":false,"pubDesc":"2016-05-20大陆上映","rt":"2016-05-20","sc":8.9,"show":"","showst":3,"type":0,"ver":"3D/中国巨幕","wish":123045,"wishst":0},{"cat":"喜剧,爱情","dur":98,"enm":"Meet Miss Anxiety","fra":"新加坡,日本","frt":"2015-02-05,2016-04-08","globalReleased":true,"id":78991,"img":"http://p1.meituan.net/w.h/movie/6f7d5be7aa398e583b4f752fce7e24b0413982.jpg","movieType":0,"nm":"我的早更女友","onlinePlay":true,"pubDesc":"2014-12-12大陆上映","rt":"2014-12-12","sc":8.2,"show":"","showst":2,"type":0,"ver":"2D","wish":34203,"wishst":0},{"cat":"爱情,剧情","dur":108,"enm":"Yesterday Once More","globalReleased":true,"id":246582,"img":"http://p1.meituan.net/w.h/movie/702e327012a1e8d82c644690f2a9d827196818.jpg","movieType":0,"nm":"谁的青春不迷茫","onlinePlay":false,"pubDesc":"2016-04-22大陆上映","rt":"2016-04-22","sc":8.7,"show":"","showst":2,"type":0,"ver":"2D","wish":94492,"wishst":0},{"cat":"动作,剧情,冒险","dur":99,"enm":"My Beloved Bodyguard","globalReleased":true,"id":246152,"img":"http://p0.meituan.net/w.h/movie/89d790d8f8524dc1f15465420d4857a5380818.jpg","movieType":0,"nm":"我的特工爷爷","onlinePlay":false,"pubDesc":"2016-04-01大陆上映","rt":"2016-04-01","sc":8,"show":"","showst":2,"type":0,"ver":"2D/中国巨幕","wish":201507,"wishst":0}]
     * total : 33583
     * type : 0
     */

    private List<DataBean> data;

    public String getCorrection() {
        return correction;
    }

    public void setCorrection(String correction) {
        this.correction = correction;
    }

    public int getAlgotype() {
        return algotype;
    }

    public void setAlgotype(int algotype) {
        this.algotype = algotype;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int total;
        private int type;
        /**
         * cat : 喜剧,爱情
         * dur : 134
         * enm : Our Times
         * fra : 中国台湾,中国香港,新加坡,韩国
         * frt : 2015-08-13,2015-10-15,2015-10-22,2016-05-12
         * globalReleased : true
         * id : 341569
         * img : http://p1.meituan.net/w.h/movie/19e87124de0929c5df86cb760776afc5655939.jpg
         * movieType : 0
         * nm : 我的少女时代
         * onlinePlay : false
         * pubDesc : 2015-11-19大陆上映
         * rt : 2015-11-19
         * sc : 9.2
         * show :
         * showst : 2
         * type : 0
         * ver : 2D
         * wish : 54553
         * wishst : 0
         */

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String cat;
            private int dur;
            private String enm;
            private String fra;
            private String frt;
            private boolean globalReleased;
            private int id;
            private String img;
            private int movieType;
            private String nm;
            private boolean onlinePlay;
            private String pubDesc;
            private String rt;
            private double sc;
            private String show;
            private int showst;
            private int type;
            private String ver;
            private int wish;
            private int wishst;

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getEnm() {
                return enm;
            }

            public void setEnm(String enm) {
                this.enm = enm;
            }

            public String getFra() {
                return fra;
            }

            public void setFra(String fra) {
                this.fra = fra;
            }

            public String getFrt() {
                return frt;
            }

            public void setFrt(String frt) {
                this.frt = frt;
            }

            public boolean isGlobalReleased() {
                return globalReleased;
            }

            public void setGlobalReleased(boolean globalReleased) {
                this.globalReleased = globalReleased;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getMovieType() {
                return movieType;
            }

            public void setMovieType(int movieType) {
                this.movieType = movieType;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public boolean isOnlinePlay() {
                return onlinePlay;
            }

            public void setOnlinePlay(boolean onlinePlay) {
                this.onlinePlay = onlinePlay;
            }

            public String getPubDesc() {
                return pubDesc;
            }

            public void setPubDesc(String pubDesc) {
                this.pubDesc = pubDesc;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getShow() {
                return show;
            }

            public void setShow(String show) {
                this.show = show;
            }

            public int getShowst() {
                return showst;
            }

            public void setShowst(int showst) {
                this.showst = showst;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public int getWishst() {
                return wishst;
            }

            public void setWishst(int wishst) {
                this.wishst = wishst;
            }
        }
    }
}

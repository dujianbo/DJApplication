package com.personal.djb.catmovie.bean.cinema;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class CinemaBean {

    /**
     * expires : 43200
     */

    private ControlBean control;

    private int status;
    private DataBean data;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ControlBean {
        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean {

        @SerializedName("朝阳区")
        private List<CinemaBean.BaseBean> chaoyang;
        @SerializedName("海淀区")
        private List<CinemaBean.BaseBean> haidian;
        @SerializedName("东城区")
        private List<CinemaBean.BaseBean> dongcheng;
        @SerializedName("丰台区")
        private List<CinemaBean.BaseBean> fengtai;
        @SerializedName("大兴区")
        private List<CinemaBean.BaseBean> daxing;
        @SerializedName("西城区")
        private List<CinemaBean.BaseBean> xicheng;
        @SerializedName("通州区")
        private List<CinemaBean.BaseBean> tongzhou;
        @SerializedName("昌平区")
        private List<CinemaBean.BaseBean> changping;
        @SerializedName("房山区")
        private List<CinemaBean.BaseBean> fangshan;
        @SerializedName("顺义区")
        private List<CinemaBean.BaseBean> shunyi;
        @SerializedName("门头沟区")
        private List<CinemaBean.BaseBean> mentougou;
        @SerializedName("石景山区")
        private List<CinemaBean.BaseBean> shijingshan;
        @SerializedName("怀柔区")
        private List<CinemaBean.BaseBean> huairou;
        @SerializedName("平谷区")
        private List<CinemaBean.BaseBean> pinggu;
        @SerializedName("密云县")
        private List<CinemaBean.BaseBean> miyun;
        @SerializedName("延庆县")
        private List<CinemaBean.BaseBean> yanqin;

        public List<BaseBean> getChaoyang() {
            return chaoyang;
        }

        public void setChaoyang(List<BaseBean> chaoyang) {
            this.chaoyang = chaoyang;
        }

        public List<BaseBean> getHaidian() {
            return haidian;
        }

        public void setHaidian(List<BaseBean> haidian) {
            this.haidian = haidian;
        }

        public List<BaseBean> getDongcheng() {
            return dongcheng;
        }

        public void setDongcheng(List<BaseBean> dongcheng) {
            this.dongcheng = dongcheng;
        }

        public List<BaseBean> getFengtai() {
            return fengtai;
        }

        public void setFengtai(List<BaseBean> fengtai) {
            this.fengtai = fengtai;
        }

        public List<BaseBean> getDaxing() {
            return daxing;
        }

        public void setDaxing(List<BaseBean> daxing) {
            this.daxing = daxing;
        }

        public List<BaseBean> getXicheng() {
            return xicheng;
        }

        public void setXicheng(List<BaseBean> xicheng) {
            this.xicheng = xicheng;
        }

        public List<BaseBean> getTongzhou() {
            return tongzhou;
        }

        public void setTongzhou(List<BaseBean> tongzhou) {
            this.tongzhou = tongzhou;
        }

        public List<BaseBean> getChangping() {
            return changping;
        }

        public void setChangping(List<BaseBean> changping) {
            this.changping = changping;
        }

        public List<BaseBean> getFangshan() {
            return fangshan;
        }

        public void setFangshan(List<BaseBean> fangshan) {
            this.fangshan = fangshan;
        }

        public List<BaseBean> getShunyi() {
            return shunyi;
        }

        public void setShunyi(List<BaseBean> shunyi) {
            this.shunyi = shunyi;
        }

        public List<BaseBean> getMentougou() {
            return mentougou;
        }

        public void setMentougou(List<BaseBean> mentougou) {
            this.mentougou = mentougou;
        }

        public List<BaseBean> getShijingshan() {
            return shijingshan;
        }

        public void setShijingshan(List<BaseBean> shijingshan) {
            this.shijingshan = shijingshan;
        }

        public List<BaseBean> getHuairou() {
            return huairou;
        }

        public void setHuairou(List<BaseBean> huairou) {
            this.huairou = huairou;
        }

        public List<BaseBean> getPinggu() {
            return pinggu;
        }

        public void setPinggu(List<BaseBean> pinggu) {
            this.pinggu = pinggu;
        }

        public List<BaseBean> getMiyun() {
            return miyun;
        }

        public void setMiyun(List<BaseBean> miyun) {
            this.miyun = miyun;
        }

        public List<BaseBean> getYanqin() {
            return yanqin;
        }

        public void setYanqin(List<BaseBean> yanqin) {
            this.yanqin = yanqin;
        }

        public static class Chao extends CinemaBean.BaseBean{}

        public static class Hai extends CinemaBean.BaseBean{}

        public static class Dong extends CinemaBean.BaseBean{}

        public static class Feng extends CinemaBean.BaseBean{}

        public static class Da extends CinemaBean.BaseBean{}

        public static class Xi extends CinemaBean.BaseBean{}

        public static class Tong extends CinemaBean.BaseBean{}

        public static class Chang extends CinemaBean.BaseBean{}

        public static class Fang extends CinemaBean.BaseBean{}

        public static class Shun extends CinemaBean.BaseBean{}

        public static class Men extends CinemaBean.BaseBean{}

        public static class Shi extends CinemaBean.BaseBean{}

        public static class Huai extends CinemaBean.BaseBean{}

        public static class Ping extends CinemaBean.BaseBean {}

        public static class Mi extends CinemaBean.BaseBean{}

        public static class Yan extends CinemaBean.BaseBean{}

        public static class HuiMin extends CinemaBean.BaseBean{}

        public static class SaiHan extends CinemaBean.BaseBean{}

        @Override
        public String toString() {
            return "DataBean{" +
                    "chaoyang=" + chaoyang +
                    ", haidian=" + haidian +
                    ", dongcheng=" + dongcheng +
                    ", fengtai=" + fengtai +
                    ", daxing=" + daxing +
                    ", xicheng=" + xicheng +
                    ", tongzhou=" + tongzhou +
                    ", changping=" + changping +
                    ", fangshan=" + fangshan +
                    ", shunyi=" + shunyi +
                    ", mentougou=" + mentougou +
                    ", shijingshan=" + shijingshan +
                    ", huairou=" + huairou +
                    ", pinggu=" + pinggu +
                    ", miyun=" + miyun +
                    ", yanqin=" + yanqin +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CinemaBean{" +
                "control=" + control +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class BaseBean {
        public int brdId;
        public float dealPrice;
        public int referencePrice;
        public int showCount;
        public String nm;
        public int preferential;
        public int poiId;
        public int deal;
        public int distance;
        public int follow;
        public String brd;
        public String dis;
        public float sellPrice;
        public String addr;
        public double lat;
        public double lng;
        public int imax;
        public String ct;
        public String area;
        public int sellmin;
        public boolean sell;
        public int id;


        //  我自定义的属性
        public double djbDistance;

        public double getDjbDistance() {
            return djbDistance;
        }

        public void setDjbDistance(double djbDistance) {
            this.djbDistance = djbDistance;
        }

        public int getBrdId() {
            return brdId;
        }

        public void setBrdId(int brdId) {
            this.brdId = brdId;
        }

        public float getDealPrice() {
            return dealPrice;
        }

        public void setDealPrice(float dealPrice) {
            this.dealPrice = dealPrice;
        }

        public int getReferencePrice() {
            return referencePrice;
        }

        public void setReferencePrice(int referencePrice) {
            this.referencePrice = referencePrice;
        }

        public int getShowCount() {
            return showCount;
        }

        public void setShowCount(int showCount) {
            this.showCount = showCount;
        }

        public String getNm() {
            return nm;
        }

        public void setNm(String nm) {
            this.nm = nm;
        }

        public int getPreferential() {
            return preferential;
        }

        public void setPreferential(int preferential) {
            this.preferential = preferential;
        }

        public int getPoiId() {
            return poiId;
        }

        public void setPoiId(int poiId) {
            this.poiId = poiId;
        }

        public int getDeal() {
            return deal;
        }

        public void setDeal(int deal) {
            this.deal = deal;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public String getBrd() {
            return brd;
        }

        public void setBrd(String brd) {
            this.brd = brd;
        }

        public String getDis() {
            return dis;
        }

        public void setDis(String dis) {
            this.dis = dis;
        }

        public float getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(float sellPrice) {
            this.sellPrice = sellPrice;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getImax() {
            return imax;
        }

        public void setImax(int imax) {
            this.imax = imax;
        }

        public String getCt() {
            return ct;
        }

        public void setCt(String ct) {
            this.ct = ct;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getSellmin() {
            return sellmin;
        }

        public void setSellmin(int sellmin) {
            this.sellmin = sellmin;
        }

        public boolean isSell() {
            return sell;
        }

        public void setSell(boolean sell) {
            this.sell = sell;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "BaseBean{" +
                    "brdId=" + brdId +
                    ", dealPrice=" + dealPrice +
                    ", referencePrice=" + referencePrice +
                    ", showCount=" + showCount +
                    ", nm='" + nm + '\'' +
                    ", preferential=" + preferential +
                    ", poiId=" + poiId +
                    ", deal=" + deal +
                    ", distance=" + distance +
                    ", follow=" + follow +
                    ", brd='" + brd + '\'' +
                    ", dis='" + dis + '\'' +
                    ", sellPrice=" + sellPrice +
                    ", addr='" + addr + '\'' +
                    ", lat=" + lat +
                    ", lng=" + lng +
                    ", imax=" + imax +
                    ", ct='" + ct + '\'' +
                    ", area='" + area + '\'' +
                    ", sellmin=" + sellmin +
                    ", sell=" + sell +
                    ", id=" + id +
                    '}';
        }
    }
}

package com.personal.djb.catmovie.bean.cinema;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class PlaceBean {


    /**
     * id : 1
     * detail : 北京多彩印刷有限公司附近
     * area : 0
     * province : 北京市
     * parentArea : 0
     * lng : 116.379677
     * isForeign : false
     * district : 昌平区
     * lat : 40.100168
     * city : 北京
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String detail;
        private int area;
        private String province;
        private int parentArea;
        private double lng;
        private boolean isForeign;
        private String district;
        private double lat;
        private String city;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getParentArea() {
            return parentArea;
        }

        public void setParentArea(int parentArea) {
            this.parentArea = parentArea;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public boolean isIsForeign() {
            return isForeign;
        }

        public void setIsForeign(boolean isForeign) {
            this.isForeign = isForeign;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}

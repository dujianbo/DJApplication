package com.personal.djb.catmovie.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class HotSearchBean {
    /**
     * url :
     * value : 魔兽
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String url;
        private String value;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

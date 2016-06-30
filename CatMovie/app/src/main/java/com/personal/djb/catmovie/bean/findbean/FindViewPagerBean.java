package com.personal.djb.catmovie.bean.findbean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class FindViewPagerBean {


    /**
     * id : 15147
     * name : 【旧】【2】这部剧不仅黄暴，还很有深度
     * commonTitle : 这部剧不仅黄暴，还很有深度
     * imgUrl : http://p0.meituan.net/mmc/f532e05aa465800f48ae007129b7d61075532.jpg
     * imgUrlSize : (720, 350)
     * bigImgTypeUrl :
     * bigImgUrlSize : (0, 0)
     * app : movie
     * type : 3
     * typeDesc : 链接到应用内部页面
     * standIdList : [14]
     * startTime : 1467169860000
     * endTime : 1467215700000
     * level : 2
     * newUser : -1
     * closable : 2
     * channelType : 0
     * channelListMap : {}
     * businessName :
     * businessIds :
     * url : meituanmovie://www.meituan.com/forum/postDetail?postID=105065
     * movieIdList : []
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String name;
        private String commonTitle;
        private String imgUrl;
        private String imgUrlSize;
        private String bigImgTypeUrl;
        private String bigImgUrlSize;
        private String app;
        private int type;
        private String typeDesc;
        private long startTime;
        private long endTime;
        private int level;
        private int newUser;
        private int closable;
        private int channelType;
        private ChannelListMapBean channelListMap;
        private String businessName;
        private String businessIds;
        private String url;
        private List<Integer> standIdList;
        private List<?> movieIdList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCommonTitle() {
            return commonTitle;
        }

        public void setCommonTitle(String commonTitle) {
            this.commonTitle = commonTitle;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getImgUrlSize() {
            return imgUrlSize;
        }

        public void setImgUrlSize(String imgUrlSize) {
            this.imgUrlSize = imgUrlSize;
        }

        public String getBigImgTypeUrl() {
            return bigImgTypeUrl;
        }

        public void setBigImgTypeUrl(String bigImgTypeUrl) {
            this.bigImgTypeUrl = bigImgTypeUrl;
        }

        public String getBigImgUrlSize() {
            return bigImgUrlSize;
        }

        public void setBigImgUrlSize(String bigImgUrlSize) {
            this.bigImgUrlSize = bigImgUrlSize;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeDesc() {
            return typeDesc;
        }

        public void setTypeDesc(String typeDesc) {
            this.typeDesc = typeDesc;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getNewUser() {
            return newUser;
        }

        public void setNewUser(int newUser) {
            this.newUser = newUser;
        }

        public int getClosable() {
            return closable;
        }

        public void setClosable(int closable) {
            this.closable = closable;
        }

        public int getChannelType() {
            return channelType;
        }

        public void setChannelType(int channelType) {
            this.channelType = channelType;
        }

        public ChannelListMapBean getChannelListMap() {
            return channelListMap;
        }

        public void setChannelListMap(ChannelListMapBean channelListMap) {
            this.channelListMap = channelListMap;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getBusinessIds() {
            return businessIds;
        }

        public void setBusinessIds(String businessIds) {
            this.businessIds = businessIds;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<Integer> getStandIdList() {
            return standIdList;
        }

        public void setStandIdList(List<Integer> standIdList) {
            this.standIdList = standIdList;
        }

        public List<?> getMovieIdList() {
            return movieIdList;
        }

        public void setMovieIdList(List<?> movieIdList) {
            this.movieIdList = movieIdList;
        }

        public static class ChannelListMapBean {
        }
    }
}

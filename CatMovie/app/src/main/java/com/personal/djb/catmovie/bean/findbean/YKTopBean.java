package com.personal.djb.catmovie.bean.findbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class YKTopBean implements Serializable{

    /**
     * data : [{"bgImg":"http://p1.meituan.net/movie/89e2f3ca27b09e307f4fb4a6b59fad8b804062.png","bgUrl":"meituanmovie://www.meituan.com/movie?id=117488&amp;nm=不法之徒迈·韦尔斯","content":"战利品是属于胜利者的，别在我背上撒尿还骗我是下雨。","date":1467216000000,"horImg":"http://p0.meituan.net/movie/92386f6bca021bf445d6d20ac9e1917254550.jpg","id":61,"img":"http://p1.meituan.net/w.h/movie/__42978953__3970511.jpg","objectId":117488,"objectName":"不法之徒迈·韦尔斯","pubDate":1976,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=117488&amp;nm=不法之徒迈·韦尔斯"},{"bgImg":"http://p0.meituan.net/movie/e595ded3b1da4e75feac349599550e55134861.jpg","bgUrl":"meituanmovie://www.meituan.com/movie?id=66125&amp;nm=月升王国","content":"望远镜让我能更近地看东西，我假装这是我的特异功能。","date":1467129600000,"horImg":"http://p1.meituan.net/movie/e79abe01361c2ae8211094b811e33f08176201.jpg","id":60,"img":"http://p0.meituan.net/w.h/movie/67/4552013.jpg","objectId":66125,"objectName":"月升王国","pubDate":2012,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=66125&amp;nm=月升王国"},{"bgImg":"http://p0.meituan.net/movie/a7654ac291c00aa2cddb60abc775de27180987.jpg","bgUrl":"meituanmovie://www.meituan.com/movie?id=32124&amp;nm=步履不停","content":"父母的骄傲也许是盲目的，孩子长大不总会如你所愿。","date":1467043200000,"horImg":"http://p0.meituan.net/movie/b57d07c94740cb7dd16cf4d4400b857f285868.jpg","id":59,"img":"http://p0.meituan.net/w.h/movie/73/5153829.jpg","objectId":32124,"objectName":"步履不停","pubDate":2008,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=32124&amp;nm=步履不停"},{"bgImg":"http://p0.meituan.net/movie/9b4e2f32b4d7b2fc8b9a2ccbe1d9d568162173.jpg","bgUrl":"meituanmovie://www.meituan.com/movie?id=488&amp;nm=机器人总动员","content":"我不要只是生存，我要活得有意义。","date":1466956800000,"horImg":"http://p1.meituan.net/movie/e4fbdccda435f1d72bcae40a5531d13659040.jpg","id":58,"img":"http://p0.meituan.net/w.h/movie/53/930936.jpg","objectId":488,"objectName":"机器人总动员","pubDate":2008,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=488&amp;nm=机器人总动员"},{"bgImg":"http://p0.meituan.net/movie/6158576bd3eb5fa582a5f1e9e8f1bb26216622.jpg","bgUrl":"meituanmovie://www.meituan.com/movie?id=298609&amp;nm=笑傲江湖2：东方不败","content":"尘世如潮人如水，只叹江湖几人回。","date":1466870400000,"horImg":"http://p0.meituan.net/movie/ad8de9ee55798b77d4a4d81589ef0a3830370.jpg","id":57,"img":"http://p1.meituan.net/w.h/movie/6d7a8643b1f982f88df2269209c76588155594.jpg","objectId":298609,"objectName":"笑傲江湖2：东方不败","pubDate":1992,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=298609&amp;nm=笑傲江湖2：东方不败"},{"bgImg":"http://p0.meituan.net/movie/fa14e6c284441392637b744ac81fcb57229914.jpg","bgUrl":"meituanmovie://www.meituan.com/movie?id=246333&amp;nm=惊天魔盗团2","content":"万事并不总如表面看上去那么简单。","date":1466784000000,"horImg":"http://p1.meituan.net/movie/f3e2d1f13fd1b0ec50f66424814d7685257679.jpg","id":56,"img":"http://p1.meituan.net/w.h/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg","objectId":246333,"objectName":"惊天魔盗团2","pubDate":2016,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=246333&amp;nm=惊天魔盗团2"},{"bgImg":"http://p0.meituan.net/movie/a6f97c36a265ba7e14293dec69e307e493142.jpg","bgUrl":"meituanmovie://www.meituan.com/movie?id=42651&amp;nm=一年级生","content":"笔中自有力量，学习永不停歇，直到踏进棺材。","date":1466697600000,"horImg":"http://p1.meituan.net/movie/bdf157d31d521fb585d919892df4c4a569807.jpg","id":55,"img":"http://p0.meituan.net/w.h/movie/26/6520688.jpg","objectId":42651,"objectName":"一年级生","pubDate":2011,"type":0,"url":"meituanmovie://www.meituan.com/movie?id=42651&amp;nm=一年级生"}]
     * title : 每日台词
     * tag : dailyDialogue
     */

    private String title;
    private String tag;
    /**
     * bgImg : http://p1.meituan.net/movie/89e2f3ca27b09e307f4fb4a6b59fad8b804062.png
     * bgUrl : meituanmovie://www.meituan.com/movie?id=117488&amp;nm=不法之徒迈·韦尔斯
     * content : 战利品是属于胜利者的，别在我背上撒尿还骗我是下雨。
     * date : 1467216000000
     * horImg : http://p0.meituan.net/movie/92386f6bca021bf445d6d20ac9e1917254550.jpg
     * id : 61
     * img : http://p1.meituan.net/w.h/movie/__42978953__3970511.jpg
     * objectId : 117488
     * objectName : 不法之徒迈·韦尔斯
     * pubDate : 1976
     * type : 0
     * url : meituanmovie://www.meituan.com/movie?id=117488&amp;nm=不法之徒迈·韦尔斯
     */

    private List<DataBean> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String bgImg;
        private String bgUrl;
        private String content;
        private long date;
        private String horImg;
        private int id;
        private String img;
        private int objectId;
        private String objectName;
        private int pubDate;
        private int type;
        private String url;

        public String getBgImg() {
            return bgImg;
        }

        public void setBgImg(String bgImg) {
            this.bgImg = bgImg;
        }

        public String getBgUrl() {
            return bgUrl;
        }

        public void setBgUrl(String bgUrl) {
            this.bgUrl = bgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getHorImg() {
            return horImg;
        }

        public void setHorImg(String horImg) {
            this.horImg = horImg;
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

        public int getObjectId() {
            return objectId;
        }

        public void setObjectId(int objectId) {
            this.objectId = objectId;
        }

        public String getObjectName() {
            return objectName;
        }

        public void setObjectName(String objectName) {
            this.objectName = objectName;
        }

        public int getPubDate() {
            return pubDate;
        }

        public void setPubDate(int pubDate) {
            this.pubDate = pubDate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "bgImg='" + bgImg + '\'' +
                    ", bgUrl='" + bgUrl + '\'' +
                    ", content='" + content + '\'' +
                    ", date=" + date +
                    ", horImg='" + horImg + '\'' +
                    ", id=" + id +
                    ", img='" + img + '\'' +
                    ", objectId=" + objectId +
                    ", objectName='" + objectName + '\'' +
                    ", pubDate=" + pubDate +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "YKTopBean{" +
                "title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", data=" + data +
                '}';
    }
}

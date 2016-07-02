package com.personal.djb.catmovie.bean.findbean.yingku;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/1 0001.
 */
public class BaseDataBean {

    /**
     * boardtype : 4
     * content : 《每周北美票房榜》——将北美地区热映的影片，按照上周末票房从高到低排列，取前10名，每周一更新，相关数据来源于猫眼获取的北美票房数据。
     * created : 2016-06-07
     * id : 2
     * movies : [{"dir":"戴夫·格林","id":13511,"img":"http://p1.meituan.net/w.h/movie/a375ebfcdc2a396423c368b943813b01179997.jpg","nm":"忍者神龟2：破影而出","rt":"2016-07-02","star":"梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","sumBoxOffice":3532,"weekBoxOffice":3532},{"dir":"布莱恩·辛格","id":246177,"img":"http://p1.meituan.net/w.h/movie/ba2386a26648c1875e3fc780950f0ae0144875.jpg","nm":"X战警：天启","rt":"2016-06-03","star":"詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","sumBoxOffice":11700,"weekBoxOffice":2283},{"dir":"西娅·夏罗克","id":341535,"img":"http://p1.meituan.net/w.h/movie/63a1602e1042b9407789cab569a0978f83801.jpg","nm":"遇见你之前","rt":"2016-06-03(美国)","star":"山姆·克拉弗林,艾米莉亚·克拉克,查里斯·丹斯","sumBoxOffice":1872,"weekBoxOffice":1872},{"dir":"詹姆斯·波宾","id":248680,"img":"http://p1.meituan.net/w.h/movie/dbcc3eabae012da794fe4b74ed1365fc659390.jpg","nm":"爱丽丝梦游仙境2：镜中奇遇记","rt":"2016-05-27","star":"约翰尼·德普,安妮·海瑟薇,米娅·华希科沃斯卡","sumBoxOffice":5140,"weekBoxOffice":1131},{"dir":"费格尔·雷利,克莱·凯蒂","id":246188,"img":"http://p0.meituan.net/w.h/movie/b721e73740601799c065affb619d837e151622.jpg","nm":"愤怒的小鸟","rt":"2016-05-20","star":"杰森·苏戴奇斯,乔什·盖德,丹尼·麦克布耐德","sumBoxOffice":8712,"weekBoxOffice":1021},{"dir":"安东尼·罗素,乔·罗素","id":246234,"img":"http://p1.meituan.net/w.h/movie/73f8a5ba9c96f136454d737530cac56b448798.jpg","nm":"美国队长3","rt":"2016-05-06","star":"克里斯·埃文斯,小罗伯特·唐尼,斯嘉丽·约翰逊","sumBoxOffice":38918,"weekBoxOffice":783},{"dir":"尼古拉斯·斯托勒","id":338354,"img":"http://p1.meituan.net/w.h/movie/7c14e7ef430a4a1095f0ebd6595d9a03319947.jpg","nm":"邻居大战2：姐妹会崛起","rt":"2016-05-20(美国)","star":"塞斯·罗根,科洛·莫瑞兹,扎克·埃夫隆","sumBoxOffice":487,"weekBoxOffice":487},{"dir":"阿吉瓦·沙弗尔","id":345986,"img":"http://p1.meituan.net/w.h/movie/ecc2b5ec9e8f74861ce0ecc805aa99be185706.jpg","nm":"流行歌星：永不停歇","rt":"2016-06-03(美国)","star":"乔玛·塔昆,伊莫珍·波茨,萨拉·丝沃曼","sumBoxOffice":470,"weekBoxOffice":470},{"dir":"乔恩·费儒","id":246970,"img":"http://p1.meituan.net/w.h/movie/0c9ee4c5b44928292bc00914686374a1160686.jpg","nm":"奇幻森林","rt":"2016-04-15","star":"尼尔·塞西,郭子睿,比尔·默瑞","sumBoxOffice":34767,"weekBoxOffice":445},{"dir":"沙恩·布莱克","id":248095,"img":"http://p0.meituan.net/w.h/movie/103eb70f0ea9180d6d68dcf6e005f79587635.jpg","nm":"耐撕侦探","rt":"2016","star":"马特·波莫,瑞恩·高斯林,罗素·克劳","sumBoxOffice":2905,"weekBoxOffice":345}]
     * paging : {"hasMore":false,"limit":10,"offset":0,"total":10}
     * shareHidden : 1
     * title : 每周北美票房榜
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        public int boardtype;
        public String content;
        public String created;
        public int id;
        /**
         * hasMore : false
         * limit : 10
         * offset : 0
         * total : 10
         */

        public PagingBean paging;
        public int shareHidden;
        public String title;
        /**
         * dir : 戴夫·格林
         * id : 13511
         * img : http://p1.meituan.net/w.h/movie/a375ebfcdc2a396423c368b943813b01179997.jpg
         * nm : 忍者神龟2：破影而出
         * rt : 2016-07-02
         * star : 梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森
         * sumBoxOffice : 3532
         * weekBoxOffice : 3532
         */

        public List<MoviesBean> movies;

        public int getBoardtype() {
            return boardtype;
        }

        public void setBoardtype(int boardtype) {
            this.boardtype = boardtype;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public int getShareHidden() {
            return shareHidden;
        }

        public void setShareHidden(int shareHidden) {
            this.shareHidden = shareHidden;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class PagingBean implements Serializable{
            public boolean hasMore;
            public int limit;
            public int offset;
            public int total;

            public boolean isHasMore() {
                return hasMore;
            }

            public void setHasMore(boolean hasMore) {
                this.hasMore = hasMore;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            @Override
            public String toString() {
                return "PagingBean{" +
                        "hasMore=" + hasMore +
                        ", limit=" + limit +
                        ", offset=" + offset +
                        ", total=" + total +
                        '}';
            }
        }

        public static class MoviesBean implements Serializable{
            public String dir;
            public int id;
            public String img;
            public String nm;
            public String rt;
            public String star;
            public float sc;
            public int sumBoxOffice;
            public int weekBoxOffice;
            public int wish;
            public int monthWish;

            public int getMonthWish() {
                return monthWish;
            }

            public void setMonthWish(int monthWish) {
                this.monthWish = monthWish;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public float getSc() {
                return sc;
            }

            public void setSc(float sc) {
                this.sc = sc;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
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

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public int getSumBoxOffice() {
                return sumBoxOffice;
            }

            public void setSumBoxOffice(int sumBoxOffice) {
                this.sumBoxOffice = sumBoxOffice;
            }

            public int getWeekBoxOffice() {
                return weekBoxOffice;
            }

            public void setWeekBoxOffice(int weekBoxOffice) {
                this.weekBoxOffice = weekBoxOffice;
            }

            @Override
            public String toString() {
                return "MoviesBean{" +
                        "dir='" + dir + '\'' +
                        ", id=" + id +
                        ", img='" + img + '\'' +
                        ", nm='" + nm + '\'' +
                        ", rt='" + rt + '\'' +
                        ", star='" + star + '\'' +
                        ", sumBoxOffice=" + sumBoxOffice +
                        ", weekBoxOffice=" + weekBoxOffice +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "boardtype=" + boardtype +
                    ", content='" + content + '\'' +
                    ", created='" + created + '\'' +
                    ", id=" + id +
                    ", paging=" + paging +
                    ", shareHidden=" + shareHidden +
                    ", title='" + title + '\'' +
                    ", movies=" + movies +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BaseDataBean{" +
                "data=" + data +
                '}';
    }
}

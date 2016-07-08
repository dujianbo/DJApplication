package com.personal.djb.catmovie.bean.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/25 0025.
 */
public class HotMovieBean {
    /**
     * expires : 1800
     */

    private ControlBean control;
    /**
     * control : {"expires":1800}
     * status : 0
     * data : {"hasNext":false,"movies":[{"late":false,"cnms":0,"sn":0,"showInfo":"今天149家影院放映1885场","nm":"惊天魔盗团2","preSale":0,"vd":"","dir":"朱浩伟","star":"杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森","cat":"动作,喜剧,惊悚","wish":513937,"3d":true,"pn":244,"scm":"周董变魔术，敌人挡不住","imax":false,"snum":100273,"showDate":"","dur":126,"img":"http://p1.meituan.net/165.220/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg","sc":8.6,"ver":"2D/3D/中国巨幕","rt":"2016-06-24上映","src":"","time":"","id":246333},{"late":false,"cnms":0,"sn":0,"showInfo":"今天148家影院放映1736场","nm":"独立日：卷土重来","preSale":0,"vd":"","dir":"罗兰·艾默里奇","star":"利亚姆·海姆斯沃斯,杰夫·高布伦,杰西·厄舍","cat":"动作,冒险,科幻","wish":164747,"3d":true,"pn":226,"scm":"外星再入侵，地核将燃尽","imax":true,"snum":77065,"showDate":"","dur":120,"img":"http://p1.meituan.net/165.220/movie/b408a9322cd0da51d4bdd3261ba3d1c0278866.jpg","sc":8.5,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-24上映","src":"","time":"","id":246375},{"late":false,"cnms":0,"sn":0,"showInfo":"今天145家影院放映795场","nm":"三人行","preSale":0,"vd":"","dir":"杜琪峰","star":"赵薇,古天乐,钟汉良","cat":"动作,犯罪,悬疑","wish":72910,"3d":false,"pn":190,"scm":"悍匪有手段，中枪再作案","imax":false,"snum":32496,"showDate":"","dur":88,"img":"http://p1.meituan.net/165.220/movie/1c82df174422d9374f821482f445171b129671.jpg","sc":6.9,"ver":"2D","rt":"2016-06-24上映","src":"","time":"","id":246972},{"late":false,"cnms":0,"sn":0,"showInfo":"今天128家影院放映495场","nm":"海底总动员2：多莉去哪儿","preSale":0,"vd":"","dir":"安德鲁·斯坦顿,安格斯·麦克莱恩","star":"艾伦·德詹尼丝,徐帆,艾伯特·布鲁克斯","cat":"动画,喜剧,冒险","wish":144949,"3d":true,"pn":123,"scm":"唠叨鱼多莉，上路找父母","imax":true,"snum":72953,"showDate":"","dur":97,"img":"http://p0.meituan.net/165.220/movie/4c20a91505854dcd0f9617069c5fec21843230.jpg","sc":8.7,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"2016-06-17上映","src":"","time":"","id":246366},{"late":false,"cnms":0,"sn":0,"showInfo":"今天119家影院放映391场","nm":"魔兽","preSale":0,"vd":"","dir":"邓肯·琼斯","star":"崔维斯·费米尔,宝拉·巴顿,本·福斯特","cat":"动作,冒险,奇幻","wish":479894,"3d":true,"pn":347,"scm":"怒拳为谁握，联盟斗部落","imax":true,"snum":754647,"showDate":"","dur":124,"img":"http://p0.meituan.net/165.220/movie/f94e67dcd5b48f034bd7adc892f1b20695854.jpeg","sc":9.2,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-08上映","src":"","time":"","id":78421},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-08 下周五上映","nm":"致青春·原来你还在这里","preSale":1,"vd":"","dir":"周拓如","star":"吴亦凡,刘亦菲,金世佳","cat":"剧情,爱情","wish":209771,"3d":false,"pn":342,"scm":"霸道男学霸，爱上女学渣","imax":false,"snum":4964,"showDate":"","dur":98,"img":"http://p0.meituan.net/165.220/movie/37e7ed909843270f0880c325b6be7b60436509.jpg","sc":0,"ver":"2D","rt":"下周五上映","src":"","time":"","id":246575},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-02 本周六上映","nm":"忍者神龟2：破影而出","preSale":1,"vd":"","dir":"戴夫·格林","star":"梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","cat":"动作,冒险,喜剧","wish":266319,"3d":true,"pn":266,"scm":"儿时忍者龟，犀牛也回归","imax":true,"snum":1717,"showDate":"","dur":113,"img":"http://p1.meituan.net/165.220/movie/a375ebfcdc2a396423c368b943813b01179997.jpg","sc":0,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"本周六上映","src":"","time":"","id":13511},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-06-30 本周四上映","nm":"所以\u2026\u2026和黑粉结婚了","preSale":1,"vd":"","dir":"金帝荣","star":"朴灿烈,袁姗姗,姜潮","cat":"爱情,喜剧","wish":93731,"3d":false,"pn":121,"scm":"黑粉变新娘，这是闹哪样","imax":false,"snum":7748,"showDate":"","dur":99,"img":"http://p1.meituan.net/165.220/movie/79311d41bea3be8f1677c0b69a5868fa289298.jpg","sc":0,"ver":"2D","rt":"本周四上映","src":"","time":"","id":343379},{"late":false,"cnms":0,"sn":0,"showInfo":"今天62家影院放映118场","nm":"X战警：天启","preSale":0,"vd":"","dir":"布莱恩·辛格","star":"詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","cat":"动作,科幻,奇幻","wish":559919,"3d":true,"pn":692,"scm":"天启当炮灰，千年洗剪吹","imax":true,"snum":460717,"showDate":"","dur":144,"img":"http://p1.meituan.net/165.220/movie/ba2386a26648c1875e3fc780950f0ae0144875.jpg","sc":9.1,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-03上映","src":"","time":"","id":246177},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"赏金猎人","preSale":1,"vd":"","dir":"申太罗","star":"李敏镐,钟汉良,唐嫣","cat":"动作,喜剧,剧情,悬疑","wish":128582,"3d":true,"pn":196,"scm":"编外铁饭碗，收钱抓逃犯","imax":false,"snum":4537,"showDate":"","dur":105,"img":"http://p1.meituan.net/165.220/movie/45f8587cb425a01c9a279082772a8bd0379497.jpg","sc":0,"ver":"2D/3D","rt":"本周五上映","src":"","time":"","id":338506},{"late":false,"cnms":0,"sn":0,"showInfo":"今天40家影院放映71场","nm":"近在咫尺的爱恋","preSale":0,"vd":"","dir":"程孝泽","star":"彭于晏,郭采洁,杨子姗","cat":"爱情,运动,剧情","wish":12564,"3d":false,"pn":49,"scm":"真爱在身旁，挥拳为理想","imax":false,"snum":789,"showDate":"","dur":103,"img":"http://p1.meituan.net/165.220/movie/a70da0aefb82886efbae696181e966ff620085.jpg","sc":7.4,"ver":"2D","rt":"2016-06-24上映","src":"","time":"","id":57217},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-08 下周五上映","nm":"大鱼海棠","preSale":1,"vd":"","dir":"梁旋,张春","star":"","cat":"动画,奇幻,剧情","wish":136962,"3d":true,"pn":113,"scm":"北冥有鱼鲲，海棠掌乾坤","imax":false,"snum":3164,"showDate":"","dur":105,"img":"http://p1.meituan.net/165.220/movie/a607ab45bcc9e8486328b39bff0132a4420994.jpg","sc":0,"ver":"3D","rt":"下周五上映","src":"","time":"","id":246591},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"魔轮","preSale":1,"vd":"","dir":"王早","star":"林心如,何润东,金世佳","cat":"悬疑,惊悚","wish":9392,"3d":true,"pn":55,"scm":"天涯号起航，失控大逃亡","imax":false,"snum":488,"showDate":"","dur":92,"img":"http://p0.meituan.net/165.220/movie/e5ff984a4bec976fb1ed429e7fe4f61c605233.jpg","sc":0,"ver":"2D/3D","rt":"本周五上映","src":"","time":"","id":342741},{"late":false,"cnms":0,"sn":0,"showInfo":"今天16家影院放映19场","nm":"大火种","preSale":0,"vd":"","dir":"苗月","star":"白威,陈瑾,王柠","cat":"剧情,历史,战争","wish":458,"3d":false,"pn":4,"scm":"誓死护宣言，智斗救火种","imax":false,"snum":53,"showDate":"","dur":114,"img":"http://p1.meituan.net/165.220/movie/c8545c7bd1f6070f0d52a77bd0ddd8738720802.jpg","sc":0,"ver":"2D","rt":"本周一上映","src":"","time":"","id":337438},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-02 本周六上映","nm":"丑小鸭历险记","preSale":1,"vd":"","dir":"郑义","star":"朱可可,阿飞,夏倚轩","cat":"动画,科幻,冒险","wish":3399,"3d":false,"pn":66,"scm":"月球机器鸭，化身小飞鸭","imax":false,"snum":111,"showDate":"","dur":81,"img":"http://p1.meituan.net/165.220/movie/0484f8ad5aec878112f1a04715d32b4e360620.jpg","sc":0,"ver":"2D","rt":"本周六上映","src":"","time":"","id":368271},{"late":false,"cnms":0,"sn":0,"showInfo":"今天13家影院放映15场","nm":"筷仙","preSale":0,"vd":"","dir":"姬雨","star":"胡影怡,朱璇,周骏","cat":"恐怖,惊悚","wish":20373,"3d":false,"pn":32,"scm":"笔仙已玩坏，筷仙来作怪","imax":false,"snum":13090,"showDate":"","dur":87,"img":"http://p1.meituan.net/165.220/movie/31a325f0796cfebbab24776024eeec91100800.jpeg","sc":4.5,"ver":"2D","rt":"2016-06-17上映","src":"","time":"","id":343597},{"late":false,"cnms":0,"sn":0,"showInfo":"今天8家影院放映14场","nm":"南口1937","preSale":0,"vd":"","dir":"朱丹","star":"孙看,孙洪涛,刘忠元","cat":"剧情,历史,战争","wish":2034,"3d":false,"pn":26,"scm":"南口抗日战，疆场洒血汗","imax":false,"snum":117,"showDate":"","dur":98,"img":"http://p1.meituan.net/165.220/movie/f4b5d903571ec3c3fc952f86261b04fd227842.jpg","sc":0,"ver":"2D","rt":"2016-06-23上映","src":"","time":"","id":368071},{"late":false,"cnms":0,"sn":0,"showInfo":"今天10家影院放映13场","nm":"百鸟朝凤","preSale":0,"vd":"","dir":"吴天明","star":"陶泽如,郑伟,李岷城","cat":"历史,音乐,剧情","wish":3981,"3d":false,"pn":105,"scm":"两代手艺人，唢呐感情深","imax":false,"snum":109885,"showDate":"","dur":108,"img":"http://p1.meituan.net/165.220/movie/ef968fd382173c45b0c2d10572ca10b8354896.jpg","sc":9.2,"ver":"2D","rt":"2016-05-06上映","src":"","time":"","id":248260},{"late":false,"cnms":0,"sn":0,"showInfo":"今天7家影院放映8场","nm":"北京遇上西雅图之不二情书","preSale":0,"vd":"","dir":"薛晓路","star":"汤唯,吴秀波,惠英红","cat":"爱情,剧情","wish":307383,"3d":false,"pn":547,"scm":"异国心未远，书信寄情缘","imax":false,"snum":449744,"showDate":"","dur":132,"img":"http://p1.meituan.net/165.220/movie/fe80bc40822d0a5f3168b73089c47d71101133.jpg","sc":8.5,"ver":"2D/中国巨幕","rt":"2016-04-29上映","src":"","time":"","id":247575},{"late":false,"cnms":0,"sn":0,"showInfo":"今天5家影院放映8场","nm":"愤怒的小鸟","preSale":0,"vd":"","dir":"费格尔·雷利,克莱·凯蒂","star":"杰森·苏戴奇斯,乔什·盖德,丹尼·麦克布耐德","cat":"动画,奇幻,冒险","wish":123045,"3d":true,"pn":132,"scm":"绿猪来偷蛋，鸟儿群奋战","imax":false,"snum":324884,"showDate":"","dur":97,"img":"http://p0.meituan.net/165.220/movie/b721e73740601799c065affb619d837e151622.jpg","sc":8.9,"ver":"3D/中国巨幕","rt":"2016-05-20上映","src":"","time":"","id":246188},{"late":false,"cnms":0,"sn":0,"showInfo":"今天4家影院放映7场","nm":"我叫MT之山口山战记","preSale":0,"vd":"","dir":"核桃","star":"奶茶,YOYO,CBI","cat":"动画,喜剧,冒险","wish":43480,"3d":true,"pn":40,"scm":"贱萌牛头人，爆笑刷副本","imax":false,"snum":20344,"showDate":"","dur":86,"img":"http://p0.meituan.net/165.220/movie/73f6abbb90259d1fa8a88fa907ca3062587352.jpg","sc":7.7,"ver":"3D/中国巨幕","rt":"2016-06-17上映","src":"","time":"","id":246045},{"late":false,"cnms":0,"sn":0,"showInfo":"今天2家影院放映6场","nm":"白毛女","preSale":0,"vd":"","dir":"侯克明","star":"雷佳,张英席,高鹏","cat":"剧情","wish":1038,"3d":true,"pn":80,"scm":"欠了高利贷，卖女来抵债","imax":false,"snum":1201,"showDate":"","dur":122,"img":"http://p1.meituan.net/165.220/movie/a8dc71209614a071cbc56cfc193b9d9785810.jpg","sc":8.9,"ver":"3D/中国巨幕","rt":"2016-03-25上映","src":"","time":"","id":345102},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"党的女儿尹灵芝","preSale":1,"vd":"","dir":"卫晓茼","star":"郜耀平,张志宏,姚安濂","cat":"剧情,历史,战争","wish":702,"3d":false,"pn":18,"scm":"心中有信仰，少年变战士","imax":false,"snum":166,"showDate":"","dur":107,"img":"http://p0.meituan.net/165.220/movie/1dffa0a076e3514234f2029d6b29b3da157605.jpg","sc":0,"ver":"2D","rt":"本周五上映","src":"","time":"","id":672123},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-08 下周五上映","nm":"摇滚藏獒","preSale":1,"vd":"","dir":"艾什·布兰农","star":"冯小刚,郭德纲,郭麒麟","cat":"动画,音乐,家庭","wish":13844,"3d":true,"pn":128,"scm":"郑钧写剧本，混搭玩摇滚","imax":false,"snum":156,"showDate":"","dur":93,"img":"http://p1.meituan.net/165.220/movie/9d32c2703351b3e1cb456a05b207550e210596.jpg","sc":0,"ver":"2D/3D/中国巨幕","rt":"下周五上映","src":"","time":"","id":247244},{"late":false,"cnms":0,"sn":0,"showInfo":"今天2家影院放映2场","nm":"叶问2：宗师传奇","preSale":0,"vd":"","dir":"叶伟信","star":"甄子丹,洪金宝,熊黛林","cat":"动作,历史,传记","wish":211,"3d":false,"pn":63,"scm":"叶问搬香港，仇恨值渐长","imax":false,"snum":1040,"showDate":"","dur":108,"img":"http://p0.meituan.net/165.220/movie/70/612717.jpg","sc":8.5,"ver":"2D","rt":"2010-04-27上映","src":"","time":"","id":524},{"late":false,"cnms":0,"sn":0,"showInfo":"今天2家影院放映2场","nm":"警察故事2013","preSale":0,"vd":"","dir":"丁晟","star":"刘烨,成龙,景甜","cat":"剧情,动作,犯罪","wish":14520,"3d":true,"pn":44,"scm":"女儿乱交友，老爹来出头","imax":true,"snum":19140,"showDate":"","dur":108,"img":"http://p0.meituan.net/165.220/movie/__29561506__7176229.jpg","sc":8,"ver":"2D/IMAX 3D","rt":"2013-12-24上映","src":"","time":"","id":77192},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"终极胜利","preSale":1,"vd":"","dir":"冼杞然","star":"窦骁,约瑟夫·费因斯,娄宇健","cat":"剧情,战争,历史","wish":4895,"3d":false,"pn":122,"scm":"辛德勒名单，推出中国版","imax":false,"snum":68,"showDate":"","dur":108,"img":"http://p1.meituan.net/165.220/movie/0c93b5987ae440497dd985982aad1c4b233221.jpg","sc":0,"ver":"2D","rt":"本周五上映","src":"","time":"","id":341123},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"大电影2.0之两个傻瓜的荒唐事","preSale":0,"vd":"","dir":"阿甘","star":"郭涛,刘心悠,李灿森","cat":"喜剧","wish":107,"3d":false,"pn":3,"scm":"姚晨王宝强，爆笑太疯狂","imax":false,"snum":94,"showDate":"","dur":97,"img":"http://p1.meituan.net/165.220/movie/76ac89dad45ac25c676246dc92794b03318660.png","sc":5.6,"ver":"2D","rt":"2007-12-21上映","src":"","time":"","id":536},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"人再囧途之泰囧","preSale":0,"vd":"","dir":"徐峥","star":"徐峥,王宝强,黄渤","cat":"喜剧","wish":22543,"3d":false,"pn":232,"scm":"囧途太荒唐，甩不掉宝强","imax":false,"snum":15645,"showDate":"","dur":105,"img":"http://p0.meituan.net/165.220/movie/38/280521.jpg","sc":8.8,"ver":"2D","rt":"2012-12-12上映","src":"","time":"","id":938},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"厨子戏子痞子","preSale":0,"vd":"","dir":"管虎","star":"黄渤,刘烨,张涵予","cat":"剧情,喜剧,动作","wish":10110,"3d":false,"pn":281,"scm":"三男一台戏，客栈解僵局","imax":false,"snum":5766,"showDate":"","dur":108,"img":"http://p0.meituan.net/165.220/movie/__7277442__6071578.jpg","sc":8.3,"ver":"2D","rt":"2013-03-29上映","src":"","time":"","id":969},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"人间蒸发","preSale":0,"vd":"","dir":"梁德森","star":"钟丽缇,柳岩,李灿森","cat":"悬疑,惊悚","wish":771,"3d":false,"pn":22,"scm":"惊心动魄的寻亲之旅","imax":false,"snum":182,"showDate":"","dur":90,"img":"http://p0.meituan.net/165.220/movie/__6680785__5992311.jpg","sc":7,"ver":"2D","rt":"2013-04-28上映","src":"","time":"","id":78094},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"宫锁沉香","preSale":0,"vd":"","dir":"潘安子","star":"周冬雨,陈晓,赵丽颖","cat":"剧情,爱情,古装","wish":3080,"3d":false,"pn":36,"scm":"唯美清宫戏，阴谋不见底","imax":false,"snum":2509,"showDate":"","dur":115,"img":"http://p0.meituan.net/165.220/movie/__18327195__7709930.jpg","sc":7.8,"ver":"2D","rt":"2013-08-13上映","src":"","time":"","id":78176},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"同桌的你","preSale":0,"vd":"","dir":"郭帆","star":"周冬雨,林更新,隋凯","cat":"剧情,爱情","wish":24267,"3d":false,"pn":88,"scm":"初恋要结婚，回国忆青春","imax":false,"snum":73072,"showDate":"","dur":98,"img":"http://p0.meituan.net/165.220/movie/__40811615__5745234.jpg","sc":8.6,"ver":"2D","rt":"2014-04-25上映","src":"","time":"","id":78653},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"催眠大师","preSale":0,"vd":"","dir":"陈正道","star":"徐峥,莫文蔚,吕中","cat":"剧情,悬疑,惊悚","wish":11396,"3d":false,"pn":78,"scm":"催眠治心伤，侧击猜真相","imax":false,"snum":46563,"showDate":"","dur":102,"img":"http://p1.meituan.net/165.220/movie/__39582689__9041214.jpg","sc":9,"ver":"2D","rt":"2014-04-29上映","src":"","time":"","id":78672},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"京城81号","preSale":0,"vd":"","dir":"叶伟民","star":"吴镇宇,林心如,杨祐宁","cat":"剧情,悬疑,恐怖","wish":36817,"3d":true,"pn":98,"scm":"古宅阴森处，少妇诉凄苦","imax":false,"snum":122415,"showDate":"","dur":90,"img":"http://p1.meituan.net/165.220/movie/__48584093__3431812.jpg","sc":5,"ver":"2D/3D","rt":"2014-07-18上映","src":"","time":"","id":78267},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"闺蜜","preSale":0,"vd":"","dir":"黄真真","star":"陈意涵,薛凯琪,杨子姗","cat":"剧情,爱情","wish":30224,"3d":false,"pn":203,"scm":"永远好闺蜜，渣男靠边去","imax":false,"snum":68775,"showDate":"","dur":118,"img":"http://p1.meituan.net/165.220/movie/__48665963__9098444.jpg","sc":8,"ver":"2D","rt":"2014-07-30上映","src":"","time":"","id":78772},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"奔跑吧！兄弟","preSale":0,"vd":"","dir":"胡笳,岑俊义","star":"杨颖,王宝强,李晨","cat":"喜剧,冒险,动作","wish":49025,"3d":false,"pn":118,"scm":"跑男再出发，集体游三亚","imax":false,"snum":361183,"showDate":"","dur":88,"img":"http://p0.meituan.net/165.220/movie/85f0d9a604e26be5523a848269346697296774.jpg","sc":4.7,"ver":"2D","rt":"2015-01-30上映","src":"","time":"","id":246316},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"左耳","preSale":0,"vd":"","dir":"苏有朋","star":"陈都灵,欧豪,杨洋","cat":"爱情","wish":82116,"3d":false,"pn":315,"scm":"青春要恋爱，别忘堕堕胎","imax":false,"snum":538351,"showDate":"","dur":117,"img":"http://p0.meituan.net/165.220/movie/da55f29972ec0b9692ba7055905892c8123092.jpg","sc":7.9,"ver":"2D","rt":"2015-04-24上映","src":"","time":"","id":246210},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"横冲直撞好莱坞","preSale":0,"vd":"","dir":"蒂姆·肯德尔","star":"赵薇,黄晓明,佟大为","cat":"喜剧,动作,冒险","wish":64522,"3d":false,"pn":273,"scm":"逗比好基友，遇到坏导游","imax":false,"snum":282976,"showDate":"","dur":119,"img":"http://p1.meituan.net/165.220/movie/1c06c9055cb3990e76ee93c2c70789bb145247.jpg","sc":7.7,"ver":"2D/中国巨幕","rt":"2015-06-26上映","src":"","time":"","id":246178},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"既然青春留不住","preSale":0,"vd":"","dir":"田蒙","star":"张翰,陈乔恩,施予斐","cat":"喜剧,爱情","wish":78433,"3d":false,"pn":172,"scm":"校草撞菠菜，青春已不再","imax":false,"snum":83892,"showDate":"","dur":92,"img":"http://p0.meituan.net/165.220/movie/90422e83536df2153ecc57ff9703e99c1111187.jpg","sc":8.2,"ver":"2D","rt":"2015-10-23上映","src":"","time":"","id":246540},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"陪安东尼度过漫长岁月","preSale":0,"vd":"","dir":"秦小珍","star":"刘畅,白百何,唐艺昕","cat":"剧情,爱情","wish":120703,"3d":false,"pn":564,"scm":"文艺大暖男，不二常相伴","imax":false,"snum":78448,"showDate":"","dur":121,"img":"http://p1.meituan.net/165.220/movie/9f0d994eafbf52e7b0145b7746d1d881246635.jpg","sc":7.8,"ver":"2D","rt":"2015-11-13上映","src":"","time":"","id":246583},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"大唐玄奘","preSale":0,"vd":"","dir":"霍建起","star":"黄晓明,蒲巴甲,徐峥","cat":"剧情,历史","wish":23799,"3d":false,"pn":232,"scm":"了却世间恨，尽是取经人","imax":false,"snum":28601,"showDate":"","dur":120,"img":"http://p0.meituan.net/165.220/movie/c1cbba6359ef62bbb5bdd5311708c828413080.jpg","sc":7.7,"ver":"2D/中国巨幕","rt":"2016-04-29上映","src":"","time":"","id":338357},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"古田会议","preSale":1,"vd":"","dir":"谭晓明","star":"许铂岑,王韦智,释小龙","cat":"剧情","wish":82,"3d":false,"pn":1,"scm":"古田开大会，确立新指挥","imax":false,"snum":6,"showDate":"","dur":92,"img":"http://p1.meituan.net/165.220/movie/09f8c875a24c9916e7150ff7d7068b15622397.jpg","sc":0,"ver":"2D","rt":"本周五上映","src":"","time":"","id":367990},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"歼十出击","preSale":0,"vd":"","dir":"宁海强","star":"王斑,杨潇,张页川","cat":"剧情,动作","wish":0,"3d":false,"pn":108,"scm":"记录中国空军","imax":false,"snum":18,"showDate":"","dur":95,"img":"http://p0.meituan.net/165.220/movie/25/9538941.jpg","sc":4.2,"ver":"2D","rt":"2011-03-10上映","src":"","time":"","id":52305},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"四大名捕2","preSale":0,"vd":"","dir":"陈嘉上,秦小珍","star":"邓超,刘亦菲,邹兆龙","cat":"动作,科幻,武侠,古装","wish":6328,"3d":true,"pn":98,"scm":"听风又辨雨，料谁作玄虚","imax":false,"snum":7592,"showDate":"","dur":118,"img":"http://p1.meituan.net/165.220/movie/3c49afde82a16b2ea90f2de724d859c3165053.jpg","sc":7,"ver":"2D/3D","rt":"2013-12-06上映","src":"","time":"","id":74993},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"北京爱情故事","preSale":0,"vd":"","dir":"陈思诚","star":"梁家辉,刘嘉玲,王学兵","cat":"爱情","wish":12286,"3d":false,"pn":145,"scm":"恋爱分五段，激情不会散","imax":false,"snum":28698,"showDate":"","dur":121,"img":"http://p0.meituan.net/165.220/movie/__33456063__6265360.jpg","sc":8,"ver":"2D","rt":"2014-02-14上映","src":"","time":"","id":78324},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"深夜前的五分钟","preSale":0,"vd":"","dir":"行定勋","star":"三浦春马,刘诗诗,张孝全","cat":"爱情,悬疑","wish":9688,"3d":false,"pn":39,"scm":"双生花疑案，旅行遇船难","imax":false,"snum":7628,"showDate":"","dur":127,"img":"http://p0.meituan.net/165.220/movie/e399a912c8754e5dbab74191e39574b7112324.jpg","sc":6.3,"ver":"2D","rt":"2014-10-23上映","src":"","time":"","id":246004},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"匆匆那年","preSale":0,"vd":"","dir":"张一白","star":"彭于晏,倪妮,郑恺","cat":"剧情,爱情","wish":74203,"3d":false,"pn":378,"scm":"纯情已不再，青春忙堕胎","imax":false,"snum":371140,"showDate":"","dur":119,"img":"http://p0.meituan.net/165.220/movie/153d2a327b80b0e57ff6feb3e6aecebf585878.jpg","sc":8,"ver":"2D","rt":"2014-12-05上映","src":"","time":"","id":245980},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"狼图腾","preSale":0,"vd":"","dir":"让·雅克·阿诺","star":"草原狼,冯绍峰,窦骁","cat":"剧情,冒险","wish":47458,"3d":true,"pn":219,"scm":"离离原上草，见狼赶紧跑","imax":true,"snum":465164,"showDate":"","dur":121,"img":"http://p1.meituan.net/165.220/movie/6bc8b987bf621990ed4df4d7923bdbe9775157.jpg","sc":8.3,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"2015-02-19上映","src":"","time":"","id":78403},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"美人鱼之海盗来袭","preSale":0,"vd":"","dir":"邱浩强","star":"李思娴,郭亚维,宋磊","cat":"动画,喜剧,冒险","wish":27716,"3d":false,"pn":38,"scm":"国产美人鱼，俩海盗来袭","imax":false,"snum":21979,"showDate":"","dur":85,"img":"http://p0.meituan.net/165.220/movie/7e15953aa8bc5be4e7bff00913426a06920765.jpg","sc":5.5,"ver":"2D","rt":"2015-07-31上映","src":"","time":"","id":248550},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"泰迪熊之玩具大战","preSale":0,"vd":"","dir":"胡韵","star":"邓小婷,赵奔,白文显","cat":"动画,冒险,喜剧","wish":7076,"3d":false,"pn":8,"scm":"恶势力进攻，泰迪救萌宠","imax":false,"snum":6525,"showDate":"","dur":78,"img":"http://p1.meituan.net/165.220/movie/495971f9751f07a720373ad6008146e8575458.jpg","sc":7.3,"ver":"2D","rt":"2016-06-09上映","src":"","time":"","id":346658},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"记忆碎片","preSale":0,"vd":"","dir":"朴裕焕","star":"雷佳音,夏梓桐,李菁","cat":"悬疑,喜剧,犯罪","wish":4154,"3d":false,"pn":47,"scm":"宿醉陷疑案，奋力查嫌犯","imax":false,"snum":3009,"showDate":"","dur":94,"img":"http://p1.meituan.net/165.220/movie/ea15948d63d527d092a70e39cbb74636357253.jpg","sc":7,"ver":"2D","rt":"2016-06-03上映","src":"","time":"","id":345923},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"海洋之恋","preSale":0,"vd":"","dir":"郑淑兰","star":"韩在石,句号,王奎荣","cat":"科幻,爱情,奇幻","wish":2600,"3d":false,"pn":26,"scm":"凄美人鱼恋，海洋是家园","imax":false,"snum":979,"showDate":"","dur":96,"img":"http://p0.meituan.net/165.220/movie/0e3af6f723d8b7d4a3ea62efba49f535814826.jpg","sc":0,"ver":"2D","rt":"2016-06-17上映","src":"","time":"","id":341109}]}
     */

    private int status;
    /**
     * hasNext : false
     * movies : [{"late":false,"cnms":0,"sn":0,"showInfo":"今天149家影院放映1885场","nm":"惊天魔盗团2","preSale":0,"vd":"","dir":"朱浩伟","star":"杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森","cat":"动作,喜剧,惊悚","wish":513937,"3d":true,"pn":244,"scm":"周董变魔术，敌人挡不住","imax":false,"snum":100273,"showDate":"","dur":126,"img":"http://p1.meituan.net/165.220/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg","sc":8.6,"ver":"2D/3D/中国巨幕","rt":"2016-06-24上映","src":"","time":"","id":246333},{"late":false,"cnms":0,"sn":0,"showInfo":"今天148家影院放映1736场","nm":"独立日：卷土重来","preSale":0,"vd":"","dir":"罗兰·艾默里奇","star":"利亚姆·海姆斯沃斯,杰夫·高布伦,杰西·厄舍","cat":"动作,冒险,科幻","wish":164747,"3d":true,"pn":226,"scm":"外星再入侵，地核将燃尽","imax":true,"snum":77065,"showDate":"","dur":120,"img":"http://p1.meituan.net/165.220/movie/b408a9322cd0da51d4bdd3261ba3d1c0278866.jpg","sc":8.5,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-24上映","src":"","time":"","id":246375},{"late":false,"cnms":0,"sn":0,"showInfo":"今天145家影院放映795场","nm":"三人行","preSale":0,"vd":"","dir":"杜琪峰","star":"赵薇,古天乐,钟汉良","cat":"动作,犯罪,悬疑","wish":72910,"3d":false,"pn":190,"scm":"悍匪有手段，中枪再作案","imax":false,"snum":32496,"showDate":"","dur":88,"img":"http://p1.meituan.net/165.220/movie/1c82df174422d9374f821482f445171b129671.jpg","sc":6.9,"ver":"2D","rt":"2016-06-24上映","src":"","time":"","id":246972},{"late":false,"cnms":0,"sn":0,"showInfo":"今天128家影院放映495场","nm":"海底总动员2：多莉去哪儿","preSale":0,"vd":"","dir":"安德鲁·斯坦顿,安格斯·麦克莱恩","star":"艾伦·德詹尼丝,徐帆,艾伯特·布鲁克斯","cat":"动画,喜剧,冒险","wish":144949,"3d":true,"pn":123,"scm":"唠叨鱼多莉，上路找父母","imax":true,"snum":72953,"showDate":"","dur":97,"img":"http://p0.meituan.net/165.220/movie/4c20a91505854dcd0f9617069c5fec21843230.jpg","sc":8.7,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"2016-06-17上映","src":"","time":"","id":246366},{"late":false,"cnms":0,"sn":0,"showInfo":"今天119家影院放映391场","nm":"魔兽","preSale":0,"vd":"","dir":"邓肯·琼斯","star":"崔维斯·费米尔,宝拉·巴顿,本·福斯特","cat":"动作,冒险,奇幻","wish":479894,"3d":true,"pn":347,"scm":"怒拳为谁握，联盟斗部落","imax":true,"snum":754647,"showDate":"","dur":124,"img":"http://p0.meituan.net/165.220/movie/f94e67dcd5b48f034bd7adc892f1b20695854.jpeg","sc":9.2,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-08上映","src":"","time":"","id":78421},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-08 下周五上映","nm":"致青春·原来你还在这里","preSale":1,"vd":"","dir":"周拓如","star":"吴亦凡,刘亦菲,金世佳","cat":"剧情,爱情","wish":209771,"3d":false,"pn":342,"scm":"霸道男学霸，爱上女学渣","imax":false,"snum":4964,"showDate":"","dur":98,"img":"http://p0.meituan.net/165.220/movie/37e7ed909843270f0880c325b6be7b60436509.jpg","sc":0,"ver":"2D","rt":"下周五上映","src":"","time":"","id":246575},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-02 本周六上映","nm":"忍者神龟2：破影而出","preSale":1,"vd":"","dir":"戴夫·格林","star":"梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","cat":"动作,冒险,喜剧","wish":266319,"3d":true,"pn":266,"scm":"儿时忍者龟，犀牛也回归","imax":true,"snum":1717,"showDate":"","dur":113,"img":"http://p1.meituan.net/165.220/movie/a375ebfcdc2a396423c368b943813b01179997.jpg","sc":0,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"本周六上映","src":"","time":"","id":13511},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-06-30 本周四上映","nm":"所以\u2026\u2026和黑粉结婚了","preSale":1,"vd":"","dir":"金帝荣","star":"朴灿烈,袁姗姗,姜潮","cat":"爱情,喜剧","wish":93731,"3d":false,"pn":121,"scm":"黑粉变新娘，这是闹哪样","imax":false,"snum":7748,"showDate":"","dur":99,"img":"http://p1.meituan.net/165.220/movie/79311d41bea3be8f1677c0b69a5868fa289298.jpg","sc":0,"ver":"2D","rt":"本周四上映","src":"","time":"","id":343379},{"late":false,"cnms":0,"sn":0,"showInfo":"今天62家影院放映118场","nm":"X战警：天启","preSale":0,"vd":"","dir":"布莱恩·辛格","star":"詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","cat":"动作,科幻,奇幻","wish":559919,"3d":true,"pn":692,"scm":"天启当炮灰，千年洗剪吹","imax":true,"snum":460717,"showDate":"","dur":144,"img":"http://p1.meituan.net/165.220/movie/ba2386a26648c1875e3fc780950f0ae0144875.jpg","sc":9.1,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-03上映","src":"","time":"","id":246177},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"赏金猎人","preSale":1,"vd":"","dir":"申太罗","star":"李敏镐,钟汉良,唐嫣","cat":"动作,喜剧,剧情,悬疑","wish":128582,"3d":true,"pn":196,"scm":"编外铁饭碗，收钱抓逃犯","imax":false,"snum":4537,"showDate":"","dur":105,"img":"http://p1.meituan.net/165.220/movie/45f8587cb425a01c9a279082772a8bd0379497.jpg","sc":0,"ver":"2D/3D","rt":"本周五上映","src":"","time":"","id":338506},{"late":false,"cnms":0,"sn":0,"showInfo":"今天40家影院放映71场","nm":"近在咫尺的爱恋","preSale":0,"vd":"","dir":"程孝泽","star":"彭于晏,郭采洁,杨子姗","cat":"爱情,运动,剧情","wish":12564,"3d":false,"pn":49,"scm":"真爱在身旁，挥拳为理想","imax":false,"snum":789,"showDate":"","dur":103,"img":"http://p1.meituan.net/165.220/movie/a70da0aefb82886efbae696181e966ff620085.jpg","sc":7.4,"ver":"2D","rt":"2016-06-24上映","src":"","time":"","id":57217},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-08 下周五上映","nm":"大鱼海棠","preSale":1,"vd":"","dir":"梁旋,张春","star":"","cat":"动画,奇幻,剧情","wish":136962,"3d":true,"pn":113,"scm":"北冥有鱼鲲，海棠掌乾坤","imax":false,"snum":3164,"showDate":"","dur":105,"img":"http://p1.meituan.net/165.220/movie/a607ab45bcc9e8486328b39bff0132a4420994.jpg","sc":0,"ver":"3D","rt":"下周五上映","src":"","time":"","id":246591},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"魔轮","preSale":1,"vd":"","dir":"王早","star":"林心如,何润东,金世佳","cat":"悬疑,惊悚","wish":9392,"3d":true,"pn":55,"scm":"天涯号起航，失控大逃亡","imax":false,"snum":488,"showDate":"","dur":92,"img":"http://p0.meituan.net/165.220/movie/e5ff984a4bec976fb1ed429e7fe4f61c605233.jpg","sc":0,"ver":"2D/3D","rt":"本周五上映","src":"","time":"","id":342741},{"late":false,"cnms":0,"sn":0,"showInfo":"今天16家影院放映19场","nm":"大火种","preSale":0,"vd":"","dir":"苗月","star":"白威,陈瑾,王柠","cat":"剧情,历史,战争","wish":458,"3d":false,"pn":4,"scm":"誓死护宣言，智斗救火种","imax":false,"snum":53,"showDate":"","dur":114,"img":"http://p1.meituan.net/165.220/movie/c8545c7bd1f6070f0d52a77bd0ddd8738720802.jpg","sc":0,"ver":"2D","rt":"本周一上映","src":"","time":"","id":337438},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-02 本周六上映","nm":"丑小鸭历险记","preSale":1,"vd":"","dir":"郑义","star":"朱可可,阿飞,夏倚轩","cat":"动画,科幻,冒险","wish":3399,"3d":false,"pn":66,"scm":"月球机器鸭，化身小飞鸭","imax":false,"snum":111,"showDate":"","dur":81,"img":"http://p1.meituan.net/165.220/movie/0484f8ad5aec878112f1a04715d32b4e360620.jpg","sc":0,"ver":"2D","rt":"本周六上映","src":"","time":"","id":368271},{"late":false,"cnms":0,"sn":0,"showInfo":"今天13家影院放映15场","nm":"筷仙","preSale":0,"vd":"","dir":"姬雨","star":"胡影怡,朱璇,周骏","cat":"恐怖,惊悚","wish":20373,"3d":false,"pn":32,"scm":"笔仙已玩坏，筷仙来作怪","imax":false,"snum":13090,"showDate":"","dur":87,"img":"http://p1.meituan.net/165.220/movie/31a325f0796cfebbab24776024eeec91100800.jpeg","sc":4.5,"ver":"2D","rt":"2016-06-17上映","src":"","time":"","id":343597},{"late":false,"cnms":0,"sn":0,"showInfo":"今天8家影院放映14场","nm":"南口1937","preSale":0,"vd":"","dir":"朱丹","star":"孙看,孙洪涛,刘忠元","cat":"剧情,历史,战争","wish":2034,"3d":false,"pn":26,"scm":"南口抗日战，疆场洒血汗","imax":false,"snum":117,"showDate":"","dur":98,"img":"http://p1.meituan.net/165.220/movie/f4b5d903571ec3c3fc952f86261b04fd227842.jpg","sc":0,"ver":"2D","rt":"2016-06-23上映","src":"","time":"","id":368071},{"late":false,"cnms":0,"sn":0,"showInfo":"今天10家影院放映13场","nm":"百鸟朝凤","preSale":0,"vd":"","dir":"吴天明","star":"陶泽如,郑伟,李岷城","cat":"历史,音乐,剧情","wish":3981,"3d":false,"pn":105,"scm":"两代手艺人，唢呐感情深","imax":false,"snum":109885,"showDate":"","dur":108,"img":"http://p1.meituan.net/165.220/movie/ef968fd382173c45b0c2d10572ca10b8354896.jpg","sc":9.2,"ver":"2D","rt":"2016-05-06上映","src":"","time":"","id":248260},{"late":false,"cnms":0,"sn":0,"showInfo":"今天7家影院放映8场","nm":"北京遇上西雅图之不二情书","preSale":0,"vd":"","dir":"薛晓路","star":"汤唯,吴秀波,惠英红","cat":"爱情,剧情","wish":307383,"3d":false,"pn":547,"scm":"异国心未远，书信寄情缘","imax":false,"snum":449744,"showDate":"","dur":132,"img":"http://p1.meituan.net/165.220/movie/fe80bc40822d0a5f3168b73089c47d71101133.jpg","sc":8.5,"ver":"2D/中国巨幕","rt":"2016-04-29上映","src":"","time":"","id":247575},{"late":false,"cnms":0,"sn":0,"showInfo":"今天5家影院放映8场","nm":"愤怒的小鸟","preSale":0,"vd":"","dir":"费格尔·雷利,克莱·凯蒂","star":"杰森·苏戴奇斯,乔什·盖德,丹尼·麦克布耐德","cat":"动画,奇幻,冒险","wish":123045,"3d":true,"pn":132,"scm":"绿猪来偷蛋，鸟儿群奋战","imax":false,"snum":324884,"showDate":"","dur":97,"img":"http://p0.meituan.net/165.220/movie/b721e73740601799c065affb619d837e151622.jpg","sc":8.9,"ver":"3D/中国巨幕","rt":"2016-05-20上映","src":"","time":"","id":246188},{"late":false,"cnms":0,"sn":0,"showInfo":"今天4家影院放映7场","nm":"我叫MT之山口山战记","preSale":0,"vd":"","dir":"核桃","star":"奶茶,YOYO,CBI","cat":"动画,喜剧,冒险","wish":43480,"3d":true,"pn":40,"scm":"贱萌牛头人，爆笑刷副本","imax":false,"snum":20344,"showDate":"","dur":86,"img":"http://p0.meituan.net/165.220/movie/73f6abbb90259d1fa8a88fa907ca3062587352.jpg","sc":7.7,"ver":"3D/中国巨幕","rt":"2016-06-17上映","src":"","time":"","id":246045},{"late":false,"cnms":0,"sn":0,"showInfo":"今天2家影院放映6场","nm":"白毛女","preSale":0,"vd":"","dir":"侯克明","star":"雷佳,张英席,高鹏","cat":"剧情","wish":1038,"3d":true,"pn":80,"scm":"欠了高利贷，卖女来抵债","imax":false,"snum":1201,"showDate":"","dur":122,"img":"http://p1.meituan.net/165.220/movie/a8dc71209614a071cbc56cfc193b9d9785810.jpg","sc":8.9,"ver":"3D/中国巨幕","rt":"2016-03-25上映","src":"","time":"","id":345102},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"党的女儿尹灵芝","preSale":1,"vd":"","dir":"卫晓茼","star":"郜耀平,张志宏,姚安濂","cat":"剧情,历史,战争","wish":702,"3d":false,"pn":18,"scm":"心中有信仰，少年变战士","imax":false,"snum":166,"showDate":"","dur":107,"img":"http://p0.meituan.net/165.220/movie/1dffa0a076e3514234f2029d6b29b3da157605.jpg","sc":0,"ver":"2D","rt":"本周五上映","src":"","time":"","id":672123},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-08 下周五上映","nm":"摇滚藏獒","preSale":1,"vd":"","dir":"艾什·布兰农","star":"冯小刚,郭德纲,郭麒麟","cat":"动画,音乐,家庭","wish":13844,"3d":true,"pn":128,"scm":"郑钧写剧本，混搭玩摇滚","imax":false,"snum":156,"showDate":"","dur":93,"img":"http://p1.meituan.net/165.220/movie/9d32c2703351b3e1cb456a05b207550e210596.jpg","sc":0,"ver":"2D/3D/中国巨幕","rt":"下周五上映","src":"","time":"","id":247244},{"late":false,"cnms":0,"sn":0,"showInfo":"今天2家影院放映2场","nm":"叶问2：宗师传奇","preSale":0,"vd":"","dir":"叶伟信","star":"甄子丹,洪金宝,熊黛林","cat":"动作,历史,传记","wish":211,"3d":false,"pn":63,"scm":"叶问搬香港，仇恨值渐长","imax":false,"snum":1040,"showDate":"","dur":108,"img":"http://p0.meituan.net/165.220/movie/70/612717.jpg","sc":8.5,"ver":"2D","rt":"2010-04-27上映","src":"","time":"","id":524},{"late":false,"cnms":0,"sn":0,"showInfo":"今天2家影院放映2场","nm":"警察故事2013","preSale":0,"vd":"","dir":"丁晟","star":"刘烨,成龙,景甜","cat":"剧情,动作,犯罪","wish":14520,"3d":true,"pn":44,"scm":"女儿乱交友，老爹来出头","imax":true,"snum":19140,"showDate":"","dur":108,"img":"http://p0.meituan.net/165.220/movie/__29561506__7176229.jpg","sc":8,"ver":"2D/IMAX 3D","rt":"2013-12-24上映","src":"","time":"","id":77192},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"终极胜利","preSale":1,"vd":"","dir":"冼杞然","star":"窦骁,约瑟夫·费因斯,娄宇健","cat":"剧情,战争,历史","wish":4895,"3d":false,"pn":122,"scm":"辛德勒名单，推出中国版","imax":false,"snum":68,"showDate":"","dur":108,"img":"http://p1.meituan.net/165.220/movie/0c93b5987ae440497dd985982aad1c4b233221.jpg","sc":0,"ver":"2D","rt":"本周五上映","src":"","time":"","id":341123},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"大电影2.0之两个傻瓜的荒唐事","preSale":0,"vd":"","dir":"阿甘","star":"郭涛,刘心悠,李灿森","cat":"喜剧","wish":107,"3d":false,"pn":3,"scm":"姚晨王宝强，爆笑太疯狂","imax":false,"snum":94,"showDate":"","dur":97,"img":"http://p1.meituan.net/165.220/movie/76ac89dad45ac25c676246dc92794b03318660.png","sc":5.6,"ver":"2D","rt":"2007-12-21上映","src":"","time":"","id":536},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"人再囧途之泰囧","preSale":0,"vd":"","dir":"徐峥","star":"徐峥,王宝强,黄渤","cat":"喜剧","wish":22543,"3d":false,"pn":232,"scm":"囧途太荒唐，甩不掉宝强","imax":false,"snum":15645,"showDate":"","dur":105,"img":"http://p0.meituan.net/165.220/movie/38/280521.jpg","sc":8.8,"ver":"2D","rt":"2012-12-12上映","src":"","time":"","id":938},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"厨子戏子痞子","preSale":0,"vd":"","dir":"管虎","star":"黄渤,刘烨,张涵予","cat":"剧情,喜剧,动作","wish":10110,"3d":false,"pn":281,"scm":"三男一台戏，客栈解僵局","imax":false,"snum":5766,"showDate":"","dur":108,"img":"http://p0.meituan.net/165.220/movie/__7277442__6071578.jpg","sc":8.3,"ver":"2D","rt":"2013-03-29上映","src":"","time":"","id":969},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"人间蒸发","preSale":0,"vd":"","dir":"梁德森","star":"钟丽缇,柳岩,李灿森","cat":"悬疑,惊悚","wish":771,"3d":false,"pn":22,"scm":"惊心动魄的寻亲之旅","imax":false,"snum":182,"showDate":"","dur":90,"img":"http://p0.meituan.net/165.220/movie/__6680785__5992311.jpg","sc":7,"ver":"2D","rt":"2013-04-28上映","src":"","time":"","id":78094},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"宫锁沉香","preSale":0,"vd":"","dir":"潘安子","star":"周冬雨,陈晓,赵丽颖","cat":"剧情,爱情,古装","wish":3080,"3d":false,"pn":36,"scm":"唯美清宫戏，阴谋不见底","imax":false,"snum":2509,"showDate":"","dur":115,"img":"http://p0.meituan.net/165.220/movie/__18327195__7709930.jpg","sc":7.8,"ver":"2D","rt":"2013-08-13上映","src":"","time":"","id":78176},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"同桌的你","preSale":0,"vd":"","dir":"郭帆","star":"周冬雨,林更新,隋凯","cat":"剧情,爱情","wish":24267,"3d":false,"pn":88,"scm":"初恋要结婚，回国忆青春","imax":false,"snum":73072,"showDate":"","dur":98,"img":"http://p0.meituan.net/165.220/movie/__40811615__5745234.jpg","sc":8.6,"ver":"2D","rt":"2014-04-25上映","src":"","time":"","id":78653},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"催眠大师","preSale":0,"vd":"","dir":"陈正道","star":"徐峥,莫文蔚,吕中","cat":"剧情,悬疑,惊悚","wish":11396,"3d":false,"pn":78,"scm":"催眠治心伤，侧击猜真相","imax":false,"snum":46563,"showDate":"","dur":102,"img":"http://p1.meituan.net/165.220/movie/__39582689__9041214.jpg","sc":9,"ver":"2D","rt":"2014-04-29上映","src":"","time":"","id":78672},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"京城81号","preSale":0,"vd":"","dir":"叶伟民","star":"吴镇宇,林心如,杨祐宁","cat":"剧情,悬疑,恐怖","wish":36817,"3d":true,"pn":98,"scm":"古宅阴森处，少妇诉凄苦","imax":false,"snum":122415,"showDate":"","dur":90,"img":"http://p1.meituan.net/165.220/movie/__48584093__3431812.jpg","sc":5,"ver":"2D/3D","rt":"2014-07-18上映","src":"","time":"","id":78267},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"闺蜜","preSale":0,"vd":"","dir":"黄真真","star":"陈意涵,薛凯琪,杨子姗","cat":"剧情,爱情","wish":30224,"3d":false,"pn":203,"scm":"永远好闺蜜，渣男靠边去","imax":false,"snum":68775,"showDate":"","dur":118,"img":"http://p1.meituan.net/165.220/movie/__48665963__9098444.jpg","sc":8,"ver":"2D","rt":"2014-07-30上映","src":"","time":"","id":78772},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"奔跑吧！兄弟","preSale":0,"vd":"","dir":"胡笳,岑俊义","star":"杨颖,王宝强,李晨","cat":"喜剧,冒险,动作","wish":49025,"3d":false,"pn":118,"scm":"跑男再出发，集体游三亚","imax":false,"snum":361183,"showDate":"","dur":88,"img":"http://p0.meituan.net/165.220/movie/85f0d9a604e26be5523a848269346697296774.jpg","sc":4.7,"ver":"2D","rt":"2015-01-30上映","src":"","time":"","id":246316},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"左耳","preSale":0,"vd":"","dir":"苏有朋","star":"陈都灵,欧豪,杨洋","cat":"爱情","wish":82116,"3d":false,"pn":315,"scm":"青春要恋爱，别忘堕堕胎","imax":false,"snum":538351,"showDate":"","dur":117,"img":"http://p0.meituan.net/165.220/movie/da55f29972ec0b9692ba7055905892c8123092.jpg","sc":7.9,"ver":"2D","rt":"2015-04-24上映","src":"","time":"","id":246210},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"横冲直撞好莱坞","preSale":0,"vd":"","dir":"蒂姆·肯德尔","star":"赵薇,黄晓明,佟大为","cat":"喜剧,动作,冒险","wish":64522,"3d":false,"pn":273,"scm":"逗比好基友，遇到坏导游","imax":false,"snum":282976,"showDate":"","dur":119,"img":"http://p1.meituan.net/165.220/movie/1c06c9055cb3990e76ee93c2c70789bb145247.jpg","sc":7.7,"ver":"2D/中国巨幕","rt":"2015-06-26上映","src":"","time":"","id":246178},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"既然青春留不住","preSale":0,"vd":"","dir":"田蒙","star":"张翰,陈乔恩,施予斐","cat":"喜剧,爱情","wish":78433,"3d":false,"pn":172,"scm":"校草撞菠菜，青春已不再","imax":false,"snum":83892,"showDate":"","dur":92,"img":"http://p0.meituan.net/165.220/movie/90422e83536df2153ecc57ff9703e99c1111187.jpg","sc":8.2,"ver":"2D","rt":"2015-10-23上映","src":"","time":"","id":246540},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"陪安东尼度过漫长岁月","preSale":0,"vd":"","dir":"秦小珍","star":"刘畅,白百何,唐艺昕","cat":"剧情,爱情","wish":120703,"3d":false,"pn":564,"scm":"文艺大暖男，不二常相伴","imax":false,"snum":78448,"showDate":"","dur":121,"img":"http://p1.meituan.net/165.220/movie/9f0d994eafbf52e7b0145b7746d1d881246635.jpg","sc":7.8,"ver":"2D","rt":"2015-11-13上映","src":"","time":"","id":246583},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"大唐玄奘","preSale":0,"vd":"","dir":"霍建起","star":"黄晓明,蒲巴甲,徐峥","cat":"剧情,历史","wish":23799,"3d":false,"pn":232,"scm":"了却世间恨，尽是取经人","imax":false,"snum":28601,"showDate":"","dur":120,"img":"http://p0.meituan.net/165.220/movie/c1cbba6359ef62bbb5bdd5311708c828413080.jpg","sc":7.7,"ver":"2D/中国巨幕","rt":"2016-04-29上映","src":"","time":"","id":338357},{"late":false,"cnms":0,"sn":0,"showInfo":"2016-07-01 本周五上映","nm":"古田会议","preSale":1,"vd":"","dir":"谭晓明","star":"许铂岑,王韦智,释小龙","cat":"剧情","wish":82,"3d":false,"pn":1,"scm":"古田开大会，确立新指挥","imax":false,"snum":6,"showDate":"","dur":92,"img":"http://p1.meituan.net/165.220/movie/09f8c875a24c9916e7150ff7d7068b15622397.jpg","sc":0,"ver":"2D","rt":"本周五上映","src":"","time":"","id":367990},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"歼十出击","preSale":0,"vd":"","dir":"宁海强","star":"王斑,杨潇,张页川","cat":"剧情,动作","wish":0,"3d":false,"pn":108,"scm":"记录中国空军","imax":false,"snum":18,"showDate":"","dur":95,"img":"http://p0.meituan.net/165.220/movie/25/9538941.jpg","sc":4.2,"ver":"2D","rt":"2011-03-10上映","src":"","time":"","id":52305},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"四大名捕2","preSale":0,"vd":"","dir":"陈嘉上,秦小珍","star":"邓超,刘亦菲,邹兆龙","cat":"动作,科幻,武侠,古装","wish":6328,"3d":true,"pn":98,"scm":"听风又辨雨，料谁作玄虚","imax":false,"snum":7592,"showDate":"","dur":118,"img":"http://p1.meituan.net/165.220/movie/3c49afde82a16b2ea90f2de724d859c3165053.jpg","sc":7,"ver":"2D/3D","rt":"2013-12-06上映","src":"","time":"","id":74993},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"北京爱情故事","preSale":0,"vd":"","dir":"陈思诚","star":"梁家辉,刘嘉玲,王学兵","cat":"爱情","wish":12286,"3d":false,"pn":145,"scm":"恋爱分五段，激情不会散","imax":false,"snum":28698,"showDate":"","dur":121,"img":"http://p0.meituan.net/165.220/movie/__33456063__6265360.jpg","sc":8,"ver":"2D","rt":"2014-02-14上映","src":"","time":"","id":78324},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"深夜前的五分钟","preSale":0,"vd":"","dir":"行定勋","star":"三浦春马,刘诗诗,张孝全","cat":"爱情,悬疑","wish":9688,"3d":false,"pn":39,"scm":"双生花疑案，旅行遇船难","imax":false,"snum":7628,"showDate":"","dur":127,"img":"http://p0.meituan.net/165.220/movie/e399a912c8754e5dbab74191e39574b7112324.jpg","sc":6.3,"ver":"2D","rt":"2014-10-23上映","src":"","time":"","id":246004},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"匆匆那年","preSale":0,"vd":"","dir":"张一白","star":"彭于晏,倪妮,郑恺","cat":"剧情,爱情","wish":74203,"3d":false,"pn":378,"scm":"纯情已不再，青春忙堕胎","imax":false,"snum":371140,"showDate":"","dur":119,"img":"http://p0.meituan.net/165.220/movie/153d2a327b80b0e57ff6feb3e6aecebf585878.jpg","sc":8,"ver":"2D","rt":"2014-12-05上映","src":"","time":"","id":245980},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"狼图腾","preSale":0,"vd":"","dir":"让·雅克·阿诺","star":"草原狼,冯绍峰,窦骁","cat":"剧情,冒险","wish":47458,"3d":true,"pn":219,"scm":"离离原上草，见狼赶紧跑","imax":true,"snum":465164,"showDate":"","dur":121,"img":"http://p1.meituan.net/165.220/movie/6bc8b987bf621990ed4df4d7923bdbe9775157.jpg","sc":8.3,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"2015-02-19上映","src":"","time":"","id":78403},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"美人鱼之海盗来袭","preSale":0,"vd":"","dir":"邱浩强","star":"李思娴,郭亚维,宋磊","cat":"动画,喜剧,冒险","wish":27716,"3d":false,"pn":38,"scm":"国产美人鱼，俩海盗来袭","imax":false,"snum":21979,"showDate":"","dur":85,"img":"http://p0.meituan.net/165.220/movie/7e15953aa8bc5be4e7bff00913426a06920765.jpg","sc":5.5,"ver":"2D","rt":"2015-07-31上映","src":"","time":"","id":248550},{"late":false,"cnms":0,"sn":0,"showInfo":"今天暂无场次","nm":"泰迪熊之玩具大战","preSale":0,"vd":"","dir":"胡韵","star":"邓小婷,赵奔,白文显","cat":"动画,冒险,喜剧","wish":7076,"3d":false,"pn":8,"scm":"恶势力进攻，泰迪救萌宠","imax":false,"snum":6525,"showDate":"","dur":78,"img":"http://p1.meituan.net/165.220/movie/495971f9751f07a720373ad6008146e8575458.jpg","sc":7.3,"ver":"2D","rt":"2016-06-09上映","src":"","time":"","id":346658},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"记忆碎片","preSale":0,"vd":"","dir":"朴裕焕","star":"雷佳音,夏梓桐,李菁","cat":"悬疑,喜剧,犯罪","wish":4154,"3d":false,"pn":47,"scm":"宿醉陷疑案，奋力查嫌犯","imax":false,"snum":3009,"showDate":"","dur":94,"img":"http://p1.meituan.net/165.220/movie/ea15948d63d527d092a70e39cbb74636357253.jpg","sc":7,"ver":"2D","rt":"2016-06-03上映","src":"","time":"","id":345923},{"late":false,"cnms":0,"sn":0,"showInfo":"今天1家影院放映1场","nm":"海洋之恋","preSale":0,"vd":"","dir":"郑淑兰","star":"韩在石,句号,王奎荣","cat":"科幻,爱情,奇幻","wish":2600,"3d":false,"pn":26,"scm":"凄美人鱼恋，海洋是家园","imax":false,"snum":979,"showDate":"","dur":96,"img":"http://p0.meituan.net/165.220/movie/0e3af6f723d8b7d4a3ea62efba49f535814826.jpg","sc":0,"ver":"2D","rt":"2016-06-17上映","src":"","time":"","id":341109}]
     */

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
        private boolean hasNext;
        /**
         * late : false
         * cnms : 0
         * sn : 0
         * showInfo : 今天149家影院放映1885场
         * nm : 惊天魔盗团2
         * preSale : 0
         * vd :
         * dir : 朱浩伟
         * star : 杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森
         * cat : 动作,喜剧,惊悚
         * wish : 513937
         * 3d : true
         * pn : 244
         * scm : 周董变魔术，敌人挡不住
         * imax : false
         * snum : 100273
         * showDate :
         * dur : 126
         * img : http://p1.meituan.net/165.220/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg
         * sc : 8.6
         * ver : 2D/3D/中国巨幕
         * rt : 2016-06-24上映
         * src :
         * time :
         * id : 246333
         */

        private List<MoviesBean> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean implements Serializable{
            private boolean late;
            private int cnms;
            private int sn;
            private String showInfo;
            private String nm;
            private int preSale;
            private String vd;
            private String dir;
            private String star;
            private String cat;
            private int wish;
            @SerializedName("3d")
            private boolean value3d;
            private int pn;
            private String scm;
            private boolean imax;
            private int snum;
            private String showDate;
            private int dur;
            private String img;
            private double sc;
            private String ver;
            private String rt;
            private String src;
            private String time;
            private int id;

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }

            public boolean isValue3d() {
                return value3d;
            }

            public void setValue3d(boolean value3d) {
                this.value3d = value3d;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }


//    /**
//     * coming : []
//     * hot : [{"cat":"动作,喜剧,惊悚","civilPubSt":0,"desc":"主演:杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森","dir":"朱浩伟","dur":126,"effectShowNum":0,"fra":"美国,韩国","frt":"2016-06-10,2016-07-14","globalReleased":true,"headLineShow":false,"headLines":[],"headLinesVO":[{"movieId":246333,"title":"首周2.8亿夺冠，已超首部内地总票房","type":"票房","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12414"},{"movieId":246333,"title":"专访导演：\u201c天眼\u201d可能是真的","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12404"}],"id":246333,"img":"http://p1.meituan.net/w.h/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg","late":false,"localPubSt":0,"mk":8.6,"newsHeadlines":[{"movieId":246333,"title":"首周2.8亿夺冠，已超首部内地总票房","type":"票房","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12414"},{"movieId":246333,"title":"专访导演：\u201c天眼\u201d可能是真的","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12404"}],"nm":"惊天魔盗团2","pn":244,"preSale":0,"preShow":false,"pubDate":1466697600000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-24","sc":8.6,"scm":"周董变魔术，敌人挡不住","showInfo":"今天149家影院放映1885场","showNum":1885,"showst":3,"snum":100273,"star":"杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森","totalShowNum":3351,"ver":"2D/3D/中国巨幕","videoId":46617,"videoName":"侠盗版预告片","videourl":"http://v.meituan.net/movie/videos/d47aa4104383436f84dba9b65f3d8aab.mp4","vnum":27,"weight":1,"wish":513937,"wishst":0},{"cat":"动作,冒险,科幻","civilPubSt":0,"desc":"主演:利亚姆·海姆斯沃斯,杰夫·高布伦,杰西·厄舍","dir":"罗兰·艾默里奇","dur":120,"effectShowNum":0,"fra":"美国,韩国","frt":"2016-06-24,2016-06-22","globalReleased":true,"headLineShow":false,"id":246375,"img":"http://p1.meituan.net/w.h/movie/b408a9322cd0da51d4bdd3261ba3d1c0278866.jpg","late":false,"localPubSt":0,"mk":8.5,"nm":"独立日：卷土重来","pn":226,"preSale":0,"preShow":false,"pubDate":1466697600000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-24","sc":8.5,"scm":"外星再入侵，地核将燃尽","showInfo":"今天148家影院放映1736场","showNum":1736,"showst":3,"snum":77065,"star":"利亚姆·海姆斯沃斯,杰夫·高布伦,杰西·厄舍","totalShowNum":3094,"ver":"3D/IMAX 3D/中国巨幕/全景声","videoId":45961,"videoName":"超级版预告片","videourl":"http://v.meituan.net/movie/videos/54b5ef29210142259b85810ceaa8a5a0.mp4","vnum":54,"weight":1,"wish":164747,"wishst":0},{"cat":"动作,犯罪,悬疑","civilPubSt":0,"desc":"主演:赵薇,古天乐,钟汉良","dir":"杜琪峰","dur":88,"effectShowNum":0,"globalReleased":true,"headLineShow":false,"id":246972,"img":"http://p1.meituan.net/w.h/movie/1c82df174422d9374f821482f445171b129671.jpg","late":false,"localPubSt":0,"mk":6.9,"nm":"三人行","pn":190,"preSale":0,"preShow":false,"pubDate":1466697600000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-24","sc":6.9,"scm":"悍匪有手段，中枪再作案","showInfo":"今天145家影院放映795场","showNum":795,"showst":3,"snum":32496,"star":"赵薇,古天乐,钟汉良","totalShowNum":1325,"ver":"2D","videoId":46531,"videoName":"终极版预告片","videourl":"http://v.meituan.net/movie/videos/332b704884a1477ea749ceda2246a173.mp4","vnum":15,"weight":1,"wish":72910,"wishst":0},{"cat":"动画,喜剧,冒险","civilPubSt":0,"desc":"主演:艾伦·德詹尼丝,徐帆,艾伯特·布鲁克斯","dir":"安德鲁·斯坦顿,安格斯·麦克莱恩","dur":97,"effectShowNum":0,"fra":"美国,韩国","frt":"2016-06-17,2016-07-07","globalReleased":true,"headLineShow":false,"id":246366,"img":"http://p0.meituan.net/w.h/movie/4c20a91505854dcd0f9617069c5fec21843230.jpg","late":false,"localPubSt":0,"mk":8.7,"nm":"海底总动员2：多莉去哪儿","pn":123,"preSale":0,"preShow":false,"pubDate":1466092800000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-17","sc":8.7,"scm":"唠叨鱼多莉，上路找父母","showInfo":"今天128家影院放映495场","showNum":495,"showst":3,"snum":72953,"star":"艾伦·德詹尼丝,徐帆,艾伯特·布鲁克斯","totalShowNum":785,"ver":"2D/3D/IMAX 3D/中国巨幕","videoId":46088,"videoName":"国外版预告片","videourl":"http://v.meituan.net/movie/videos/4660747457784682a2ae11931deead64.mp4","vnum":33,"weight":1,"wish":144949,"wishst":0},{"cat":"动作,冒险,奇幻","civilPubSt":0,"desc":"主演:崔维斯·费米尔,宝拉·巴顿,本·福斯特","dir":"邓肯·琼斯","dur":124,"effectShowNum":0,"fra":"美国,韩国","frt":"2016-06-10,2016-06-09","globalReleased":true,"headLineShow":false,"id":78421,"img":"http://p0.meituan.net/w.h/movie/f94e67dcd5b48f034bd7adc892f1b20695854.jpeg","late":false,"localPubSt":0,"mk":9.2,"nm":"魔兽","pn":347,"preSale":0,"preShow":false,"pubDate":1465315200000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-08","sc":9.2,"scm":"怒拳为谁握，联盟斗部落","showInfo":"今天119家影院放映391场","showNum":391,"showst":3,"snum":754647,"star":"崔维斯·费米尔,宝拉·巴顿,本·福斯特","totalShowNum":686,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","videoId":44969,"videoName":"中文版终极预告片","videourl":"http://v.meituan.net/movie/videos/17ee6973027343e9be7fbe71bed28637.mp4","vnum":64,"weight":1,"wish":479894,"wishst":0},{"cat":"剧情,爱情","civilPubSt":0,"desc":"主演:吴亦凡,刘亦菲,金世佳","dir":"周拓如","dur":98,"effectShowNum":347,"globalReleased":false,"headLineShow":false,"id":246575,"img":"http://p0.meituan.net/w.h/movie/37e7ed909843270f0880c325b6be7b60436509.jpg","late":false,"localPubSt":0,"mk":0,"nm":"致青春·原来你还在这里","pn":342,"preSale":1,"preShow":false,"pubDate":1467907200000,"pubShowNum":347,"recentShowDate":1467820800000,"recentShowNum":4,"rt":"2016-07-08","sc":0,"scm":"霸道男学霸，爱上女学渣","showInfo":"2016-07-08 下周五上映","showNum":0,"showst":4,"snum":4969,"star":"吴亦凡,刘亦菲,金世佳","totalShowNum":416,"ver":"2D","videoId":46347,"videoName":"先导预告片","videourl":"http://v.meituan.net/movie/videos/854x480782fcdcaae134019b1c0a2e37a72078e.mp4","vnum":4,"weight":1,"wish":209818,"wishst":0},{"cat":"动作,冒险,喜剧","civilPubSt":0,"desc":"主演:梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","dir":"戴夫·格林","dur":113,"effectShowNum":331,"fra":"美国,韩国","frt":"2016-06-03,2016-06-16","globalReleased":false,"headLineShow":false,"id":13511,"img":"http://p1.meituan.net/w.h/movie/a375ebfcdc2a396423c368b943813b01179997.jpg","late":false,"localPubSt":0,"mk":0,"nm":"忍者神龟2：破影而出","pn":266,"preSale":1,"preShow":false,"pubDate":1467388800000,"pubShowNum":331,"recentShowDate":1467302400000,"recentShowNum":62,"rt":"2016-07-02","sc":0,"scm":"儿时忍者龟，犀牛也回归","showInfo":"2016-07-02 本周六上映","showNum":0,"showst":4,"snum":1719,"star":"梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","totalShowNum":567,"ver":"2D/3D/IMAX 3D/中国巨幕","videoId":45882,"videoName":"中文定档版预告片","videourl":"http://v.meituan.net/movie/videos/854x480c3ab0a78ce994a7494c4a01760da799f.mp4","vnum":87,"weight":1,"wish":266339,"wishst":0},{"cat":"爱情,喜剧","civilPubSt":0,"desc":"主演:朴灿烈,袁姗姗,姜潮","dir":"金帝荣","dur":99,"effectShowNum":160,"globalReleased":false,"headLineShow":false,"id":343379,"img":"http://p1.meituan.net/w.h/movie/79311d41bea3be8f1677c0b69a5868fa289298.jpg","late":false,"localPubSt":0,"mk":0,"nm":"所以\u2026\u2026和黑粉结婚了","pn":121,"preSale":1,"preShow":false,"pubDate":1467216000000,"pubShowNum":160,"recentShowDate":1467216000000,"recentShowNum":0,"rt":"2016-06-30","sc":0,"scm":"黑粉变新娘，这是闹哪样","showInfo":"2016-06-30 本周四上映","showNum":0,"showst":4,"snum":7752,"star":"朴灿烈,袁姗姗,姜潮","totalShowNum":223,"ver":"2D","videoId":46570,"videoName":"终极版预告片","videourl":"http://v.meituan.net/movie/videos/b397c267f81644d593d5b514e92b7a9b.mp4","vnum":11,"weight":1,"wish":93761,"wishst":0},{"cat":"动作,科幻,奇幻","civilPubSt":0,"desc":"主演:詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","dir":"布莱恩·辛格","dur":144,"effectShowNum":0,"fra":"美国","frt":"2016-05-27","globalReleased":true,"headLineShow":false,"id":246177,"img":"http://p1.meituan.net/w.h/movie/ba2386a26648c1875e3fc780950f0ae0144875.jpg","late":false,"localPubSt":0,"mk":9.1,"nm":"X战警：天启","pn":692,"preSale":0,"preShow":false,"pubDate":1464883200000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-03","sc":9.1,"scm":"天启当炮灰，千年洗剪吹","showInfo":"今天62家影院放映118场","showNum":118,"showst":3,"snum":460717,"star":"詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","totalShowNum":221,"ver":"3D/IMAX 3D/中国巨幕/全景声","videoId":45863,"videoName":"国际版预告片2","videourl":"http://v.meituan.net/movie/videos/a5683be4c13c43f58d72a3bd1d20a047.mp4","vnum":114,"weight":1,"wish":559919,"wishst":0},{"cat":"动作,喜剧,剧情,悬疑","civilPubSt":0,"desc":"主演:李敏镐,钟汉良,唐嫣","dir":"申太罗","dur":105,"effectShowNum":100,"globalReleased":false,"headLineShow":false,"id":338506,"img":"http://p1.meituan.net/w.h/movie/45f8587cb425a01c9a279082772a8bd0379497.jpg","late":false,"localPubSt":0,"mk":0,"nm":"赏金猎人","pn":196,"preSale":1,"preShow":false,"pubDate":1467302400000,"pubShowNum":100,"recentShowDate":1467302400000,"recentShowNum":0,"rt":"2016-07-01","sc":0,"scm":"编外铁饭碗，收钱抓逃犯","showInfo":"2016-07-01 本周五上映","showNum":0,"showst":4,"snum":4537,"star":"李敏镐,钟汉良,唐嫣","totalShowNum":188,"ver":"2D/3D","videoId":46269,"videoName":"黄金天团制作特辑","videourl":"http://v.meituan.net/movie/videos/5ebe4106095c4d2fb2cc9a8bb2fcaae5.mp4","vnum":8,"weight":1,"wish":128603,"wishst":0},{"cat":"爱情,运动,剧情","civilPubSt":0,"desc":"主演:彭于晏,郭采洁,杨子姗","dir":"程孝泽","dur":103,"effectShowNum":0,"fra":"中国台湾","frt":"2010-08-13","globalReleased":true,"headLineShow":false,"id":57217,"img":"http://p1.meituan.net/w.h/movie/a70da0aefb82886efbae696181e966ff620085.jpg","late":false,"localPubSt":0,"mk":7.4,"nm":"近在咫尺的爱恋","pn":49,"preSale":0,"preShow":false,"pubDate":1466697600000,"pubShowNum":0,"recentShowDate":1466956800000,"recentShowNum":0,"rt":"2016-06-24","sc":7.4,"scm":"真爱在身旁，挥拳为理想","showInfo":"今天40家影院放映71场","showNum":71,"showst":3,"snum":789,"star":"彭于晏,郭采洁,杨子姗","totalShowNum":91,"ver":"2D","videoId":46249,"videoName":"终极版预告片","videourl":"http://v.meituan.net/movie/videos/e1ef7b884f3945fa88c8b6741eb00695.mp4","vnum":9,"weight":1,"wish":12564,"wishst":0},{"cat":"动画,奇幻,剧情","civilPubSt":0,"dir":"梁旋,张春","dur":105,"effectShowNum":55,"globalReleased":false,"headLineShow":false,"id":246591,"img":"http://p1.meituan.net/w.h/movie/a607ab45bcc9e8486328b39bff0132a4420994.jpg","late":false,"localPubSt":0,"mk":0,"nm":"大鱼海棠","pn":113,"preSale":1,"preShow":false,"pubDate":1467907200000,"pubShowNum":55,"recentShowDate":1467907200000,"recentShowNum":0,"rt":"2016-07-08","sc":0,"scm":"北冥有鱼鲲，海棠掌乾坤","showInfo":"2016-07-08 下周五上映","showNum":0,"showst":4,"snum":3166,"totalShowNum":73,"ver":"3D","videoId":46132,"videoName":"源起版预告片","videourl":"http://v.meituan.net/movie/videos/0a6f705daa8b4c469b3dc3b6bbc73bb4.mp4","vnum":5,"weight":1,"wish":136985,"wishst":0}]
//     * movieIds : [246333,246375,246972,246366,78421,246575,13511,343379,246177,338506,57217,246591,342741,337438,368271,343597,368071,248260,247575,246188,246045,345102,672123,247244,524,77192,341123,536,938,969,78094,78176,78653,78672,78267,78772,246316,246210,246178,246540,246583,338357,367990,52305,74993,78324,246004,245980,78403,248550,346658,345923,341109]
//     * stid : 576591972453269000
//     * stids : [{"movieId":246333,"stid":"576591972453269000_a246333_c0"},{"movieId":246375,"stid":"576591972453269000_a246375_c1"},{"movieId":246972,"stid":"576591972453269000_a246972_c2"},{"movieId":246366,"stid":"576591972453269000_a246366_c3"},{"movieId":78421,"stid":"576591972453269000_a78421_c4"},{"movieId":246575,"stid":"576591972453269000_a246575_c5"},{"movieId":13511,"stid":"576591972453269000_a13511_c6"},{"movieId":343379,"stid":"576591972453269000_a343379_c7"},{"movieId":246177,"stid":"576591972453269000_a246177_c8"},{"movieId":338506,"stid":"576591972453269000_a338506_c9"},{"movieId":57217,"stid":"576591972453269000_a57217_c10"},{"movieId":246591,"stid":"576591972453269000_a246591_c11"}]
//     * total : 53
//     */
//
//    private DataBean data;
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private String stid;
//        private int total;
//        private List<?> coming;
//        /**
//         * cat : 动作,喜剧,惊悚
//         * civilPubSt : 0
//         * desc : 主演:杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森
//         * dir : 朱浩伟
//         * dur : 126
//         * effectShowNum : 0
//         * fra : 美国,韩国
//         * frt : 2016-06-10,2016-07-14
//         * globalReleased : true
//         * headLineShow : false
//         * headLines : []
//         * headLinesVO : [{"movieId":246333,"title":"首周2.8亿夺冠，已超首部内地总票房","type":"票房","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12414"},{"movieId":246333,"title":"专访导演：\u201c天眼\u201d可能是真的","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12404"}]
//         * id : 246333
//         * img : http://p1.meituan.net/w.h/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg
//         * late : false
//         * localPubSt : 0
//         * mk : 8.6
//         * newsHeadlines : [{"movieId":246333,"title":"首周2.8亿夺冠，已超首部内地总票房","type":"票房","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12414"},{"movieId":246333,"title":"专访导演：\u201c天眼\u201d可能是真的","type":"专访","url":"meituanmovie://www.meituan.com/forum/newsDetail?id=12404"}]
//         * nm : 惊天魔盗团2
//         * pn : 244
//         * preSale : 0
//         * preShow : false
//         * pubDate : 1466697600000
//         * pubShowNum : 0
//         * recentShowDate : 1466956800000
//         * recentShowNum : 0
//         * rt : 2016-06-24
//         * sc : 8.6
//         * scm : 周董变魔术，敌人挡不住
//         * showInfo : 今天149家影院放映1885场
//         * showNum : 1885
//         * showst : 3
//         * snum : 100273
//         * star : 杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森
//         * totalShowNum : 3351
//         * ver : 2D/3D/中国巨幕
//         * videoId : 46617
//         * videoName : 侠盗版预告片
//         * videourl : http://v.meituan.net/movie/videos/d47aa4104383436f84dba9b65f3d8aab.mp4
//         * vnum : 27
//         * weight : 1
//         * wish : 513937
//         * wishst : 0
//         */
//
//        private List<HotBean> hot;
//        private List<Integer> movieIds;
//        /**
//         * movieId : 246333
//         * stid : 576591972453269000_a246333_c0
//         */
//
//        private List<StidsBean> stids;
//
//        public String getStid() {
//            return stid;
//        }
//
//        public void setStid(String stid) {
//            this.stid = stid;
//        }
//
//        public int getTotal() {
//            return total;
//        }
//
//        public void setTotal(int total) {
//            this.total = total;
//        }
//
//        public List<?> getComing() {
//            return coming;
//        }
//
//        public void setComing(List<?> coming) {
//            this.coming = coming;
//        }
//
//        public List<HotBean> getHot() {
//            return hot;
//        }
//
//        public void setHot(List<HotBean> hot) {
//            this.hot = hot;
//        }
//
//        public List<Integer> getMovieIds() {
//            return movieIds;
//        }
//
//        public void setMovieIds(List<Integer> movieIds) {
//            this.movieIds = movieIds;
//        }
//
//        public List<StidsBean> getStids() {
//            return stids;
//        }
//
//        public void setStids(List<StidsBean> stids) {
//            this.stids = stids;
//        }
//
//        public static class HotBean {
//            private String cat;
//            private int civilPubSt;
//            private String desc;
//            private String dir;
//            private int dur;
//            private int effectShowNum;
//            private String fra;
//            private String frt;
//            private boolean globalReleased;
//            private boolean headLineShow;
//            private int id;
//            private String img;
//            private boolean late;
//            private int localPubSt;
//            private double mk;
//            private String nm;
//            private int pn;
//            private int preSale;
//            private boolean preShow;
//            private long pubDate;
//            private int pubShowNum;
//            private long recentShowDate;
//            private int recentShowNum;
//            private String rt;
//            private double sc;
//            private String scm;
//            private String showInfo;
//            private int showNum;
//            private int showst;
//            private int snum;
//            private String star;
//            private int totalShowNum;
//            private String ver;
//            private int videoId;
//            private String videoName;
//            private String videourl;
//            private int vnum;
//            private int weight;
//            private int wish;
//            private int wishst;
//            private List<?> headLines;
//            /**
//             * movieId : 246333
//             * title : 首周2.8亿夺冠，已超首部内地总票房
//             * type : 票房
//             * url : meituanmovie://www.meituan.com/forum/newsDetail?id=12414
//             */
//
//            private List<HeadLinesVOBean> headLinesVO;
//            /**
//             * movieId : 246333
//             * title : 首周2.8亿夺冠，已超首部内地总票房
//             * type : 票房
//             * url : meituanmovie://www.meituan.com/forum/newsDetail?id=12414
//             */
//
//            private List<NewsHeadlinesBean> newsHeadlines;
//
//            public String getCat() {
//                return cat;
//            }
//
//            public void setCat(String cat) {
//                this.cat = cat;
//            }
//
//            public int getCivilPubSt() {
//                return civilPubSt;
//            }
//
//            public void setCivilPubSt(int civilPubSt) {
//                this.civilPubSt = civilPubSt;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public String getDir() {
//                return dir;
//            }
//
//            public void setDir(String dir) {
//                this.dir = dir;
//            }
//
//            public int getDur() {
//                return dur;
//            }
//
//            public void setDur(int dur) {
//                this.dur = dur;
//            }
//
//            public int getEffectShowNum() {
//                return effectShowNum;
//            }
//
//            public void setEffectShowNum(int effectShowNum) {
//                this.effectShowNum = effectShowNum;
//            }
//
//            public String getFra() {
//                return fra;
//            }
//
//            public void setFra(String fra) {
//                this.fra = fra;
//            }
//
//            public String getFrt() {
//                return frt;
//            }
//
//            public void setFrt(String frt) {
//                this.frt = frt;
//            }
//
//            public boolean isGlobalReleased() {
//                return globalReleased;
//            }
//
//            public void setGlobalReleased(boolean globalReleased) {
//                this.globalReleased = globalReleased;
//            }
//
//            public boolean isHeadLineShow() {
//                return headLineShow;
//            }
//
//            public void setHeadLineShow(boolean headLineShow) {
//                this.headLineShow = headLineShow;
//            }
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getImg() {
//                return img;
//            }
//
//            public void setImg(String img) {
//                this.img = img;
//            }
//
//            public boolean isLate() {
//                return late;
//            }
//
//            public void setLate(boolean late) {
//                this.late = late;
//            }
//
//            public int getLocalPubSt() {
//                return localPubSt;
//            }
//
//            public void setLocalPubSt(int localPubSt) {
//                this.localPubSt = localPubSt;
//            }
//
//            public double getMk() {
//                return mk;
//            }
//
//            public void setMk(double mk) {
//                this.mk = mk;
//            }
//
//            public String getNm() {
//                return nm;
//            }
//
//            public void setNm(String nm) {
//                this.nm = nm;
//            }
//
//            public int getPn() {
//                return pn;
//            }
//
//            public void setPn(int pn) {
//                this.pn = pn;
//            }
//
//            public int getPreSale() {
//                return preSale;
//            }
//
//            public void setPreSale(int preSale) {
//                this.preSale = preSale;
//            }
//
//            public boolean isPreShow() {
//                return preShow;
//            }
//
//            public void setPreShow(boolean preShow) {
//                this.preShow = preShow;
//            }
//
//            public long getPubDate() {
//                return pubDate;
//            }
//
//            public void setPubDate(long pubDate) {
//                this.pubDate = pubDate;
//            }
//
//            public int getPubShowNum() {
//                return pubShowNum;
//            }
//
//            public void setPubShowNum(int pubShowNum) {
//                this.pubShowNum = pubShowNum;
//            }
//
//            public long getRecentShowDate() {
//                return recentShowDate;
//            }
//
//            public void setRecentShowDate(long recentShowDate) {
//                this.recentShowDate = recentShowDate;
//            }
//
//            public int getRecentShowNum() {
//                return recentShowNum;
//            }
//
//            public void setRecentShowNum(int recentShowNum) {
//                this.recentShowNum = recentShowNum;
//            }
//
//            public String getRt() {
//                return rt;
//            }
//
//            public void setRt(String rt) {
//                this.rt = rt;
//            }
//
//            public double getSc() {
//                return sc;
//            }
//
//            public void setSc(double sc) {
//                this.sc = sc;
//            }
//
//            public String getScm() {
//                return scm;
//            }
//
//            public void setScm(String scm) {
//                this.scm = scm;
//            }
//
//            public String getShowInfo() {
//                return showInfo;
//            }
//
//            public void setShowInfo(String showInfo) {
//                this.showInfo = showInfo;
//            }
//
//            public int getShowNum() {
//                return showNum;
//            }
//
//            public void setShowNum(int showNum) {
//                this.showNum = showNum;
//            }
//
//            public int getShowst() {
//                return showst;
//            }
//
//            public void setShowst(int showst) {
//                this.showst = showst;
//            }
//
//            public int getSnum() {
//                return snum;
//            }
//
//            public void setSnum(int snum) {
//                this.snum = snum;
//            }
//
//            public String getStar() {
//                return star;
//            }
//
//            public void setStar(String star) {
//                this.star = star;
//            }
//
//            public int getTotalShowNum() {
//                return totalShowNum;
//            }
//
//            public void setTotalShowNum(int totalShowNum) {
//                this.totalShowNum = totalShowNum;
//            }
//
//            public String getVer() {
//                return ver;
//            }
//
//            public void setVer(String ver) {
//                this.ver = ver;
//            }
//
//            public int getVideoId() {
//                return videoId;
//            }
//
//            public void setVideoId(int videoId) {
//                this.videoId = videoId;
//            }
//
//            public String getVideoName() {
//                return videoName;
//            }
//
//            public void setVideoName(String videoName) {
//                this.videoName = videoName;
//            }
//
//            public String getVideourl() {
//                return videourl;
//            }
//
//            public void setVideourl(String videourl) {
//                this.videourl = videourl;
//            }
//
//            public int getVnum() {
//                return vnum;
//            }
//
//            public void setVnum(int vnum) {
//                this.vnum = vnum;
//            }
//
//            public int getWeight() {
//                return weight;
//            }
//
//            public void setWeight(int weight) {
//                this.weight = weight;
//            }
//
//            public int getWish() {
//                return wish;
//            }
//
//            public void setWish(int wish) {
//                this.wish = wish;
//            }
//
//            public int getWishst() {
//                return wishst;
//            }
//
//            public void setWishst(int wishst) {
//                this.wishst = wishst;
//            }
//
//            public List<?> getHeadLines() {
//                return headLines;
//            }
//
//            public void setHeadLines(List<?> headLines) {
//                this.headLines = headLines;
//            }
//
//            public List<HeadLinesVOBean> getHeadLinesVO() {
//                return headLinesVO;
//            }
//
//            public void setHeadLinesVO(List<HeadLinesVOBean> headLinesVO) {
//                this.headLinesVO = headLinesVO;
//            }
//
//            public List<NewsHeadlinesBean> getNewsHeadlines() {
//                return newsHeadlines;
//            }
//
//            public void setNewsHeadlines(List<NewsHeadlinesBean> newsHeadlines) {
//                this.newsHeadlines = newsHeadlines;
//            }
//
//            public static class HeadLinesVOBean {
//                private int movieId;
//                private String title;
//                private String type;
//                private String url;
//
//                public int getMovieId() {
//                    return movieId;
//                }
//
//                public void setMovieId(int movieId) {
//                    this.movieId = movieId;
//                }
//
//                public String getTitle() {
//                    return title;
//                }
//
//                public void setTitle(String title) {
//                    this.title = title;
//                }
//
//                public String getType() {
//                    return type;
//                }
//
//                public void setType(String type) {
//                    this.type = type;
//                }
//
//                public String getUrl() {
//                    return url;
//                }
//
//                public void setUrl(String url) {
//                    this.url = url;
//                }
//            }
//
//            public static class NewsHeadlinesBean {
//                private int movieId;
//                private String title;
//                private String type;
//                private String url;
//
//                public int getMovieId() {
//                    return movieId;
//                }
//
//                public void setMovieId(int movieId) {
//                    this.movieId = movieId;
//                }
//
//                public String getTitle() {
//                    return title;
//                }
//
//                public void setTitle(String title) {
//                    this.title = title;
//                }
//
//                public String getType() {
//                    return type;
//                }
//
//                public void setType(String type) {
//                    this.type = type;
//                }
//
//                public String getUrl() {
//                    return url;
//                }
//
//                public void setUrl(String url) {
//                    this.url = url;
//                }
//            }
//        }
//
//        public static class StidsBean {
//            private int movieId;
//            private String stid;
//
//            public int getMovieId() {
//                return movieId;
//            }
//
//            public void setMovieId(int movieId) {
//                this.movieId = movieId;
//            }
//
//            public String getStid() {
//                return stid;
//            }
//
//            public void setStid(String stid) {
//                this.stid = stid;
//            }
//        }
//    }
}

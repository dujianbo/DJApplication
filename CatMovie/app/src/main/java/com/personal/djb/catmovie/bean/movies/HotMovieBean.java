package com.personal.djb.catmovie.bean.movies;

import com.google.gson.annotations.SerializedName;

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
     * data : {"hasNext":false,"movies":[{"showInfo":"今天150家影院放映1999场","cnms":0,"sn":0,"late":false,"vd":"","dir":"朱浩伟","star":"杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森","cat":"动作,喜剧,惊悚","wish":513937,"3d":true,"imax":false,"snum":28714,"scm":"周董变魔术，敌人挡不住","dur":126,"showDate":"","src":"","pn":244,"nm":"惊天魔盗团2","sc":8.7,"ver":"2D/3D/中国巨幕","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg","preSale":0,"time":"","id":246333},{"showInfo":"今天152家影院放映1818场","cnms":0,"sn":0,"late":false,"vd":"","dir":"罗兰·艾默里奇","star":"利亚姆·海姆斯沃斯,麦卡·梦露,杰西·厄舍","cat":"动作,冒险,科幻","wish":164747,"3d":true,"imax":true,"snum":26812,"scm":"外星再入侵，地核将燃尽","dur":120,"showDate":"","src":"","pn":226,"nm":"独立日：卷土重来","sc":8.5,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/b408a9322cd0da51d4bdd3261ba3d1c0278866.jpg","preSale":0,"time":"","id":246375},{"showInfo":"今天146家影院放映919场","cnms":0,"sn":0,"late":false,"vd":"","dir":"杜琪峰","star":"赵薇,古天乐,钟汉良","cat":"动作,犯罪,悬疑","wish":72910,"3d":false,"imax":false,"snum":15392,"scm":"悍匪有手段，中枪再作案","dur":88,"showDate":"","src":"","pn":186,"nm":"三人行","sc":7.2,"ver":"2D","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/1c82df174422d9374f821482f445171b129671.jpg","preSale":0,"time":"","id":246972},{"showInfo":"今天145家影院放映678场","cnms":0,"sn":0,"late":false,"vd":"","dir":"安德鲁·斯坦顿,安格斯·麦克莱恩","star":"艾伦·德詹尼丝,徐帆,艾伯特·布鲁克斯","cat":"动画,喜剧,冒险","wish":144949,"3d":true,"imax":true,"snum":57619,"scm":"唠叨鱼多莉，上路找父母","dur":97,"showDate":"","src":"","pn":123,"nm":"海底总动员2：多莉去哪儿","sc":8.7,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"2016-06-17上映","img":"http://p0.meituan.net/165.220/movie/4c20a91505854dcd0f9617069c5fec21843230.jpg","preSale":0,"time":"","id":246366},{"showInfo":"今天120家影院放映399场","cnms":0,"sn":0,"late":false,"vd":"","dir":"邓肯·琼斯","star":"崔维斯·费米尔,宝拉·巴顿,本·福斯特","cat":"动作,冒险,奇幻","wish":479894,"3d":true,"imax":true,"snum":711867,"scm":"怒拳为谁握，联盟斗部落","dur":124,"showDate":"","src":"","pn":347,"nm":"魔兽","sc":9.2,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-08上映","img":"http://p0.meituan.net/165.220/movie/f94e67dcd5b48f034bd7adc892f1b20695854.jpeg","preSale":0,"time":"","id":78421},{"showInfo":"2016-07-08上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"周拓如","star":"吴亦凡,刘亦菲,金世佳","cat":"剧情,爱情","wish":197433,"3d":false,"imax":false,"snum":4084,"scm":"霸道男学霸，爱上女学渣","dur":98,"showDate":"","src":"","pn":333,"nm":"致青春·原来你还在这里","sc":0,"ver":"2D","rt":"2016-07-08上映","img":"http://p0.meituan.net/165.220/movie/37e7ed909843270f0880c325b6be7b60436509.jpg","preSale":1,"time":"","id":246575},{"showInfo":"2016-07-02 下周六上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"戴夫·格林","star":"梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","cat":"动作,冒险,喜剧","wish":257613,"3d":true,"imax":true,"snum":1391,"scm":"儿时忍者龟，犀牛也回归","dur":113,"showDate":"","src":"","pn":249,"nm":"忍者神龟2：破影而出","sc":0,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"下周六上映","img":"http://p1.meituan.net/165.220/movie/a375ebfcdc2a396423c368b943813b01179997.jpg","preSale":1,"time":"","id":13511},{"showInfo":"今天60家影院放映106场","cnms":0,"sn":0,"late":false,"vd":"","dir":"布莱恩·辛格","star":"詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","cat":"动作,科幻,奇幻","wish":559919,"3d":true,"imax":true,"snum":440000,"scm":"天启当炮灰，千年洗剪吹","dur":144,"showDate":"","src":"","pn":692,"nm":"X战警：天启","sc":9.1,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-03上映","img":"http://p1.meituan.net/165.220/movie/ba2386a26648c1875e3fc780950f0ae0144875.jpg","preSale":0,"time":"","id":246177},{"showInfo":"2016-06-30 下周四上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"金帝荣","star":"朴灿烈,袁姗姗,姜潮","cat":"爱情,喜剧","wish":88025,"3d":false,"imax":false,"snum":6707,"scm":"黑粉变新娘，这是闹哪样","dur":99,"showDate":"","src":"","pn":121,"nm":"所以\u2026\u2026和黑粉结婚了","sc":0,"ver":"2D","rt":"下周四上映","img":"http://p1.meituan.net/165.220/movie/79311d41bea3be8f1677c0b69a5868fa289298.jpg","preSale":1,"time":"","id":343379},{"showInfo":"今天48家影院放映90场","cnms":0,"sn":0,"late":false,"vd":"","dir":"程孝泽","star":"彭于晏,郭采洁,杨子姗","cat":"爱情,运动,剧情","wish":12564,"3d":false,"imax":false,"snum":363,"scm":"真爱在身旁，挥拳为理想","dur":103,"showDate":"","src":"","pn":49,"nm":"近在咫尺的爱恋","sc":7.5,"ver":"2D","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/a70da0aefb82886efbae696181e966ff620085.jpg","preSale":0,"time":"","id":57217},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"申太罗","star":"李敏镐,钟汉良,唐嫣","cat":"动作,喜剧,剧情,悬疑","wish":120344,"3d":true,"imax":false,"snum":4070,"scm":"编外铁饭碗，收钱抓逃犯","dur":105,"showDate":"","src":"","pn":196,"nm":"赏金猎人","sc":0,"ver":"2D/3D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/45f8587cb425a01c9a279082772a8bd0379497.jpg","preSale":1,"time":"","id":338506},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"王早","star":"林心如,何润东,金世佳","cat":"悬疑,惊悚,恐怖","wish":8433,"3d":true,"imax":false,"snum":422,"scm":"天涯号起航，失控大逃亡","dur":92,"showDate":"","src":"","pn":49,"nm":"魔轮","sc":0,"ver":"2D/3D","rt":"下周五上映","img":"http://p0.meituan.net/165.220/movie/1d44cc6185a7ee4ee1def1f3a0f5821a584683.jpg","preSale":1,"time":"","id":342741},{"showInfo":"今天18家影院放映20场","cnms":0,"sn":0,"late":false,"vd":"","dir":"姬雨","star":"胡影怡,朱璇,周骏","cat":"恐怖,惊悚","wish":20373,"3d":false,"imax":false,"snum":11900,"scm":"笔仙已玩坏，筷仙来作怪","dur":87,"showDate":"","src":"","pn":32,"nm":"筷仙","sc":4.4,"ver":"2D","rt":"2016-06-17上映","img":"http://p1.meituan.net/165.220/movie/31a325f0796cfebbab24776024eeec91100800.jpeg","preSale":0,"time":"","id":343597},{"showInfo":"2016-07-02 下周六上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"郑义","star":"朱可可,阿飞,夏倚轩","cat":"动画,科幻,冒险","wish":2758,"3d":false,"imax":false,"snum":84,"scm":"月球机器鸭，化身小飞鸭","dur":81,"showDate":"","src":"","pn":66,"nm":"丑小鸭历险记","sc":0,"ver":"2D","rt":"下周六上映","img":"http://p1.meituan.net/165.220/movie/0484f8ad5aec878112f1a04715d32b4e360620.jpg","preSale":1,"time":"","id":368271},{"showInfo":"今天10家影院放映11场","cnms":0,"sn":0,"late":false,"vd":"","dir":"吴天明","star":"陶泽如,郑伟,李岷城","cat":"历史,音乐,剧情","wish":3981,"3d":false,"imax":false,"snum":108722,"scm":"两代手艺人，唢呐感情深","dur":108,"showDate":"","src":"","pn":105,"nm":"百鸟朝凤","sc":9.2,"ver":"2D","rt":"2016-05-06上映","img":"http://p1.meituan.net/165.220/movie/ef968fd382173c45b0c2d10572ca10b8354896.jpg","preSale":0,"time":"","id":248260},{"showInfo":"今天6家影院放映11场","cnms":0,"sn":0,"late":false,"vd":"","dir":"詹姆斯·波宾","star":"约翰尼·德普,安妮·海瑟薇,米娅·华希科沃斯卡","cat":"奇幻,冒险,喜剧","wish":248677,"3d":true,"imax":true,"snum":223549,"scm":"镜中再梦游，拯救老朋友","dur":115,"showDate":"","src":"","pn":82,"nm":"爱丽丝梦游仙境2：镜中奇遇记","sc":8.6,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2016-05-27上映","img":"http://p1.meituan.net/165.220/movie/dbcc3eabae012da794fe4b74ed1365fc659390.jpg","preSale":0,"time":"","id":248680},{"showInfo":"今天10家影院放映10场","cnms":0,"sn":0,"late":false,"vd":"","dir":"核桃","star":"奶茶,YOYO,CBI","cat":"动画,喜剧,冒险","wish":43480,"3d":true,"imax":false,"snum":18745,"scm":"贱萌牛头人，爆笑刷副本","dur":86,"showDate":"","src":"","pn":40,"nm":"我叫MT之山口山战记","sc":7.6,"ver":"3D/中国巨幕","rt":"2016-06-17上映","img":"http://p0.meituan.net/165.220/movie/73f6abbb90259d1fa8a88fa907ca3062587352.jpg","preSale":0,"time":"","id":246045},{"showInfo":"2016-06-27 下周一上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"苗月","star":"白威,陈瑾,王柠","cat":"剧情,历史,战争","wish":316,"3d":false,"imax":false,"snum":26,"scm":"誓死护宣言，智斗救火种","dur":114,"showDate":"","src":"","pn":4,"nm":"大火种","sc":0,"ver":"2D","rt":"下周一上映","img":"http://p1.meituan.net/165.220/movie/c8545c7bd1f6070f0d52a77bd0ddd8738720802.jpg","preSale":1,"time":"","id":337438},{"showInfo":"今天6家影院放映6场","cnms":0,"sn":0,"late":false,"vd":"","dir":"薛晓路","star":"汤唯,吴秀波,惠英红","cat":"爱情,剧情","wish":307383,"3d":false,"imax":false,"snum":444385,"scm":"异国心未远，书信寄情缘","dur":132,"showDate":"","src":"","pn":547,"nm":"北京遇上西雅图之不二情书","sc":8.5,"ver":"2D/中国巨幕","rt":"2016-04-29上映","img":"http://p1.meituan.net/165.220/movie/fe80bc40822d0a5f3168b73089c47d71101133.jpg","preSale":0,"time":"","id":247575},{"showInfo":"今天4家影院放映6场","cnms":0,"sn":0,"late":false,"vd":"","dir":"朱丹","star":"孙看,孙洪涛,刘忠元","cat":"剧情,历史,战争","wish":2034,"3d":false,"imax":false,"snum":105,"scm":"南口抗日战，疆场洒血汗","dur":98,"showDate":"","src":"","pn":26,"nm":"南口1937","sc":0,"ver":"2D","rt":"本周四上映","img":"http://p1.meituan.net/165.220/movie/f4b5d903571ec3c3fc952f86261b04fd227842.jpg","preSale":0,"time":"","id":368071},{"showInfo":"今天5家影院放映5场","cnms":0,"sn":0,"late":false,"vd":"","dir":"费格尔·雷利,克莱·凯蒂","star":"杰森·苏戴奇斯,乔什·盖德,丹尼·麦克布耐德","cat":"动画,奇幻,冒险","wish":123045,"3d":true,"imax":false,"snum":317954,"scm":"绿猪来偷蛋，鸟儿群奋战","dur":97,"showDate":"","src":"","pn":132,"nm":"愤怒的小鸟","sc":8.9,"ver":"3D/中国巨幕","rt":"2016-05-20上映","img":"http://p0.meituan.net/165.220/movie/b721e73740601799c065affb619d837e151622.jpg","preSale":0,"time":"","id":246188},{"showInfo":"今天3家影院放映3场","cnms":0,"sn":0,"late":false,"vd":"","dir":"叶伟信","star":"甄子丹,洪金宝,熊黛林","cat":"动作,历史,传记","wish":211,"3d":false,"imax":false,"snum":1031,"scm":"叶问搬香港，仇恨值渐长","dur":108,"showDate":"","src":"","pn":63,"nm":"叶问2：宗师传奇","sc":8.5,"ver":"2D","rt":"2010-04-27上映","img":"http://p0.meituan.net/165.220/movie/70/612717.jpg","preSale":0,"time":"","id":524},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"卫晓茼","star":"郜耀平,张志宏,姚安濂","cat":"剧情,历史","wish":612,"3d":false,"imax":false,"snum":130,"scm":"心中有信仰，少年变战士","dur":107,"showDate":"","src":"","pn":17,"nm":"党的女儿尹灵芝","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p0.meituan.net/165.220/movie/1dffa0a076e3514234f2029d6b29b3da157605.jpg","preSale":1,"time":"","id":672123},{"showInfo":"今天2家影院放映2场","cnms":0,"sn":0,"late":false,"vd":"","dir":"徐峥","star":"徐峥,王宝强,黄渤","cat":"喜剧","wish":22543,"3d":false,"imax":false,"snum":15633,"scm":"囧途太荒唐，甩不掉宝强","dur":105,"showDate":"","src":"","pn":232,"nm":"人再囧途之泰囧","sc":8.8,"ver":"2D","rt":"2012-12-12上映","img":"http://p0.meituan.net/165.220/movie/38/280521.jpg","preSale":0,"time":"","id":938},{"showInfo":"今天2家影院放映2场","cnms":0,"sn":0,"late":false,"vd":"","dir":"郭帆","star":"周冬雨,林更新,隋凯","cat":"剧情,爱情","wish":24267,"3d":false,"imax":false,"snum":73036,"scm":"初恋要结婚，回国忆青春","dur":98,"showDate":"","src":"","pn":88,"nm":"同桌的你","sc":8.6,"ver":"2D","rt":"2014-04-25上映","img":"http://p0.meituan.net/165.220/movie/__40811615__5745234.jpg","preSale":0,"time":"","id":78653},{"showInfo":"今天2家影院放映2场","cnms":0,"sn":0,"late":false,"vd":"","dir":"麦子","star":"徐洁儿,陈欢,陈炳强","cat":"恐怖,惊悚,悬疑","wish":3301,"3d":false,"imax":false,"snum":5335,"scm":"同学生日会，恶鬼来相会","dur":90,"showDate":"","src":"","pn":145,"nm":"死亡游戏","sc":5.8,"ver":"2D","rt":"2016-06-03上映","img":"http://p1.meituan.net/165.220/movie/c3eec836300c5a9f0429d96921aa168472038.jpg","preSale":0,"time":"","id":346286},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"冼杞然","star":"窦骁,约瑟夫·费因斯,娄宇健","cat":"剧情,战争,历史","wish":4783,"3d":false,"imax":false,"snum":65,"scm":"辛德勒名单，推出中国版","dur":108,"showDate":"","src":"","pn":122,"nm":"终极胜利","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/0c93b5987ae440497dd985982aad1c4b233221.jpg","preSale":1,"time":"","id":341123},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"潘安子","star":"周冬雨,陈晓,赵丽颖","cat":"剧情,爱情,古装","wish":3080,"3d":false,"imax":false,"snum":2508,"scm":"唯美清宫戏，阴谋不见底","dur":115,"showDate":"","src":"","pn":36,"nm":"宫锁沉香","sc":7.8,"ver":"2D","rt":"2013-08-13上映","img":"http://p0.meituan.net/165.220/movie/__18327195__7709930.jpg","preSale":0,"time":"","id":78176},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"杨瑾","star":"李书晨,王琛,卫永绍","cat":"动画,家庭","wish":184,"3d":false,"imax":false,"snum":54,"scm":"离家偷玩耍，乡下过暑假","dur":108,"showDate":"","src":"","pn":98,"nm":"有人赞美聪慧,有人则不","sc":7.8,"ver":"2D","rt":"2013-11待定","img":"http://p0.meituan.net/165.220/movie/__16523889__6590864.jpg","preSale":0,"time":"2013-11","id":78224},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"丁晟","star":"刘烨,成龙,景甜","cat":"剧情,动作,犯罪","wish":14520,"3d":true,"imax":true,"snum":19130,"scm":"女儿乱交友，老爹来出头","dur":108,"showDate":"","src":"","pn":44,"nm":"警察故事2013","sc":8,"ver":"2D/IMAX 3D","rt":"2013-12-24上映","img":"http://p0.meituan.net/165.220/movie/__29561506__7176229.jpg","preSale":0,"time":"","id":77192},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"陈思诚","star":"梁家辉,刘嘉玲,王学兵","cat":"爱情","wish":12286,"3d":false,"imax":false,"snum":28683,"scm":"恋爱分五段，激情不会散","dur":121,"showDate":"","src":"","pn":145,"nm":"北京爱情故事","sc":8,"ver":"2D","rt":"2014-02-14上映","img":"http://p0.meituan.net/165.220/movie/__33456063__6265360.jpg","preSale":0,"time":"","id":78324},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"陈正道","star":"徐峥,莫文蔚,吕中","cat":"剧情,悬疑,惊悚","wish":11396,"3d":false,"imax":false,"snum":46528,"scm":"催眠治心伤，侧击猜真相","dur":102,"showDate":"","src":"","pn":78,"nm":"催眠大师","sc":9,"ver":"2D","rt":"2014-04-29上映","img":"http://p1.meituan.net/165.220/movie/__39582689__9041214.jpg","preSale":0,"time":"","id":78672},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"黄真真","star":"陈意涵,薛凯琪,杨子姗","cat":"剧情,爱情","wish":30224,"3d":false,"imax":false,"snum":68736,"scm":"永远好闺蜜，渣男靠边去","dur":118,"showDate":"","src":"","pn":203,"nm":"闺蜜","sc":8,"ver":"2D","rt":"2014-07-30上映","img":"http://p1.meituan.net/165.220/movie/__48665963__9098444.jpg","preSale":0,"time":"","id":78772},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"卢恒宇,李姝洁","star":"张杰,皇贞季,宝木中阳","cat":"动画,喜剧,奇幻","wish":25775,"3d":false,"imax":false,"snum":122597,"scm":"男主有点拽，二次元崩坏","dur":100,"showDate":"","src":"","pn":79,"nm":"十万个冷笑话","sc":8.2,"ver":"2D","rt":"2014-12-31上映","img":"http://p1.meituan.net/165.220/movie/56cb96b2c6df95b66db4ce719993d5d0666565.jpg","preSale":0,"time":"","id":246067},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"胡笳,岑俊义","star":"杨颖,王宝强,李晨","cat":"喜剧,冒险,动作","wish":49025,"3d":false,"imax":false,"snum":361066,"scm":"跑男再出发，集体游三亚","dur":88,"showDate":"","src":"","pn":118,"nm":"奔跑吧！兄弟","sc":4.7,"ver":"2D","rt":"2015-01-30上映","img":"http://p0.meituan.net/165.220/movie/85f0d9a604e26be5523a848269346697296774.jpg","preSale":0,"time":"","id":246316},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"蒂姆·肯德尔","star":"赵薇,黄晓明,佟大为","cat":"喜剧,动作,冒险","wish":64522,"3d":false,"imax":false,"snum":282729,"scm":"逗比好基友，遇到坏导游","dur":119,"showDate":"","src":"","pn":273,"nm":"横冲直撞好莱坞","sc":7.7,"ver":"2D/中国巨幕","rt":"2015-06-26上映","img":"http://p1.meituan.net/165.220/movie/1c06c9055cb3990e76ee93c2c70789bb145247.jpg","preSale":0,"time":"","id":246178},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"彭发","star":"徐娇,张兆辉,毕秀茹","cat":"惊悚,恐怖","wish":34072,"3d":true,"imax":false,"snum":20071,"scm":"古宅心慌慌，通灵少女忙","dur":87,"showDate":"","src":"","pn":101,"nm":"通灵之六世古宅","sc":4.5,"ver":"2D/3D/中国巨幕","rt":"2015-07-24上映","img":"http://p1.meituan.net/165.220/movie/575f1cee324dd206d981b4dfcb1ec1bd1381691.jpg","preSale":0,"time":"","id":246249},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"田蒙","star":"张翰,陈乔恩,施予斐","cat":"喜剧,爱情","wish":78433,"3d":false,"imax":false,"snum":83822,"scm":"校草撞菠菜，青春已不再","dur":92,"showDate":"","src":"","pn":172,"nm":"既然青春留不住","sc":8.2,"ver":"2D","rt":"2015-10-23上映","img":"http://p0.meituan.net/165.220/movie/90422e83536df2153ecc57ff9703e99c1111187.jpg","preSale":0,"time":"","id":246540},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"阿甘","star":"郭涛,刘心悠,李灿森","cat":"喜剧","wish":107,"3d":false,"imax":false,"snum":93,"scm":"姚晨王宝强，爆笑太疯狂","dur":97,"showDate":"","src":"","pn":3,"nm":"大电影2.0之两个傻瓜的荒唐事","sc":5.6,"ver":"2D","rt":"2007-12-21上映","img":"http://p1.meituan.net/165.220/movie/76ac89dad45ac25c676246dc92794b03318660.png","preSale":0,"time":"","id":536},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"张琦","star":"杨幂,刘恺威,周秀娜","cat":"喜剧,爱情,悬疑","wish":280,"3d":false,"imax":false,"snum":754,"scm":"假戏变真做，幂威秀爱火","dur":95,"showDate":"","src":"","pn":300,"nm":"Hold住爱","sc":5.9,"ver":"2D","rt":"2012-08-23上映","img":"http://p0.meituan.net/165.220/movie/29/274730.jpg","preSale":0,"time":"","id":729},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"管虎","star":"黄渤,刘烨,张涵予","cat":"剧情,喜剧,动作","wish":10110,"3d":false,"imax":false,"snum":5758,"scm":"三男一台戏，客栈解僵局","dur":108,"showDate":"","src":"","pn":281,"nm":"厨子戏子痞子","sc":8.3,"ver":"2D","rt":"2013-03-29上映","img":"http://p0.meituan.net/165.220/movie/__7277442__6071578.jpg","preSale":0,"time":"","id":969},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"梁德森","star":"钟丽缇,柳岩,李灿森","cat":"悬疑,惊悚","wish":771,"3d":false,"imax":false,"snum":182,"scm":"惊心动魄的寻亲之旅","dur":90,"showDate":"","src":"","pn":22,"nm":"人间蒸发","sc":7,"ver":"2D","rt":"2013-04-28上映","img":"http://p0.meituan.net/165.220/movie/__6680785__5992311.jpg","preSale":0,"time":"","id":78094},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"叶伟民","star":"吴镇宇,林心如,杨祐宁","cat":"剧情,悬疑,恐怖","wish":36817,"3d":true,"imax":false,"snum":122347,"scm":"古宅阴森处，少妇诉凄苦","dur":90,"showDate":"","src":"","pn":98,"nm":"京城81号","sc":5,"ver":"2D/3D","rt":"2014-07-18上映","img":"http://p1.meituan.net/165.220/movie/__48584093__3431812.jpg","preSale":0,"time":"","id":78267},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"苏有朋","star":"陈都灵,欧豪,杨洋","cat":"爱情","wish":82116,"3d":false,"imax":false,"snum":538026,"scm":"青春要恋爱，别忘堕堕胎","dur":117,"showDate":"","src":"","pn":315,"nm":"左耳","sc":7.9,"ver":"2D","rt":"2015-04-24上映","img":"http://p0.meituan.net/165.220/movie/da55f29972ec0b9692ba7055905892c8123092.jpg","preSale":0,"time":"","id":246210},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"邱浩强","star":"李思娴,郭亚维,宋磊","cat":"动画,喜剧,冒险","wish":27716,"3d":false,"imax":false,"snum":21955,"scm":"国产美人鱼，俩海盗来袭","dur":85,"showDate":"","src":"","pn":38,"nm":"美人鱼之海盗来袭","sc":5.5,"ver":"2D","rt":"2015-07-31上映","img":"http://p0.meituan.net/165.220/movie/7e15953aa8bc5be4e7bff00913426a06920765.jpg","preSale":0,"time":"","id":248550},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"秦小珍","star":"刘畅,白百何,唐艺昕","cat":"剧情,爱情","wish":120703,"3d":false,"imax":false,"snum":78361,"scm":"文艺大暖男，不二常相伴","dur":121,"showDate":"","src":"","pn":564,"nm":"陪安东尼度过漫长岁月","sc":7.8,"ver":"2D","rt":"2015-11-13上映","img":"http://p1.meituan.net/165.220/movie/9f0d994eafbf52e7b0145b7746d1d881246635.jpg","preSale":0,"time":"","id":246583},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"朴裕焕","star":"雷佳音,夏梓桐,李菁","cat":"悬疑,喜剧,犯罪","wish":4154,"3d":false,"imax":false,"snum":2956,"scm":"宿醉陷疑案，奋力查嫌犯","dur":94,"showDate":"","src":"","pn":47,"nm":"记忆碎片","sc":6.9,"ver":"2D","rt":"2016-06-03上映","img":"http://p1.meituan.net/165.220/movie/ea15948d63d527d092a70e39cbb74636357253.jpg","preSale":0,"time":"","id":345923}]}
     */

    private int status;
    /**
     * hasNext : false
     * movies : [{"showInfo":"今天150家影院放映1999场","cnms":0,"sn":0,"late":false,"vd":"","dir":"朱浩伟","star":"杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森","cat":"动作,喜剧,惊悚","wish":513937,"3d":true,"imax":false,"snum":28714,"scm":"周董变魔术，敌人挡不住","dur":126,"showDate":"","src":"","pn":244,"nm":"惊天魔盗团2","sc":8.7,"ver":"2D/3D/中国巨幕","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg","preSale":0,"time":"","id":246333},{"showInfo":"今天152家影院放映1818场","cnms":0,"sn":0,"late":false,"vd":"","dir":"罗兰·艾默里奇","star":"利亚姆·海姆斯沃斯,麦卡·梦露,杰西·厄舍","cat":"动作,冒险,科幻","wish":164747,"3d":true,"imax":true,"snum":26812,"scm":"外星再入侵，地核将燃尽","dur":120,"showDate":"","src":"","pn":226,"nm":"独立日：卷土重来","sc":8.5,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/b408a9322cd0da51d4bdd3261ba3d1c0278866.jpg","preSale":0,"time":"","id":246375},{"showInfo":"今天146家影院放映919场","cnms":0,"sn":0,"late":false,"vd":"","dir":"杜琪峰","star":"赵薇,古天乐,钟汉良","cat":"动作,犯罪,悬疑","wish":72910,"3d":false,"imax":false,"snum":15392,"scm":"悍匪有手段，中枪再作案","dur":88,"showDate":"","src":"","pn":186,"nm":"三人行","sc":7.2,"ver":"2D","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/1c82df174422d9374f821482f445171b129671.jpg","preSale":0,"time":"","id":246972},{"showInfo":"今天145家影院放映678场","cnms":0,"sn":0,"late":false,"vd":"","dir":"安德鲁·斯坦顿,安格斯·麦克莱恩","star":"艾伦·德詹尼丝,徐帆,艾伯特·布鲁克斯","cat":"动画,喜剧,冒险","wish":144949,"3d":true,"imax":true,"snum":57619,"scm":"唠叨鱼多莉，上路找父母","dur":97,"showDate":"","src":"","pn":123,"nm":"海底总动员2：多莉去哪儿","sc":8.7,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"2016-06-17上映","img":"http://p0.meituan.net/165.220/movie/4c20a91505854dcd0f9617069c5fec21843230.jpg","preSale":0,"time":"","id":246366},{"showInfo":"今天120家影院放映399场","cnms":0,"sn":0,"late":false,"vd":"","dir":"邓肯·琼斯","star":"崔维斯·费米尔,宝拉·巴顿,本·福斯特","cat":"动作,冒险,奇幻","wish":479894,"3d":true,"imax":true,"snum":711867,"scm":"怒拳为谁握，联盟斗部落","dur":124,"showDate":"","src":"","pn":347,"nm":"魔兽","sc":9.2,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-08上映","img":"http://p0.meituan.net/165.220/movie/f94e67dcd5b48f034bd7adc892f1b20695854.jpeg","preSale":0,"time":"","id":78421},{"showInfo":"2016-07-08上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"周拓如","star":"吴亦凡,刘亦菲,金世佳","cat":"剧情,爱情","wish":197433,"3d":false,"imax":false,"snum":4084,"scm":"霸道男学霸，爱上女学渣","dur":98,"showDate":"","src":"","pn":333,"nm":"致青春·原来你还在这里","sc":0,"ver":"2D","rt":"2016-07-08上映","img":"http://p0.meituan.net/165.220/movie/37e7ed909843270f0880c325b6be7b60436509.jpg","preSale":1,"time":"","id":246575},{"showInfo":"2016-07-02 下周六上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"戴夫·格林","star":"梅根·福克斯,皮特·普劳泽克,阿伦·瑞奇森","cat":"动作,冒险,喜剧","wish":257613,"3d":true,"imax":true,"snum":1391,"scm":"儿时忍者龟，犀牛也回归","dur":113,"showDate":"","src":"","pn":249,"nm":"忍者神龟2：破影而出","sc":0,"ver":"2D/3D/IMAX 3D/中国巨幕","rt":"下周六上映","img":"http://p1.meituan.net/165.220/movie/a375ebfcdc2a396423c368b943813b01179997.jpg","preSale":1,"time":"","id":13511},{"showInfo":"今天60家影院放映106场","cnms":0,"sn":0,"late":false,"vd":"","dir":"布莱恩·辛格","star":"詹姆斯·麦卡沃伊,迈克尔·法斯宾德,詹妮弗·劳伦斯","cat":"动作,科幻,奇幻","wish":559919,"3d":true,"imax":true,"snum":440000,"scm":"天启当炮灰，千年洗剪吹","dur":144,"showDate":"","src":"","pn":692,"nm":"X战警：天启","sc":9.1,"ver":"3D/IMAX 3D/中国巨幕/全景声","rt":"2016-06-03上映","img":"http://p1.meituan.net/165.220/movie/ba2386a26648c1875e3fc780950f0ae0144875.jpg","preSale":0,"time":"","id":246177},{"showInfo":"2016-06-30 下周四上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"金帝荣","star":"朴灿烈,袁姗姗,姜潮","cat":"爱情,喜剧","wish":88025,"3d":false,"imax":false,"snum":6707,"scm":"黑粉变新娘，这是闹哪样","dur":99,"showDate":"","src":"","pn":121,"nm":"所以\u2026\u2026和黑粉结婚了","sc":0,"ver":"2D","rt":"下周四上映","img":"http://p1.meituan.net/165.220/movie/79311d41bea3be8f1677c0b69a5868fa289298.jpg","preSale":1,"time":"","id":343379},{"showInfo":"今天48家影院放映90场","cnms":0,"sn":0,"late":false,"vd":"","dir":"程孝泽","star":"彭于晏,郭采洁,杨子姗","cat":"爱情,运动,剧情","wish":12564,"3d":false,"imax":false,"snum":363,"scm":"真爱在身旁，挥拳为理想","dur":103,"showDate":"","src":"","pn":49,"nm":"近在咫尺的爱恋","sc":7.5,"ver":"2D","rt":"本周五上映","img":"http://p1.meituan.net/165.220/movie/a70da0aefb82886efbae696181e966ff620085.jpg","preSale":0,"time":"","id":57217},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"申太罗","star":"李敏镐,钟汉良,唐嫣","cat":"动作,喜剧,剧情,悬疑","wish":120344,"3d":true,"imax":false,"snum":4070,"scm":"编外铁饭碗，收钱抓逃犯","dur":105,"showDate":"","src":"","pn":196,"nm":"赏金猎人","sc":0,"ver":"2D/3D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/45f8587cb425a01c9a279082772a8bd0379497.jpg","preSale":1,"time":"","id":338506},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"王早","star":"林心如,何润东,金世佳","cat":"悬疑,惊悚,恐怖","wish":8433,"3d":true,"imax":false,"snum":422,"scm":"天涯号起航，失控大逃亡","dur":92,"showDate":"","src":"","pn":49,"nm":"魔轮","sc":0,"ver":"2D/3D","rt":"下周五上映","img":"http://p0.meituan.net/165.220/movie/1d44cc6185a7ee4ee1def1f3a0f5821a584683.jpg","preSale":1,"time":"","id":342741},{"showInfo":"今天18家影院放映20场","cnms":0,"sn":0,"late":false,"vd":"","dir":"姬雨","star":"胡影怡,朱璇,周骏","cat":"恐怖,惊悚","wish":20373,"3d":false,"imax":false,"snum":11900,"scm":"笔仙已玩坏，筷仙来作怪","dur":87,"showDate":"","src":"","pn":32,"nm":"筷仙","sc":4.4,"ver":"2D","rt":"2016-06-17上映","img":"http://p1.meituan.net/165.220/movie/31a325f0796cfebbab24776024eeec91100800.jpeg","preSale":0,"time":"","id":343597},{"showInfo":"2016-07-02 下周六上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"郑义","star":"朱可可,阿飞,夏倚轩","cat":"动画,科幻,冒险","wish":2758,"3d":false,"imax":false,"snum":84,"scm":"月球机器鸭，化身小飞鸭","dur":81,"showDate":"","src":"","pn":66,"nm":"丑小鸭历险记","sc":0,"ver":"2D","rt":"下周六上映","img":"http://p1.meituan.net/165.220/movie/0484f8ad5aec878112f1a04715d32b4e360620.jpg","preSale":1,"time":"","id":368271},{"showInfo":"今天10家影院放映11场","cnms":0,"sn":0,"late":false,"vd":"","dir":"吴天明","star":"陶泽如,郑伟,李岷城","cat":"历史,音乐,剧情","wish":3981,"3d":false,"imax":false,"snum":108722,"scm":"两代手艺人，唢呐感情深","dur":108,"showDate":"","src":"","pn":105,"nm":"百鸟朝凤","sc":9.2,"ver":"2D","rt":"2016-05-06上映","img":"http://p1.meituan.net/165.220/movie/ef968fd382173c45b0c2d10572ca10b8354896.jpg","preSale":0,"time":"","id":248260},{"showInfo":"今天6家影院放映11场","cnms":0,"sn":0,"late":false,"vd":"","dir":"詹姆斯·波宾","star":"约翰尼·德普,安妮·海瑟薇,米娅·华希科沃斯卡","cat":"奇幻,冒险,喜剧","wish":248677,"3d":true,"imax":true,"snum":223549,"scm":"镜中再梦游，拯救老朋友","dur":115,"showDate":"","src":"","pn":82,"nm":"爱丽丝梦游仙境2：镜中奇遇记","sc":8.6,"ver":"2D/3D/IMAX 3D/中国巨幕/全景声","rt":"2016-05-27上映","img":"http://p1.meituan.net/165.220/movie/dbcc3eabae012da794fe4b74ed1365fc659390.jpg","preSale":0,"time":"","id":248680},{"showInfo":"今天10家影院放映10场","cnms":0,"sn":0,"late":false,"vd":"","dir":"核桃","star":"奶茶,YOYO,CBI","cat":"动画,喜剧,冒险","wish":43480,"3d":true,"imax":false,"snum":18745,"scm":"贱萌牛头人，爆笑刷副本","dur":86,"showDate":"","src":"","pn":40,"nm":"我叫MT之山口山战记","sc":7.6,"ver":"3D/中国巨幕","rt":"2016-06-17上映","img":"http://p0.meituan.net/165.220/movie/73f6abbb90259d1fa8a88fa907ca3062587352.jpg","preSale":0,"time":"","id":246045},{"showInfo":"2016-06-27 下周一上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"苗月","star":"白威,陈瑾,王柠","cat":"剧情,历史,战争","wish":316,"3d":false,"imax":false,"snum":26,"scm":"誓死护宣言，智斗救火种","dur":114,"showDate":"","src":"","pn":4,"nm":"大火种","sc":0,"ver":"2D","rt":"下周一上映","img":"http://p1.meituan.net/165.220/movie/c8545c7bd1f6070f0d52a77bd0ddd8738720802.jpg","preSale":1,"time":"","id":337438},{"showInfo":"今天6家影院放映6场","cnms":0,"sn":0,"late":false,"vd":"","dir":"薛晓路","star":"汤唯,吴秀波,惠英红","cat":"爱情,剧情","wish":307383,"3d":false,"imax":false,"snum":444385,"scm":"异国心未远，书信寄情缘","dur":132,"showDate":"","src":"","pn":547,"nm":"北京遇上西雅图之不二情书","sc":8.5,"ver":"2D/中国巨幕","rt":"2016-04-29上映","img":"http://p1.meituan.net/165.220/movie/fe80bc40822d0a5f3168b73089c47d71101133.jpg","preSale":0,"time":"","id":247575},{"showInfo":"今天4家影院放映6场","cnms":0,"sn":0,"late":false,"vd":"","dir":"朱丹","star":"孙看,孙洪涛,刘忠元","cat":"剧情,历史,战争","wish":2034,"3d":false,"imax":false,"snum":105,"scm":"南口抗日战，疆场洒血汗","dur":98,"showDate":"","src":"","pn":26,"nm":"南口1937","sc":0,"ver":"2D","rt":"本周四上映","img":"http://p1.meituan.net/165.220/movie/f4b5d903571ec3c3fc952f86261b04fd227842.jpg","preSale":0,"time":"","id":368071},{"showInfo":"今天5家影院放映5场","cnms":0,"sn":0,"late":false,"vd":"","dir":"费格尔·雷利,克莱·凯蒂","star":"杰森·苏戴奇斯,乔什·盖德,丹尼·麦克布耐德","cat":"动画,奇幻,冒险","wish":123045,"3d":true,"imax":false,"snum":317954,"scm":"绿猪来偷蛋，鸟儿群奋战","dur":97,"showDate":"","src":"","pn":132,"nm":"愤怒的小鸟","sc":8.9,"ver":"3D/中国巨幕","rt":"2016-05-20上映","img":"http://p0.meituan.net/165.220/movie/b721e73740601799c065affb619d837e151622.jpg","preSale":0,"time":"","id":246188},{"showInfo":"今天3家影院放映3场","cnms":0,"sn":0,"late":false,"vd":"","dir":"叶伟信","star":"甄子丹,洪金宝,熊黛林","cat":"动作,历史,传记","wish":211,"3d":false,"imax":false,"snum":1031,"scm":"叶问搬香港，仇恨值渐长","dur":108,"showDate":"","src":"","pn":63,"nm":"叶问2：宗师传奇","sc":8.5,"ver":"2D","rt":"2010-04-27上映","img":"http://p0.meituan.net/165.220/movie/70/612717.jpg","preSale":0,"time":"","id":524},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"卫晓茼","star":"郜耀平,张志宏,姚安濂","cat":"剧情,历史","wish":612,"3d":false,"imax":false,"snum":130,"scm":"心中有信仰，少年变战士","dur":107,"showDate":"","src":"","pn":17,"nm":"党的女儿尹灵芝","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p0.meituan.net/165.220/movie/1dffa0a076e3514234f2029d6b29b3da157605.jpg","preSale":1,"time":"","id":672123},{"showInfo":"今天2家影院放映2场","cnms":0,"sn":0,"late":false,"vd":"","dir":"徐峥","star":"徐峥,王宝强,黄渤","cat":"喜剧","wish":22543,"3d":false,"imax":false,"snum":15633,"scm":"囧途太荒唐，甩不掉宝强","dur":105,"showDate":"","src":"","pn":232,"nm":"人再囧途之泰囧","sc":8.8,"ver":"2D","rt":"2012-12-12上映","img":"http://p0.meituan.net/165.220/movie/38/280521.jpg","preSale":0,"time":"","id":938},{"showInfo":"今天2家影院放映2场","cnms":0,"sn":0,"late":false,"vd":"","dir":"郭帆","star":"周冬雨,林更新,隋凯","cat":"剧情,爱情","wish":24267,"3d":false,"imax":false,"snum":73036,"scm":"初恋要结婚，回国忆青春","dur":98,"showDate":"","src":"","pn":88,"nm":"同桌的你","sc":8.6,"ver":"2D","rt":"2014-04-25上映","img":"http://p0.meituan.net/165.220/movie/__40811615__5745234.jpg","preSale":0,"time":"","id":78653},{"showInfo":"今天2家影院放映2场","cnms":0,"sn":0,"late":false,"vd":"","dir":"麦子","star":"徐洁儿,陈欢,陈炳强","cat":"恐怖,惊悚,悬疑","wish":3301,"3d":false,"imax":false,"snum":5335,"scm":"同学生日会，恶鬼来相会","dur":90,"showDate":"","src":"","pn":145,"nm":"死亡游戏","sc":5.8,"ver":"2D","rt":"2016-06-03上映","img":"http://p1.meituan.net/165.220/movie/c3eec836300c5a9f0429d96921aa168472038.jpg","preSale":0,"time":"","id":346286},{"showInfo":"2016-07-01 下周五上映","cnms":0,"sn":0,"late":false,"vd":"","dir":"冼杞然","star":"窦骁,约瑟夫·费因斯,娄宇健","cat":"剧情,战争,历史","wish":4783,"3d":false,"imax":false,"snum":65,"scm":"辛德勒名单，推出中国版","dur":108,"showDate":"","src":"","pn":122,"nm":"终极胜利","sc":0,"ver":"2D","rt":"下周五上映","img":"http://p1.meituan.net/165.220/movie/0c93b5987ae440497dd985982aad1c4b233221.jpg","preSale":1,"time":"","id":341123},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"潘安子","star":"周冬雨,陈晓,赵丽颖","cat":"剧情,爱情,古装","wish":3080,"3d":false,"imax":false,"snum":2508,"scm":"唯美清宫戏，阴谋不见底","dur":115,"showDate":"","src":"","pn":36,"nm":"宫锁沉香","sc":7.8,"ver":"2D","rt":"2013-08-13上映","img":"http://p0.meituan.net/165.220/movie/__18327195__7709930.jpg","preSale":0,"time":"","id":78176},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"杨瑾","star":"李书晨,王琛,卫永绍","cat":"动画,家庭","wish":184,"3d":false,"imax":false,"snum":54,"scm":"离家偷玩耍，乡下过暑假","dur":108,"showDate":"","src":"","pn":98,"nm":"有人赞美聪慧,有人则不","sc":7.8,"ver":"2D","rt":"2013-11待定","img":"http://p0.meituan.net/165.220/movie/__16523889__6590864.jpg","preSale":0,"time":"2013-11","id":78224},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"丁晟","star":"刘烨,成龙,景甜","cat":"剧情,动作,犯罪","wish":14520,"3d":true,"imax":true,"snum":19130,"scm":"女儿乱交友，老爹来出头","dur":108,"showDate":"","src":"","pn":44,"nm":"警察故事2013","sc":8,"ver":"2D/IMAX 3D","rt":"2013-12-24上映","img":"http://p0.meituan.net/165.220/movie/__29561506__7176229.jpg","preSale":0,"time":"","id":77192},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"陈思诚","star":"梁家辉,刘嘉玲,王学兵","cat":"爱情","wish":12286,"3d":false,"imax":false,"snum":28683,"scm":"恋爱分五段，激情不会散","dur":121,"showDate":"","src":"","pn":145,"nm":"北京爱情故事","sc":8,"ver":"2D","rt":"2014-02-14上映","img":"http://p0.meituan.net/165.220/movie/__33456063__6265360.jpg","preSale":0,"time":"","id":78324},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"陈正道","star":"徐峥,莫文蔚,吕中","cat":"剧情,悬疑,惊悚","wish":11396,"3d":false,"imax":false,"snum":46528,"scm":"催眠治心伤，侧击猜真相","dur":102,"showDate":"","src":"","pn":78,"nm":"催眠大师","sc":9,"ver":"2D","rt":"2014-04-29上映","img":"http://p1.meituan.net/165.220/movie/__39582689__9041214.jpg","preSale":0,"time":"","id":78672},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"黄真真","star":"陈意涵,薛凯琪,杨子姗","cat":"剧情,爱情","wish":30224,"3d":false,"imax":false,"snum":68736,"scm":"永远好闺蜜，渣男靠边去","dur":118,"showDate":"","src":"","pn":203,"nm":"闺蜜","sc":8,"ver":"2D","rt":"2014-07-30上映","img":"http://p1.meituan.net/165.220/movie/__48665963__9098444.jpg","preSale":0,"time":"","id":78772},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"卢恒宇,李姝洁","star":"张杰,皇贞季,宝木中阳","cat":"动画,喜剧,奇幻","wish":25775,"3d":false,"imax":false,"snum":122597,"scm":"男主有点拽，二次元崩坏","dur":100,"showDate":"","src":"","pn":79,"nm":"十万个冷笑话","sc":8.2,"ver":"2D","rt":"2014-12-31上映","img":"http://p1.meituan.net/165.220/movie/56cb96b2c6df95b66db4ce719993d5d0666565.jpg","preSale":0,"time":"","id":246067},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"胡笳,岑俊义","star":"杨颖,王宝强,李晨","cat":"喜剧,冒险,动作","wish":49025,"3d":false,"imax":false,"snum":361066,"scm":"跑男再出发，集体游三亚","dur":88,"showDate":"","src":"","pn":118,"nm":"奔跑吧！兄弟","sc":4.7,"ver":"2D","rt":"2015-01-30上映","img":"http://p0.meituan.net/165.220/movie/85f0d9a604e26be5523a848269346697296774.jpg","preSale":0,"time":"","id":246316},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"蒂姆·肯德尔","star":"赵薇,黄晓明,佟大为","cat":"喜剧,动作,冒险","wish":64522,"3d":false,"imax":false,"snum":282729,"scm":"逗比好基友，遇到坏导游","dur":119,"showDate":"","src":"","pn":273,"nm":"横冲直撞好莱坞","sc":7.7,"ver":"2D/中国巨幕","rt":"2015-06-26上映","img":"http://p1.meituan.net/165.220/movie/1c06c9055cb3990e76ee93c2c70789bb145247.jpg","preSale":0,"time":"","id":246178},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"彭发","star":"徐娇,张兆辉,毕秀茹","cat":"惊悚,恐怖","wish":34072,"3d":true,"imax":false,"snum":20071,"scm":"古宅心慌慌，通灵少女忙","dur":87,"showDate":"","src":"","pn":101,"nm":"通灵之六世古宅","sc":4.5,"ver":"2D/3D/中国巨幕","rt":"2015-07-24上映","img":"http://p1.meituan.net/165.220/movie/575f1cee324dd206d981b4dfcb1ec1bd1381691.jpg","preSale":0,"time":"","id":246249},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"田蒙","star":"张翰,陈乔恩,施予斐","cat":"喜剧,爱情","wish":78433,"3d":false,"imax":false,"snum":83822,"scm":"校草撞菠菜，青春已不再","dur":92,"showDate":"","src":"","pn":172,"nm":"既然青春留不住","sc":8.2,"ver":"2D","rt":"2015-10-23上映","img":"http://p0.meituan.net/165.220/movie/90422e83536df2153ecc57ff9703e99c1111187.jpg","preSale":0,"time":"","id":246540},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"阿甘","star":"郭涛,刘心悠,李灿森","cat":"喜剧","wish":107,"3d":false,"imax":false,"snum":93,"scm":"姚晨王宝强，爆笑太疯狂","dur":97,"showDate":"","src":"","pn":3,"nm":"大电影2.0之两个傻瓜的荒唐事","sc":5.6,"ver":"2D","rt":"2007-12-21上映","img":"http://p1.meituan.net/165.220/movie/76ac89dad45ac25c676246dc92794b03318660.png","preSale":0,"time":"","id":536},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"张琦","star":"杨幂,刘恺威,周秀娜","cat":"喜剧,爱情,悬疑","wish":280,"3d":false,"imax":false,"snum":754,"scm":"假戏变真做，幂威秀爱火","dur":95,"showDate":"","src":"","pn":300,"nm":"Hold住爱","sc":5.9,"ver":"2D","rt":"2012-08-23上映","img":"http://p0.meituan.net/165.220/movie/29/274730.jpg","preSale":0,"time":"","id":729},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"管虎","star":"黄渤,刘烨,张涵予","cat":"剧情,喜剧,动作","wish":10110,"3d":false,"imax":false,"snum":5758,"scm":"三男一台戏，客栈解僵局","dur":108,"showDate":"","src":"","pn":281,"nm":"厨子戏子痞子","sc":8.3,"ver":"2D","rt":"2013-03-29上映","img":"http://p0.meituan.net/165.220/movie/__7277442__6071578.jpg","preSale":0,"time":"","id":969},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"梁德森","star":"钟丽缇,柳岩,李灿森","cat":"悬疑,惊悚","wish":771,"3d":false,"imax":false,"snum":182,"scm":"惊心动魄的寻亲之旅","dur":90,"showDate":"","src":"","pn":22,"nm":"人间蒸发","sc":7,"ver":"2D","rt":"2013-04-28上映","img":"http://p0.meituan.net/165.220/movie/__6680785__5992311.jpg","preSale":0,"time":"","id":78094},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"叶伟民","star":"吴镇宇,林心如,杨祐宁","cat":"剧情,悬疑,恐怖","wish":36817,"3d":true,"imax":false,"snum":122347,"scm":"古宅阴森处，少妇诉凄苦","dur":90,"showDate":"","src":"","pn":98,"nm":"京城81号","sc":5,"ver":"2D/3D","rt":"2014-07-18上映","img":"http://p1.meituan.net/165.220/movie/__48584093__3431812.jpg","preSale":0,"time":"","id":78267},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"苏有朋","star":"陈都灵,欧豪,杨洋","cat":"爱情","wish":82116,"3d":false,"imax":false,"snum":538026,"scm":"青春要恋爱，别忘堕堕胎","dur":117,"showDate":"","src":"","pn":315,"nm":"左耳","sc":7.9,"ver":"2D","rt":"2015-04-24上映","img":"http://p0.meituan.net/165.220/movie/da55f29972ec0b9692ba7055905892c8123092.jpg","preSale":0,"time":"","id":246210},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"邱浩强","star":"李思娴,郭亚维,宋磊","cat":"动画,喜剧,冒险","wish":27716,"3d":false,"imax":false,"snum":21955,"scm":"国产美人鱼，俩海盗来袭","dur":85,"showDate":"","src":"","pn":38,"nm":"美人鱼之海盗来袭","sc":5.5,"ver":"2D","rt":"2015-07-31上映","img":"http://p0.meituan.net/165.220/movie/7e15953aa8bc5be4e7bff00913426a06920765.jpg","preSale":0,"time":"","id":248550},{"showInfo":"今天暂无场次","cnms":0,"sn":0,"late":false,"vd":"","dir":"秦小珍","star":"刘畅,白百何,唐艺昕","cat":"剧情,爱情","wish":120703,"3d":false,"imax":false,"snum":78361,"scm":"文艺大暖男，不二常相伴","dur":121,"showDate":"","src":"","pn":564,"nm":"陪安东尼度过漫长岁月","sc":7.8,"ver":"2D","rt":"2015-11-13上映","img":"http://p1.meituan.net/165.220/movie/9f0d994eafbf52e7b0145b7746d1d881246635.jpg","preSale":0,"time":"","id":246583},{"showInfo":"今天1家影院放映1场","cnms":0,"sn":0,"late":false,"vd":"","dir":"朴裕焕","star":"雷佳音,夏梓桐,李菁","cat":"悬疑,喜剧,犯罪","wish":4154,"3d":false,"imax":false,"snum":2956,"scm":"宿醉陷疑案，奋力查嫌犯","dur":94,"showDate":"","src":"","pn":47,"nm":"记忆碎片","sc":6.9,"ver":"2D","rt":"2016-06-03上映","img":"http://p1.meituan.net/165.220/movie/ea15948d63d527d092a70e39cbb74636357253.jpg","preSale":0,"time":"","id":345923}]
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
         * showInfo : 今天150家影院放映1999场
         * cnms : 0
         * sn : 0
         * late : false
         * vd :
         * dir : 朱浩伟
         * star : 杰西·艾森伯格,马克·鲁法洛,伍迪·哈里森
         * cat : 动作,喜剧,惊悚
         * wish : 513937
         * 3d : true
         * imax : false
         * snum : 28714
         * scm : 周董变魔术，敌人挡不住
         * dur : 126
         * showDate :
         * src :
         * pn : 244
         * nm : 惊天魔盗团2
         * sc : 8.7
         * ver : 2D/3D/中国巨幕
         * rt : 本周五上映
         * img : http://p1.meituan.net/165.220/movie/2e0b5b5c6c086cc93068fbc212f9f6b0142193.jpg
         * preSale : 0
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

        public static class MoviesBean {
            private String showInfo;
            private int cnms;
            private int sn;
            private boolean late;
            private String vd;
            private String dir;
            private String star;
            private String cat;
            private int wish;
            @SerializedName("3d")
            private boolean value3d;
            private boolean imax;
            private int snum;
            private String scm;
            private int dur;
            private String showDate;
            private String src;
            private int pn;
            private String nm;
            private double sc;
            private String ver;
            private String rt;
            private String img;
            private int preSale;
            private String time;
            private int id;

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
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

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
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

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
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
}

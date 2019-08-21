package com.sky.categorydemo.repo.model;

import java.util.List;

/**
 * 描述：
 *
 * @author renpeng
 * @since 2019/8/20
 */
public class CategoryBean {

    public static class LevelonesBean {
        /**
         * id : 2007
         * picurl : http://3gimg.qq.com/BabytingWeb/yunying/course/508fe4c41fd027b6ee9961f6a738a970.png
         * showtype : 1
         * strName : 儿歌童谣
         * vecCategorys : [{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/bd6a01c134467f5e85292da45049384e.jpg","strName":"热门儿歌","uID":1138},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/dc1da973890589f0a24027f82f99af7c.jpg","strName":"儿歌童谣","uID":1141},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/f4f61fece52c7669139cb6cf46945b80.jpg","strName":"早教儿歌","uID":1140}]
         */

        private int id;
        private String picurl;
        private int showtype;
        private String strName;
        private List<CategoryItem> vecCategorys;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public int getShowtype() {
            return showtype;
        }

        public void setShowtype(int showtype) {
            this.showtype = showtype;
        }

        public String getStrName() {
            return strName;
        }

        public void setStrName(String strName) {
            this.strName = strName;
        }

        public List<CategoryItem> getVecCategorys() {
            return vecCategorys;
        }

        public void setVecCategorys(List<CategoryItem> vecCategorys) {
            this.vecCategorys = vecCategorys;
        }

    }


    private List<MainCategory> mainCategoryList;

    public List<MainCategory> getMainCategoryList() {
        return mainCategoryList;
    }

    public void setMainCategoryList(List<MainCategory> mainCategoryList) {
        this.mainCategoryList = mainCategoryList;
    }

    public static class MainCategory {
        /**
         * levelones : [{"id":2007,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/508fe4c41fd027b6ee9961f6a738a970.png","showtype":1,"strName":"儿歌童谣","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/bd6a01c134467f5e85292da45049384e.jpg","strName":"热门儿歌","uID":1138},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/dc1da973890589f0a24027f82f99af7c.jpg","strName":"儿歌童谣","uID":1141},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/f4f61fece52c7669139cb6cf46945b80.jpg","strName":"早教儿歌","uID":1140}]},{"id":2008,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/c61907678d5132ddc5bafb47cfc53a40.png","showtype":1,"strName":"童话寓言","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/c5063a55a3feb6826f24a68f7ee5de58.jpg","strName":"经典童话","uID":1142},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/dd3764d41960150fdcb014eb5914b1b8.jpg","strName":"寓言故事","uID":1143},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/da56959645ad85868f7a28f50b75bc75.jpg","strName":"当代童话","uID":1144}]},{"id":2009,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/322d4d15a337ac42ecd38a5fe0e75381.png","showtype":1,"strName":"国学历史","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/359ce26a6c3c1a4955caf12e7b2229cb.jpg","strName":"古诗","uID":1154},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/83e81e7bb8882e7e53b36c57b921157b.jpg","strName":"经典名著","uID":1153},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/52a5cd4cb78c70784bc8f0de2d56d80c.jpg","strName":"国学启蒙","uID":1152},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/f2f8fa11d7775b176b4d223dec58f738.jpg","strName":"民间故事","uID":1151},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/d3257a0280b8779df398ed1f9e2f67a3.jpg","strName":"成语故事","uID":1150},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/cbd899a3e7cd2d95bcf8619d099b798f.jpg","strName":"世界名人","uID":1149},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/31dc4ce320e90c4415994eb9032f8c3b.jpg","strName":"历史故事","uID":1148}]},{"id":2010,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/67cb009101950a4ae06f2f51529ac884.png","showtype":1,"strName":"科普益智","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/541c63afb056acae37cb39a90aa7f95f.jpg","strName":"生活安全","uID":1159},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/724ee783a7146afce1fdf850340ad1e9.jpg","strName":"自然知识","uID":1158},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/22cc8454496f249d0b69d68cb82c6b4d.jpg","strName":"文化风俗","uID":1157},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/5cc11837791c019902aaad1bdf60a965.jpg","strName":"天文地理","uID":1156},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/32c4f8e6bcc594e1ab375fb441daf234.jpg","strName":"奇趣百科","uID":1155}]},{"id":2011,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/049ed54d3e42bcfae70f17d652ac7009.png","showtype":1,"strName":"绘本故事","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/466e9d00b16abd545cb68d0a72381441.jpg","strName":"亲子成长","uID":1161},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/4ea211faaff4cb60434c721ac889fcdc.jpg","strName":"想象力","uID":1162},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/e4b472d7f8f4fb1a2830724430c5033d.jpg","strName":"品格培养","uID":1163},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/a3f15ccf4876eec89ae08f3601bfc3a2.jpg","strName":"社交能力","uID":1165},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/292614d956ca60dd85557f667da98ea1.jpg","strName":"情商培养","uID":1166}]},{"id":2003,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/4da1dd12aeae9c600a0b5198bdf60227.png","showtype":1,"strName":"母婴育儿","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/eb908d06619edfacd41e21d031046996.jpg","strName":"育儿知识","uID":1178},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/b19192db9e1216e2a0b3fa3b4869cca4.jpg","strName":"轻音乐","uID":1177}]},{"id":2004,"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/925183d93d76d0a8b626e9c58f923e34.png","showtype":1,"strName":"连载故事","vecCategorys":[{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/a6b6a45fdf50fbbb654771161628d281.jpg","strName":"经典故事","uID":1179},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/91064f2f9b184ef159501d7fd5e64d1b.jpg","strName":"奇幻冒险","uID":1180},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/d1a6214242c590b974c8ac4cfd760ba4.jpg","strName":"少儿军事","uID":1181},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/1d8d92535f45f4c17ad00d6cda16fcea.jpg","strName":"魔法科幻","uID":1182},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/f25681d9ce003dda89cbaa97deb00415.jpg","strName":"校园故事","uID":1183},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/1f7e1e3467055aead5380223d55b31e8.jpg","strName":"动物故事","uID":1184},{"picurl":"http://3gimg.qq.com/BabytingWeb/yunying/course/7788c3b1a22fde26b74b5ae624d3eb9d.jpg","strName":"侦探推理","uID":1185}]}]
         * picurl : http://3gimg.qq.com/BabytingWeb/yunying/course/d52cf694eb3bb90fa146e5e607c00a4e.png
         * strName : 儿歌故事
         */

        private String picurl;
        private String strName;
        private List<LevelonesBean> levelones;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getStrName() {
            return strName;
        }

        public void setStrName(String strName) {
            this.strName = strName;
        }

        public List<LevelonesBean> getLevelones() {
            return levelones;
        }

        public void setLevelones(List<LevelonesBean> levelones) {
            this.levelones = levelones;
        }


    }
}

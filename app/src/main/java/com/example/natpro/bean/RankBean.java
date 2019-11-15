package com.example.natpro.bean;

import java.util.List;

public class RankBean {


    /**
     * curPage : 1
     * datas : [{"coinCount":3484,"level":35,"rank":1,"userId":20382,"username":"g**eii"},{"coinCount":3441,"level":35,"rank":2,"userId":27535,"username":"1**08491840"},{"coinCount":3178,"level":32,"rank":3,"userId":3559,"username":"A**ilEyon"},{"coinCount":2656,"level":27,"rank":4,"userId":1260,"username":"于**家的吴蜀黍"},{"coinCount":2494,"level":25,"rank":5,"userId":833,"username":"w**lwaywang6"},{"coinCount":2467,"level":25,"rank":6,"userId":9621,"username":"S**24n"},{"coinCount":2463,"level":25,"rank":7,"userId":1534,"username":"j**gbin"},{"coinCount":2453,"level":25,"rank":8,"userId":863,"username":"m**qitian"},{"coinCount":2403,"level":25,"rank":9,"userId":15603,"username":"r**eryxx"},{"coinCount":2353,"level":24,"rank":10,"userId":2068,"username":"i**Cola"},{"coinCount":2352,"level":24,"rank":11,"userId":7541,"username":"l**64301766"},{"coinCount":2347,"level":24,"rank":12,"userId":23244,"username":"a**ian"},{"coinCount":2343,"level":24,"rank":13,"userId":1871,"username":"l**shifu"},{"coinCount":2337,"level":24,"rank":14,"userId":1440,"username":"w**.wanandroid.com"},{"coinCount":2332,"level":24,"rank":15,"userId":3753,"username":"S**phenCurry"},{"coinCount":2332,"level":24,"rank":16,"userId":2,"username":"x**oyang"},{"coinCount":2329,"level":24,"rank":17,"userId":7809,"username":"1**23822235"},{"coinCount":2322,"level":24,"rank":18,"userId":27596,"username":"1**5915093@qq.com"},{"coinCount":2318,"level":24,"rank":19,"userId":7590,"username":"陈**啦啦啦"},{"coinCount":2295,"level":23,"rank":20,"userId":6142,"username":"c**huah"},{"coinCount":2295,"level":23,"rank":21,"userId":10010,"username":"c**01220122"},{"coinCount":2254,"level":23,"rank":22,"userId":27602,"username":"l**hehan"},{"coinCount":2254,"level":23,"rank":23,"userId":7710,"username":"i**Cola7"},{"coinCount":2235,"level":23,"rank":24,"userId":22832,"username":"7**502274@qq.com"},{"coinCount":2214,"level":23,"rank":25,"userId":14829,"username":"l**changwen"},{"coinCount":2211,"level":23,"rank":26,"userId":2199,"username":"M**459"},{"coinCount":2196,"level":22,"rank":27,"userId":28454,"username":"c**xzxzc"},{"coinCount":2175,"level":22,"rank":28,"userId":14032,"username":"M**eor"},{"coinCount":2175,"level":22,"rank":29,"userId":8152,"username":"t**g111"},{"coinCount":2160,"level":22,"rank":30,"userId":28694,"username":"c**ng0218"}]
     * offset : 0
     * over : false
     * pageCount : 290
     * size : 30
     * total : 8676
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * coinCount : 3484
         * level : 35
         * rank : 1
         * userId : 20382
         * username : g**eii
         */

        private int coinCount;
        private int level;
        private int rank;
        private int userId;
        private String username;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

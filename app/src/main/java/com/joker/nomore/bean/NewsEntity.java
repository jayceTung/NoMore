package com.joker.nomore.bean;

import java.util.List;

/**
 * Created by Joker on 2015/10/19.
 */
public class NewsEntity {

    /**
     * detail : [{"title":"分享视频 ","digg_count":1,"publish_time":0,"source":"微博视频","group_id":"4006917770","create_time":0,"behot_time":1425185036000,"repin_count":1,"bury_count":2015,"article_url":"xxx"}]
     * desc : null
     * status : 000000
     */
    private List<DetailEntity> detail;
    private String desc;
    private String status;

    public void setDetail(List<DetailEntity> detail) {
        this.detail = detail;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DetailEntity> getDetail() {
        return detail;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public class DetailEntity {
        /**
         * title : 分享视频
         * digg_count : 1
         * publish_time : 0
         * source : 微博视频
         * group_id : 4006917770
         * create_time : 0
         * behot_time : 1425185036000
         * repin_count : 1
         * bury_count : 2015
         * article_url : xxx
         */
        private String title;
        private int digg_count;
        private int publish_time;
        private String source;
        private String group_id;
        private int create_time;
        private long behot_time;
        private int repin_count;
        private int bury_count;
        private String article_url;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public void setPublish_time(int publish_time) {
            this.publish_time = publish_time;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public void setBehot_time(long behot_time) {
            this.behot_time = behot_time;
        }

        public void setRepin_count(int repin_count) {
            this.repin_count = repin_count;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public String getTitle() {
            return title;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public int getPublish_time() {
            return publish_time;
        }

        public String getSource() {
            return source;
        }

        public String getGroup_id() {
            return group_id;
        }

        public int getCreate_time() {
            return create_time;
        }

        public long getBehot_time() {
            return behot_time;
        }

        public int getRepin_count() {
            return repin_count;
        }

        public int getBury_count() {
            return bury_count;
        }

        public String getArticle_url() {
            return article_url;
        }
    }
}

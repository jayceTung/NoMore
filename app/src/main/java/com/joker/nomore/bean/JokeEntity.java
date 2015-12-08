package com.joker.nomore.bean;

import java.util.List;

/**
 * Created by Joker on 2015/10/19.
 */
public class JokeEntity {

    /**
     * detail : [{"content":"xxx","id":1115,"author":"xxx","picUrl":"","status":"1","xhid":"90851"},{"content":"xxx","id":1110,"author":"xxx","picUrl":"","status":"1","xhid":"90850"}]
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

    @Override
    public String toString() {
        return getDetail().get(0).toString();
    }

    public class DetailEntity {
        /**
         * content : xxx
         * id : 1115
         * author : xxx
         * picUrl :
         * status : 1
         * xhid : 90851
         */
        private String content;
        private int id;
        private String author;
        private String picUrl;
        private String status;
        private String xhid;

        public void setContent(String content) {
            this.content = content;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setXhid(String xhid) {
            this.xhid = xhid;
        }

        public String getContent() {
            return content;
        }

        public int getId() {
            return id;
        }

        public String getAuthor() {
            return author;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public String getStatus() {
            return status;
        }

        public String getXhid() {
            return xhid;
        }

        @Override
        public String toString() {
            return author;
        }
    }
}

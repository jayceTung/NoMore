package com.joker.nomore.bean;

import java.util.List;

/**
 * Created by Joker on 2015/12/10.
 */
public class ArticleEntity {

    /**
     * detail : [{"my_abstract":"贫穷的表面原因是因为财富的匮乏，但其深层的原因是因为社会机会、资源的不平等。","title":"贫穷的本质在于缺乏社会资源","article_type":"2","article_url":"http://sunxiaoji.baijia.baidu.com/article/49755"}]
     * desc : null
     * status : 000000
     */
    private List<DetailEntity> detail;
    private String desc;
    private String status;

    public List<DetailEntity> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailEntity> detail) {
        this.detail = detail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class DetailEntity {
        /**
         * my_abstract : 贫穷的表面原因是因为财富的匮乏，但其深层的原因是因为社会机会、资源的不平等。
         * title : 贫穷的本质在于缺乏社会资源
         * article_type : 2
         * article_url : http://sunxiaoji.baijia.baidu.com/article/49755
         */
        private String my_abstract;
        private String title;
        private String article_type;
        private String article_url;

        public String getMy_abstract() {
            return my_abstract;
        }

        public void setMy_abstract(String my_abstract) {
            this.my_abstract = my_abstract;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getArticle_type() {
            return article_type;
        }

        public void setArticle_type(String article_type) {
            this.article_type = article_type;
        }

        public String getArticle_url() {
            return article_url;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }
    }
}

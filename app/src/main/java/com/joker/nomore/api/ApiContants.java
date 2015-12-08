package com.joker.nomore.api;

/**
 * Created by Joker on 2015/10/14.
 */
public class ApiContants {
    public static final class Uri {
        public static final String host = "http://api.1-blog.com/biz/bizserver";

    }

    public static final class Path {
        public static final String jokes = "/xiaohua/list.do?";
        public static final String news = "/news/list.do?";
        public static final String weather = "/weather/list.do?";
        public static final String articles = "/article/list.do?";
        public static final String search = "/search/list.do?";
    }

    public static final class append {
        public static final String page = "page=";
    }

    public static final class StatusCode {
        public static final String success = "000000";
    }
}

package com.example.jxgg.mynewsapp.api;

/**
 * Created by JXGG on 2017/1/15.
 */

public class Httpurls {
    public final static String HOST = "v.juhe.cn/toutiao/index";
    public final static String HOST2 = "v.juhe.cn/weixin/query";
    public final static String DOUYU = "capi.douyucdn.cn/api/v1/live";
    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";
    public final static String TYPE = "type=";
    public final static String APPKEY_TOP = "6ca7c3fa878c6c759d1b3e1581b307f4";//新闻头条
    public final static String APPKEY_WX = "cdb033f9036eacbaa21d43cf7539e3bd";//微信精选
    private final static String URL_SPLITTER = "?";
    private final static String URL_UNDERLINE = "_";
    public final static String URL_API_HOST = HTTP + HOST ;
    public final static String URL_API_HOST2 = HTTP + HOST2 ;
    public final static String URL_API_HOST3 = HTTP + DOUYU ;

    /*====================== 公用接口 ====================================*/
    public final static String GET_NEWS = URL_API_HOST;
    public final static String WX = URL_API_HOST2;
    public final static String GETDOUYU = URL_API_HOST3;
}

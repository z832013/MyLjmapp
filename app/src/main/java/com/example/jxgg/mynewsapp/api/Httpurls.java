package com.example.jxgg.mynewsapp.api;

/**
 * Created by JXGG on 2017/1/15.
 */

public class Httpurls {
    public final static String HOST = "v.juhe.cn/toutiao/index";
    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";
    public final static String TYPE = "type=";
    public final static String APPKEY_TOP = "6ca7c3fa878c6c759d1b3e1581b307f4";//新闻头条
    public final static String APPKEY_WX = "&key=cdb033f9036eacbaa21d43cf7539e3bd";//微信精选
    private final static String URL_SPLITTER = "?";
    private final static String URL_UNDERLINE = "_";
    //http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
    //http://v.juhe.cn/toutiao/index?type=top&key=6ca7c3fa878c6c759d1b3e1581b307f4
    public final static String URL_API_HOST = HTTP + HOST ;

    /*====================== 公用接口 ====================================*/
    public final static String GET_NEWS = URL_API_HOST;
    public final static String AAAAA = "http://api.jxyapp.cn/video/getDetail?cid=5&aid=79";
}

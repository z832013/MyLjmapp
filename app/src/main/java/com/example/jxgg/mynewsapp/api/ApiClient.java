package com.example.jxgg.mynewsapp.api;

import com.sedigital.selogutil.SELogUtil;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by JXGG on 2017/1/15.
 */

public class ApiClient {
    private static ApiClient myapi;
    public static ApiClient getapi(){
        if (myapi==null){
            myapi=new ApiClient();
        }
        return myapi;
    }


    public void GetTopNews(String tab,HttpCallBack callBack){
        RequestParams params=new RequestParams(Httpurls.GET_NEWS);
        params.addBodyParameter("type", tab);
        params.addBodyParameter("key", Httpurls.APPKEY_TOP);
        HttpTools.post(params,callBack);
    }

    /**
     * 微信精选
     * @param pon 		当前页数，默认1
     * @param callBack
     */
    public void Getwx(String pon,HttpCallBack callBack){
        RequestParams params=new RequestParams(Httpurls.WX);
        params.addBodyParameter("pno", pon);
        params.addBodyParameter("ps", "20");
        params.addBodyParameter("dtype", "json");
        params.addBodyParameter("key", Httpurls.APPKEY_WX);
        HttpTools.post(params,callBack);
    }
}

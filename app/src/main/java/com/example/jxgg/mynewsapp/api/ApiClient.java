package com.example.jxgg.mynewsapp.api;

import com.sedigital.selogutil.SELogUtil;

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
        SELogUtil.logE("我的地址："+params);
        HttpTools.post(params,callBack);
    }
}

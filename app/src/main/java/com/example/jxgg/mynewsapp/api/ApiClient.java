package com.example.jxgg.mynewsapp.api;

import com.sedigital.selogutil.SELogUtil;

import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

/**
 * Created by JXGG on 2017/1/15.
 */

public class ApiClient {
    private static ApiClient myapi;

    public static ApiClient getapi() {
        if (myapi == null) {
            myapi = new ApiClient();
        }
        return myapi;
    }


    public void GetTopNews(String tab, HttpCallBack callBack) {
        RequestParams params = new RequestParams(Httpurls.GET_NEWS);
        params.addBodyParameter("type", tab);
        params.addBodyParameter("key", Httpurls.APPKEY_TOP);
        LogUtil.e("返回地址：" + params);
        HttpTools.post(params, callBack);
    }

    /**
     * 微信精选
     *
     * @param pon      当前页数，默认1
     * @param callBack
     */
    public void Getwx(String pon, HttpCallBack callBack) {
        RequestParams params = new RequestParams(Httpurls.WX);
        params.addBodyParameter("pno", pon);
        params.addBodyParameter("ps", "20");
        params.addBodyParameter("dtype", "json");
        params.addBodyParameter("key", Httpurls.APPKEY_WX);
        HttpTools.post(params, callBack);
    }

    public void GetVideo(HttpCallBack callBack) {
        RequestParams params = new RequestParams(
                "http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-104&message_cursor=-1");
        HttpTools.post(params, callBack);
    }

    /**
     * 斗鱼列表
     *
     * @param offset
     * @param callBack
     */
    public void GetDouYu(String offset, HttpCallBack callBack) {
        RequestParams params = new RequestParams(Httpurls.GETDOUYU);
        params.addBodyParameter("limit", "20");
        params.addBodyParameter("offset", offset);
        LogUtil.e("返回地址2：" + params);
        HttpTools.post(params, callBack);
    }
}

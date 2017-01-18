package com.example.jxgg.mynewsapp.api;

/**
 * Created by Administrator on 2016/2/16.
 */
public interface HttpCallBack {

    void onSuccess(String response);

    void onError(Throwable ex, boolean isOnCallback);

}

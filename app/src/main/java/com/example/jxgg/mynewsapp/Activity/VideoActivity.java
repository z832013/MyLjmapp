package com.example.jxgg.mynewsapp.Activity;

import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.api.ApiClient;
import com.example.jxgg.mynewsapp.api.HttpCallBack;
import com.example.jxgg.mynewsapp.base.BaseActivity;
import com.example.jxgg.mynewsapp.model.JsonResult;
import com.example.jxgg.mynewsapp.model.VideoModel;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;

import java.io.IOException;

@ContentView(R.layout.activity_video)
public class VideoActivity extends BaseActivity {
    @Override
    protected void initView(Bundle savedInstanceState) {
        getvideo();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    private void getvideo() {
        ApiClient.getapi().GetVideo(new HttpCallBack() {
            @Override
            public void onSuccess(String response) {
                LogUtil.e("返回的视频：" + response);
                try {
                    JsonResult result = JsonResult.parse(response);
                    LogUtil.e("视频返回：" + result.getMessage() + "，返回信息：" + result.getData());
                    VideoModel model= JSON.parseObject(result.getData(),VideoModel.class);
                    LogUtil.e("视频输出："+model.getData().get(0).getGroup().getLarge_cover().getUrl_list().get(0).getUrl());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }
}

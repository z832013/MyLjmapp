package com.example.jxgg.mynewsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.example.jxgg.mynewsapp.Activity.WebActivity;
import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.abadper.WxNewsAdapter;
import com.example.jxgg.mynewsapp.api.ApiClient;
import com.example.jxgg.mynewsapp.api.HttpCallBack;
import com.example.jxgg.mynewsapp.base.BaseFragment;
import com.example.jxgg.mynewsapp.model.JsonResult;
import com.example.jxgg.mynewsapp.model.WxModel;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

/**
 * Created by JXGG on 2017/1/19.
 */
@ContentView(R.layout.fragment_wx_news)
public class WxFragment extends BaseFragment implements XRecyclerView.LoadingListener {
    @ViewInject(R.id.xRecyclerView)
    private XRecyclerView xRecyclerView;
    WxNewsAdapter mAdapter;
    private WxModel wxModel;
    @Override
    protected void initView(Bundle savedInstanceState) {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new WxNewsAdapter(xRecyclerView);

        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
//        xRecyclerView.setArrowImageView(R.mipmap.icon_refresh);//下拉刷新图片
        xRecyclerView.setAdapter(mAdapter);
        onRefresh();
    }

    @Override
    protected void setListener() {
        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(false);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    private void getwx(String pon) {
        ApiClient.getapi().Getwx(pon, new HttpCallBack() {
            @Override
            public void onSuccess(String response) {
                try {
                    JsonResult result = JsonResult.parse(response);
                    LogUtil.e("微信返回：" + result.getReason() + "，错误号：" + result.getError_code() + "，信息：" + result.getResult());
                    wxModel = JSON.parseObject(result.getResult(), WxModel.class);
                    xRecyclerView.refreshComplete();
                    if (result.getError_code() == 0) {
                        mAdapter.setData(wxModel.getList());
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
                            @Override
                            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                                Intent intent = new Intent(mContext, WebActivity.class);
                                intent.putExtra("url", wxModel.getList().get(position).getUrl());
                                intent.putExtra("wx", "100");
                                startActivity(intent);
                            }
                        });

                    }
                } catch (Exception e) {
                    showToast("没有获取到数据，请刷新重试");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        getwx(1+"");
    }

    @Override
    public void onLoadMore() {
    }
}

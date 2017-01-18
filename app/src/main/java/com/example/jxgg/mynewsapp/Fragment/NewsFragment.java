package com.example.jxgg.mynewsapp.Fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.abadper.TopNewsAdapter;
import com.example.jxgg.mynewsapp.api.ApiClient;
import com.example.jxgg.mynewsapp.api.HttpCallBack;
import com.example.jxgg.mynewsapp.base.BaseFragment;
import com.example.jxgg.mynewsapp.model.JsonResult;
import com.example.jxgg.mynewsapp.model.TopNewsModel;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by JXGG on 2017/1/14.
 */
@ContentView(R.layout.fragment_news)
public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @ViewInject(R.id.fresh)
    private SwipeRefreshLayout fresh;
    @ViewInject(R.id.mRecyclerView)
    private RecyclerView mRecyclerView;
    TopNewsAdapter adapter;
    private String mytab;
    View footView;
    TextView tv;
    ProgressBar pb;


    public static NewsFragment settab(String tab) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", tab);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new TopNewsAdapter(mRecyclerView);
        mRecyclerView.setAdapter(adapter);


    }

    @Override
    protected void setListener() {
        fresh.setOnRefreshListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mytab = bundle.getString("tab");
        gettopnews(mytab);
    }


    private void gettopnews(String tab) {
        ApiClient.getapi().GetTopNews(tab, new HttpCallBack() {
            @Override
            public void onSuccess(String response) {

                try {
                    if (fresh != null) {
                        fresh.setRefreshing(false);
                    }
                    JsonResult result = JsonResult.parse(response);
                    LogUtil.e("错误码：" + result.getError_code());
                    LogUtil.e("返回信息：" + result.getReason() + "，返回值：" + result.getResult());
                    TopNewsModel topNewsModel = JSON.parseObject(result.getResult(), TopNewsModel.class);
                    if (result.getError_code() == 0) {
                        adapter.setData(topNewsModel.getData());
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    LogUtil.e("错误！！");
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }

    public void update(final TopNewsModel model) {

    }


    @Override
    public void onRefresh() {
        gettopnews(mytab);
    }
}

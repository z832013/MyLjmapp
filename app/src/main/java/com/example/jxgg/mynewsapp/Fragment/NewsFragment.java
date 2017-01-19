package com.example.jxgg.mynewsapp.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.jxgg.mynewsapp.Activity.WebActivity;
import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.abadper.TopNewsAdapter;
import com.example.jxgg.mynewsapp.abadper.WxNewsAdapter;
import com.example.jxgg.mynewsapp.api.ApiClient;
import com.example.jxgg.mynewsapp.api.HttpCallBack;
import com.example.jxgg.mynewsapp.base.BaseFragment;
import com.example.jxgg.mynewsapp.model.JsonResult;
import com.example.jxgg.mynewsapp.model.TopNewsModel;
import com.example.jxgg.mynewsapp.model.WxModel;
import com.example.jxgg.mynewsapp.utils.T;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.IOException;

import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

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

    private LinearLayoutManager mLinearLayoutManager;


    public static Fragment settab(Activity activity, String tab) {
        Bundle bundle = new Bundle();
        if (tab == "wx") {
            WxFragment wx = new WxFragment();
            wx.setArguments(bundle);
            return wx;
        } else {
            bundle.putString("tab", tab);
            NewsFragment newsFragment = new NewsFragment();
            newsFragment.setArguments(bundle);
            return newsFragment;
        }

    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        footView = LayoutInflater.from(getActivity()).inflate(R.layout.item_footview, null);
        footView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv = (TextView) footView.findViewById(R.id.tv);
        pb = (ProgressBar) footView.findViewById(R.id.pb);
//        footView = getActivity().getLayoutInflater().inflate(R.layout.item_footview, null, false);
        adapter = new TopNewsAdapter(mRecyclerView);


    }

    @Override
    protected void setListener() {
        fresh.setOnRefreshListener(this);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mytab = bundle.getString("tab");
        if (!mytab.equals("wx")) {
            mRecyclerView.setAdapter(adapter);
            gettopnews(mytab);
        }
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
                    final TopNewsModel topNewsModel = JSON.parseObject(result.getResult(), TopNewsModel.class);
                    if (result.getError_code() == 0) {
                        adapter.setData(topNewsModel.getData());
                        adapter.notifyDataSetChanged();
                        adapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
                            @Override
                            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                                Intent intent = new Intent(mContext, WebActivity.class);
                                intent.putExtra("url", topNewsModel.getData().get(position).getUrl());
                                intent.putExtra("title", topNewsModel.getData().get(position).getTitle());
                                startActivity(intent);
                            }
                        });
//                        adapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
//                            @Override
//                            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
//
//                                T.open(getActivity(), WebActivity.class, true, "url", topNewsModel.getData().get(position).getUrl());
//                            }
//                        });
                    }
                    myLoadMore(null);
                } catch (Exception e) {
                    if (fresh != null) {
                        fresh.setRefreshing(false);
                    }
                    showToast("没有获取到数据，请刷新重试");
                    LogUtil.e("错误！！");
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }

    public void myLoadMore(final WxModel model) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private boolean isScroll = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && isScroll) {
                    int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    LogUtil.e("我的totalItemCount：" + totalItemCount + "，lastVisibleItem：" + lastVisibleItem);
                    if (lastVisibleItem == (totalItemCount - 1)) {
                        LogUtil.e("到底了！！");

                        adapter.mygetitem(lastVisibleItem);
                        adapter.notifyDataSetChanged();

                        isScroll = false;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isScroll = true;
                } else {
                    isScroll = false;
                }
            }
        });
    }


    private void myLoadMores() {


    }

    private void LoadMore() {

    }

    @Override
    public void onRefresh() {

        mRecyclerView.setAdapter(adapter);
        gettopnews(mytab);

    }
}

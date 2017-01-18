package com.example.jxgg.mynewsapp.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.abadper.TopNewsAdapter;
import com.example.jxgg.mynewsapp.api.ApiClient;
import com.example.jxgg.mynewsapp.api.HttpCallBack;
import com.example.jxgg.mynewsapp.model.JsonResult;
import com.example.jxgg.mynewsapp.model.TopNewsModel;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by JXGG on 2017/1/14.
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
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


    View view;


    public static NewsFragment settab(String tab) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tab", tab);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_news, container, false);
        }
//        fresh.setColorSchemeResources(R.color.main, android.R.color.holo_orange_light, android.R.color.holo_red_light, android.R.color.holo_green_light);
//        fresh.setOnRefreshListener(this);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        Bundle bundle = getArguments();
        mytab = bundle.getString("tab");
        gettopnews(mytab);
        return view;
    }


    private void gettopnews(String tab) {
        ApiClient.getapi().GetTopNews(tab, new HttpCallBack() {
            @Override
            public void onSuccess(String response) {

                try {

                    JsonResult result = JsonResult.parse(response);
                    LogUtil.e("错误码：" + result.getError_code());
                    LogUtil.e("返回信息：" + result.getReason() + "，返回值：" + result.getResult());
                    TopNewsModel topNewsModel = JSON.parseObject(result.getResult(), TopNewsModel.class);
                    if (result.getError_code() == 0) {

                        update(topNewsModel);
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
        if (fresh != null) {
            fresh.setRefreshing(false);
        }

            if (adapter == null) {
                footView = LayoutInflater.from(getActivity()).inflate(R.layout.item_top_new, null);
                footView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tv = (TextView) footView.findViewById(R.id.tv);
                pb = (ProgressBar) footView.findViewById(R.id.pb);
                adapter = new TopNewsAdapter(getActivity(),model.getData());
                if (mRecyclerView != null) {
                    mRecyclerView.setLayoutManager(mLinearLayoutManager);
                    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        private boolean isScroll = false;

                        @Override
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged(recyclerView, newState);
                            if (newState == RecyclerView.SCROLL_STATE_IDLE && isScroll) {
                                int lastVisibleItem = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                                int totalItemCount = mLinearLayoutManager.getItemCount();
                                Log.i("News_Fragment", totalItemCount + "");
                                Log.i("News_Fragment", lastVisibleItem + "");
                                if (lastVisibleItem == (totalItemCount - 1)) {
                                    Log.i("Gif_Fragment", "LoadMore");
                                    LoadMore();
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
                    rv.setAdapter(adapter);
                }

            } else {
                adapter.setList(result.getResult().getData());
            }

    }


    @Override
    public void onRefresh() {
        gettopnews(mytab);
    }
}

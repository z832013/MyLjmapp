package com.example.jxgg.mynewsapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jxgg.mynewsapp.utils.ToastUtils;

import org.xutils.x;

/**
 * Created by JXGG on 2017/1/14.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mContext=this;
        initView(savedInstanceState);
        setListener();
        processLogic(savedInstanceState);
    }

    /**
     * 初始化布局以及View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);


    protected void showToast(String text) {
        ToastUtils.showShort(mContext, text);
    }

    /*
	 * 返回
	 */
    public void doBack(View view) {
        onBackPressed();
    }
}

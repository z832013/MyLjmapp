package com.example.jxgg.mynewsapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jxgg.mynewsapp.app.AppContext;
import com.example.jxgg.mynewsapp.utils.ToastUtils;

import org.xutils.x;


/**
 * Created by Administrator on 2016/5/20.
 */
public abstract class BaseFragment extends Fragment {

    private boolean injected = false;
    protected BaseActivity mActivity;
    public Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = AppContext.getInstance();
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }else {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        }
    }




    /**
     * 初始化View控件
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
        ToastUtils.showShort(mContext,text);
    }

    protected void showLoadingDialog(String tip) {
        mActivity.showLoadingDialog(tip);
    }

    protected void dismissLoadingDialog() {
        if (isVisible()) {
            mActivity.dismissLoadingDialog();
        }
    }
}

package com.example.jxgg.mynewsapp.Activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by JXGG on 2017/1/18.
 */
@ContentView(R.layout.web_activity)
public class WebActivity extends BaseActivity {
    @ViewInject(R.id.newstitle)
    private Toolbar newstitle;
    @ViewInject(R.id.webView)
    private WebView mwebView;
    private String url;
    private String title;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mwebView.setOnKeyListener(new View.OnKeyListener() { // webview can
            // go back
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && mwebView.canGoBack()) {
                    mwebView.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void setListener() {
        newstitle.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        String wx = getIntent().getStringExtra("wx");
        if (wx.equals("100")) {
            finish();
        }
        mwebView.loadUrl(url);
        newstitle.setTitle(title);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mwebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mwebView.onPause();
    }
}

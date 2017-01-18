package com.example.jxgg.mynewsapp.app;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * Created by JXGG on 2017/1/15.
 */

public class AppContext extends Application {
    public static Context applicationContext;
    private static AppContext instance;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
        applicationContext = this;
        instance = this;
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }
}

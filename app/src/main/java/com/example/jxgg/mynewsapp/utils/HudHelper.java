package com.example.jxgg.mynewsapp.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by Administrator on 2016/6/30.
 */
public class HudHelper {

    private static HudHelper helper = null;
    private KProgressHUD hud = null;

    public static HudHelper getInstance() {
        if (helper == null){
            helper = new HudHelper();
        }
        return helper;
    }

    public KProgressHUD initDefautHud(Context context){
        hud = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        return hud;
    }

}

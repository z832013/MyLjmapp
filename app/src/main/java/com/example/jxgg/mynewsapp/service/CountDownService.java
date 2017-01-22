package com.example.jxgg.mynewsapp.service;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.provider.AlarmClock;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.widget.CountDown;

import org.xutils.common.util.LogUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by JXGG on 2017/1/22.
 */

public class CountDownService extends Service {
    public static AppWidgetManager appWidgetManager;
    private RemoteViews views;
    private ComponentName componentName;
    private static final String TAG = "UpdateService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK); // 时间的流逝
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED); // 时间被改变，人为设置时间
        registerReceiver(boroadcastReceiver, intentFilter); //注册监听时间的系统广播
        init();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateClock();
        return START_STICKY;
    }

    private void init() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SET_ALARM");
        PendingIntent Pfullintent = PendingIntent.getActivity(this, 0, startClock(), 0);
        views = new RemoteViews(getPackageName(), R.layout.widget_desktop_clock);
        views.setOnClickPendingIntent(R.id.clock, Pfullintent);
        // 将AppWidgetProvider的子类包装成ComponentName对象
        componentName = new ComponentName(this,
                CountDown.class);

        appWidgetManager = AppWidgetManager.getInstance(this);
    }

    public Intent startClock() {
        Intent intent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        if (isIntentAvailable(this, intent)) {

        } else {
            intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        }
        return intent;
    }

    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                //     PackageManager.GET_ACTIVITIES);//     判断Intent是否存在的代码，
                // 基本上都是上面这一段，可以拿到我的电脑上来就报错，
                // 显示Must be one or more of : 等等的
                //     在这里可以把PackageManager.GET_ACTIVITIES 换成PackageManager.MATCH_DEFAULT_ONLY
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    // 用于监听系统时间变化Intent.ACTION_TIME_TICK的BroadcastReceiver，此BroadcastReceiver须为动态注册
    private BroadcastReceiver boroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context acontext, Intent intent) {
            LogUtil.e("动态注册 received");
            updateClock();
        }
    };

    private void updateClock() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String sYear = String.valueOf(cal.get(Calendar.YEAR));
        String sMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        String sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));


//        t1.setText("系统的时间为：" + sYear + "年" + sMonth + "月" + sDay + "日");


        int uYear = Integer.parseInt("2018");

        int uMonth = Integer.parseInt("9");

        int uDay = Integer.parseInt("19");


//        t2.setText("到期日为：" + String.valueOf(uYear) + "年" + String.valueOf(uMonth) + "月" + String.valueOf(uDay) + "日");


        views.setTextViewText(R.id.newtime, sYear + "/" + sMonth + "/" + sDay );
        // views.setTextViewText(R.id.clockDate, year + "年" + month + "月" + day+"日");
        views.setTextViewText(R.id.daoqitime, String.valueOf(uYear) + "/" + String.valueOf(uMonth) + "/" + String.valueOf(uDay) );

        //将时间设置成系统的时间
        cal.set(Integer.parseInt(sYear), Integer.parseInt(sMonth), Integer.parseInt(sDay));
        //将sysTime转换成毫秒
        long sysTime = cal.getTimeInMillis();

        //将时间设置到用户输入的时间
        cal.set(uYear, uMonth, uDay);
        //将userTime转换成毫秒
        long userTime = cal.getTimeInMillis();

        //计算两个时间相隔的天数
        long diffDay = (userTime - sysTime) / (1000 * 60 * 60 * 24);
        views.setTextViewText(R.id.tiancon, diffDay + "天");


        appWidgetManager.updateAppWidget(componentName, views);

    }


}

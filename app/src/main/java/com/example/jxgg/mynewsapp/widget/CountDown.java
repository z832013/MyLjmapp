package com.example.jxgg.mynewsapp.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.Time;

import com.example.jxgg.mynewsapp.service.CountDownService;

/**
 * Created by JXGG on 2017/1/22.
 */

public class CountDown extends AppWidgetProvider {
    private Intent intent;
    //onUpdate

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        intent = new Intent(context, CountDownService.class);
        context.startService(intent);

        Time time = new Time();
        time.setToNow();

        //使用Service更新时间
        Intent intent2 = new Intent(context, CountDownService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent2, 0);
        //使用Alarm定时更新界面数据
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC, time.toMillis(true), 60 * 1000, pendingIntent);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        context.stopService(new Intent(context, CountDownService.class));
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        context.startService(new Intent(context, CountDownService.class));
    }

    @Override
    public void onDisabled(Context context) {
        context.stopService(new Intent(context, CountDownService.class));
    }
}

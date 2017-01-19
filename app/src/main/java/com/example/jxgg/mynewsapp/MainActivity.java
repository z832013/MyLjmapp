package com.example.jxgg.mynewsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.jxgg.mynewsapp.Activity.VideoActivity;
import com.example.jxgg.mynewsapp.Fragment.NewsFragment;
import com.example.jxgg.mynewsapp.abadper.Mainpageradapter;
import com.example.jxgg.mynewsapp.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @ViewInject(R.id.maintab)
    private TabLayout maintab;
    @ViewInject(R.id.pager)
    private ViewPager pager;//
    @ViewInject(R.id.activity_main)
    private DrawerLayout activity_main;
    @ViewInject(R.id.mytoolbar)
    private Toolbar mytoolbar;
    @ViewInject(R.id.mynav)
    private NavigationView mynav;
    private long newTime;

    private Mainpageradapter adapter;

    ArrayList<Fragment> fragmentList = new ArrayList<>();
    String[][] tabstring = new String[][]{{"头条", "top"}, {"微信", "wx"}, {"科技", "keji"}, {"娱乐", "yule"}, {"社会", "shehui"}, {"国内", "guonei"}, {"国际", "guoji"}, {"体育", "tiyu"}, {"军事", "junshi"}, {"财经", "caijing"}, {"时尚", "shishang"}};

    @Override
    protected void initView(Bundle savedInstanceState) {

        maintab.setupWithViewPager(pager);
        maintab.setTabMode(TabLayout.MODE_SCROLLABLE);

        mytoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_main.openDrawer(GravityCompat.START);
            }
        });
        //设置Toolbar和DrawerLayout实现动画和联动
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, activity_main, mytoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        activity_main.addDrawerListener(toggle);
        toggle.syncState();
        mynav.setNavigationItemSelectedListener(this);
        for (int i = 0; i < tabstring.length; i++) {
            fragmentList.add(NewsFragment.settab(MainActivity.this, tabstring[i][1]));
        }

        adapter = new Mainpageradapter(getSupportFragmentManager(), fragmentList, tabstring);
        pager.setAdapter(adapter);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_video:
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
                break;
            case R.id.nav_gif:
                break;
        }
        activity_main.closeDrawers();
        return true;
    }

    /**
     * 监听返回键
     */
    @Override
    public void onBackPressed() {

        if (activity_main.isDrawerOpen(mynav)) {
            activity_main.closeDrawers();
            return;
        }


        if (System.currentTimeMillis() - newTime > 2000) {
            newTime = System.currentTimeMillis();
            Snackbar snackbar = Snackbar.make(activity_main, "再按一次返回键退出程序", Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            snackbar.show();
            //Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}

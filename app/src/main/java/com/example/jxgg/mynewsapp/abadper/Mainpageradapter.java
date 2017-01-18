package com.example.jxgg.mynewsapp.abadper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by JXGG on 2017/1/14.
 */

public class Mainpageradapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    private String[][] taplist;

    public Mainpageradapter(FragmentManager fm, ArrayList<Fragment> list, String[][] taplist) {
        super(fm);
        this.list = list;
        this.taplist = taplist;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return taplist[position][0];
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//不销毁

    }

}

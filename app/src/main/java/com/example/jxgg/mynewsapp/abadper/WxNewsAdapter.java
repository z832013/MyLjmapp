package com.example.jxgg.mynewsapp.abadper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.model.TopNewsModel;
import com.example.jxgg.mynewsapp.model.WxModel;
import com.example.jxgg.mynewsapp.utils.ImageOptionsHelper;

import org.xutils.x;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewHolder;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by JXGG on 2017/1/17.
 */

public class WxNewsAdapter extends BGARecyclerViewAdapter<WxModel.ListBean> {
    private boolean isFooterView = false;
    private int myitem=99999;
    View footView;
    final int NOFOOT = 1;
    final int YESFOOT = 2;

    public WxNewsAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_top_new);

    }



    public void mygetitem(int item) {
        this.myitem = item;
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, WxModel.ListBean model) {
//        if (myitem == position) {
//            helper.setVisibility(R.id.mytv, View.VISIBLE);
//        } else {
//            helper.setVisibility(R.id.mytv, View.GONE);
//        }
        helper.setText(R.id.topnewstitle, model.getTitle());
        helper.setVisibility(R.id.topnewstime, View.GONE);
        LinearLayout allimage = helper.getView(R.id.imageall);
        ImageView oneimage = helper.getImageView(R.id.oneimage);
        oneimage.setVisibility(View.VISIBLE);
        allimage.setVisibility(View.GONE);
        x.image().bind(oneimage, model.getFirstImg(), ImageOptionsHelper.getAvatarOptions());
    }
}

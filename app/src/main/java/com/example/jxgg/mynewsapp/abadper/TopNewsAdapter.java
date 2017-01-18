package com.example.jxgg.mynewsapp.abadper;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jxgg.mynewsapp.R;
import com.example.jxgg.mynewsapp.model.TopNewsModel;
import com.example.jxgg.mynewsapp.utils.ImageOptionsHelper;

import org.xutils.x;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by JXGG on 2017/1/17.
 */
//RecyclerView.Adapter<RecyclerView.ViewHolder>
public class TopNewsAdapter extends BGARecyclerViewAdapter<TopNewsModel.DataBean> {
    public TopNewsAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_top_new);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, TopNewsModel.DataBean model) {

        helper.setText(R.id.topnewstitle, model.getTitle());
        helper.setText(R.id.topnewstime, model.getDate());
        if (model.getThumbnail_pic_s() != null && model.getThumbnail_pic_s02() != null && model.getThumbnail_pic_s03() != null) {
            LinearLayout allimage = helper.getView(R.id.imageall);
            ImageView image1 = helper.getImageView(R.id.image1);
            ImageView image2 = helper.getImageView(R.id.image2);
            ImageView image3 = helper.getImageView(R.id.image3);
            allimage.setVisibility(View.VISIBLE);
            ImageView oneimage = helper.getImageView(R.id.oneimage);
            oneimage.setVisibility(View.GONE);
            x.image().bind(image1, model.getThumbnail_pic_s(), ImageOptionsHelper.getAvatarOptions());
            x.image().bind(image2, model.getThumbnail_pic_s02(), ImageOptionsHelper.getAvatarOptions());
            x.image().bind(image3, model.getThumbnail_pic_s03(), ImageOptionsHelper.getAvatarOptions());
        } else {
            LinearLayout allimage = helper.getView(R.id.imageall);
            ImageView oneimage = helper.getImageView(R.id.oneimage);
            oneimage.setVisibility(View.VISIBLE);
            allimage.setVisibility(View.GONE);
            x.image().bind(oneimage, model.getThumbnail_pic_s(), ImageOptionsHelper.getAvatarOptions());
        }
    }
}

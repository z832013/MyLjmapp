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

/**
 * Created by JXGG on 2017/1/17.
 */
//RecyclerView.Adapter<RecyclerView.ViewHolder>
public class TopNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TopNewsModel.DataBean> list;
    View view;
    Context context;
    LayoutInflater inflaters;
    final int NOFOOT = 1;
    final int YESFOOT = 2;


    public  TopNewsAdapter(Context context, List<TopNewsModel.DataBean> mymodel) {
        this.list = mymodel;
//        this.view = view;
        this.context = context;
        inflaters = LayoutInflater.from(context);
    }


//        if (model.getData().get(position).getThumbnail_pic_s() != null) {
//            if (model.getData().get(position).getThumbnail_pic_s02() != null) {
//                if (model.getData().get(position).getThumbnail_pic_s03() != null) {
//                    LinearLayout allimage = helper.getView(R.id.imageall);
//                    ImageView image1 = helper.getImageView(R.id.image1);
//                    ImageView image2 = helper.getImageView(R.id.image3);
//                    ImageView image3 = helper.getImageView(R.id.image3);
//                    allimage.setVisibility(View.VISIBLE);
//                    x.image().bind(image1, model.getData().get(position).getThumbnail_pic_s(), ImageOptionsHelper.getAvatarOptions());
//                    x.image().bind(image2, model.getData().get(position).getThumbnail_pic_s02(), ImageOptionsHelper.getAvatarOptions());
//                    x.image().bind(image3, model.getData().get(position).getThumbnail_pic_s03(), ImageOptionsHelper.getAvatarOptions());
//                }
//            } else {
//                LinearLayout allimage = helper.getView(R.id.imageall);
//                ImageView onewimage = helper.getImageView(R.id.oneimage);
//                onewimage.setVisibility(View.VISIBLE);
//                allimage.setVisibility(View.GONE);
//                x.image().bind(onewimage, model.getData().get(position).getThumbnail_pic_s(), ImageOptionsHelper.getAvatarOptions());
//            }
//        }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        holder = new MyHolder(inflaters.inflate(R.layout.item_top_new, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            ((MyHolder) holder).setTitle(list.get(position).getTitle());
            ((MyHolder) holder).setDate(list.get(position).getDate());
            ((MyHolder) holder).setImg4(list.get(position).getThumbnail_pic_s());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView topnewstitle, topnewstime;
        ImageView image1, image2, image3, onewimage;
        LinearLayout imageall;

        public MyHolder(View itemView) {
            super(itemView);
            topnewstitle = (TextView) itemView.findViewById(R.id.topnewstitle);
            topnewstime = (TextView) itemView.findViewById(R.id.topnewstime);
            image1 = (ImageView) itemView.findViewById(R.id.image1);
            image2 = (ImageView) itemView.findViewById(R.id.image2);
            image3 = (ImageView) itemView.findViewById(R.id.image3);
            onewimage = (ImageView) itemView.findViewById(R.id.oneimage);
            imageall = (LinearLayout) itemView.findViewById(R.id.imageall);
        }

        public void setTitle(String title) {
            if (null == topnewstitle) return;
            topnewstitle.setText(title + "\n");

        }

        public void setDate(String date) {
            if (null == topnewstime) return;
            topnewstime.setText(date);
        }

        public void setImg1(String imgUrl) {
            if (null == image1) return;
            x.image().bind(image1, imgUrl, ImageOptionsHelper.getAvatarOptions());
        }

        public void setImg2(String imgUrl) {
            if (null == image2) return;
            x.image().bind(image2, imgUrl, ImageOptionsHelper.getAvatarOptions());
        }

        public void setImg3(String imgUrl) {
            if (null == image3) return;
            x.image().bind(image3, imgUrl, ImageOptionsHelper.getAvatarOptions());
        }

        public void setImg4(String imgUrl) {
            if (null == onewimage) return;
            x.image().bind(onewimage, imgUrl, ImageOptionsHelper.getAvatarOptions());
        }


    }
}

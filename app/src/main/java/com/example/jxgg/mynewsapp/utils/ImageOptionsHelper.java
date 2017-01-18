package com.example.jxgg.mynewsapp.utils;

import android.widget.ImageView;

import com.example.jxgg.mynewsapp.R;

import org.xutils.image.ImageOptions;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ImageOptionsHelper {
    public static ImageOptions getDefaultOptions() {
        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
//                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.default_error)
                .setFailureDrawableId(R.drawable.default_error)
                .build();
        return imageOptions;
    }

    public static ImageOptions getAvatarOptions() {
        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
//                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
//                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
//                         加载中或错误图片的ScaleType
                .setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.default_error)
                .setFailureDrawableId(R.drawable.default_error)
                .build();
        return imageOptions;
    }

    public static ImageOptions getBannerOptions() {
        ImageOptions imageOptions = new ImageOptions.Builder()
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.FIT_XY)
                .setLoadingDrawableId(R.drawable.default_error)
                .setFailureDrawableId(R.drawable.default_error)
                .build();
        return imageOptions;
    }


    public static ImageOptions getPhotoOptions() {
        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
//                .setRadius(DensityUtil.dip2px(5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
//                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFadeIn(true)
                .setLoadingDrawableId(R.drawable.default_error)
                .setFailureDrawableId(R.drawable.default_error)
                .build();
        return imageOptions;
    }
}

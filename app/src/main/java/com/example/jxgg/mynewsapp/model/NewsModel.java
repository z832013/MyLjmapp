package com.example.jxgg.mynewsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JXGG on 2017/1/17.
 */

public class NewsModel implements Parcelable {

    /**
     * category : 头条
     * title : Baby怀胎的这10个月，和黄晓明是这样秀恩爱的！
     * thumbnail_pic_s03 : http://03.imgmini.eastday.com/mobile/20170117/20170117140153_fceb576f27495377407f23eb81870fe0_3_mwpm_03200403.jpeg
     * thumbnail_pic_s02 : http://03.imgmini.eastday.com/mobile/20170117/20170117140153_fceb576f27495377407f23eb81870fe0_2_mwpm_03200403.jpeg
     * uniquekey : 1db00452b0f8304f05c449f68ed644a4
     * author_name : 东森新闻云
     * thumbnail_pic_s : http://03.imgmini.eastday.com/mobile/20170117/20170117140153_fceb576f27495377407f23eb81870fe0_1_mwpm_03200403.jpeg
     * date : 2017-01-17 14:01
     * url : http://mini.eastday.com/mobile/170117140153536.html
     */

    private String category;
    private String title;
    private String thumbnail_pic_s03;
    private String thumbnail_pic_s02;
    private String uniquekey;
    private String author_name;
    private String thumbnail_pic_s;
    private String date;
    private String url;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.category);
        dest.writeString(this.title);
        dest.writeString(this.thumbnail_pic_s03);
        dest.writeString(this.thumbnail_pic_s02);
        dest.writeString(this.uniquekey);
        dest.writeString(this.author_name);
        dest.writeString(this.thumbnail_pic_s);
        dest.writeString(this.date);
        dest.writeString(this.url);
    }

    public NewsModel() {
    }

    protected NewsModel(Parcel in) {
        this.category = in.readString();
        this.title = in.readString();
        this.thumbnail_pic_s03 = in.readString();
        this.thumbnail_pic_s02 = in.readString();
        this.uniquekey = in.readString();
        this.author_name = in.readString();
        this.thumbnail_pic_s = in.readString();
        this.date = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<NewsModel> CREATOR = new Parcelable.Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }

        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}

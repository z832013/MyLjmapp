package com.example.jxgg.mynewsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JXGG on 2017/1/17.
 */

public class TopNewsModel implements Parcelable {

    private List<DataBean> data;
    private List<ListBean> list;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class DataBean {
        /**
         * category : 头条
         * title : 他被人排挤 是文弱的张国荣帮他撑腰
         * thumbnail_pic_s03 : http://04.imgmini.eastday.com/mobile/20170117/20170117092215_87a751b77993385822e2696475a1bb27_3_mwpm_03200403.jpeg
         * thumbnail_pic_s02 : http://04.imgmini.eastday.com/mobile/20170117/20170117092215_87a751b77993385822e2696475a1bb27_2_mwpm_03200403.jpeg
         * uniquekey : 3a570937532343ca9263f08604b92ff3
         * author_name : 腾讯娱乐
         * thumbnail_pic_s : http://04.imgmini.eastday.com/mobile/20170117/20170117092215_87a751b77993385822e2696475a1bb27_1_mwpm_03200403.jpeg
         * date : 2017-01-17 09:22
         * url : http://mini.eastday.com/mobile/170117092215942.html
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public TopNewsModel() {
    }

    protected TopNewsModel(Parcel in) {
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Creator<TopNewsModel> CREATOR = new Creator<TopNewsModel>() {
        @Override
        public TopNewsModel createFromParcel(Parcel source) {
            return new TopNewsModel(source);
        }

        @Override
        public TopNewsModel[] newArray(int size) {
            return new TopNewsModel[size];
        }
    };

    public static class ListBean {
        /**
         * id : wechat_20170116034547
         * title : 整容脸也可以是演技派，瞅瞅李小璐就知道了！
         * source : 电影天堂
         * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-12136083.jpg/640
         * mark :
         * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20170116034547
         */

        private String id;
        private String title;
        private String source;
        private String firstImg;
        private String mark;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFirstImg() {
            return firstImg;
        }

        public void setFirstImg(String firstImg) {
            this.firstImg = firstImg;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

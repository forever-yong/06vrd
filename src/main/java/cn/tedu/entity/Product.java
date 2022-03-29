package cn.tedu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private int id;
    private String title;
    private String author;
    private String intro;
    private String imgUrl;
    private int viewCount;
    private int likeCount;
    private long created;
    private int categoryId;

    public String getTime(){
        //创建日期格式化对象
        SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //把发布日期转换成指定格式
        return f.format(new Date(created));
    }
    public Product(int id, String title, String author, String intro, String imgUrl, int viewCount, int likeCount, long created, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.intro = intro;
        this.imgUrl = imgUrl;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.created = created;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", created=" + created +
                ", categoryId=" + categoryId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}

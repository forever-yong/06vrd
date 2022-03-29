package cn.tedu.entity;

public class Banner {
    private int id;
    private String imgUrl;

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Banner(int id, String imgUrl) {
        this.id = id;
        this.imgUrl = imgUrl;
    }
}

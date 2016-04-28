package com.example.android.movieapp;

/**
 * Created by Esraa on 20-Apr-16.
 */
public class Review  extends ExtraItemBase{

    private String reviewURL;
    private String auther;
    private String content;


    public Review() {
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewURL='" + reviewURL + '\'' +
                ", auther='" + auther + '\'' +

                ", content='" + content + '\'' +
                "} " + super.toString();
    }

    public Review(String key, String type, String reviewURL, String auther, String content) {
        super(key, type);
        this.reviewURL = reviewURL;
        this.auther = auther;
        this.content = content;
    }

    public String getReviewURL() {
        return reviewURL;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReviewURL(String reviewURL) {
        this.reviewURL = reviewURL;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }
}

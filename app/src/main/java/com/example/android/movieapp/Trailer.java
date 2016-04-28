package com.example.android.movieapp;

/**
 * Created by Esraa on 21-Apr-16.
 */
public class Trailer extends ExtraItemBase{

    private String youtubeURL;

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public Trailer() {
    }

    public Trailer(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "youtubeURL='" + youtubeURL + '\'' +
                "} " + super.toString();
    }

    public Trailer(String key, String type, String youtubeURL) {
        super(key, type);
        this.youtubeURL = youtubeURL;
    }


    public void setYoutubeURL(String youtubeURL) {
        this.youtubeURL = youtubeURL;
    }
}

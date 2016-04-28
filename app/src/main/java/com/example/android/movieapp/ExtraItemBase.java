package com.example.android.movieapp;

/**
 * Created by Esraa on 21-Apr-16.
 */
public class ExtraItemBase {


   protected String key;//movie ID
    protected String type;

    @Override
    public String toString() {
        return "ExtraItemBase{" +
                "key='" + key + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public ExtraItemBase() {
    }

    public ExtraItemBase(String key, String type) {

        this.key = key;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

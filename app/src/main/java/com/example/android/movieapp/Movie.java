package com.example.android.movieapp;

/**
 * Created by Esraa on 25-Mar-16.
 */
public class Movie {

    public Movie(String original_title, String poster_path,
                 String overview, int vote_count, double vote_average, String release_date, String key) {
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.key = key;
    }

    public Movie() {
    }

    String original_title;
    String poster_path;
    String overview;

    int vote_count;
    double vote_average;
    String release_date;
    String key;



    public String getKey() {
        return key;
    }


    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "original_title='" + original_title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_count=" + vote_count +
                ", vote_average=" + vote_average +
                ", release_date='" + release_date + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

}

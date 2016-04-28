package com.example.android.movieapp;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by Esraa on 20-Apr-16.
 */



public class ReviewsAsynckTask extends AsyncTask<String, Void, String> {


    protected void onPostExecute(String result) {
    }

    @Override
    protected String doInBackground(String... params) {

        ReviewsFetch m = new ReviewsFetch();

        try {
            return m.callMoviesReviews(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
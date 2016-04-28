package com.example.android.movieapp;

import android.os.AsyncTask;

import java.io.IOException;

/**
 * Created by Esraa on 18-Apr-16.
 */


public class TrailersAsynckTask extends AsyncTask<String, Void, String> {


    protected void onPostExecute(String result) {
    }

    @Override
    protected String doInBackground(String... params) {

        TrailersFetch m = new TrailersFetch();

        try {
            return m.callMoviesTrailers(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
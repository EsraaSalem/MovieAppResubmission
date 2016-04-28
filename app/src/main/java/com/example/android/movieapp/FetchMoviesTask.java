package com.example.android.movieapp;

import android.os.AsyncTask;

/**
 * Created by Esraa on 09-Apr-16.
 */

public class FetchMoviesTask extends AsyncTask<String, Void, String> {


    protected void onPostExecute(String result) {

    }

    @Override
    protected String doInBackground(String... params)  {

        MovieAPICalling m = new MovieAPICalling();

        return m.callMovieAPI(params[0]);

    }
}
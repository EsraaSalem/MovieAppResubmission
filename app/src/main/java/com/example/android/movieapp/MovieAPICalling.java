package com.example.android.movieapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Esraa on 09-Apr-16.
 */
public class MovieAPICalling {


    public static String movieApiKey = "";

    public MovieAPICalling() {

    }
    public String callMovieAPI(String mode)
    {


        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String moviesJSONStr = "";
        try {
            String urlStr = "";
            if (mode.equals("highRated")) {

                urlStr = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + movieApiKey;
            } else if (mode.equals("mostPopular")) {

                urlStr = "https://api.themoviedb.org/3/movie/popular?api_key=" + movieApiKey;
            }
        URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            moviesJSONStr = buffer.toString();
        } catch (Exception e) {
            Log.e("Movie App Exception_", "Error ", e);

            return "";
        }

        return moviesJSONStr;


    }
}

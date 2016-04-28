package com.example.android.movieapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Esraa on 15-Apr-16.
 */
public class Connection {


    public String connect(String urlStr) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String moviesJSONStr = "";

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


        return moviesJSONStr;

    }
}

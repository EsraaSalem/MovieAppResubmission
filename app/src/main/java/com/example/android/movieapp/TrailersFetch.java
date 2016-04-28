package com.example.android.movieapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Esraa on 15-Apr-16.
 */
public class TrailersFetch {

    public static String movieApiKey = "";

    public String callMoviesTrailers(String movieKey) throws IOException {


        String trailersJSONStr = "";

        String urlStr = "http://api.themoviedb.org/3/movie/" + movieKey + "/videos?api_key=" + movieApiKey;

        Connection conn = new Connection();
        trailersJSONStr = conn.connect(urlStr);
        return trailersJSONStr;


    }

    public Vector<ExtraItemBase> getTrailersURLs(String trailerJson) {

        Vector<ExtraItemBase> trailersURLs = new Vector<ExtraItemBase>();
        try {
            JSONObject jsonObj = new JSONObject(trailerJson);
            JSONArray jsonArray = jsonObj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject oneMovie = jsonArray.getJSONObject(i);
                String videoKey = oneMovie.getString("key").toString();
                ExtraItemBase trailer = new Trailer("", "trailer",
                         videoKey.toString().trim());

                trailersURLs.add(trailer);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trailersURLs;
    }
}

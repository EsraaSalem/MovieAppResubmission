package com.example.android.movieapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Vector;

/**
 * Created by Esraa on 20-Apr-16.
 */
public class ReviewsFetch {


    public static String movieApiKey = "d9c1db839e8826febb843941033c59cc";

    public String callMoviesReviews(String movieKey) throws IOException {


        String apiURl = "http://api.themoviedb.org/3/movie/" + movieKey + "/reviews?api_key=" + movieApiKey;
        String returenedJson = "";

        Connection conn = new Connection();
        returenedJson = conn.connect(apiURl);

        //TODO Call the url to fetch the reviews
        return returenedJson;
    }

    public Vector<ExtraItemBase> getReviews(String returnedJson) {

        // TODO Parse the api call response "Returned json"
        // and get the movie reviews this calling for only one movie at a time
        Vector<ExtraItemBase> moviesReviews = new Vector<ExtraItemBase>();

        try {
            JSONObject jsonObj = new JSONObject(returnedJson);
            JSONArray jsonArray = jsonObj.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject oneMovie = jsonArray.getJSONObject(i);


                String reviewURL = oneMovie.getString("url").toString();
                String auther = oneMovie.getString("author").toString();
                String content = oneMovie.getString("content").toString();
                ExtraItemBase r = new Review("", "review", reviewURL, auther, content);
                moviesReviews.add(r);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesReviews;


    }
}

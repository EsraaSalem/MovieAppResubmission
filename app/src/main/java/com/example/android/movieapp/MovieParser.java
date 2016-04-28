package com.example.android.movieapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by Esraa on 25-Mar-16.
 */
public class MovieParser {

    Vector<Movie> allMovies;
    String apiCallType;
    public Vector<Movie> parseMovieAPIOutput(String result)
    {

        allMovies = new Vector<Movie>();
        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONArray jsonArray = jsonObj.getJSONArray("results");
            for(int i = 0 ; i < jsonArray.length(); i++)
            {
                JSONObject oneMovie = jsonArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setOriginal_title(oneMovie.getString("original_title").toString());
                movie.setOverview(oneMovie.getString("overview"));
                movie.setPoster_path(oneMovie.getString("poster_path").toString());
                movie.setRelease_date(oneMovie.getString("release_date"));
                movie.setVote_count(oneMovie.getInt("vote_count"));
                movie.setKey(oneMovie.getString("id"));
                movie.setVote_average(oneMovie.getDouble("vote_average"));
                allMovies.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return allMovies;
    }
}

package com.example.android.movieapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class DetailedFragment extends Fragment {

    public static View rootView;
    public static Vector<Movie> allMovieToView;
    MovieFavouritesDB movieFavouritesDB;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_detailed_movie, container, false);
        movieFavouritesDB = new MovieFavouritesDB(getContext());
        View header = inflater.inflate(R.layout.details_header, null);
        final Movie movie = fillDetailedFragmentData(header);
        getListViewData(movie.getKey(), header);
        Button btnAddToFavourite = (Button) rootView.findViewById(R.id.btnAddTofavourite);
        btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = movieFavouritesDB.insertMovie(movie);
                if (res) {
                    Toast.makeText(getContext(), "Added Successfully :)", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "Already in favourites", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    public DetailedFragment() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }


    public Movie fillDetailedFragmentData(View header) {
        TextView t = (TextView) header.findViewById(R.id.movieTitle);
        ImageView imageView = (ImageView) header.findViewById(R.id.movieImgId);
        TextView tvTitle = (TextView) header.findViewById(R.id.movieTitle);
        TextView tvOverview = (TextView) header.findViewById(R.id.overview);
        TextView tvReleaseDate = (TextView) header.findViewById(R.id.relaese_date);
        TextView tvVote_average = (TextView) header.findViewById(R.id.tvVote_average);
        TextView tvVote_count = (TextView) header.findViewById(R.id.tvVote_count);


        final String original_title1 = getArguments().getString("original_title");

        final String original_title = getArguments().getString("original_title");
        final String overview = getArguments().getString("overview");
        final String post_path = getArguments().getString("post_path");
        final String release_date = getArguments().getString("release_date");
        final int vote_count = Integer.parseInt(getArguments().getString("vote_count"));
        final String key = getArguments().getString("key");
        final double vote_average = Double.parseDouble(getArguments().getString("vote_average"));

        Movie movie = new Movie(original_title, post_path,
                overview, vote_count, vote_average,
                release_date, key);

        tvTitle.setText("Movie title: " + original_title);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500" + post_path).into(imageView);
        tvReleaseDate.setText("Release date: " + release_date);
        tvOverview.setText("Description: " + overview);
        tvVote_average.setText("Vote Avarage:" + vote_average);
        tvVote_count.setText("Vote Count : " + vote_count);
        return movie;


    }

    public void getListViewData(String key, View header) {
        String result = "";
        TrailersAsynckTask trailersAsynckTask = new TrailersAsynckTask();

        try {
            result = trailersAsynckTask.execute(key).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TrailersFetch trailersFetch = new TrailersFetch();
        final Vector<ExtraItemBase> alltrailers = trailersFetch.getTrailersURLs(result);
        String reviewResult = "";
        ReviewsAsynckTask reviewsAsynckTask = new ReviewsAsynckTask();
        try {
            reviewResult = reviewsAsynckTask.execute(key).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ReviewsFetch reviewsFetch = new ReviewsFetch();
        Vector<ExtraItemBase> reviews = new Vector<ExtraItemBase>();
        reviews = reviewsFetch.getReviews(reviewResult);
        final Vector<ExtraItemBase> allMovieListViewDetails = new Vector<ExtraItemBase>();

        for (int i = 0; i < alltrailers.size(); i++) {
            allMovieListViewDetails.add(alltrailers.get(i));
        }
        for (int i = 0; i < reviews.size(); i++) {
            allMovieListViewDetails.add(reviews.get(i));
        }
        ListView listView = (ListView) rootView.findViewById(R.id.lvTrailersandReviews);
        TrailersListView adapter = new TrailersListView(getContext(), allMovieListViewDetails, 1 + alltrailers.size());
        listView.addHeaderView(header);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                if (position >= 1 && position <= alltrailers.size()) {
                    ExtraItemBase selecteditem = allMovieListViewDetails.get(position - 1);
                    Trailer t = (Trailer) selecteditem;
                    watchYoutubeVideo(t.getYoutubeURL());
                } else if (position >= alltrailers.size() + 1 && position <= allMovieListViewDetails.size()) {
                    ExtraItemBase selecteditem = allMovieListViewDetails.get(position - 1);
                    Review r = (Review) selecteditem;
                    openBroswer(r.getReviewURL());
                }

            }
        });

    }

    public void watchYoutubeVideo(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(intent);
        }
    }

    public void openBroswer(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(id));
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();


    }


}

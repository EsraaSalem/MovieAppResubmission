package com.example.android.movieapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.Vector;
import java.util.concurrent.ExecutionException;
public class MainActivityMovieAppFragment extends Fragment {

    public static View rootView;
    public static Vector<Movie>  allMovieToView;
    NameListener nameListenerFromMainFragment;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_activity_movie_app, container, false);
        asynchTaskLogic();
        return rootView;
    }

    public GridView showAllMovies(Vector<Movie> allMovieToView)
    {
        GridView gridView = (GridView) rootView.findViewById(R.id.gridviewMoviesImages);
        gridView.setAdapter(null);
        gridView.setAdapter(new GridViewMovieImages(getActivity(), allMovieToView));
        return gridView;

    }
    public MainActivityMovieAppFragment() {
    }


    public void asynchTaskLogic()
    {


        try {
            FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
            SharedPreferences sharedPrefs =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            String sortType = sharedPrefs.getString(getString(R.string.pref_sortmethod_key), "mostPopular");
           if(!sortType.equals("myFavourites")) {
                String output = fetchMoviesTask.execute(sortType.trim()).get();
                MovieParser movieParser = new MovieParser();
                allMovieToView = movieParser.parseMovieAPIOutput(output);
            }
            else
            {
                MovieFavouritesDB movieFavouritesDB = new MovieFavouritesDB(getContext());
                allMovieToView = movieFavouritesDB.getAllMovies();
            }

            GridView gridView = showAllMovies(allMovieToView);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie selectedMovie = allMovieToView.get(position);
                    nameListenerFromMainFragment.setSelectedName(selectedMovie);

                                   }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        String unitType = sharedPrefs.getString(getString(R.string.pref_sortmethod_key), "mostPopular");
        return true;
  }



    @Override
    public void onResume() {
        super.onResume();

        asynchTaskLogic();
    }

    public void setNameListener(NameListener nameListener)
    {
        nameListenerFromMainFragment = nameListener;
    }
}

package com.example.android.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Vector;

/**
 * Created by Esraa on 09-Apr-16.
 */
public class GridViewMovieImages extends BaseAdapter {

    private Context mContext;
    Vector<Movie> allMoviesToShow;
    LayoutInflater inflater;

    public GridViewMovieImages(Context c, Vector<Movie> movies) {

        allMoviesToShow = new Vector<Movie>();
        allMoviesToShow = movies;
        mContext = c;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return allMoviesToShow.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ImageView imageView = (ImageView) inflater.inflate(R.layout.movieimgview, null);//new ImageView(mContext);
        TextView textView = new TextView(mContext);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w185" +
                allMoviesToShow.get(position).getPoster_path()).centerCrop().resize(185,185).into(imageView);

        return imageView;
    }
}
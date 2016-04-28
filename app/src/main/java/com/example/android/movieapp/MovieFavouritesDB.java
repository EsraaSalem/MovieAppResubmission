package com.example.android.movieapp;

/**
 * Created by Esraa on 23-Apr-16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Vector;

public class MovieFavouritesDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movieAppDB.db";
    public static final String CONTACTS_TABLE_NAME = "movie";

    public static final String id = "id";
    public static final String original_title = "original_title";
    public static final String overview = "overview";
    public static final String post_path = "post_path";
    public static final String release_date = "release_date";
    public static final String vote_count = "vote_count";
    public static final String vote_avarage = "vote_avarage";

    public static final String key = "key";




    private HashMap hp;

    public MovieFavouritesDB(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table movie " +
                        "(id integer primary key, " +
                        "original_title text,overview text,post_path text, " +
                        "release_date text,vote_count text, key text,vote_avarage text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS movie");
        onCreate(db);
    }

    public boolean insertMovie  (Movie movie)
    {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        Cursor res = getDataByName(movie.getKey());

        int c = res.getCount();
        if(c ==0)
        {
            contentValues.put("original_title", movie.getOriginal_title());
            contentValues.put("overview", movie.getOverview());
            contentValues.put("post_path", movie.getPoster_path());
            contentValues.put("release_date", movie.getRelease_date());
            contentValues.put("vote_count", String.valueOf(movie.getVote_count()));
            contentValues.put("vote_avarage", String.valueOf(movie.getVote_average()));
            contentValues.put("key", movie.getKey());

            db.insert("movie", null, contentValues);
            return true;
        }



        return false;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from movie where id="+id+"", null );
        return res;
    }
    public Cursor getDataByName(String movieKey){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from movie where key='"+movieKey.trim()+"'", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public Vector<Movie> getAllMovies()
    {
        Vector<Movie> array_list = new Vector<Movie>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from movie", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Movie movie1 = new Movie();
            movie1.setOriginal_title(res.getString(res.getColumnIndex("original_title")));
            movie1.setOverview(res.getString(res.getColumnIndex("overview")));
            movie1.setPoster_path(res.getString(res.getColumnIndex("post_path")));
            movie1.setRelease_date(res.getString(res.getColumnIndex("release_date")));
            movie1.setVote_count(Integer.parseInt(res.getString(res.getColumnIndex("vote_count"))));
            movie1.setKey(res.getString(res.getColumnIndex("key")));
            movie1.setVote_average(Double.parseDouble(res.getString(res.getColumnIndex("vote_avarage"))));

            array_list.add(movie1);
            res.moveToNext();
        }
        return array_list;
    }
}

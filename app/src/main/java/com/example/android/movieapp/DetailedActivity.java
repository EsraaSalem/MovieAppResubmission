package com.example.android.movieapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


/**
 * Created by Esraa on 24-Apr-16.
 */

public class DetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (null == savedInstanceState) {
            DetailedFragment detailedFragment = new DetailedFragment();
            detailedFragment.setArguments(extras);
            getSupportFragmentManager().beginTransaction().add(R.id.detailedContainer,
                    detailedFragment).addToBackStack("").commit();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


}

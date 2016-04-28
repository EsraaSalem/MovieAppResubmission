package com.example.android.movieapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by Esraa on 18-Apr-16.
 */
public class TrailersListView extends BaseAdapter {


    private Context mContext;
    private  int reviewStartPos;
    private static final String TAG= "Check ";
    Vector<ExtraItemBase> trailersAndReview;
    LayoutInflater inflater;

    public TrailersListView(Context mContext, Vector<ExtraItemBase> givenTrailersAndReview ,int reviewsStart) {
        trailersAndReview = new Vector<ExtraItemBase>();
        for (int i = 0 ; i < givenTrailersAndReview.size(); i++)
        {
            this.trailersAndReview.add(givenTrailersAndReview.get(i));
        }
        this.mContext = mContext;
        reviewStartPos = reviewsStart;

        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return trailersAndReview.size();
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

        View trailerLayoutView = inflater.inflate(R.layout.trailer_format,null);
        View reviewLayoutView = inflater.inflate(R.layout.reviewformat,null);
        View trailerHeaderView = inflater.inflate(R.layout.trailerseparator,null);
        View reviewHeaderView = inflater.inflate(R.layout.review_separator,null);


        String type_ = trailersAndReview.get(position).getType();
        Log.d(TAG, "TYPE = "+type_+"  size : "+trailersAndReview.size());

        if(trailersAndReview.get(position).getType().equals("trailer"))
        {
            ImageView imageView = (ImageView)trailerLayoutView.findViewById(R.id.playPhoto);
            imageView.setImageResource(R.drawable.p);
            TextView tvTrailerUrl  = (TextView)trailerLayoutView.findViewById(R.id.tvTrailerURL);
            Trailer trailer = new Trailer();
            trailer = (Trailer) trailersAndReview.get(position);

            String value = "Trailer # "+(position)+trailer.getYoutubeURL();
            Log.v("Youtube url ", value);
            tvTrailerUrl.setText(value);
            return trailerLayoutView;
        }else

        if(trailersAndReview.get(position).getType().equals("review"))
        {
            Review r = new Review();
            r = (Review)trailersAndReview.get(position);

            TextView url  = (TextView)reviewLayoutView.findViewById(R.id.tvReviewURL);
            TextView content  = (TextView)reviewLayoutView.findViewById(R.id.tvReviewContent);
            TextView auther  = (TextView)reviewLayoutView.findViewById(R.id.tvReviewAuther);

            url.setText(r.getReviewURL());
            content.setText(r.getContent());
            auther.setText(r.getAuther());

            return reviewLayoutView;
        }




        return null;




    }
}

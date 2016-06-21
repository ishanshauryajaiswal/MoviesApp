/*
 * Copyright (c) 2016 Akshat Pandey . All Right Reserved .
 * <p>
 * Save to the extent permitted by law, you may not use,copy,modify,
 * distribute or create derivative works of this material or any partof it
 * without the prior written consent of Akshat Pandey.
 * <p>
 * The above Copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 */

package com.crossfire.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.crossfire.moviesapp.model.MoviePOJO;
import com.squareup.picasso.Picasso;


/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 28-05-2016
 */
public class MovieDetailActivity extends AppCompatActivity {

    TextView movie_detail_title,movie_detail_release_date,movie_detail_vote_average,movie_detail_overview;
    ImageView image_detail_poster_path;
    ImageView image_detail_backdrop_path;
    MoviePOJO temp=new MoviePOJO();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);
        if(getIntent().hasExtra("movie_detail")){
            temp=(MoviePOJO) getIntent().getExtras().get("movie_detail");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Something's Wrong",Toast.LENGTH_SHORT).show();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(temp.title);
        image_detail_poster_path=(ImageView)findViewById(R.id.movie_detail_poster_path__image);
        image_detail_backdrop_path =(ImageView)findViewById(R.id.movie_detail_backdrop_image);
        movie_detail_title=(TextView)findViewById(R.id.movie_detail_title_text);
        movie_detail_release_date=(TextView)findViewById(R.id.movie_detail_release_date_text);
        movie_detail_vote_average=(TextView)findViewById(R.id.movie_detail_vote_average_text);
        movie_detail_overview=(TextView)findViewById(R.id.movie_detail_overview_text);
        movie_detail_release_date.setText(getString(R.string.release_date).concat(temp.getRelease_date()));
        movie_detail_vote_average.setText(getString(R.string.average).concat(String.valueOf(temp.getVote_average())));
        movie_detail_overview.setText(temp.getOverview());
        movie_detail_title.setText(temp.getTitle());
        load_images();
    }

    public void load_images()
    {
        Picasso.with(getApplicationContext())
                .load(temp.getBackdrop_path())
                .placeholder(R.color.colorAccent)
                .into(image_detail_backdrop_path);
        Picasso.with(getApplicationContext())
                .load(temp.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(image_detail_poster_path);
    }
}
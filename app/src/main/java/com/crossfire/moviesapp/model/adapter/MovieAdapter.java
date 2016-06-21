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

package com.crossfire.moviesapp.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.crossfire.moviesapp.MovieDetailActivity;
import com.crossfire.moviesapp.R;
import com.crossfire.moviesapp.model.Movie;
import com.crossfire.moviesapp.model.MoviePOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 16-05-2016
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private Context mContext;
    public List<MoviePOJO> movies = new ArrayList<>();

    public MovieAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        final Holder viewHolder=new Holder(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= viewHolder.getAdapterPosition();
                MoviePOJO temp=movies.get(position);
                Intent intent=new Intent(mContext,MovieDetailActivity.class);
                intent.putExtra("movie_detail", temp);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        MoviePOJO question=movies.get(position);
        holder.name.setText(movies.get(position).getTitle());
        Picasso.with(mContext)
                .load(movies.get(position).getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return (movies == null) ? 0 : movies.size();
    }

    public void setMovieList(List<MoviePOJO> movieList) {
        if (!movieList.isEmpty()) {
            movies.addAll(movieList);
            notifyDataSetChanged();
        }
    }

    public class Holder extends RecyclerView.ViewHolder {

        CardView card;
        LinearLayout ripple;
        ImageView image;
        RelativeLayout holder;
        TextView name;

        public Holder(View itemView) {
            super(itemView);
            ripple = (LinearLayout) itemView.findViewById(R.id.mainHolder);
            card = (CardView) itemView.findViewById(R.id.placeCard);
            image = (ImageView) itemView.findViewById(R.id.movieImage);
            holder = (RelativeLayout) itemView.findViewById(R.id.movieNameHolder);
            name = (TextView) itemView.findViewById(R.id.movieName);
        }
    }
}

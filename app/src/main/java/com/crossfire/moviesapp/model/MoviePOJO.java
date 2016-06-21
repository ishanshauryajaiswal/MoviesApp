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

package com.crossfire.moviesapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 28-05-2016
 */
public class MoviePOJO implements Parcelable {

    public String original_title;
    public String title;
    public String poster_path;
    public String backdrop_path;
    public String overview;
    public String release_date;
    public float vote_average;

    public MoviePOJO()
    {

    }
    public MoviePOJO(Parcel in)
    {
        super();
        original_title=in.readString();
        title=in.readString();
        poster_path=in.readString();
        backdrop_path=in.readString();
        overview=in.readString();
        release_date=in.readString();
        vote_average=in.readFloat();

    }

    public static final Creator<MoviePOJO> CREATOR=new Parcelable.Creator<MoviePOJO>() {
        @Override
        public MoviePOJO createFromParcel(Parcel source) {
            return new MoviePOJO(source);
        }
        @Override
        public MoviePOJO[] newArray(int size) {
            return new MoviePOJO[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel,int i){
        parcel.writeString(original_title);
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeFloat(vote_average);
    }


    public String getOriginal_title(){
        return original_title;
    }
    public String getTitle(){
        return title;
    }
    public String getPoster_path(){
        return "http://image.tmdb.org/t/p/w185"+poster_path;
    }
    public String getBackdrop_path() {
        return "http://image.tmdb.org/t/p/w500"+backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getVote_average() {
        return vote_average;
    }
}

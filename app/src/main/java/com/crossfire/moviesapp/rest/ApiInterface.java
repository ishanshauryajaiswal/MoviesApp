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

package com.crossfire.moviesapp.rest;

import com.crossfire.moviesapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 20-05-2016
 */
public interface ApiInterface {

    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey , @Query("page")int page);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}


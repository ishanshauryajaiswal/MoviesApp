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

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Akshat Pandey
 * @version 1.0
 * @date 20-05-2016
 */
public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

package com.crossfire.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crossfire.moviesapp.model.Movie;
import com.crossfire.moviesapp.model.MoviePOJO;
import com.crossfire.moviesapp.model.MoviesResponse;
import com.crossfire.moviesapp.model.adapter.MovieAdapter;
import com.crossfire.moviesapp.model.helper.EndlessRecyclerViewScrollListener;
import com.crossfire.moviesapp.rest.ApiClient;
import com.crossfire.moviesapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridLayoutManager mStaggeredLayoutManager;
    private MovieAdapter mAdapter;
    private final static String API_KEY = "ab5aee0b18da89dd9e026d35754c24f1";
    private ArrayList<MoviePOJO>mlist_save_state=new ArrayList<>();
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MovieAdapter(getApplicationContext());

        ConfigViews();

        if (savedInstanceState!=null){
            mlist_save_state=savedInstanceState.getParcelableArrayList(getString(R.string.saved_list));
            mAdapter.setMovieList(mlist_save_state);
        }
        else {

            apiService = ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY, 1);

            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    List<MoviePOJO> movies = response.body().results;
                    mlist_save_state.addAll(movies);
                    mAdapter.setMovieList(movies);
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    Log.e("App", t.toString());
                }
            });
        }
        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(mStaggeredLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                getmore(page);
            }
        });

    }

    public void getmore(int offset){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Call<MoviesResponse> call = apiService.getPopularMovies(API_KEY,offset);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<MoviePOJO> movies = response.body().results;
                mlist_save_state.addAll(movies);
                mAdapter.setMovieList(movies);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("App", t.toString());
            }
        });
    }

    private void ConfigViews() {

        mRecyclerView= (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mStaggeredLayoutManager = new GridLayoutManager(getBaseContext(),2);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.saved_list),mlist_save_state);
    }
}
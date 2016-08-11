package com.springer.patryk.popularmoviesapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.springer.patryk.popularmoviesapp.adapters.MovieAdapter;
import com.springer.patryk.popularmoviesapp.models.Movie;
import com.springer.patryk.popularmoviesapp.models.MovieResponse;
import com.springer.patryk.popularmoviesapp.rest.ApiClient;
import com.springer.patryk.popularmoviesapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public static final String EXTRA_VOTE="average_vote";
    public static final String EXTRA_RELEASED_DATE="released_date";
    public static final String EXTRA_POSTER="poster";
    public static final String EXTRA_PLOT_SYNOPSIS="plot_synopsis";
    public static final String EXTRA_TITLE="title";

    private final static String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private final static String API_KEY = BuildConfig.THE_MOVIE_DB_API_TOKEN;
    private GridView gridView;
    private MovieAdapter gridViewAdapter;

    public MainActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        callApiForData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scrollPosition",gridView.getFirstVisiblePosition());
    }

    public void callApiForData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String preferenceValue = sharedPreferences.getString(getString(R.string.pref_sort_key), getString(R.string.pref_sort_default));
        Call<MovieResponse> call;
        if (preferenceValue.equals(getString(R.string.pref_sort_top_rated)))
            call = apiService.getTopRatedMovies(API_KEY);
        else
            call = apiService.getMostPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
                         @Override
                         public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                             int statusCode = response.code();
                             setGridViewAdapter(response);
                         }

                         @Override
                         public void onFailure(Call<MovieResponse> call, Throwable t) {

                         }
                     }
        );
    }

    public void setGridViewAdapter(Response<MovieResponse> response) {
        List<Movie> movies = response.body().getResults();
        gridViewAdapter = new MovieAdapter(getActivity(), movies);
        gridView.setAdapter(gridViewAdapter);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);


            gridView = (GridView) rootView.findViewById(R.id.moviesList);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                    Movie movieItem = gridViewAdapter.getItem(i);
                                                    Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
                                                    intent.putExtra(EXTRA_TITLE, movieItem.getOriginalTitle());
                                                    intent.putExtra(EXTRA_PLOT_SYNOPSIS, movieItem.getOverview());
                                                    intent.putExtra(EXTRA_POSTER, movieItem.getPosterPath());
                                                    intent.putExtra(EXTRA_RELEASED_DATE, movieItem.getReleaseDate());
                                                    intent.putExtra(EXTRA_VOTE, movieItem.getVoteAverage().toString());
                                                    startActivity(intent);
                                                }
                                            }
            );

        return rootView;
    }


}

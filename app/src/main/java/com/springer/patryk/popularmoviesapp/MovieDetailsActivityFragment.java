package com.springer.patryk.popularmoviesapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.springer.patryk.popularmoviesapp.adapters.MovieAdapter;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailsActivityFragment extends Fragment {

    public MovieDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_movie_details, container, false);
        Intent incomeMovie=getActivity().getIntent();
        String title=incomeMovie.getStringExtra(MainActivityFragment.EXTRA_TITLE);
        String vote=incomeMovie.getStringExtra(MainActivityFragment.EXTRA_VOTE);
        String releasedDate=incomeMovie.getStringExtra(MainActivityFragment.EXTRA_RELEASED_DATE);
        String plotSynopsis=incomeMovie.getStringExtra(MainActivityFragment.EXTRA_PLOT_SYNOPSIS);
        String posterPath=incomeMovie.getStringExtra(MainActivityFragment.EXTRA_POSTER);
        ((TextView)rootView.findViewById(R.id.movieDetails_title)).setText(title);
        ((TextView)rootView.findViewById(R.id.movieDetails_plot_synopsis)).setText("Description: "+plotSynopsis);
        ((TextView)rootView.findViewById(R.id.movieDetails_release_date)).setText("Released: "+releasedDate);
        ((TextView)rootView.findViewById(R.id.movieDetails_vote_avarage)).setText("Vote: "+vote);
        Picasso.with(getContext()).load(MovieAdapter.BASE_URL+"w185/"+posterPath).into((ImageView) rootView.findViewById(R.id.movieDetails_poster));
        return rootView;
    }
}

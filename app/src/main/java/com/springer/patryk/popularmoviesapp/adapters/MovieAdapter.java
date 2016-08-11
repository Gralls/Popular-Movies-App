package com.springer.patryk.popularmoviesapp.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.springer.patryk.popularmoviesapp.R;
import com.springer.patryk.popularmoviesapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Patryk on 2016-07-25.
 */
public class MovieAdapter extends ArrayAdapter<Movie>  {
    private static final String LOG_TAG=MovieAdapter.class.getSimpleName();
    public static final String BASE_URL="http://image.tmdb.org/t/p/";
    public MovieAdapter(Activity context,List<Movie> movieList){
        super(context,0,movieList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
           convertView= LayoutInflater.from(getContext()).inflate(R.layout.movie_element,null);
        Movie movie=getItem(position);
        ImageView poster = (ImageView)convertView.findViewById(R.id.moviePoster);
        Picasso.with(getContext()).load(BASE_URL+"w185/"+movie.getPosterPath()).into(poster);
        return convertView;
    }


}

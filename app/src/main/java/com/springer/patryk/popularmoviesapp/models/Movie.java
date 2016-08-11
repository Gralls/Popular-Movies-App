package com.springer.patryk.popularmoviesapp.models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Patryk on 2016-07-25.
 */
public class Movie {
    private final static String LOG_TAG = Movie.class.getSimpleName();
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("id")
    private Integer id;


    public Movie(String posterPath, String overview, String releaseDate,
                 String originalTitle, Double voteAverage, Integer id) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.voteAverage = voteAverage;
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

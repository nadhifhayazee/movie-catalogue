package com.example.submission1.presenter;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.submission1.R;
import com.example.submission1.model.Movie;

import java.util.ArrayList;

public class MoviesPresenter {

    private MoviesView mainView;
    private ArrayList<Movie> movies;
    private String[] dataTitle;
    private String[] dataGenre;
    private String[] dataDuration;
    private String[] dataDirector;
    private String[] dataRating;
    private String[] dataSynopsis;
    private TypedArray dataPoster;
    private TypedArray dataThumbnail;
    private Context context;
    public MoviesPresenter(MoviesView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        movies = new ArrayList<>();
        dataTitle = context.getResources().getStringArray(R.array.data_movie_title);
        dataGenre = context.getResources().getStringArray(R.array.data_movie_genre);
        dataDuration = context.getResources().getStringArray(R.array.data_movie_duration);
        dataDirector = context.getResources().getStringArray(R.array.data_movie_director);
        dataRating = context.getResources().getStringArray(R.array.data_movie_rating);
        dataSynopsis = context.getResources().getStringArray(R.array.data_movie_synopsis);
        dataPoster = context.getResources().obtainTypedArray(R.array.data_movie_poster);
        dataThumbnail = context.getResources().obtainTypedArray(R.array.data_movie_thumbnail);
        addItem();
    }

    private void addItem() {
        for (int i=0;i<dataTitle.length;i++){
            movies.add(new Movie(dataTitle[i],dataGenre[i],dataDuration[i],dataDirector[i],
                    dataRating[i],dataPoster.getResourceId(i,-1),dataThumbnail.getResourceId(i,-1),
                    dataSynopsis[i]));
        }
        getMovieList();
    }

    public void getMovieList() {
        mainView.showMovieList(movies);
    }
}

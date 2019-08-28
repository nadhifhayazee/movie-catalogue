package com.example.submission1.presenter;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.submission1.R;
import com.example.submission1.model.Movie;

import java.util.ArrayList;

public class TvShowPresenter {

    private TvShowView tvShowView;
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

    public TvShowPresenter(TvShowView tvShowView, Context context) {
        this.tvShowView = tvShowView;
        this.context = context;
        movies = new ArrayList<>();
        dataTitle = context.getResources().getStringArray(R.array.data_tv_show_title);
        dataGenre = context.getResources().getStringArray(R.array.data_tv_show_genre);
        dataDuration = context.getResources().getStringArray(R.array.data_tv_show_duration);
        dataDirector = context.getResources().getStringArray(R.array.data_tv_show_director);
        dataRating = context.getResources().getStringArray(R.array.data_tv_show_rating);
        dataSynopsis = context.getResources().getStringArray(R.array.data_tv_show_synopsis);
        dataPoster = context.getResources().obtainTypedArray(R.array.data_tv_show_poster);
        dataThumbnail = context.getResources().obtainTypedArray(R.array.data_tv_show_thumbnail);
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
        tvShowView.showTvShowList(movies);
    }
}

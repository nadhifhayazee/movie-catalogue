package com.example.submission1.view.favorite;

import android.database.Cursor;

import com.example.submission1.database.DatabaseContract;
import com.example.submission1.database.TableHelper;
import com.example.submission1.model.MovieModel;
import com.example.submission1.statics.Formater;

import java.util.ArrayList;

public class FavoritePresenter {


    private FavoriteView favoriteView;

    public FavoritePresenter(FavoriteView catalogueView) {
        this.favoriteView = catalogueView;
    }

    public void getMovieFavorites() {
        Cursor cursor = TableHelper.getMovieHelper().queryAll();
        ArrayList<MovieModel> movies = new ArrayList<>();

        while (cursor.moveToNext()) {
            movies.add(Formater
                    .fromJson(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.MovieColumns.MOVIE_JSON)),
                            MovieModel.class));
        }

        favoriteView.showFavorite(movies);
        if (movies.size() == 0) {
            favoriteView.onEmpty();
        }

    }

    public void getTvFavorites() {
        Cursor cursor = TableHelper.getTvHelper().queryAll();
        ArrayList<MovieModel> tv = new ArrayList<>();

        while (cursor.moveToNext()) {
            tv.add(Formater
                    .fromJson(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.TvColumns.TV_JSON)),
                            MovieModel.class));
        }

        favoriteView.showFavorite(tv);
        if (tv.size() == 0) {
            favoriteView.onEmpty();
        }
    }
}

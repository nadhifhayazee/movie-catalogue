package com.example.submission1.view.detail;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.submission1.BuildConfig;
import com.example.submission1.database.DatabaseContract;
import com.example.submission1.database.TableHelper;
import com.example.submission1.model.MovieModel;
import com.example.submission1.presenter.basedetail.BaseDetailPresenter;
import com.example.submission1.presenter.basedetail.DetailMovieView;
import com.example.submission1.rest.ApiUtil;
import com.example.submission1.utils.Formater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMoviePresenter extends BaseDetailPresenter {


    private MovieModel movie;


    public DetailMoviePresenter(DetailMovieView detailMovieView, int movie_id) {
        super(detailMovieView, movie_id);
        isFavorited();
    }

    @Override
    public void deleteFavorite() {
        if (TableHelper.getMovieHelper().deleteById(movie_id) > 0) {
            isFavorited = false;
            detailMovieView.onDeleteSuccess();
            detailMovieView.isNotFavorited();
        } else {
            detailMovieView.onDeleteFailed();
        }
    }

    @Override
    public void addFavorite() {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MovieColumns.MOVIE_ID, movie.id);
        values.put(DatabaseContract.MovieColumns.MOVIE_JSON, Formater.toJson(movie));
        long result = TableHelper.getMovieHelper().insert(values);
        if (result > 0) {
            isFavorited = true;
            detailMovieView.onInsertSuccess();
            detailMovieView.isFavorited();
        } else
            detailMovieView.onInsertFailed();
    }


    @Override
    public void getDetail() {
        ApiUtil.getRequest().getDetailMovie(movie_id, BuildConfig.Api_Key, mDeviceLanguage)
                .enqueue(new Callback<MovieModel>() {
                    @Override
                    public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                        if (response.isSuccessful()) {
                            movie = response.body();
                            detailMovieView.bind(movie);
                            detailMovieView.hideProgressBar();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void isFavorited() {
        Cursor cursor = TableHelper.getMovieHelper().queryById(movie_id);
        if (cursor.moveToFirst()) {
            isFavorited = true;
            detailMovieView.isFavorited();
        } else {
            isFavorited = false;

        }
    }

}

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

public class DetailTvPresenter extends BaseDetailPresenter {
    private MovieModel tv;

    public DetailTvPresenter(DetailMovieView detailMovieView, int movie_id) {
        super(detailMovieView, movie_id);
        isFavorited();
    }


    @Override
    public void isFavorited() {
        Cursor cursor = TableHelper.getTvHelper().queryById(movie_id);
        if (cursor.moveToFirst()) {
            isFavorited = true;
            detailMovieView.isFavorited();
        } else {
            isFavorited = false;
        }
    }

    @Override
    public void getDetail() {
        ApiUtil.getRequest().getDetailTv(movie_id, BuildConfig.Api_Key, mDeviceLanguage)
                .enqueue(new Callback<MovieModel>() {
                    @Override
                    public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                        if (response.isSuccessful()) {
                            tv = response.body();
                            detailMovieView.bind(tv);
                            detailMovieView.hideProgressBar();
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieModel> call, Throwable t) {
                        detailMovieView.hideProgressBar();

                    }
                });
    }

    @Override
    public void deleteFavorite() {
        if (TableHelper.getTvHelper().deleteById(movie_id) > 0) {
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
        values.put(DatabaseContract.TvColumns.TV_ID, tv.id);
        values.put(DatabaseContract.TvColumns.TV_JSON, Formater.toJson(tv));
        long result = TableHelper.getTvHelper().insert(values);
        if (result > 0) {
            isFavorited = true;
            detailMovieView.onInsertSuccess();
            detailMovieView.isFavorited();

        } else
            detailMovieView.onInsertFailed();
    }
}

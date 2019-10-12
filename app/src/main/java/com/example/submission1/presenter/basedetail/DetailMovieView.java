package com.example.submission1.presenter.basedetail;

import com.example.submission1.model.MovieModel;

public interface DetailMovieView {
    void bind(MovieModel movie);
    void hideProgressBar();
    void onInsertSuccess();
    void onInsertFailed();
    void isFavorited();
    void onDeleteSuccess();
    void onDeleteFailed();
    void isNotFavorited();
}

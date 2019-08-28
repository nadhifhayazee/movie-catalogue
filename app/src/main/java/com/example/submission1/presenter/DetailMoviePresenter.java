package com.example.submission1.presenter;

import com.example.submission1.model.Movie;

public class DetailMoviePresenter {
    private DetailMovieView detailMovieView;

    public DetailMoviePresenter(DetailMovieView detailMovieView) {
        this.detailMovieView = detailMovieView;
    }

    public void startBinding(Movie movie) {
        detailMovieView.bind(movie);
    }
}

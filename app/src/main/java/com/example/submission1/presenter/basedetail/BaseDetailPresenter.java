package com.example.submission1.presenter.basedetail;

import java.util.Locale;

public abstract class BaseDetailPresenter {
    public DetailMovieView detailMovieView;
    public int movie_id;
    public String mDeviceLanguage;
    public boolean isFavorited;

    public BaseDetailPresenter(DetailMovieView detailMovieView, int movie_id) {
        this.detailMovieView = detailMovieView;
        this.movie_id = movie_id;
        switch (Locale.getDefault().getLanguage()){
            case "in":
                mDeviceLanguage = "id";
                break;
            default:
                mDeviceLanguage = "en";
                break;
        }
    }

    public void onBtnFavoriteClicked(){
        if (!isFavorited){
            addFavorite();
        } else {
            deleteFavorite();
        }
    }

    public abstract void deleteFavorite();
    public abstract void addFavorite();
    public abstract void getDetail();
    public abstract void isFavorited();


}

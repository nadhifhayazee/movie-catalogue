package com.example.submission1.view.favorite;

import com.example.submission1.model.MovieModel;

import java.util.ArrayList;

public interface FavoriteView {
    void showFavorite(ArrayList<MovieModel> resuts);

    void onEmpty();
}

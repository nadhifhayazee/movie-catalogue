package com.example.submission1.presenter.basecatalogue;

import com.example.submission1.model.Genre;
import com.example.submission1.model.MovieModel;

import java.util.ArrayList;

public interface CatalogueView {
    void showMovieList(ArrayList<MovieModel> movies, ArrayList<Genre> genres);

    void hideProgressBar();

}

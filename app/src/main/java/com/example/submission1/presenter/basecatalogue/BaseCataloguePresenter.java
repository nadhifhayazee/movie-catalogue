package com.example.submission1.presenter.basecatalogue;

import com.example.submission1.model.Genre;
import com.example.submission1.model.MovieModel;

import java.util.ArrayList;
import java.util.Locale;

public abstract class BaseCataloguePresenter {

    public ArrayList<MovieModel> mMovies;
    public ArrayList<Genre> mGenres;
    public String mDeviceLanguage;
    public CatalogueView catalogueView;

    public BaseCataloguePresenter(CatalogueView catalogueView) {
        this.catalogueView = catalogueView;
        switch (Locale.getDefault().getLanguage()) {
            case "in":
                mDeviceLanguage = "id";
                break;
            default:
                mDeviceLanguage = "en";
                break;
        }
    }


    public abstract void initGenres();
}

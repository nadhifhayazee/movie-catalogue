package com.example.submission1.view.tv;

import com.example.submission1.BuildConfig;
import com.example.submission1.presenter.basecatalogue.BaseCataloguePresenter;
import com.example.submission1.presenter.basecatalogue.CatalogueView;
import com.example.submission1.rest.ApiUtil;
import com.example.submission1.rest.response.GenresResponse;
import com.example.submission1.rest.response.MovieListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowPresenter extends BaseCataloguePresenter {



    public TvShowPresenter(CatalogueView catalogueView) {
        super(catalogueView);
    }

    @Override
    public void initGenres() {
        ApiUtil.getRequest().getTvGenres(BuildConfig.Api_Key,mDeviceLanguage).enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                if (response.isSuccessful()){
                    mGenres = response.body().genres;
                    sendMoviesRequest();
                }
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {

            }
        });
    }

    private void sendMoviesRequest() {
        ApiUtil.getRequest().getTvList(BuildConfig.Api_Key,mDeviceLanguage,2).enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if (response.isSuccessful()){
                    mMovies = response.body().results;
                    catalogueView.hideProgressBar();
                    catalogueView.showMovieList(mMovies,mGenres);
                }
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                catalogueView.hideProgressBar();
            }
        });
    }


}

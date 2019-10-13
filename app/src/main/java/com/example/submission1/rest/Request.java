package com.example.submission1.rest;

import com.example.submission1.model.MovieModel;
import com.example.submission1.rest.response.GenresResponse;
import com.example.submission1.rest.response.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Request {
    @GET("movie/now_playing")
    Call<MovieListResponse> getMovieList(@Query("api_key") String api_key, @Query("language") String language,
                                         @Query("page") int page);

    @GET("tv/popular")
    Call<MovieListResponse> getTvList(@Query("api_key") String api_key, @Query("language") String language,
                                      @Query("page") int page);


    @GET("movie/{movie_id}")
    Call<MovieModel> getDetailMovie(@Path("movie_id") int movie_id, @Query("api_key") String api_key,
                                    @Query("language") String language);

    @GET("tv/{tv_id}")
    Call<MovieModel> getDetailTv(@Path("tv_id") int tv_id, @Query("api_key") String api_key,
                                 @Query("language") String language);


    @GET("genre/movie/list")
    Call<GenresResponse> getMovieGenres(@Query("api_key") String api_key,
                                        @Query("language") String language);

    @GET("genre/tv/list")
    Call<GenresResponse> getTvGenres(@Query("api_key") String api_key,
                                     @Query("language") String language);
}

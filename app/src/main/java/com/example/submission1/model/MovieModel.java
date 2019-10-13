package com.example.submission1.model;

import java.util.ArrayList;

public class MovieModel {
    public int id;
    public String original_title;
    public String release_date;
    public ArrayList<Genre> genres;
    public ArrayList<Integer> genre_ids;
    public float vote_average;
    public String overview;
    public String poster_path;
    public String backdrop_path;
    public String first_air_date;
    public String original_name;
    public int runtime;

    public MovieModel movieModel;

    public MovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public String getGenres(ArrayList<Genre> genres, ArrayList<Integer> genre_ids) {
        StringBuilder genre = new StringBuilder();

        if (genres != null && genres.size() != 0 && genre_ids != null && genre_ids.size() != 0) {

            for (Genre item : genres) {
                if (genre_ids.get(0) == item.id) {
                    genre.append(item.name);
                }
            }
            return genre.toString();
        }

        return "-";

    }

    public String getGenre() {
        if (genres != null && genres.size() != 0) {
            return genres.get(0).name;
        }
        return "-";
    }


}

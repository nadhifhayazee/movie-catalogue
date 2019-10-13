package com.example.submission1.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    static String TABLE_FAVORITE_MOVIE = "movie_fav";
    static String TABLE_FAVORITE_TV = "tv_fav";

    public static final class MovieColumns implements BaseColumns {
        public static String MOVIE_ID = "movie_id";
        public static String MOVIE_JSON = "movie_json";
    }

    public static final class TvColumns implements BaseColumns {
        public static String TV_ID = "tv_id";
        public static String TV_JSON = "tv_json";
    }

}

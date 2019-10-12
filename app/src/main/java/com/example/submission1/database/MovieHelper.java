package com.example.submission1.database;

import android.content.Context;

public class MovieHelper extends BaseSqlHelper {


    public MovieHelper(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return DatabaseContract.TABLE_FAVORITE_MOVIE;
    }

    @Override
    public String getID() {
        return DatabaseContract.MovieColumns.MOVIE_ID;
    }
}

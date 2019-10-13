package com.example.submission1.database;

import android.app.Application;
import android.content.Context;

public class TableHelper extends Application {
    private static TvHelper tvHelper;
    private static MovieHelper movieHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        builder(getApplicationContext());
    }

    private void builder(Context context) {

        movieHelper = new MovieHelper(context);
        tvHelper = new TvHelper(context);
    }

    public static TvHelper getTvHelper() {
        return tvHelper;
    }

    public static MovieHelper getMovieHelper() {
        return movieHelper;
    }


}

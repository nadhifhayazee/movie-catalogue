package com.example.submission1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbfavorite";

    private static final int DATABASE_VERSION = 2;

    private static final String SQL_CREATE_TABLE_MOVIE = "CREATE TABLE " + DatabaseContract.TABLE_FAVORITE_MOVIE
            + "("
            + DatabaseContract.MovieColumns.MOVIE_ID + " INTEGER PRIMARY KEY, "
            + DatabaseContract.MovieColumns.MOVIE_JSON + " TEXT"
            + ")";

    private static final String SQL_CREATE_TABLE_TV = "CREATE TABLE " + DatabaseContract.TABLE_FAVORITE_TV
            + "("
            + DatabaseContract.TvColumns.TV_ID + " INTEGER PRIMARY KEY, "
            + DatabaseContract.TvColumns.TV_JSON + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_FAVORITE_TV);
        onCreate(db);

    }
}

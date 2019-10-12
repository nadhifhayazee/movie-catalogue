package com.example.submission1.database;

import android.content.Context;

public class TvHelper extends BaseSqlHelper {


    public TvHelper(Context context) {
        super(context);
    }

    @Override
    public String getTableName() {
        return DatabaseContract.TABLE_FAVORITE_TV;
    }

    @Override
    public String getID() {
        return DatabaseContract.TvColumns.TV_ID;
    }
}

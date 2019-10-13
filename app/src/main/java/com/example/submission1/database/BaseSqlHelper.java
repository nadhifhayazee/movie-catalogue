package com.example.submission1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

public abstract class BaseSqlHelper {

    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase db;

    public BaseSqlHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = databaseHelper.getReadableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (db.isOpen()) db.close();
    }

    public Cursor queryAll() {
        return db.query(getTableName(),
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public Cursor queryById(int id) {
        return db.query(getTableName(), null
                , getID() + " = ?"
                , new String[]{String.valueOf(id)}
                , null
                , null
                , null
                , null);
    }

    public long insert(ContentValues values) {
        Gson gson = new Gson();

        return db.insert(getTableName(), null, values);
    }


    public int deleteById(int id) {
        return db.delete(getTableName(), getID() +
                " = ?", new String[]{String.valueOf(id)});
    }

    public abstract String getTableName();

    public abstract String getID();
}

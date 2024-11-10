package com.example.android_10.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String CREATE_TABLE_LISTS =
            "CREATE TABLE Lists (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "date INTEGER NOT NULL, " +
                    "description TEXT)";

    private final String CREATE_TABLE_TYPE =
            "CREATE TABLE Type (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "label TEXT NOT NULL, " +
                    "rule TEXT NOT NULL)";

    private final String CREATE_TABLE_PRODUCT =
            "CREATE TABLE Product (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "count REAL NOT NULL, " +
                    "list_id INTEGER NOT NULL, " +
                    "checked INTEGER NOT NULL, " +
                    "count_type INTEGER NOT NULL, " +
                    "FOREIGN KEY(list_id) REFERENCES Lists(_id), " +
                    "FOREIGN KEY(count_type) REFERENCES Type(_id))";

    public DatabaseHelper(Context context) {
        super(context, MyConstants.DATABASE_NAME, null, MyConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LISTS);
        db.execSQL(CREATE_TABLE_TYPE);
        db.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Product");
        db.execSQL("DROP TABLE IF EXISTS Type");
        db.execSQL("DROP TABLE IF EXISTS Lists");
        onCreate(db);
    }
}

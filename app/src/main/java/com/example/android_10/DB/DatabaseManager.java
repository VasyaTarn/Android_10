package com.example.android_10.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private final SQLiteDatabase db;

    public DatabaseManager(Context context)
    {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
        db = dbHelper.getWritableDatabase();
    }

    public long insertList(String name, long date, String description)
    {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        values.put("description", description);
        return db.insert("Lists", null, values);
    }

    public Cursor getAllLists()
    {
        return db.query("Lists", null, null, null, null, null, "date DESC");
    }

    public void updateList(int id, String name, long date, String description)
    {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("date", date);
        values.put("description", description);
        db.update("lists", values, "_id = ?", new String[]{String.valueOf(id)});
    }

    public void deleteList(int id)
    {
        db.delete("lists", "_id = ?", new String[]{String.valueOf(id)});
    }

    public void close()
    {
        db.close();
    }
}

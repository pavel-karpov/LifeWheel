package com.pavel_karpov.first.myapp1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pavel_karpov.first.myapp1.database.DbSceme.DbNoteName;

public class NoteDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;
    public NoteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ DbNoteName.NAME + " ( _id integer primary key autoincrement, "
                + DbNoteName.Columns.NOTE + " TEXT, "+ DbNoteName.Columns.DATE
                + " TEXT, "+DbNoteName.Columns.UUID+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

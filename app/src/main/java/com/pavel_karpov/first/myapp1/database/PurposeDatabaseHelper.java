package com.pavel_karpov.first.myapp1.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pavel_karpov.first.myapp1.database.DbSceme.DbPurposeName;

public class PurposeDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "purposes.db";
    private static final int DATABASE_VERSION = 1;
    public PurposeDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ DbPurposeName.NAME + " ( _id integer primary key autoincrement, "+ DbPurposeName.Columns.PURPOSE + " TEXT, "+ DbPurposeName.Columns.DATE +
                " TEXT, "+ DbPurposeName.Columns.RATING + " TEXT, "+ DbPurposeName.Columns.UUID + " TEXT, "+ DbPurposeName.Columns.CATEGORY + " TEXT, " + DbPurposeName.Columns.ISCOMPLETED
                +" INTEGER, "+DbPurposeName.Columns.FLAGCOMPLETED + " INTEGER, " + DbPurposeName.Columns.PHOTO_PATH + " TEXT, " + DbPurposeName.Columns.PURPOSE_DESCRIPTION + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

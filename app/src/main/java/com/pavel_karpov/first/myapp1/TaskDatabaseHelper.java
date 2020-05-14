package com.pavel_karpov.first.myapp1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.pavel_karpov.first.myapp1.database.DbSceme.DbName;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Tasks.db";
    private static final int DATABASE_VERSION = 1;
    public TaskDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DbName.NAME + " ( _id integer primary key autoincrement, " + DbName.Colls.TASK + " TEXT, " + DbName.Colls.TASK_DASCRIPTION + " TEXT, "
        + DbName.Colls.PERCENT + " TEXT, " + DbName.Colls.ISCOMPLETED + " INTEGER, " + DbName.Colls.FLAGCOMPLETED + " INTEGER, " + DbName.Colls.UUID_PURPOSE + " TEXT, "
                + DbName.Colls.UUID_TASK + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

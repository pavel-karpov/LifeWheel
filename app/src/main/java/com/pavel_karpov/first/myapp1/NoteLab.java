package com.pavel_karpov.first.myapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pavel_karpov.first.myapp1.database.DbSceme.DbNoteName;

import java.util.ArrayList;

public class NoteLab {
    private Context mContext;
    private static NoteLab mNoteLab;
    private SQLiteDatabase mNoteDatabase;
    public static NoteLab getInstance(Context context){
        if (mNoteLab==null){
            mNoteLab = new NoteLab(context);
        }
        return mNoteLab;
    }
    private NoteLab(Context context){
        mContext=context;
        mNoteDatabase = new NoteDatabaseHelper(mContext).getWritableDatabase();
    }

    public static ContentValues getContentValuesNote(Note note){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbNoteName.Columns.NOTE,note.getmText());
        contentValues.put(DbNoteName.Columns.DATE,note.getmDate());
        contentValues.put(DbNoteName.Columns.UUID,note.getmUuid());
        return contentValues;
    }
    public void addTaskNote(Note note){
        ContentValues contentValues = getContentValuesNote(note);
        mNoteDatabase.insert(DbNoteName.NAME,null,contentValues);
    }
    public void updateTaskNote(Note note){
        ContentValues contentValues = getContentValuesNote(note);
        mNoteDatabase.update(DbNoteName.NAME,contentValues,DbNoteName.Columns.UUID + " = ?", new String[]{note.getmUuid()});
    }
    public void deleteTaskNote(String uuid){
        mNoteDatabase = new NoteDatabaseHelper(mContext).getWritableDatabase();
        mNoteDatabase.delete(DbNoteName.NAME,  DbNoteName.Columns.UUID + " = ? ",new String[]{uuid});
    }
    public void deleteAllNotes(){
        mNoteDatabase = new NoteDatabaseHelper(mContext).getWritableDatabase();
        mNoteDatabase.delete(DbNoteName.NAME,  null,null);
    }
    public ArrayList<Note> readTaskNotes(){
        ArrayList<Note>notes =new ArrayList<>();
        Cursor cursor = mNoteDatabase.query(DbNoteName.NAME,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            return notes;
        }
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String sNote = cursor.getString(cursor.getColumnIndex(DbNoteName.Columns.NOTE));
                String date = cursor.getString(cursor.getColumnIndex(DbNoteName.Columns.DATE));
                String uuid = cursor.getString(cursor.getColumnIndex(DbNoteName.Columns.UUID));
                Note note =new Note(sNote,date,uuid);
                notes.add(note);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return notes;
    }
}

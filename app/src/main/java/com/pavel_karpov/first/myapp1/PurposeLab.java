package com.pavel_karpov.first.myapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pavel_karpov.first.myapp1.database.DbSceme.DbPurposeName;

import java.util.ArrayList;

public class PurposeLab {
    private static PurposeLab mPurposeLab;
    private Context mContext;
    private SQLiteDatabase mPurposeDtabase;
    public static PurposeLab getInstance(Context context){
        if (mPurposeLab==null){
            mPurposeLab = new PurposeLab(context);
        }
        return mPurposeLab;
    }
    private PurposeLab(Context context){
        mContext=context;
        mPurposeDtabase = new PurposeDatabaseHelper(mContext).getWritableDatabase();
    }

    public static ContentValues getContentValuesNote(Purpose purpose){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbPurposeName.Columns.PURPOSE,purpose.getmPurpose());
        contentValues.put(DbPurposeName.Columns.DATE,purpose.getmDate());
        contentValues.put(DbPurposeName.Columns.RATING,purpose.getmRating());
        contentValues.put(DbPurposeName.Columns.CATEGORY,purpose.getmCategory());
        contentValues.put(DbPurposeName.Columns.UUID,purpose.getmUuid());
        contentValues.put(DbPurposeName.Columns.ISCOMPLETED,purpose.getIsCompleted());
        contentValues.put(DbPurposeName.Columns.FLAGCOMPLETED,purpose.getFlagCompleted());
        contentValues.put(DbPurposeName.Columns.PHOTO_PATH, purpose.getmPhotoPath());
        contentValues.put(DbPurposeName.Columns.PURPOSE_DESCRIPTION,purpose.getmPurposeDescription());
        return contentValues;
    }
    public void addPurpose(Purpose purpose){
        ContentValues contentValues = getContentValuesNote(purpose);
        mPurposeDtabase.insert(DbPurposeName.NAME,null,contentValues);
    }
    public int updatePurpose(Purpose purpose){
        ContentValues contentValues = getContentValuesNote(purpose);
       return mPurposeDtabase.update(DbPurposeName.NAME,contentValues,DbPurposeName.Columns.UUID + " = ?", new String[]{purpose.getmUuid()});

    }
    public Purpose getPurpose(String uuid){
        Cursor cursor = mPurposeDtabase.query(DbPurposeName.NAME,null,DbPurposeName.Columns.UUID + " = ?",new String[]{uuid},null,null,null);
        cursor.moveToFirst();
        String purpose = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE));
        String date = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.DATE));
        String rating = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.RATING));
        String category = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.CATEGORY));
        String muuid = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.UUID));
        int isCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.ISCOMPLETED));
        int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.FLAGCOMPLETED));
        String photoPath = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PHOTO_PATH));
        String purposeDescription = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE_DESCRIPTION));
        Purpose note =new Purpose(purpose,date,rating,category,muuid,isCompleted,flagCompleted,photoPath,purposeDescription);
        return note;
    }
    public void deletePurpose(Purpose purpose){
        mPurposeDtabase = new PurposeDatabaseHelper(mContext).getWritableDatabase();
        mPurposeDtabase.delete(DbPurposeName.NAME,  DbPurposeName.Columns.UUID + " = ? ",new String[]{purpose.getmUuid()});
    }
    public void deleteAll(){
        mPurposeDtabase = new PurposeDatabaseHelper(mContext).getWritableDatabase();
        mPurposeDtabase.delete(DbPurposeName.NAME,null,null);
    }
    public ArrayList<Purpose> readPurposes(){
        ArrayList<Purpose>purposes =new ArrayList<>();
        Cursor cursor = mPurposeDtabase.query(DbPurposeName.NAME,null,null,null,null,null,null);
        if (cursor.getCount()==0){
            return purposes;
        }
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String purpose = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE));
                String date = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.DATE));
                String rating = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.RATING));
                String category = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.CATEGORY));
                String uuid = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.UUID));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.FLAGCOMPLETED));
                String photoPath = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PHOTO_PATH));
                String purposeDescription = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE_DESCRIPTION));
                Purpose note =new Purpose(purpose,date,rating,category,uuid,isCompleted,flagCompleted,photoPath,purposeDescription);
                purposes.add(note);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return purposes;
    }
    public ArrayList<Purpose> readPurposeseCategory(String mCategory){
        ArrayList<Purpose>purposesCategory = new ArrayList<>();
        Cursor cursor = mPurposeDtabase.query(DbPurposeName.NAME,null,DbPurposeName.Columns.CATEGORY + " = ?",new String[]{mCategory},null,null,null);
        if (cursor.getCount()==0){
            return purposesCategory;
        }
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String purpose = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE));
                String date = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.DATE));
                String rating = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.RATING));
                String category = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.CATEGORY));
                String uuid = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.UUID));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.FLAGCOMPLETED));
                String photoPath = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PHOTO_PATH));
                String purposeDescription = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE_DESCRIPTION));
                Purpose note =new Purpose(purpose,date,rating,category,uuid,isCompleted,flagCompleted,photoPath,purposeDescription);
                purposesCategory.add(note);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return purposesCategory;
    }
    public ArrayList<Purpose>readPurposesCompleted(String mIsCompleted){
        ArrayList<Purpose>purposesCategory = new ArrayList<>();
        Cursor cursor = mPurposeDtabase.query(DbPurposeName.NAME,null,DbPurposeName.Columns.ISCOMPLETED + " = ?",new String[]{mIsCompleted},null,null,null);
        if (cursor.getCount()==0){
            return purposesCategory;
        }
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String purpose = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE));
                String date = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.DATE));
                String rating = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.RATING));
                String category = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.CATEGORY));
                String uuid = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.UUID));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.FLAGCOMPLETED));
                String photoPath = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PHOTO_PATH));
                String purposeDescription = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE_DESCRIPTION));
                Purpose note =new Purpose(purpose,date,rating,category,uuid,isCompleted,flagCompleted,photoPath,purposeDescription);
                purposesCategory.add(note);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return purposesCategory;
    }
    public ArrayList<Purpose> readPurposesDate(String mDate){
        ArrayList<Purpose>purposesDate = new ArrayList<>();
        Cursor cursor = mPurposeDtabase.query(DbPurposeName.NAME,null,DbPurposeName.Columns.DATE + " = ?",new String[]{mDate},null,null,null);
        if (cursor.getCount()==0){
            return purposesDate;
        }
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String purpose = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE));
                String date = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.DATE));
                String rating = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.RATING));
                String category = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.CATEGORY));
                String uuid = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.UUID));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbPurposeName.Columns.FLAGCOMPLETED));
                if (isCompleted==1){
                    String photoPath = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PHOTO_PATH));
                    String purposeDescription = cursor.getString(cursor.getColumnIndex(DbPurposeName.Columns.PURPOSE_DESCRIPTION));
                    Purpose note =new Purpose(purpose,date,rating,category,uuid,isCompleted,flagCompleted,photoPath,purposeDescription);
                    purposesDate.add(note);
                    cursor.moveToNext();}
                else cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        cursor.close();
        return purposesDate;
    }

}

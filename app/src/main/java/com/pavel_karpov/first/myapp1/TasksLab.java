package com.pavel_karpov.first.myapp1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pavel_karpov.first.myapp1.database.DbSceme.DbName;

import java.util.ArrayList;

public class TasksLab {
    private Context mContext;
    private SQLiteDatabase sqLiteDatabase;
    private static TasksLab mTasksLab;

    public static TasksLab getInstance(Context context){
        if (mTasksLab==null) {
            mTasksLab = new TasksLab(context);
        }
        return mTasksLab;
    }

    private TasksLab(Context context){
        mContext = context.getApplicationContext();
        sqLiteDatabase = new TaskDatabaseHelper(mContext).getWritableDatabase();
        if (sqLiteDatabase!=null){
           // Toast.makeText(mContext,"ss",Toast.LENGTH_LONG).show();
        }
    }

    public static ContentValues getContentValues(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbName.Colls.TASK,task.getmTask());
        contentValues.put(DbName.Colls.TASK_DASCRIPTION, task.getmTaskDescription());
        contentValues.put(DbName.Colls.PERCENT,task.getmPercent());
        contentValues.put(DbName.Colls.ISCOMPLETED,task.getmIsCompleted());
        contentValues.put(DbName.Colls.FLAGCOMPLETED,task.getmFlagCompleted());
        contentValues.put(DbName.Colls.UUID_PURPOSE,task.getmUUID());
        contentValues.put(DbName.Colls.UUID_TASK,task.getmUUIDTask());
        return contentValues;
    }
    public void addTask(Task task){
        ContentValues contentValues = getContentValues(task);
        sqLiteDatabase.insert(DbName.NAME,null,contentValues);
    }
    public int updateTask(Task task){
        ContentValues contentValues = getContentValues(task);
       return sqLiteDatabase.update(DbName.NAME,contentValues,  DbName.Colls.UUID_TASK + " = ?", new String[]{task.getmUUIDTask()});
    }
    public void deleteTask(Task task) {
        sqLiteDatabase = new TaskDatabaseHelper(mContext).getWritableDatabase();
        sqLiteDatabase.delete(DbName.NAME,  DbName.Colls.UUID_TASK + " = ?", new String[]{task.getmUUIDTask()});
    }
    public void deleteAllTasks() {
        sqLiteDatabase = new TaskDatabaseHelper(mContext).getWritableDatabase();
        sqLiteDatabase.delete(DbName.NAME,  null, null);
    }

    public ArrayList<Task> readTasks(){
        ArrayList<Task>tasks = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(DbName.NAME,null,null,null,null,null,null);
        if (cursor.getCount()==0){return tasks;}
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String task = cursor.getString(cursor.getColumnIndex(DbName.Colls.TASK));
                String taskDescription = cursor.getString(cursor.getColumnIndex(DbName.Colls.TASK_DASCRIPTION));
                String percent = cursor.getString(cursor.getColumnIndex(DbName.Colls.PERCENT));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbName.Colls.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbName.Colls.FLAGCOMPLETED));
                String uuidPurpose = cursor.getString(cursor.getColumnIndex(DbName.Colls.UUID_PURPOSE));
                String uuidTask = cursor.getString(cursor.getColumnIndex(DbName.Colls.UUID_TASK));
                Task mTask = new Task(task,taskDescription,percent,isCompleted,flagCompleted,uuidPurpose,uuidTask);
                tasks.add(mTask);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        else { return tasks;}
        cursor.close();
        return tasks;
    }
    public ArrayList<Task>readTasksUUID(String mUuidPurpose){
        ArrayList<Task>tasks = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(DbName.NAME,null,DbName.Colls.UUID_PURPOSE + " = ?",new String[]{mUuidPurpose},null,null,null);
        if (cursor.getCount()==0){return tasks;}
        if (cursor!=null){
            cursor.moveToFirst();
            do {
                String task = cursor.getString(cursor.getColumnIndex(DbName.Colls.TASK));
                String taskDescription = cursor.getString(cursor.getColumnIndex(DbName.Colls.TASK_DASCRIPTION));
                String percent = cursor.getString(cursor.getColumnIndex(DbName.Colls.PERCENT));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbName.Colls.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbName.Colls.FLAGCOMPLETED));
                String uuidPurpose = cursor.getString(cursor.getColumnIndex(DbName.Colls.UUID_PURPOSE));
                String uuidTask = cursor.getString(cursor.getColumnIndex(DbName.Colls.UUID_TASK));
                Task mTask = new Task(task,taskDescription,percent,isCompleted,flagCompleted,uuidPurpose,uuidTask);
                tasks.add(mTask);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }
        else { return tasks;}
        cursor.close();
        return tasks;

    }
    public Float readTasksChecked(String mUuidPurpose){
        float percentAll=0;
        ArrayList<Task>tasks = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.query(DbName.NAME,null,DbName.Colls.UUID_PURPOSE + " = ?",new String[]{mUuidPurpose},null,null,null);
        if (cursor.getCount()==0){return Float.valueOf(0);}
        if (cursor!=null) {
            cursor.moveToFirst();
            do {
                String task = cursor.getString(cursor.getColumnIndex(DbName.Colls.TASK));
                String taskDescription = cursor.getString(cursor.getColumnIndex(DbName.Colls.TASK_DASCRIPTION));
                String percent = cursor.getString(cursor.getColumnIndex(DbName.Colls.PERCENT));
                int isCompleted = cursor.getInt(cursor.getColumnIndex(DbName.Colls.ISCOMPLETED));
                int flagCompleted = cursor.getInt(cursor.getColumnIndex(DbName.Colls.FLAGCOMPLETED));
                String uuidPurpose = cursor.getString(cursor.getColumnIndex(DbName.Colls.UUID_PURPOSE));
                String uuidTask = cursor.getString(cursor.getColumnIndex(DbName.Colls.UUID_TASK));
                if (isCompleted == 1) {
                    //Task mTask = new Task(task,taskDescription,percent,isCompleted,flagCompleted,uuidPurpose,uuidTask);
                    //tasks.add(mTask);
                    if (percent.length() <= 2) {
                        percentAll += Float.parseFloat(percent.substring(0, 1));
                    }
                   else if (percent.length() <= 3) {
                        percentAll += Float.parseFloat(percent.substring(0, 2));
                    }
                   else if (percent.length() <= 4) {
                        percentAll += Float.parseFloat(percent.substring(0, 3));
                    }
                   // cursor.moveToNext();
                }
                cursor.moveToNext();
            } while (!cursor.isAfterLast()) ;
        }
        else { return Float.valueOf(0);}
        cursor.close();
        return percentAll;
    }

    public void close(){
        sqLiteDatabase.close();
    }

}

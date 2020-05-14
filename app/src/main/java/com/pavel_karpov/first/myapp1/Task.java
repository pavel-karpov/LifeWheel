package com.pavel_karpov.first.myapp1;

public class Task {
    private String mTask;
    private String mTaskDescription;
    private String mPercent;
    private int mIsCompleted;
    private int mFlagCompleted;
    private String mUUID;
    private String mUUIDTask;


    Task(String task, String taskDescription, String percent, int isCompleted, int flagCompleted, String UUID, String UUIDTask){
        mTask = task;
        mTaskDescription = taskDescription;
        mPercent = percent;
        mIsCompleted = isCompleted;
        mFlagCompleted = flagCompleted;
        mUUID = UUID;
        mUUIDTask = UUIDTask;
    }

    public String getmUUIDTask() {
        return mUUIDTask;
    }
    public void setmUUIDTask(String mUUIDTask) {
        this.mUUIDTask = mUUIDTask;
    }
    public void setmIsCompleted(int mIsCompleted) {
        this.mIsCompleted = mIsCompleted;
    }
    public int getmIsCompleted() {
        return mIsCompleted;
    }
    public void setmFlagCompleted(int mFlagCompleted) {
        this.mFlagCompleted = mFlagCompleted;
    }
    public int getmFlagCompleted() {
        return mFlagCompleted;
    }
    public void setmUUID(String mUUID) {
        this.mUUID = mUUID;
    }
    public String getmUUID() {
        return mUUID;
    }
    public String getmTask() {
        return mTask;
    }
    public void setmTask(String mTask) {
        this.mTask = mTask;
    }
    public String getmTaskDescription() {
        return mTaskDescription;
    }
    public void setmTaskDescription(String mTaskDescription) {
        this.mTaskDescription = mTaskDescription;
    }
    public String getmPercent() {
        return mPercent;
    }
    public void setmPercent(String mPercent) {
        this.mPercent = mPercent;
    }

}

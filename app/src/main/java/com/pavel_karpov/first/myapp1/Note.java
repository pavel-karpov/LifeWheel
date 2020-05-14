package com.pavel_karpov.first.myapp1;

public class Note {
    private String mText;
    private String mDate;
    private String mUuid;

    public Note(String text, String date, String uuid) {
        mText = text;
        mUuid = uuid;
        mDate = date;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmUuid() {
        return mUuid;
    }

    public String getmText() {
        return mText;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public void setmUuid(String mUuid) {
        this.mUuid = mUuid;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }
}

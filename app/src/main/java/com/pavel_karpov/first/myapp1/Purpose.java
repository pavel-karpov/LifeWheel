package com.pavel_karpov.first.myapp1;

import java.io.Serializable;

public class Purpose implements Serializable {
    private String mPurpose;
    private String mDate;
    private String mRating;
    private String mUuid;
    private String mCategory;
    private int isCompleted;
    private int flagCompleted;
    private String mPhotoPath;
    private String mPurposeDescription;
    public Purpose(String purpose,String date, String rating, String category,String uuid, int isCompleted, int flagCompleted, String photoPath, String purposeDescription){
        mPurpose=purpose;
        mDate=date;
        mRating=rating;
        mUuid=uuid;
        mCategory=category;
        this.isCompleted=isCompleted;
        this.flagCompleted=flagCompleted;
        mPhotoPath = photoPath;
        mPurposeDescription = purposeDescription;
    }

    public String getmPhotoPath() {
        return mPhotoPath;
    }

    public void setmPhotoPath(String mPhotoPath) {
        this.mPhotoPath = mPhotoPath;
    }

    public String getmPurposeDescription() {
        return mPurposeDescription;
    }

    public void setmPurposeDescription(String mPurposeDescription) {
        this.mPurposeDescription = mPurposeDescription;
    }

    public int getFlagCompleted() {
        return flagCompleted;
    }
    public void setFlagCompleted(int flagCompleted) {
        this.flagCompleted = flagCompleted;
    }
    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getmPurpose() {
        return mPurpose;
    }

    public void setmPurpose(String mPurpose) {
        this.mPurpose = mPurpose;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmUuid() {
        return mUuid;
    }

    public void setmUuid(String mUuid) {
        this.mUuid = mUuid;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}

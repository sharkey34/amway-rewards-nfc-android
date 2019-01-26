package com.example.ericsharkey.amwayrewards.Models;

public class SweepstakeItem {

    private String mTitle;
    private String mDesc;
    private String mItemImage;
    private String mPoints;
    private String mEntries;

    public SweepstakeItem(String mTitle, String mDesc, String mItemImage, String mPoints, String mEntries) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;
        this.mItemImage = mItemImage;
        this.mPoints = mPoints;
        this.mEntries = mEntries;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public String getmItemImage() {
        return mItemImage;
    }

    public String getmPoints() {
        return mPoints;
    }

    public String getmEntries() {
        return mEntries;
    }
}

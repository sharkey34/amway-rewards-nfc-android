package com.example.ericsharkey.amwayrewards.Models;

public class ScavengerItem {

    private String mTitle;
    private String mDesc;
    private String mPoints;
    private String mCategoryImageString;

    public ScavengerItem(String mTitle, String mDesc, String mPoints, String mCategoryImageString) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;
        this.mPoints = mPoints;
        this.mCategoryImageString = mCategoryImageString;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public String getmPoints() {
        return mPoints;
    }

    public String getmCategoryImageString() {
        return mCategoryImageString;
    }
}

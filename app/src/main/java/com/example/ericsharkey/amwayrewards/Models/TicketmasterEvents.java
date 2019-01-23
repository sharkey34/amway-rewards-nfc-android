package com.example.ericsharkey.amwayrewards.Models;

public class TicketmasterEvents {

    private String mName;
    private String mSiteString;
    private String mImageString;
    private String mLocalDate;
    private String mLocalTime;
    private String mMinPrice;

    public TicketmasterEvents(String mName, String mSiteString, String mImageString, String mLocalDate, String mLocalTime, String mMinPrice) {
        this.mName = mName;
        this.mSiteString = mSiteString;
        this.mImageString = mImageString;
        this.mLocalDate = mLocalDate;
        this.mLocalTime = mLocalTime;
        this.mMinPrice = mMinPrice;
    }

    public String getmName() {
        return mName;
    }

    public String getmSiteString() {
        return mSiteString;
    }

    public String getmImageString() {
        return mImageString;
    }

    public String getmLocalDate() {
        return mLocalDate;
    }

    public String getmLocalTime() {
        return mLocalTime;
    }

    public String getmMinPrice() {
        return mMinPrice;
    }
}

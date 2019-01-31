package com.example.ericsharkey.amwayrewards.Models;

public class TicketmasterEvent {

    private final String mName;
    private final String mSiteString;
    private final String mImageString;
    private final String mLocalDate;
    private final String mLocalTime;
    private final String mMinPrice;

    public TicketmasterEvent(String mName, String mSiteString, String mImageString, String mLocalDate, String mLocalTime, String mMinPrice) {
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

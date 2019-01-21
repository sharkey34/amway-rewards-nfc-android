package com.example.ericsharkey.amwayrewards.Models;

public class TicketmasterEvents {

    private String mName;
    private String mDesc;
    private String mDateTime;

    public TicketmasterEvents(String mName, String mDesc, String mDateTime) {
        this.mName = mName;
        this.mDesc = mDesc;
        this.mDateTime = mDateTime;
    }


    public String getmName() {
        return mName;
    }

    public String getmDesc() {
        return mDesc;
    }

    public String getmDateTime() {
        return mDateTime;
    }
}

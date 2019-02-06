package com.example.ericsharkey.amwayrewards.Models;

public class SweepstakeItem {

    private String title;
    private String desc;
    private String entry;
    private String limit;
    private int imageNum;

    //    private String mItemImage;

    // Empty Constructor for FireBase.
    public SweepstakeItem(){
    }

    public SweepstakeItem(String title, String desc, String entry, String limit, int imageNum) {
        this.title = title;
        this.desc = desc;
        this.entry = entry;
        this.limit = limit;
        this.imageNum = imageNum;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getEntry() {
        return entry;
    }

    public String getLimit() {
        return limit;
    }

    public int getImageNum() {
        return imageNum;
    }
}

package com.example.ericsharkey.amwayrewards.Models;

public class ScavengerItem {

    private String title;
    private String desc;
    private String points;

    public ScavengerItem(String title, String desc, String points) {
        this.title = title;
        this.desc = desc;
        this.points = points;
    }

    // Empty constructor for FireBase.
    public ScavengerItem(){
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getPoints() {
        return points;
    }
}

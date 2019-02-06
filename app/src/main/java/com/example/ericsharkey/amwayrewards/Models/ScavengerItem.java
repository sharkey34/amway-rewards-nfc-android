package com.example.ericsharkey.amwayrewards.Models;

public class ScavengerItem {

    private String title;
    private String desc;
    private String points;
    private int category;


    public ScavengerItem(String title, String desc, String points, int category) {
        this.title = title;
        this.desc = desc;
        this.points = points;
        this.category = category;
    }

    public ScavengerItem() {
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

    public int getCategory() {
        return category;
    }

}

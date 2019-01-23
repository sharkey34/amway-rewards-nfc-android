package com.example.ericsharkey.amwayrewards.Constants;

public class Const {




    // Ticketmaster
    public static final String TICKETMASTER_KEY = " LMgOmroBN2ghx1eucDOlHLIcCE1E6lIV ";
    public static final String VENUE_ID = "KovZpZAEvEEA";
    public static final String CLASSIFICATION_NAME = "Music, Arts & Entertainment, Miscellaneous";
    public static final String TICKETMASTER_EVENTS = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=" + TICKETMASTER_KEY +
            "&venueId=" + VENUE_ID +
            "&classificationName=" + CLASSIFICATION_NAME;

    // Launcher Activity
    public static final Boolean LOGIN_FRAGMENT = false;
    public static final Boolean EVENTS_FRAGMENT = true;
    public static final String LAUNCHER_INTENT = "com.example.ericsharkey.amwayrewards.LauncherActivity";


    // LoginFragment
    public static final int RC_SIGN_IN = 1;
    public static final String LOGIN_TAG = "Login";

    // Events Fragment
    public static final String EVENTS_TAG = "Events";


    // Type ID
    // Venue ID

}

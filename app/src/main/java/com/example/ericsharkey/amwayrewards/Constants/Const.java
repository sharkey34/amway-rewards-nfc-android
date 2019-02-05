package com.example.ericsharkey.amwayrewards.Constants;

public class Const {

    // Ticketmaster
    private static final String TICKETMASTER_KEY = " LMgOmroBN2ghx1eucDOlHLIcCE1E6lIV ";
    private static final String VENUE_ID = "KovZpZAEvEEA";
    private static final String CLASSIFICATION_NAME = "Music, Arts & Entertainment, Miscellaneous";
    public static final String TICKETMASTER_EVENTS = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=" + TICKETMASTER_KEY +
            "&venueId=" + VENUE_ID +
            "&classificationName=" + CLASSIFICATION_NAME;

    // Launcher Activity
    public static final int RC_SIGN_IN = 1;

    // Events Fragment
    public static final String EVENTS_TAG = "com.example.ericsharkey.amwayrewards.EventsFragment";
    public static final int BASE_ID = 0x00101;

    // ScavengerHuntFragment
    public static final String SCAVENGER_TAG = "com.example.ericsharkey.amwayrewards.ScavengerHuntFragment";

    // Rewards Fragment
    public static final String REWARDS_TAG = "com.example.ericsharkey.amwayrewards.RewardsFragment";

    // Sweepstakes Fragment
    public static final String SWEEPSTAKES_TAG = "com.example.ericsharkey.amwayrewards.SweepstakesFragment";

    // Scanner Fragment
    public static final String SCANNER_TAG = "com.example.ericsharkey.amwayrewards.ScannerFragment";
    public static final String EXTRA_TITLE = "com.example.ericsharkey.amwayrewards.ScannerFragment.TITLE";
    public static final String EXTRA_POINTS = "com.example.ericsharkey.amwayrewards.ScannerFragment.POINTS";


    // Web Fragment
    public static final String WEB_EXTRA = "com.example.ericsharkey.amwayrewards.WebFragment.EXTRA";
    public static final String WEB_TAG = "com.example.ericsharkey.amwayrewards.WebFragment";


}

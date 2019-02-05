package com.example.ericsharkey.amwayrewards.interfaces;

import android.support.v4.app.Fragment;

public interface MainInterface {

    void addFragment(Fragment fragment, String tag);
    void addWebView(String urlString);
}

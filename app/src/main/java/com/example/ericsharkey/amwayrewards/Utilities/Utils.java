package com.example.ericsharkey.amwayrewards.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ericsharkey.amwayrewards.Activities.MainActivity;

public class Utils {


    // Function to add fragment
    public static void addFragment(Activity activity){
        Intent main = new Intent();
        main.setClass(activity, MainActivity.class);
        activity.startActivity(main);
        activity.finish();
    }

    // Function to check if the device is connected and start the download task if it is.
    public static boolean isConnected(Activity activity){

        if (activity == null){
            return false;
        }
        ConnectivityManager connMgr = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connMgr == null){
            return false;
        }
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}

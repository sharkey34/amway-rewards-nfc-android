package com.example.ericsharkey.amwayrewards.Utilities;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

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
        return networkInfo != null && networkInfo.isConnected();
    }
}

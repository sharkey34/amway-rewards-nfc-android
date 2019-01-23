package com.example.ericsharkey.amwayrewards.fragments;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.interfaces.EventTaskInterface;
import com.example.ericsharkey.amwayrewards.services.EventTask;
import java.util.ArrayList;

public class EventsFragment extends ListFragment implements EventTaskInterface {
    private ArrayList<TicketmasterEvents> mEvents = new ArrayList<>();

    public static EventsFragment newInstance(){
        return new EventsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(isConnected()){
            EventTask task = new EventTask(this);
            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onPostExecute(ArrayList<TicketmasterEvents> events) {
        // TODO: add to adapter then to list.
        mEvents = events;
    }

    // Function to check if the device is connected and start the download task if it is.
    private boolean isConnected(){

        if (getActivity() == null){
            return false;
        }

        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
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

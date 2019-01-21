package com.example.ericsharkey.amwayrewards.fragments;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ericsharkey.amwayrewards.R;

public class EventsFragment extends ListFragment {

    public static EventsFragment newInstance(){
        return new EventsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }
}

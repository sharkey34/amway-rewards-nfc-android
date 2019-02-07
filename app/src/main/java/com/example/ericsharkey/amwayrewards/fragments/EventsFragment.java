package com.example.ericsharkey.amwayrewards.fragments;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.example.ericsharkey.amwayrewards.Adapters.EventAdapter;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvent;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.Utilities.Utils;
import com.example.ericsharkey.amwayrewards.interfaces.EventTaskInterface;
import com.example.ericsharkey.amwayrewards.interfaces.MainInterface;
import com.example.ericsharkey.amwayrewards.services.EventTask;
import java.util.ArrayList;

public class EventsFragment extends ListFragment implements EventTaskInterface {

    private ArrayList<TicketmasterEvent> mEvents = new ArrayList<>();

    private MainInterface mainInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        if(context instanceof MainInterface){
            mainInterface = (MainInterface)context;
        }
    }


    public static EventsFragment newInstance(){
        return new EventsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(Utils.isConnected(getActivity())){
            EventTask task = new EventTask(this);
            task.execute();
        } else {
            Toast.makeText(getContext(), R.string.no_connection, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onPostExecute(ArrayList<TicketmasterEvent> events) {
        mEvents = events;

        EventAdapter adapter = new EventAdapter(getContext(), mEvents);
        this.getListView().setDivider(null);
        this.getListView().setDividerHeight(0);
        this.setListAdapter(adapter);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        if(mainInterface != null){
            mainInterface.addWebView(mEvents.get(position).getmSiteString());
        }
    }
}
package com.example.ericsharkey.amwayrewards.Adapters;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<TicketmasterEvents> mEvents;


    public EventAdapter(Context context, ArrayList<TicketmasterEvents> events) {
        mContext = context;
        mEvents = events;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

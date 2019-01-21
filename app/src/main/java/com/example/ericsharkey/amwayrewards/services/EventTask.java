package com.example.ericsharkey.amwayrewards.services;
import android.os.AsyncTask;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;
import java.util.ArrayList;

public class EventTask extends AsyncTask<Void,Void, ArrayList<TicketmasterEvents>> {


    @Override
    protected ArrayList<TicketmasterEvents> doInBackground(Void... voids) {



        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<TicketmasterEvents> ticketmasterEvents) {
        super.onPostExecute(ticketmasterEvents);
    }
}

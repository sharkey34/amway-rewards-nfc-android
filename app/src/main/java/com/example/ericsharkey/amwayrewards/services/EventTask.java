package com.example.ericsharkey.amwayrewards.services;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;
import com.example.ericsharkey.amwayrewards.interfaces.EventTaskInterface;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class EventTask extends AsyncTask<Void,Void, ArrayList<TicketmasterEvents>> {

    private EventTaskInterface mInterface;

    public EventTask(EventTaskInterface _interface) {
        mInterface = _interface;
    }

    @Override
    protected ArrayList<TicketmasterEvents> doInBackground(Void... voids) {


        ArrayList<TicketmasterEvents> events = new ArrayList<>();

        final String address = Const.TICKETMASTER_EVENTS;
        HttpURLConnection connection = null;
        InputStream is = null;

        try {
            URL url = new URL(address);
            // Open connection
            connection = (HttpURLConnection)url.openConnection();
            connection.connect();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        try {
            is = Objects.requireNonNull(connection).getInputStream();

            JSONObject obj1 = new JSONObject(IOUtils.toString(is,"UTF-8"));

            Log.d("TAG", "doInBackground: " + obj1);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        // Closing the connection.
        finally {
            if(connection != null){
                if(is != null){
                    try{
                        is.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                connection.disconnect();
            }
        }
        return events;
    }

    @Override
    protected void onPostExecute(ArrayList<TicketmasterEvents> ticketmasterEvents) {
//        mInterface.onPostExecute(ticketmasterEvents);
    }
}

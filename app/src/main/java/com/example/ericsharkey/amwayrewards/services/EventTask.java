package com.example.ericsharkey.amwayrewards.services;
import android.os.AsyncTask;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvent;
import com.example.ericsharkey.amwayrewards.interfaces.EventTaskInterface;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class EventTask extends AsyncTask<Void,Void, ArrayList<TicketmasterEvent>> {

    private final EventTaskInterface mInterface;

    public EventTask(EventTaskInterface _interface) {
        mInterface = _interface;
    }

    @Override
    protected ArrayList<TicketmasterEvent> doInBackground(Void... voids) {

        ArrayList<TicketmasterEvent> ticketmasterEvents = new ArrayList<>();

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

            JSONObject mainObject = new JSONObject(IOUtils.toString(is,"UTF-8"));
            JSONObject embedded = mainObject.getJSONObject("_embedded");
            JSONArray events = embedded.getJSONArray("events");

            for (int i = 0; i < events.length(); i++) {

                JSONObject event = events.getJSONObject(i);

                String name = event.getString("name");
                String siteString = event.getString("url");

                JSONArray images = event.getJSONArray("images");

                String imageString = null;

                for (int j = 0; j < images.length(); j++) {
                    JSONObject image = images.getJSONObject(j);

                    int width = image.getInt("width");

                    if(width == 640){
                        imageString = image.getString("url");
                        break;
                    }
                }

                JSONObject dates = event.getJSONObject("dates");
                JSONObject start = dates.getJSONObject("start");

                String localDateString = start.getString("localDate");
                String localTimeString = start.getString("localTime");

                JSONArray priceRanges = event.getJSONArray("priceRanges");

                String minPrice = null;
                if(priceRanges.length() >= 1){
                    JSONObject objectZero = priceRanges.getJSONObject(0);
                    minPrice = objectZero.getString("min");
                }

                ticketmasterEvents.add(new TicketmasterEvent(name, siteString,
                        imageString, localDateString, localTimeString,minPrice));
            }
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
        return ticketmasterEvents;
    }

    @Override
    protected void onPostExecute(ArrayList<TicketmasterEvent> ticketmasterEvents) {
        mInterface.onPostExecute(ticketmasterEvents);
    }
}

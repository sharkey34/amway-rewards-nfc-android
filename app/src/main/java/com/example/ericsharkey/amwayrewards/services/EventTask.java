package com.example.ericsharkey.amwayrewards.services;
import android.os.AsyncTask;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class EventTask extends AsyncTask<Void,Void, ArrayList<TicketmasterEvents>> {


    @Override
    protected ArrayList<TicketmasterEvents> doInBackground(Void... voids) {


        ArrayList<TicketmasterEvents> events = new ArrayList<>();

        final String address = Const.TICKETMASTER_KEY;
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

//            JSONArray arr1 = obj1.getJSONArray("items");
//
//            for (int i = 0; i < arr1.length(); i++) {
//                JSONObject obj2 = arr1.getJSONObject(i);
//                JSONObject obj3 = obj2.getJSONObject("volumeInfo");
//
//                String title = obj3.getString("title");
//
//                JSONObject obj4 = obj3.getJSONObject("imageLinks");
//
//                String imageURL = obj4.getString("thumbnail");
//                Log.i("TAG", "doInBackground: " + imageURL);
//
//                if (title != null && imageURL != null){
//                    events.add(i,new (title, imageURL) );
//                }
//            }
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
        super.onPostExecute(ticketmasterEvents);
    }
}

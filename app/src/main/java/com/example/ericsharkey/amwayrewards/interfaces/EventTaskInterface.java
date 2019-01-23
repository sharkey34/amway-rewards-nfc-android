package com.example.ericsharkey.amwayrewards.interfaces;

import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;

import java.util.ArrayList;

public interface EventTaskInterface {

    void onPostExecute(ArrayList<TicketmasterEvents> events);
}

package com.example.ericsharkey.amwayrewards.interfaces;

import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvent;

import java.util.ArrayList;

public interface EventTaskInterface {

    void onPostExecute(ArrayList<TicketmasterEvent> events);
}

package com.example.ericsharkey.amwayrewards.fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ericsharkey.amwayrewards.Adapters.ScavengerAdapter;
import com.example.ericsharkey.amwayrewards.Models.ScavengerItem;
import com.example.ericsharkey.amwayrewards.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScavengerHuntFragment extends ListFragment {

    private final ArrayList<ScavengerItem> scavengerItems = new ArrayList<>();

    public static ScavengerHuntFragment newInstance(){
        return new ScavengerHuntFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("scavenger");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("TAG", "onCancelled: " + databaseError.getMessage());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scavenger, container, false);
    }

    private void getData(DataSnapshot dataSnapshot){

        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
           ScavengerItem item = snapshot.getValue(ScavengerItem.class);
           scavengerItems.add(item);
        }

        ScavengerAdapter adapter = new ScavengerAdapter(getContext(), scavengerItems);
        this.setListAdapter(adapter);
    }
}

package com.example.ericsharkey.amwayrewards.fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ericsharkey.amwayrewards.Adapters.SweepstakesAdapter;
import com.example.ericsharkey.amwayrewards.Models.SweepstakeItem;
import com.example.ericsharkey.amwayrewards.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SweepstakesFragment extends ListFragment {

    private final ArrayList<SweepstakeItem> sweepstakeItems = new ArrayList<>();

    // Should use bundle instead.
    public static SweepstakesFragment newInstance(){
        return new SweepstakesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("sweepstakes");

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



    private void getData(DataSnapshot dataSnapshot){

        for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
            SweepstakeItem item = snapshot.getValue(SweepstakeItem.class);
            sweepstakeItems.add(item);
        }

        SweepstakesAdapter adapter = new SweepstakesAdapter(getContext(), sweepstakeItems);
        this.setListAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sweepstakes, container, false);
    }
}

package com.example.ericsharkey.amwayrewards.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ericsharkey.amwayrewards.R;

public class ScavengerHuntFragment extends Fragment {

    public static ScavengerHuntFragment newInstance(){
        return new ScavengerHuntFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scavenger, container, false);
    }
}

package com.example.ericsharkey.amwayrewards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // TODO: Pull from shared preferences and see if the user has logged in
        // If logged in send to Rewards Page to login or send to main activity.
    }
}

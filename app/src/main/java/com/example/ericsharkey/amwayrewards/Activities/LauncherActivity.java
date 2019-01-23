package com.example.ericsharkey.amwayrewards.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.google.firebase.auth.FirebaseAuth;
public class LauncherActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        mAuth = FirebaseAuth.getInstance();

        // TODO: Pull from shared preferences and see if the user has logged in
        // If logged in send to Rewards Page to login or send to main activity.
        Intent main = new Intent();

        if (mAuth.getCurrentUser() == null){
            main.putExtra(Const.LAUNCHER_INTENT, Const.LOGIN_FRAGMENT);

        } else {
            main.putExtra(Const.LAUNCHER_INTENT, Const.EVENTS_FRAGMENT);
        }
        main.setClass(getApplicationContext(), MainActivity.class);
        startActivity(main);
        finish();
    }
}

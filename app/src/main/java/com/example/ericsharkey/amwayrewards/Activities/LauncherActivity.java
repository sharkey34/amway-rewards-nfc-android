package com.example.ericsharkey.amwayrewards.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
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
        main.setClass(getApplicationContext(), MainActivity.class);
        startActivity(main);
        finish();
//
//        if (mAuth.getCurrentUser() == null){
//            Intent main = new Intent();
//            main.setClass(getApplicationContext(), MainActivity.class);
//            startActivity(main);
//            finish();
//        } else {
//            Intent events = new Intent();
//            events.setClass(getApplicationContext(), TicketmasterEvents.class);
//            startActivity(events);
//            finish();
//        }
    }
}

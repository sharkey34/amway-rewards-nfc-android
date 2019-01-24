package com.example.ericsharkey.amwayrewards.Activities;
import android.app.usage.EventStats;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.Utilities.Utils;
import com.example.ericsharkey.amwayrewards.fragments.EventsFragment;
import com.example.ericsharkey.amwayrewards.fragments.RewardsFragment;
import com.example.ericsharkey.amwayrewards.fragments.ScannerFragment;
import com.example.ericsharkey.amwayrewards.fragments.ScavengerHuntFragment;
import com.example.ericsharkey.amwayrewards.fragments.SweepstakesFragment;
import com.example.ericsharkey.amwayrewards.interfaces.MainInterface;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private BottomNavigationView mNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: move fragment creation here so they're created once and use RxJava to update the data.
        mNav = findViewById(R.id.main_nav);
        mNav.setOnNavigationItemSelectedListener(navItemSelected);
            addFragment(EventsFragment.newInstance(), Const.EVENTS_TAG);
    }

    @Override
    public void addFragment(Fragment fragment, String tag) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment,tag)
                .commit();
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.nav_events:
                    addFragment(EventsFragment.newInstance(), Const.EVENTS_TAG);
                    Log.i("TAG", "onNavigationItemSelected: " + menuItem.getItemId() + " " + R.id.nav_events);
                    return true;
                case R.id.nav_scavenger:
                    addFragment(ScavengerHuntFragment.newInstance(), Const.SCAVENGER_TAG);
                    Log.i("TAG", "onNavigationItemSelected: " + menuItem.getItemId() + " " + R.id.nav_scavenger);
                    return true;
                case  R.id.nav_rewards:
                    addFragment(RewardsFragment.newInstance(), Const.REWARDS_TAG);
                    Log.i("TAG", "onNavigationItemSelected: " + menuItem.getItemId() + " " +  R.id.nav_rewards);
                    return true;
                case  R.id.nav_sweepstakes:
                    addFragment(SweepstakesFragment.newInstance(), Const.SWEEPSTAKES_TAG);
                    Log.i("TAG", "onNavigationItemSelected: " + menuItem.getItemId() + " " +  R.id.nav_sweepstakes);
                    return true;
                case R.id.nav_scanner:
                    addFragment(ScannerFragment.newInstance(), Const.SCANNER_TAG);
                    Log.i("TAG", "onNavigationItemSelected: " + menuItem.getItemId() + " " + R.id.nav_scanner);
                    return true;
                default:
                        return false;
            }
        }
    };
}


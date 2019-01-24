package com.example.ericsharkey.amwayrewards.Activities;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.fragments.EventsFragment;
import com.example.ericsharkey.amwayrewards.fragments.NFCFragment;
import com.example.ericsharkey.amwayrewards.fragments.RewardsFragment;
import com.example.ericsharkey.amwayrewards.fragments.ScavengerHuntFragment;
import com.example.ericsharkey.amwayrewards.fragments.SweepstakesFragment;
import com.example.ericsharkey.amwayrewards.interfaces.MainInterface;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private BottomNavigationView mNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Make custom implementation
       if(getSupportActionBar() != null){
           getSupportActionBar().setDisplayShowTitleEnabled(false);
           getSupportActionBar().setDisplayShowHomeEnabled(true);
           getSupportActionBar().setLogo(R.drawable.menu_logo);
           getSupportActionBar().setDisplayUseLogoEnabled(true);
       }

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =
                    intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];

                    Toast.makeText(this, messages[i].toString(),Toast.LENGTH_LONG ).show();
                }
                // TODO: Process the messages array.
            }
        }
    }



    private BottomNavigationView.OnNavigationItemSelectedListener navItemSelected = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.nav_events:
                    addFragment(EventsFragment.newInstance(), Const.EVENTS_TAG);
                    return true;
                case R.id.nav_scavenger:
                    addFragment(ScavengerHuntFragment.newInstance(), Const.SCAVENGER_TAG);
                    return true;
                case  R.id.nav_rewards:
                    addFragment(RewardsFragment.newInstance(), Const.REWARDS_TAG);
                    return true;
                case  R.id.nav_sweepstakes:
                    addFragment(SweepstakesFragment.newInstance(), Const.SWEEPSTAKES_TAG);
                    return true;
                case R.id.nav_scanner:
                    addFragment(NFCFragment.newInstance(), Const.SCANNER_TAG);
                    return true;
                default:
                        return false;
            }
        }
    };
}


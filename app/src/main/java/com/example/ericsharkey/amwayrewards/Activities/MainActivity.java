package com.example.ericsharkey.amwayrewards.Activities;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.provider.Settings;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private BottomNavigationView mNav;
    private PendingIntent mPendingIntent;
    private NfcAdapter mNFCAdapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


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


        mNFCAdapter = NfcAdapter.getDefaultAdapter(this);

        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, this.getClass())
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
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

                }
                // TODO: Process the messages array.

                // TODO: Currently only processing one.
                parseMessage(messages[0]);
            }
        }
    }


    private void parseMessage(NdefMessage message) {

        try {
            byte[] payload = message.getRecords()[0].getPayload();

            String textEncoding = ((payload[0] & 0200) == 0) ? "UTF-8" : "UTF-16";
            int languageCodeLength = payload[0] & 0077;
            String text = new String(payload, languageCodeLength + 1,
                            payload.length - languageCodeLength - 1, textEncoding);

            String[] item = text.split("/");

            String title = item[0];
            String points = item[1];

            NFCFragment fragment = NFCFragment.newInstance();

            Bundle bundle = new Bundle();

            bundle.putString(Const.EXTRA_TITLE, title);
            bundle.putString(Const.EXTRA_POINTS, points);

            fragment.setArguments(bundle);

            if(mAuth.getCurrentUser() != null){
                String uid = mAuth.getCurrentUser().getUid();
                mDatabase.child("users").child(uid).child("points").setValue(points);
            }

            mNav.setSelectedItemId(R.id.nav_scanner);
            addFragment(fragment, Const.SCANNER_TAG);

        } catch (UnsupportedEncodingException e) {

            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mNFCAdapter != null) {
            if (!mNFCAdapter.isEnabled())
                showWirelessSettings();
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onPause() {

       mNFCAdapter.disableForegroundDispatch(this);
        super.onPause();
    }

    private void showWirelessSettings() {
        Toast.makeText(this, R.string.enable_NFC, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        startActivity(intent);
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

                    // TODO: Clean up
                    addFragment(NFCFragment.newInstance(), Const.SCANNER_TAG);
                    return true;
                default:
                        return false;
            }
        }
    };
}


package com.example.ericsharkey.amwayrewards.Activities;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.fragments.EventsFragment;
import com.example.ericsharkey.amwayrewards.fragments.LoginFragment;
import com.example.ericsharkey.amwayrewards.interfaces.MainInterface;

public class MainActivity extends AppCompatActivity implements MainInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent main = getIntent();

        if(main.getBooleanExtra(Const.LAUNCHER_INTENT, false)) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, EventsFragment.newInstance(), Const.EVENTS_TAG)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, LoginFragment.newInstance(), Const.LOGIN_TAG)
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Const.RC_SIGN_IN){
            LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(Const.LOGIN_TAG);

            if(fragment != null){
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    public void addFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }
}


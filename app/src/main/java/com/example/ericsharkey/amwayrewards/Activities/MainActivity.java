package com.example.ericsharkey.amwayrewards.Activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, LoginFragment.newInstance(), Const.LOGINTAG)
                .commit();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(Const.LOGINTAG);

         if(fragment != null){
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}


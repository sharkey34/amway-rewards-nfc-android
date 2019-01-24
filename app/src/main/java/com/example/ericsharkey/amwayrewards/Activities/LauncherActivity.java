package com.example.ericsharkey.amwayrewards.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.Utilities.Utils;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Arrays;
import java.util.List;

public class LauncherActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    // TODO: Implement ViewModel.
//    private LauncherViewModel mLauncherViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // TODO: Pull from shared preferences and see if the user has logged in
        // If logged in send to Rewards Page to login or send to main activity.

        if (mAuth.getCurrentUser() != null){
            toMain();
        } else {
            setUpAuthUI();
        }
    }

    private void toMain(){
        Intent main = new Intent();
        main.setClass(this, MainActivity.class);
        startActivity(main);
        finish();
    }

    private void setUpAuthUI(){
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setLogo(R.drawable.logo)
                        .build(),
                Const.RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Const.RC_SIGN_IN) {
            handleSignInResponse(resultCode);
        }
    }

    @MainThread
    private void handleSignInResponse(int resultCode) {
//        IdpResponse response = IdpResponse.fromResultIntent(data);
//        Log.i(TAG, "handleSignInResponse: " + response.getEmail());

        if (resultCode == Activity.RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            if(user == null){
                return;
            }

            mDatabase.child("users").child(user.getUid()).child("email").setValue(user.getEmail());
            mDatabase.child("users").child(user.getUid()).child("name").setValue(user.getDisplayName());

            Toast.makeText(this, R.string.signin_successful,Toast.LENGTH_SHORT).show();
            toMain();
        }

        if(resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, R.string.signin_cancelled, Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Put in a REPO and call in ViewModel.
    private void signOut(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LauncherActivity.this, R.string.sign_out, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // TODO: Put in a REPO and call in ViewModel.
    private void deleteAccount(){

        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(LauncherActivity.this, R.string.delete_acnt,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

package com.example.ericsharkey.amwayrewards.fragments;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.ViewModels.LoginViewModel;
import com.example.ericsharkey.amwayrewards.interfaces.MainInterface;
import com.facebook.CallbackManager;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    public static final int RC_SIGN_IN = 1;
    public static final String TAG = "Facebook Login";
    private MainInterface mInterface;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private LoginViewModel mLoginViewModel;

    public static LoginFragment newInstance (){
        return new LoginFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof MainInterface) {
            mInterface = (MainInterface)context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        // Use the application default credentials
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child("5").setValue("hey");


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.i(TAG, "onAuthStateChanged: Changed");

                if(mAuth.getCurrentUser() != null) {
                    mInterface.addFragment(EventsFragment.newInstance());
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
                RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(getActivity() == null){
            return;
        }

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                // TODO: Save currentUser
                Log.i(TAG, "onActivityResult: " + user);

                Toast.makeText(getContext(), R.string.signin_successful,Toast.LENGTH_SHORT).show();
            } else {

                if(response == null){
                    Toast.makeText(getContext(), R.string.signin_cancelled,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), R.string.signin_failed,Toast.LENGTH_SHORT).show();

                    if(response.getError() != null) {
                        Log.i(TAG, "onActivityResult: " + response.getError().getErrorCode());
                    }
                }
            }
        }
    }


    private void signOut(){

        if(getContext() == null){
            return;
        }

        AuthUI.getInstance()
                .signOut(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), R.string.sign_out,Toast.LENGTH_SHORT).show();
                   }
                });
    }

    private void deleteAccount(){
        if(getContext() == null){
            return;
        }

        AuthUI.getInstance()
                .delete(getContext())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), R.string.delete_acnt,Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

package com.example.ericsharkey.amwayrewards.fragments;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;
import com.example.ericsharkey.amwayrewards.ViewModels.LoginViewModel;
import com.example.ericsharkey.amwayrewards.interfaces.MainInterface;
import com.firebase.ui.auth.AuthUI;
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


        // TODO: Still signed in.
        if(mAuth.getCurrentUser() != null){
            Log.i(Const.LOGIN_TAG, "onCreate: "+ mAuth.getCurrentUser().getDisplayName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
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
                Const.RC_SIGN_IN);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

            Toast.makeText(getContext(), R.string.signin_successful,Toast.LENGTH_SHORT).show();
            mInterface.addFragment(EventsFragment.newInstance());
        }

        if(resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getContext(), R.string.signin_cancelled, Toast.LENGTH_SHORT).show();
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

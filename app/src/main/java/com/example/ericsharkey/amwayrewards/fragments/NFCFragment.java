package com.example.ericsharkey.amwayrewards.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.R;

public class NFCFragment extends Fragment {


    public static NFCFragment newInstance(){
        return new NFCFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity() == null){
            return;
        }
        Bundle bundle = getArguments();

        if (bundle != null){

            String title = bundle.getString(Const.EXTRA_TITLE);
            String points = bundle.getString(Const.EXTRA_POINTS);

            ImageView image = getActivity().findViewById(R.id.nfc_image);
            TextView titleTv = getActivity().findViewById(R.id.scavenger_title);
            TextView pointsTv = getActivity().findViewById(R.id.points_text);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sky_80);

            image.setImageBitmap(bitmap);
            titleTv.setText(title);
            pointsTv.setText(points);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nfc, container, false);
    }
}

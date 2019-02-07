package com.example.ericsharkey.amwayrewards.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvent;
import com.example.ericsharkey.amwayrewards.R;
import com.facebook.FacebookActivity;

import java.util.ArrayList;
import java.util.Objects;

public class EventAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<TicketmasterEvent> mEvents;


    public EventAdapter(Context context, ArrayList<TicketmasterEvent> events) {
        mContext = context;
        mEvents = events;
    }

    @Override
    public int getCount() {
        if(mEvents != null){
            return mEvents.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mEvents != null && position >= 0 || position < Objects.requireNonNull(mEvents).size()){
            return mEvents.get(position);
        }
        return null;    }

    @Override
    public long getItemId(int position) {
        return Const.BASE_ID + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.event_item, parent, false);

            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        TicketmasterEvent event = (TicketmasterEvent) getItem(position);
        if (event != null){
            Glide.with(mContext).load(event.getmImageString()).into(vh.mImage);
            vh.mTitle.setText(event.getmName());
            String eventDateTime = mContext.getString(R.string.date_time,event.getmLocalDate(),event.getmLocalTime());
            vh.mDateTime.setText(eventDateTime);
            vh.mShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Share");
                    mContext.startActivity(Intent.createChooser(shareIntent,"Share using"));
                }
            });
        }
        return convertView;
    }

    // View Holder Class.
    static class ViewHolder{
        private final ImageView mImage;
        private final TextView mDateTime;
        private final TextView mTitle;
        private final ImageView mShare;

        ViewHolder(View _layout){
            mShare = _layout.findViewById(R.id.share_btn);
            mImage =  _layout.findViewById(R.id.event_image);
            mTitle = _layout.findViewById(R.id.event_text);
            mDateTime = _layout.findViewById(R.id.date_time);
        }
    }
}

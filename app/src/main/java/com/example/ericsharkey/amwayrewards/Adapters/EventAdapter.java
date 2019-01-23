package com.example.ericsharkey.amwayrewards.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.TicketmasterEvents;
import com.example.ericsharkey.amwayrewards.R;
import java.util.ArrayList;
import java.util.Objects;

public class EventAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<TicketmasterEvents> mEvents;


    public EventAdapter(Context context, ArrayList<TicketmasterEvents> events) {
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

        TicketmasterEvents event = (TicketmasterEvents) getItem(position);
        if (event != null){
            Glide.with(mContext).load(event.getmImageString()).into(vh.mImage);
            vh.mTitle.setText(event.getmName());
            String eventDateTime = mContext.getString(R.string.date_time,event.getmLocalDate(),event.getmLocalTime());
            vh.mDateTime.setText(eventDateTime);
        }
        return convertView;
    }

    // View Holder Class.
    static class ViewHolder{
        private final ImageView mImage;
        private final TextView mDateTime;
        private final TextView mTitle;

        ViewHolder(View _layout){
            mImage =  _layout.findViewById(R.id.event_image);
            mTitle = _layout.findViewById(R.id.event_text);
            mDateTime = _layout.findViewById(R.id.date_time);
        }
    }
}

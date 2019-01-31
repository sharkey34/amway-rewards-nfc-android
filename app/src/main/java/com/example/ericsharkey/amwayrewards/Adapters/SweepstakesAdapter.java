package com.example.ericsharkey.amwayrewards.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ericsharkey.amwayrewards.Constants.Const;
import com.example.ericsharkey.amwayrewards.Models.SweepstakeItem;
import com.example.ericsharkey.amwayrewards.R;
import java.util.ArrayList;
import java.util.Objects;

public class SweepstakesAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<SweepstakeItem> mList;

    public SweepstakesAdapter(Context context, ArrayList<SweepstakeItem> items) {
        mContext = context;
        mList = items;
    }

    @Override
    public int getCount() {
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mList != null && position >= 0 || position < Objects.requireNonNull(mList).size()){
            return mList.get(position);
        }
        return null;    }

    @Override
    public long getItemId(int position) {
        return Const.BASE_ID + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SweepstakesViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sweepstakes_item, parent, false);

            vh = new SweepstakesViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (SweepstakesViewHolder)convertView.getTag();
        }

        SweepstakeItem item = (SweepstakeItem) getItem(position);
        if (item != null){
//            Glide.with(mContext).load(item.getmItemImage()).into(vh.mImage);
            vh.mTitle.setText(item.getTitle());
            vh.mDesc.setText(item.getDesc());
            vh.mEntries.setText(item.getLimit());
            vh.mPoints.setText(item.getEntry());
        }
        return convertView;
    }

    // View Holder Class.
    static class SweepstakesViewHolder{
//        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mDesc;
        private final TextView mEntries;
        private final TextView mPoints;

        SweepstakesViewHolder(View _layout){
//            mImage =  _layout.findViewById(R.id.sweepstakes_image);
            mTitle = _layout.findViewById(R.id.sweepstakes_title);
            mDesc = _layout.findViewById(R.id.sweepstakes_desc);
            mEntries = _layout.findViewById(R.id.sweepstakes_entries);
            mPoints = _layout.findViewById(R.id.sweepstakes_points);
        }
    }
}

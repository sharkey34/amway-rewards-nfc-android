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
import com.example.ericsharkey.amwayrewards.Models.ScavengerItem;
import com.example.ericsharkey.amwayrewards.R;
import java.util.ArrayList;
import java.util.Objects;

public class ScavengerAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ScavengerItem> mList;

    public ScavengerAdapter(Context context, ArrayList<ScavengerItem> items) {
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
        ScavengerViewHolder vh;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.scavenger_item, parent, false);

            vh = new ScavengerViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ScavengerViewHolder)convertView.getTag();
        }

        ScavengerItem item = (ScavengerItem) getItem(position);
        if (item != null){
//            Glide.with(mContext).load(item.getmCategoryImageString()).into(vh.mImage);
            vh.mTitle.setText(item.getTitle());
            vh.mDesc.setText(item.getDesc());
            vh.mPoints.setText(item.getPoints());
        }
        return convertView;
    }

    // View Holder Class.
    static class ScavengerViewHolder{
//        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mDesc;
        private final TextView mPoints;

        ScavengerViewHolder(View _layout){
//            mImage =  _layout.findViewById(R.id.scavenger_image);
            mTitle = _layout.findViewById(R.id.scavenger_title);
            mDesc = _layout.findViewById(R.id.scavenger_desc);
            mPoints = _layout.findViewById(R.id.scavenger_point);
        }
    }
}
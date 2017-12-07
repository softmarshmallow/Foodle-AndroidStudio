package com.softmarshmallow.foodle.Views.Shared;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;

/**
 * Created by uzu on 9/24/17.
 */

public class BaseStoreItemViewHolder extends RecyclerView.ViewHolder
{
        
        ImageView storeImageView;
        TextView storeNameTextView;
        TextView captionTextView;
        TextView locationTextView;
        
        private final View itemView;
        private final Context context;
        private StoreContainerModel storeData;
        
        public BaseStoreItemViewHolder(View itemView, final Context context) {
                super(itemView);
                this.itemView = itemView;
                this.context = context;
                
                storeImageView = (ImageView) itemView.findViewById(R.id.storeImage);
                storeNameTextView = (TextView) itemView.findViewById(R.id.storeNameTextView);
                captionTextView = (TextView) itemView.findViewById(R.id.captionText);
                locationTextView = (TextView) itemView.findViewById(R.id.locationText);
                
                itemView.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                StoreDetailViewActivity.ShowStoreDetailWithData(context, storeData);
                        }
                });
        }
        
        public void BindViewWithData(StoreContainerModel storeData){
                this.storeData = storeData;
                Glide.with(context).load(storeData.getMainStorePhotoUrl()).into(storeImageView);
                storeNameTextView.setText(storeData.StoreName);
                captionTextView.setText(storeData.StoreShortDescription);
                locationTextView.setText(storeData.StoreAddress);
        }
}
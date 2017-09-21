package com.softmarshmallow.foodle.Views.Featured.ListStore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;



public class StoreItemViewHolder extends RecyclerView.ViewHolder
{

        Gson gson = new Gson();

        public TextView storeNameTextView;
        public TextView storeShortDescriptionTextView;
        public ImageView storeIconImageView;
        public ImageView storeFeatureGraphicImageView;
        
        StoreContainerModel storeData;
        Context context;

        public StoreItemViewHolder(View itemView, final Context context) {
                super(itemView);

                this.context = context;

                this.storeNameTextView = itemView.findViewById(R.id.storeNameTextView);
                this.storeShortDescriptionTextView = itemView.findViewById(
                        R.id.storeShortDescriptionTextView);
                this.storeFeatureGraphicImageView = itemView.findViewById(
                        R.id.storeFeatureGraphicImageView);

                itemView.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                StoreDetailViewActivity.ShowStoreDetailWithData(context, storeData);
                        }
                });

        }


        public void BindWithStoreData(StoreContainerModel storeData)
        {
                this.storeData = storeData;

                storeNameTextView.setText( storeData.StoreName);

                storeShortDescriptionTextView.setText(storeData.StoreShortDescription);

                String MainStorePhotoUrl = storeData.getMainStorePhotoUrl();
                if (MainStorePhotoUrl != null)
                {
                        Glide.with(context)
                                .load(MainStorePhotoUrl)
                                .into(storeFeatureGraphicImageView);
                }

        }
}

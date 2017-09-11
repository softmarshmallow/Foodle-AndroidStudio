package com.softmarshmallow.foodle.Views.Featured.ListStore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;

/**
 * Created by UZU on 21/08/2017.
 */

public class StoreItemViewHolder extends RecyclerView.ViewHolder
{

        Gson gson = new Gson();

        public TextView storeNameTextView;
        public TextView storeShortDescriptionTextView;
        public ImageView storeIconImageView;
        public ImageView storeFeatureGraphicImageView;

        StoreModel storeData;
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


        public void BindWithStoreData(StoreModel storeData)
        {
                this.storeData = storeData;

                storeNameTextView.setText( storeData.StoreName);

                storeShortDescriptionTextView.setText(storeData.StoreShortDescription);

                String MainStorePhotoUrl = storeData.GetMainStorePhotoUrl();
                if (MainStorePhotoUrl != null)
                {
                        Glide.with(context)
                                .load(MainStorePhotoUrl)
                                .into(storeFeatureGraphicImageView);
                }

        }
}

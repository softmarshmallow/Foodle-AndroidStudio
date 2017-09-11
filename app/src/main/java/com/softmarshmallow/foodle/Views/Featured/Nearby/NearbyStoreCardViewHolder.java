package com.softmarshmallow.foodle.Views.Featured.Nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by UZU on 23/08/2017.
 */

public class NearbyStoreCardViewHolder extends RecyclerView.ViewHolder
{

        @BindView(R.id.storeNameTextView)
        TextView storeNameTextView;

        @BindView(R.id.storeLocationTextView)
        TextView storeLocationTextView;

        @BindView(R.id.thumbnail)
        ImageView thumbnailImageView;
        private Context context;
        private StoreModel storeData;


        public NearbyStoreCardViewHolder(View itemView, final Context context) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                this.context = context;
                itemView.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                SweetAlertDialog loadingDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                                loadingDialog.show();
                                StoreDetailViewActivity.ShowStoreDetailWithData(context, storeData);
                                loadingDialog.dismiss();
                        }
                });
        }

        public void BindWithStoreData(StoreModel storeModel) {
                this.storeData = storeModel;
                storeNameTextView.setText(storeModel.StoreName);
                storeLocationTextView.setText("Some where...");
                Glide.with(context)
                        .load(storeModel.GetMainStorePhotoUrl())
//                        .override(500, 200)
                        .centerCrop()
                        .into(thumbnailImageView);
        }
}

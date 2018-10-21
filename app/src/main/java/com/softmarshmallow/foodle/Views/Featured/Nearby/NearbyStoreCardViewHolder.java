package com.softmarshmallow.foodle.Views.Featured.Nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
//import cn.pedant.SweetAlert.SweetAlertDialog;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class NearbyStoreCardViewHolder extends RecyclerView.ViewHolder
{
    
    @BindView(R.id.storeNameTextView)
    TextView storeNameTextView;
    
    @BindView(R.id.storeLocationTextView)
    TextView storeLocationTextView;
    
    @BindView(R.id.thumbnail)
    ImageView thumbnailImageView;
    private Context context;
    private StoreContainerModel storeData;
    
    
    public NearbyStoreCardViewHolder(View itemView, final Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                SweetAlertDialog loadingDialog = new SweetAlertDialog(context,
                    SweetAlertDialog.PROGRESS_TYPE);
                loadingDialog.show();
                StoreDetailViewActivity.ShowStoreDetailWithData(context, storeData);
                loadingDialog.dismiss();
            }
        });
    }
    
    public void BindWithStoreData(StoreContainerModel storeModel) {
        this.storeData = storeModel;
        storeNameTextView.setText(storeModel.StoreName);
        storeLocationTextView.setText("Some where...");
        Glide.with(context)
            .load(storeModel.getMainStorePhotoUrl())
            .apply(new RequestOptions().centerCrop())
//                        .override(500, 200)
            .into(thumbnailImageView);
    }
}

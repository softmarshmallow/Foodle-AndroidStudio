package com.softmarshmallow.foodle.Views.StoreMapsView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Featured.ListStore.StoreItemViewHolder;
import com.softmarshmallow.foodle.Views.Shared.BaseStoreItemViewHolder;
import com.softmarshmallow.foodle.Views.Shared.BaseStoresAdapter;

import java.util.List;

import butterknife.BindView;

public class MapsStoreDisplayBannerAdapter extends BaseStoresAdapter
{
        public MapsStoreDisplayBannerAdapter(Context context, List<StoreContainerModel> storeDatas) {
                super(context, storeDatas);
        }
        
        
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_maps_store_display_item, parent, false);
                
                return new MapStoreItemViewHolder(itemView, context);
        }
        
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                StoreContainerModel storeData = storeDatas.get(position);
                MapStoreItemViewHolder storeItemViewHolder = (MapStoreItemViewHolder)holder;
                storeItemViewHolder.BindViewWithData(storeData);
        }
        
}

class MapStoreItemViewHolder extends BaseStoreItemViewHolder
{
        
        public MapStoreItemViewHolder(View itemView, Context context) {
                super(itemView, context);
        }
}

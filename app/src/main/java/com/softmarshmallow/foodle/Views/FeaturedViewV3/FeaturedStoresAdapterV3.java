package com.softmarshmallow.foodle.Views.FeaturedViewV3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Shared.BaseStoreItemViewHolder;
import com.softmarshmallow.foodle.Views.Shared.BaseStoresAdapter;

import java.util.List;

/**
 * Created by uzu on 9/23/17.
 */

public class FeaturedStoresAdapterV3 extends BaseStoresAdapter
{
        
        public FeaturedStoresAdapterV3(Context context, List<StoreContainerModel> storeDatas) {
                super(context, storeDatas);
                this.storeDatas = storeDatas;
        }
        
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_featured_store_v3, parent, false);
                
                return new StoreItemViewHolder(itemView, context);
        }
        
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                StoreContainerModel storeData = storeDatas.get(position);
                StoreItemViewHolder storeItemViewHolder = (StoreItemViewHolder)holder;
                storeItemViewHolder.BindViewWithData(storeData);
        }
        
        @Override
        public int getItemCount() {
                return storeDatas.size();
        }
        
        class StoreItemViewHolder extends BaseStoreItemViewHolder
        {
                public StoreItemViewHolder(View itemView, Context context) {
                        super(itemView, context);
                }
        }
}

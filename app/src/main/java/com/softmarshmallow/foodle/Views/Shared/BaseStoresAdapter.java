package com.softmarshmallow.foodle.Views.Shared;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Featured.ListStore.StoreItemViewHolder;
import com.softmarshmallow.foodle.Views.FeaturedViewV2.FeaturedStoresAdapter;

import java.util.List;

/**
 * Created by uzu on 9/24/17.
 */

public class BaseStoresAdapter extends RecyclerView.Adapter
{
        
        
        public List<StoreContainerModel> storeDatas;
        protected final Context context;
        
        public BaseStoresAdapter(Context context, List<StoreContainerModel> storeDatas) {
                this.storeDatas = storeDatas;
                this.context = context;
        }
        
        public void updateStoreDatas(List<StoreContainerModel> storeDatas){
                this.storeDatas = storeDatas;
                notifyDataSetChanged();
        }
        
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_store_list_item, parent, false);
                
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
        
}

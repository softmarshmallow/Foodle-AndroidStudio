package com.softmarshmallow.foodle.Views.Featured.Nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;

import java.util.List;


public class NearbyStoresRecyclerViewAdapter extends RecyclerView.Adapter
{
        private final static String TAG = NearbyStoresRecyclerViewAdapter.class.getName();


        public List<StoreContainerModel> storeDatas ;
        private Context context;

        public NearbyStoresRecyclerViewAdapter(List<StoreContainerModel> storeDatas, Context context)
        {
                this.storeDatas = storeDatas;
                this.context = context;
        }

        @Override
        public int getItemCount() {
                // return max 4
                int maxDisplayLimit = 4;
                if (storeDatas.size() > maxDisplayLimit){
                        return maxDisplayLimit;
                }else {
                        return storeDatas.size();
                }
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View itemView = inflater.inflate(R.layout.card_nearby_store, parent, false);

                return new NearbyStoreCardViewHolder(itemView, context);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                Log.d(TAG, "OnBindViewHolder");

                NearbyStoreCardViewHolder storeItemViewHolder = (NearbyStoreCardViewHolder)holder;
                Log.d(TAG, "storeItemViewHolder == null : " + (storeItemViewHolder == null));

                if (storeItemViewHolder != null)
                {
                        storeItemViewHolder.BindWithStoreData(storeDatas.get(position));
                }
        }
}

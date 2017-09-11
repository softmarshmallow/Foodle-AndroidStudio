package com.softmarshmallow.foodle.Views.Featured.ListStore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.Store.StoreModel;
import com.softmarshmallow.foodle.R;

import java.util.List;

/**
 * Created by UZU on 21/08/2017.
 */

public class StoreRecyclerViewAdapter extends RecyclerView.Adapter
{
        final  static String TAG = StoreRecyclerViewAdapter.class.getName();


        public List<StoreModel> storeDatas ;
        Context context;

        public StoreRecyclerViewAdapter(List<StoreModel> storeDatas, Context context)
        {
                this.storeDatas = storeDatas;
                this.context = context;
        }

        @Override
        public int getItemCount() {
                return storeDatas.size();
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View itemView = inflater.inflate(R.layout.card_store_list_item, parent, false);

                return new StoreItemViewHolder(itemView, context);

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                Log.d(TAG, "OnBindViewHolder");

                StoreItemViewHolder storeItemViewHolder = (StoreItemViewHolder)holder;
                Log.d(TAG, "storeItemViewHolder == null : " + (storeItemViewHolder == null));

                if (storeItemViewHolder != null)
                {
                        storeItemViewHolder.BindWithStoreData(storeDatas.get(position));
                }
        }


}

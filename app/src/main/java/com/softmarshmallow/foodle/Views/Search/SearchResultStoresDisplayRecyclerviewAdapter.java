package com.softmarshmallow.foodle.Views.Search;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Shared.BaseStoreItemViewHolder;
import com.softmarshmallow.foodle.Views.StoreDetail.StoreDetailViewActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchResultStoresDisplayRecyclerviewAdapter extends RecyclerView.Adapter
{
        final static String TAG = SearchResultStoresDisplayRecyclerviewAdapter.class.getName();


        public List<StoreContainerModel> storeDatas;
        Context context;

        public SearchResultStoresDisplayRecyclerviewAdapter(List<StoreContainerModel> storeDatas, Context context) {
                this.storeDatas = storeDatas;
                this.context = context;
        }

        public void Update(List<StoreContainerModel> storeDatas){
                this.storeDatas = storeDatas;
                notifyDataSetChanged();
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                View itemView = inflater.inflate(R.layout.card_searchresult_store, parent, false);

                return new SearchResultStoreCardViewHolder(itemView, context);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                Log.d(TAG, "OnBindViewHolder");

                SearchResultStoreCardViewHolder searchResultStoreCardViewHolder = (SearchResultStoreCardViewHolder) holder;
                Log.d(TAG,
                        "SearchResultStoreCardViewHolder == null = " + (searchResultStoreCardViewHolder == null));


                if (searchResultStoreCardViewHolder != null) {
                        searchResultStoreCardViewHolder.BindViewWithData(storeDatas.get(position));
                }

        }

        @Override
        public int getItemCount() {
                return storeDatas.size();
        }
}


class SearchResultStoreCardViewHolder extends BaseStoreItemViewHolder
{
        private static final String TAG = SearchResultStoreCardViewHolder.class.getName();
        
        public SearchResultStoreCardViewHolder(View itemView, Context context) {
                super(itemView, context);
        }
}

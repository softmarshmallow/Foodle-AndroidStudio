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
                        searchResultStoreCardViewHolder.BindWithStoreData(storeDatas.get(position));
                }

        }

        @Override
        public int getItemCount() {
                return storeDatas.size();
        }
}


class SearchResultStoreCardViewHolder extends RecyclerView.ViewHolder
{
        private static final String TAG = SearchResultStoreCardViewHolder.class.getName();
        private Context context;

        @BindView(R.id.mainStorePhotoImageView)
         ImageView mainStorePhotoImageView;


        @BindView(R.id.storeNameTextView)
         TextView storeNameTextView;


        @BindView(R.id.storeShortDescriptionTextView)
         TextView storeShortDescriptionTextView;
        
        
        StoreContainerModel storeData;

        public SearchResultStoreCardViewHolder(View view, final Context context) {
                super(view);
                this.context = context;

                final Gson gson = new Gson();

                ButterKnife.bind(this, view);
                view.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(context,
                                        (StoreDetailViewActivity.class));
                                intent.putExtra((StoreContainerModel.class.getName()),
                                        gson.toJson(storeData));
                                context.startActivity(intent);
                        }
                });

        }


        public void BindWithStoreData(StoreContainerModel storeData) {
                this.storeData = storeData;


                storeNameTextView.setText(storeData.StoreName);
                storeShortDescriptionTextView.setText(storeData.StoreShortDescription);
                // set mainPhoto thumbnail
                String storeMainPhotoUrl = storeData.getMainStorePhotoUrl();
                if (storeMainPhotoUrl != null){
                        Glide.with(context)
                                .load(storeMainPhotoUrl)
                                .into(mainStorePhotoImageView);
                }else {
                        // todo load default image
                        Log.d(TAG ,"storeMainPhotoMedia is null");
                }
        }

}

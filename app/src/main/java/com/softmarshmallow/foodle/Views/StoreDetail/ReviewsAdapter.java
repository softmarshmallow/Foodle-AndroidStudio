package com.softmarshmallow.foodle.Views.StoreDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.ReviewModel;
import com.softmarshmallow.foodle.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UZU on 21/08/2017.
 */

public class ReviewsAdapter extends RecyclerView.Adapter
{

        private static final String TAG = "ReviewsAdapter";
        Context context;
        public List<ReviewModel> reviewDatas = new ArrayList<>();

        public ReviewsAdapter(Context context){
                this.context = context;
        }

        public ReviewsAdapter (Context context, List<ReviewModel> reviewDatas)
        {
                this.context = context;
                this.reviewDatas = reviewDatas;
        }

        public  void UpdateReviews(List<ReviewModel> newReviewDatas){
                reviewDatas = newReviewDatas;
                notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from (parent.getContext())
                        .inflate (R.layout.card_review, parent, false);

                return new ReviewCardViewHolder (itemView, context);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ReviewCardViewHolder reviewCardViewHolder = (ReviewCardViewHolder)holder;
                ReviewModel reviewData = reviewDatas.get(position);
                Log.d(TAG, reviewData.toString());
                reviewCardViewHolder.BindWithReviewData (reviewData);
        }

        @Override
        public int getItemCount() {
                return (reviewDatas != null) ? reviewDatas.size() : 0;
        }
}

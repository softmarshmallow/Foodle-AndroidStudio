package com.softmarshmallow.foodle.Views.StoreDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.softmarshmallow.foodle.Models.ReviewModel;
import com.softmarshmallow.foodle.R;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewCardViewHolder extends RecyclerView.ViewHolder
{

        @BindView(R.id.reviewerThumbnailImageView)
        ImageView reviewerThumbnailImageView;
        @BindView(R.id.reviewRatingBar)
        RatingBar reviewRatingBar;
        @BindView(R.id.reviewerNameTextView)
        TextView reviewerNameTextView;
        @BindView(R.id.reviewCreatedTimeTextView)
        TextView reviewCreatedTimeTextView;
        @BindView(R.id.reviewContentTextView)
        TextView reviewContentTextView;


        Context context;

        public ReviewCardViewHolder(View itemView, Context context) {
                super(itemView);

                this.context = context;
                ButterKnife.bind(this, itemView);
        }


        public void BindWithReviewData(ReviewModel reviewData) {

                reviewRatingBar.setRating(reviewData.Rating);
                reviewerNameTextView.setText("reviewer : " + reviewData.Reviewer);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

                reviewCreatedTimeTextView.setText(
                        reviewData.CreatedTime == null ? "no data.." : simpleDateFormat.format(reviewData.CreatedTime));
                reviewContentTextView.setText(reviewData.ReviewContent);
                // Glide.With (context).Load (reviewData.reviewer).Into (reviewerThumbnailImageView);
        }

}

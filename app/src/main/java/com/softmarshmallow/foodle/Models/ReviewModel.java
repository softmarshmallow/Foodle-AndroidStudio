package com.softmarshmallow.foodle.Models;

import com.google.firebase.database.Exclude;

import java.util.Date;



public class ReviewModel
{
        @Exclude
        public String Id;

        public int Rating;
        public Date CreatedTime;
        public int Reviewer;
        public String ReviewContent;

        public  ReviewModel setRating(int rating) {
                Rating = rating;
                return this;
        }

        public ReviewModel setCreatedTime(Date createdTime) {
                CreatedTime = createdTime;
                return this;

        }

        public ReviewModel setReviewer(int reviewer) {
                Reviewer = reviewer;
                return this;

        }

        public ReviewModel setReviewContent(String reviewContent) {
                ReviewContent = reviewContent;
                return this;

        }


        @Override
        public String toString() {
                return "REVIEW : " + "[Rating : " + Rating + "], " + "[CreatedTime : "+CreatedTime+"], " + "[ReviewContent : "+ReviewContent+"]";
        }
}

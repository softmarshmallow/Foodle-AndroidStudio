package com.softmarshmallow.foodle.Models;

import com.google.firebase.database.Exclude;

import java.util.Date;



public class ReviewModel
{
        @Exclude
        public String Id;

        public int Rating;
        public Date CreatedTime;
        public String ReviewerId;
        public String ReviewContent;
        
        public ReviewModel setId(String id) {
                Id = id;
                return this;
        }

        public  ReviewModel setRating(int rating) {
                Rating = rating;
                return this;
        }

        public ReviewModel setCreatedTime(Date createdTime) {
                CreatedTime = createdTime;
                return this;

        }
        
        public ReviewModel setReviewerId(String reviewerId) {
                ReviewerId = reviewerId;
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

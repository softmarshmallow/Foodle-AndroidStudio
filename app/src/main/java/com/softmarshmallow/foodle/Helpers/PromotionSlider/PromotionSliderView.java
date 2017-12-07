package com.softmarshmallow.foodle.Helpers.PromotionSlider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.softmarshmallow.foodle.R;

public class PromotionSliderView extends BaseSliderView
{
        public PromotionSliderView(Context context) {
                super(context);
        }
        
       
        
        private String promotionTitle;
        private String promotionDescription;
        
        public PromotionSliderView setPromotionTitle(String promotionTitle) {
                this.promotionTitle = promotionTitle;
                return this;
        }
        
        public PromotionSliderView setPromotionDescription(String promotionDescription) {
                this.promotionDescription = promotionDescription;
                return this;
        }
        
        @Override
        public View getView() {
        
                View v = LayoutInflater.from(getContext()).inflate(
                        R.layout.view_promotion_slider,null);
                ImageView promotionImageView = (ImageView)v.findViewById(R.id.promotionImageView);
                TextView promotionTitleTextView = (TextView)v.findViewById(R.id.promotionTitleTextView);
                TextView promotionDescriptionTextView = (TextView)v.findViewById(R.id.promotionDescriptionTextView);
                
                promotionTitleTextView.setText(promotionTitle);
                promotionDescriptionTextView.setText(promotionDescription);
                bindEventAndShow(v, promotionImageView);
                return v;
                
        }
}

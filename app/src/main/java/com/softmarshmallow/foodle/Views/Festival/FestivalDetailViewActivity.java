package com.softmarshmallow.foodle.Views.Festival;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.softmarshmallow.foodle.Models.Festival.FestivalModel;
import com.softmarshmallow.foodle.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FestivalDetailViewActivity extends AppCompatActivity
{
        
        
        public static FestivalModel festivalData;
        public static void setFestivalData(FestivalModel festivalData){
                FestivalDetailViewActivity.festivalData = festivalData;
        }
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_festival_detail_view);
                ButterKnife.bind(this);
                setFestivalWebpageView();
        }
        
        
        @BindView(R.id.festivalWebpageView)
        WebView festivalWebpageView;
        
        void setFestivalWebpageView(){
                festivalWebpageView.setWebViewClient(new WebViewClient());
                festivalWebpageView.loadUrl("http://www.foodtruckfestivalsofamerica.com/");
        }
        
}

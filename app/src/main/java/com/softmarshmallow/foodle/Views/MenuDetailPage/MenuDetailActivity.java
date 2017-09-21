package com.softmarshmallow.foodle.Views.MenuDetailPage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.OrderMenu.OrderMenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

public class MenuDetailActivity extends AppCompatActivity
{
        
        @BindView(R.id.menuThumbnailImageView)
        ImageView menuThumbnailImageView;
        @BindView(R.id.menuPriceText)
        TextView menuPriceText;
        @BindView(R.id.MenuNameTextView)
        TextView menuNameTextView;
        @BindView(R.id.orderButton)
        FancyButton orderButton;
        @BindView(R.id.menuDescriptionTextView)
        TextView menuDescriptionTextView;
        
        
        private static MenuModel menuData;
        public static void ShowMenuDetail(MenuModel menuData, Context context){
                MenuDetailActivity.menuData = menuData;
                context.startActivity(new Intent(context, MenuDetailActivity.class));
        }
        
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu_detail);
                ButterKnife.bind(this);
        
        
                LoadMenuData();
        }
        
        void LoadMenuData(){
                
                if (menuData == null) {
                        return;
                }
                
                // SET image
                Glide.with(this)
                        .load(menuData.MenuMainPhotoUrl)
                        .into(menuThumbnailImageView);
                
                // SET Name
                menuNameTextView.setText(menuData.MenuName);
                
                // SET menu description
                menuDescriptionTextView.setText(menuData.MenuShortDescription);
                
                // SET Total Price
                menuPriceText.setText(menuData.MenuPrice + " 원");
        }
        
        
        
        
        @OnClick(R.id.orderButton)
        public void onOrderMenuButtonClicked() {
                OrderMenuActivity.OrderringMenuData = menuData;
                Intent intent  =new Intent(this, OrderMenuActivity.class);
                startActivity(intent);
        }
}

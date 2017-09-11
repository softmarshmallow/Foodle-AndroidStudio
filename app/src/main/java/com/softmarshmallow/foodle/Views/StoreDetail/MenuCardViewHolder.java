package com.softmarshmallow.foodle.Views.StoreDetail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.OrderMenu.OrderMenuActivity;

/**
 * Created by UZU on 21/08/2017.
 */

public class MenuCardViewHolder extends RecyclerView.ViewHolder
{

        public TextView menuNameTextView;
        public TextView menuPriceTextView;
        public ImageView menuThumbnailImageView;
        public ImageView overflow;

        Context context;
        private MenuModel menuData;
        
        public MenuCardViewHolder(View itemView, final Context context) {
                super(itemView);
                
                this.context = context;

                this.context = context;
                menuNameTextView = (TextView)itemView.findViewById(R.id.title);
                menuPriceTextView = (TextView)itemView.findViewById(R.id.count);
                menuThumbnailImageView = (ImageView)itemView.findViewById(R.id.thumbnail);
                overflow = (ImageView)itemView.findViewById(R.id.overflow);
        
        
                itemView.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                OrderMenuActivity.OrderringMenuData = menuData;
                                Intent intent  =new Intent(context, OrderMenuActivity.class);
                                context.startActivity(intent);
                        }
                });
                
                
                overflow.setOnClickListener(new View.OnClickListener()
                {
                        @Override
                        public void onClick(View view) {
                                //showPopupMenu(overflow);
                                PopupMenu popup = new PopupMenu(context, overflow);
                                MenuInflater inflater = popup.getMenuInflater();
                                inflater.inflate(R.menu.menu_food_menu_options, popup.getMenu());
                                // popup.SetOnMenuItemClickListener (new MyMenuItemClickListener (context));
                                popup.show();
                        }
                });
        }

        public void BindWithMenuData(MenuModel menuData)
        {

                this.menuData = menuData;
                menuNameTextView.setText( menuData.MenuName);
                menuPriceTextView.setText(menuData.MenuPrice +"₩");


                // loading album cover using Glide library
                Glide.with(context).load(menuData.MenuMainPhotoUrl).into(menuThumbnailImageView);


        }



}

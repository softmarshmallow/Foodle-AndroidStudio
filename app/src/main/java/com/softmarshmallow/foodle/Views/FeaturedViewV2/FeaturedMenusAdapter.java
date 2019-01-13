package com.softmarshmallow.foodle.Views.FeaturedViewV2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.MenuDetailPage.MenuDetailActivity;
import com.softmarshmallow.foodle.Views.Shared.BaseMenusAdapter;

import java.util.List;

/**
 * Created by uzu on 9/23/17.
 */

public class FeaturedMenusAdapter extends BaseMenusAdapter
{
    public FeaturedMenusAdapter(Context mContext, List<MenuModel> menuDatas) {
        super(mContext, menuDatas);
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.card_featured_menu_v3, parent, false);
        
        return new FeaturedMenusAdapter.MenuItemViewHolder(itemView, context);
    }
    
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        
        MenuModel menuData = menuDatas.get(position);
        MenuItemViewHolder menuItemViewHolder = (MenuItemViewHolder) holder;
        menuItemViewHolder.BindViewWithData(menuData);
    }
    
    class MenuItemViewHolder extends RecyclerView.ViewHolder
    {
        
        ImageView menuImageView;
        TextView menuNameTextView;
        TextView priceTextView;
        TextView baseStoreName;
        private final Context context;
        private MenuModel menuData;
        
        public MenuItemViewHolder(View view, final Context context) {
            super(view);
            this.context = context;
            
            menuImageView = (ImageView) view.findViewById(R.id.menuImageView);
            menuNameTextView = (TextView) view.findViewById(R.id.menuNameTextView);
            priceTextView = (TextView) view.findViewById(R.id.menuPriceText);
            baseStoreName = (TextView) view.findViewById(R.id.baseStoreNameTextView);
            
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    MenuDetailActivity.ShowMenuDetail(menuData, context);
                }
            });
            
        }
        
        public void BindViewWithData(MenuModel menuData) {
            this.menuData = menuData;
            Glide.with(context)
                .load(menuData.MenuMainPhotoUrl)
                .thumbnail(Glide.with(context)
                    .load(R.drawable.loading))
                .apply(new RequestOptions()
                    .placeholder(R.drawable.loading))
                .into(menuImageView);
            menuNameTextView.setText(menuData.MenuName);
            priceTextView.setText(String.valueOf(menuData.MenuPrice));
        }
    }
}

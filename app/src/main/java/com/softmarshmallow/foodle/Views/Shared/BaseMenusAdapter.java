package com.softmarshmallow.foodle.Views.Shared;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.StoreDetail.MenuCardViewHolder;

import java.util.List;

/**
 * Created by uzu on 9/23/17.
 */

public class BaseMenusAdapter extends RecyclerView.Adapter
{
        
        protected Context context;
        public List<MenuModel> menuDatas;
        
        public BaseMenusAdapter(Context mContext, List<MenuModel> menuDatas)
        {
                this.context = mContext;
                this.menuDatas = menuDatas;
        }
        
        public  void UpdateDatas(List<MenuModel> newMenuDatas){
                this.menuDatas = newMenuDatas;
                notifyDataSetChanged();
                
        }
        
        
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_menu, parent, false);
                
                return new MenuCardViewHolder(itemView, context);
        }
        
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                MenuCardViewHolder menuCardViewHolder = (MenuCardViewHolder)holder;
                MenuModel menuData = menuDatas.get(position);
                menuCardViewHolder.BindWithMenuData(menuData);
        }
        
        @Override
        public int getItemCount() {
                return  (menuDatas != null) ? menuDatas.size() : 0;
        }
}

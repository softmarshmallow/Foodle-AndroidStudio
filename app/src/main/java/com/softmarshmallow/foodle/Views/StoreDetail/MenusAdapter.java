package com.softmarshmallow.foodle.Views.StoreDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.R;
import com.softmarshmallow.foodle.Views.Shared.BaseMenusAdapter;

import java.util.List;

/**
 * Created by UZU on 21/08/2017.
 */

public class MenusAdapter extends BaseMenusAdapter
{
        public MenusAdapter(Context mContext, List<MenuModel> menuDatas) {
                super(mContext, menuDatas);
        }
}

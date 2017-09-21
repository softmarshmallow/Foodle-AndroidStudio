package com.softmarshmallow.foodle.Models.StoreV2;

import com.softmarshmallow.foodle.Models.Menus.MenuModel;

import java.util.ArrayList;
import java.util.List;


public class StoreContainerModel extends StoreDownloadModel
{
        public List<MenuModel> Menus = new ArrayList<>();
        public List<StoreReviewModel> StoreReviews = new ArrayList<>();
        
        public StoreContainerModel setMenus(List<MenuModel> menus) {
                Menus = menus;
                return this;
        }
        
        public StoreContainerModel setStoreReviews(List<StoreReviewModel> storeReviews) {
                StoreReviews = storeReviews;
                return this;
        }
}

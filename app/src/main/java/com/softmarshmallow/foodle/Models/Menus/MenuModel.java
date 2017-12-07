package com.softmarshmallow.foodle.Models.Menus;


import com.google.firebase.database.Exclude;

/**
 * Created by UZU on 21/08/2017.
 */

public class MenuModel
{
        @Exclude
        public String Id;

        public String MenuName;
        
        public String MenuShortDescription;
        
        public String MenuMainPhotoUrl;
        @Exclude
        public String MenuMainLocalPhotoUri;
        
        public int MenuPrice;
        
        public  String MenuNutritionInformation;
        
        public String BaseStoreId;

        public MenuModel() {
        }

        public MenuModel setMenuName(String menuName) {
                MenuName = menuName;
                return this;
        }

        public MenuModel setMenuShortDescription(String menuShortDescription) {
                MenuShortDescription = menuShortDescription;
                return this;

        }

        public MenuModel setMenuMainPhotoUrl(String menuMainPhotoUrl) {
                MenuMainPhotoUrl = menuMainPhotoUrl;
                return this;

        }

        public MenuModel setMenuPrice(int menuPrice) {
                MenuPrice = menuPrice;
                return this;

        }
}

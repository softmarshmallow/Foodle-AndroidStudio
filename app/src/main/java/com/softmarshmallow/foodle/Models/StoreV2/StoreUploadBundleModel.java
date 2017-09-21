package com.softmarshmallow.foodle.Models.StoreV2;

import com.google.firebase.database.Exclude;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;

import java.util.ArrayList;
import java.util.List;


public class StoreUploadBundleModel
{
        public StoreTransferBaseModel storeTransferBaseModel = new StoreTransferBaseModel();
        public List<String> StorePhotoLocalUris = new ArrayList<>();
        public List<MenuModel> Menus = new ArrayList<>();
}

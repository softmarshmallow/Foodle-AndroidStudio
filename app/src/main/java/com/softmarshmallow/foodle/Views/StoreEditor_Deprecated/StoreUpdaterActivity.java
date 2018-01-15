package com.softmarshmallow.foodle.Views.StoreEditor_Deprecated;

import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.softmarshmallow.foodle.Models.StoreV2.StoreTransferBaseModel;

public class StoreUpdaterActivity extends StoreEditorBaseActivity
{
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        
                LoadStoreDataToEdit();
        
        }
        
        
        void LoadStoreDataToEdit(){
                StoreTransferBaseModel storeData = getStoreUploadData();
                storeNameEditText.setText(storeData.StoreName);
                
                storeShortDescriptionEditText.setText(storeData.StoreShortDescription);
                
                storeFullDescriptionEditText.setText(storeData.StoreFullDescription);
                
                SetStoreLocationData(new LatLng(storeData.getStoreLocation().first, storeData.getStoreLocation().second), storeData.StoreAddress);
                
                //// TODO: 9/12/17 implement all
                
                // init menus
                StoreMenuDatasToUpdate = StoreUploadBundleData.Menus;
        }
        
}

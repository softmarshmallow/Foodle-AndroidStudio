package com.softmarshmallow.foodle.Models.StoreV2;

import android.util.Pair;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoreTransferBaseModel
{
        public String StoreOwnerId;
        public String StoreName;
        public String StoreShortDescription;
        public String StoreFullDescription;
        public String StoreLocation;
        public String StoreAddress;
        public String StorePhoneNumber;
        public String StoreExtraContacts;
        public List<String> StorePhotoUrls = new ArrayList<>();
        
        @Exclude
        public Pair<Double, Double> getStoreLocation() {
                try {
                        String[] locationStrings;
                        locationStrings = StoreLocation.split(",");
                        return new Pair<Double, Double>(Double.parseDouble(locationStrings[0]),
                                Double.parseDouble(locationStrings[1]));
                }
                catch (Exception e) {
                        return new Pair<Double, Double>(0d, 0d);
                }
        }
        @Exclude
        public LatLng getStoreLatLng(){
                Pair<Double, Double> pair = getStoreLocation();
                return new LatLng(pair.first, pair.second);
        }
        
        public StoreTransferBaseModel setStoreOwnerId(String storeOwnerId) {
                StoreOwnerId = storeOwnerId;
                return this;
        }
        
        public StoreTransferBaseModel setStoreName(String storeName) {
                StoreName = storeName;
                return this;
        }
        
        public StoreTransferBaseModel setStoreShortDescription(String storeShortDescription) {
                StoreShortDescription = storeShortDescription;
                return this;
        }
        
        public StoreTransferBaseModel setStoreFullDescription(String storeFullDescription) {
                StoreFullDescription = storeFullDescription;
                return this;
        }
        
        public StoreTransferBaseModel setStoreLocation(String storeLocation) {
                StoreLocation = storeLocation;
                return this;
        }
        
        public StoreTransferBaseModel setStoreAddress(String storeAddress) {
                StoreAddress = storeAddress;
                return this;
        }
        
        public StoreTransferBaseModel setStorePhoneNumber(String storePhoneNumber) {
                StorePhoneNumber = storePhoneNumber;
                return this;
        }
        
        public StoreTransferBaseModel setStoreExtraContacts(String storeExtraContacts) {
                StoreExtraContacts = storeExtraContacts;
                return this;
        }
}

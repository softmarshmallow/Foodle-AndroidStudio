package com.softmarshmallow.foodle.Models.Store;

import android.util.Pair;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Services.FirebaseUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StoreModel
{

        @Exclude
        public String Id;
        public StoreModel setId(String id) {
                Id = id;
                return  this;
        }

        //region Owner
        public String StoreOwnerId;
        public StoreModel setStoreOwnerId(String storeOwnerId) {
                StoreOwnerId = storeOwnerId;
                return this;
        }

        public boolean isLocalUserIsOwner(){
                return StoreOwnerId.equals( FirebaseUserService.GetUserUID());
        }
        //endregion

        // region StoreName
        public String StoreName;

        public StoreModel setStoreName(String storeName) {
                StoreName = storeName;
                return this;
        }
        // endregion
        
        
        // region StoreShortDescription
        public String StoreShortDescription;
        
        public StoreModel setStoreShortDescription(String storeShortDescription) {
                StoreShortDescription = storeShortDescription;
                return this;
        }
        // endregion
        
        // region StoreFullDescription
        public String StoreFullDescription;
        
        public StoreModel setStoreFullDescription(String storeFullDescription) {
                StoreFullDescription = storeFullDescription;
                return this;
        }
        // endregion
        
        // region StoreLocation
        public String StoreLocation;
        
        public StoreModel setStoreLocation(String storeLocation) {
                StoreLocation = storeLocation;
                return this;
        }
        
        // region StoreAddress
        public String StoreAddress;
        
        public StoreModel setStoreAddress(String storeAddress) {
                StoreAddress = storeAddress;
                return this;
        }
        
        // endregion
        
        // region StorePhotoUrls
        @Exclude
        public List<String> StorePhotoLocalUris = new ArrayList<>();
        @Exclude
        public static final String StorePhotoUrlsKey = "StorePhotoUrls";
        @PropertyName(StorePhotoUrlsKey)
        public List<String> StorePhotoUrls = new ArrayList<String>();//{{add("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");}};
        
        public String GetMainStorePhotoUrl() {
                return StorePhotoUrls == null ?
                        ("")
                        : (StorePhotoUrls.size() > 0 ?
                        StorePhotoUrls.get(0)
                        : ("")
                );
        }
        
        public StoreModel setStorePhotoUrls(List<String> storePhotoUrls) {
                StorePhotoUrls = storePhotoUrls;
                return this;
        }
        // endregion
        
        
        // region StorePhoneNumber
        public String StorePhoneNumber;
        
        public StoreModel setStorePhoneNumber(String storePhoneNumber) {
                StorePhoneNumber = storePhoneNumber;
                return this;
        }
        // endregion
        
        // region StoreExtraContacts
        public String StoreExtraContacts;
        
        public StoreModel setStoreExtraContacts(String storeExtraContacts) {
                StoreExtraContacts = storeExtraContacts;
                return this;
        }
        // endregion
        
        // region Menus
        @Exclude
        public static final String MenusIdKey  = "MenusIds";
        @PropertyName(MenusIdKey)
        public Map<String, String> MenusIds = new HashMap<>();
        @Exclude
        public List<MenuModel> Menus = new ArrayList<>();
        public StoreModel setMenus(List<MenuModel> menus) {
                Menus = menus;
                return this;
        }
        // endregion
        
        
        // region StoreReviews
        @Exclude
        public static final String StoreReviewsIdKey  = "StoreReviewsIds";
        @PropertyName(StoreReviewsIdKey)
        public Map<String, String> StoreReviewsIds = new HashMap<>();
        @Exclude
        public List<StoreReviewModel> StoreReviews = new ArrayList<>();
        public StoreModel setStoreReviews(List<StoreReviewModel> storeReviews) {
                StoreReviews = storeReviews;
                return this;
        }
        // endregion
        
        
        //
        public Pair<Double, Double> GetStoreLocation() {
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
        
        
        public StoreModel() {
                
        }
        
}

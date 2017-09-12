package com.softmarshmallow.foodle.Models.User;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;
import com.softmarshmallow.foodle.Models.Store.StoreModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodleUserProfileModel
{
        public String id;
        public String displayName;
        public String photoUrl;
        public String email;
        public String phoneNumber;
        
        public FoodleUserProfileModel setId(String id) {
                this.id = id;
                return this;
        }
        
        public FoodleUserProfileModel setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
        }
        
        public FoodleUserProfileModel setDisplayName(String displayName) {
                this.displayName = displayName;
                return this;
        }
        
        public FoodleUserProfileModel setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
                return this;
        }
        
        public FoodleUserProfileModel setEmail(String email) {
                this.email = email;
                return this;
        }
        
        // region LikedStores
        @Exclude
        public static final String LikedStoreIdsKey = "LikedStores";
        @PropertyName(LikedStoreIdsKey)
        public Map<String, String> LikedStoreIds = new HashMap<>();
        @Exclude
        public List<StoreModel> LikedStores = new ArrayList<>();
        public FoodleUserProfileModel setLikedStores(List<StoreModel> likedStores) {
                LikedStores = likedStores;
                return this;
        }
        // endregion
        
        
        
        // region OwnedStores
        @Exclude
        public static final String OwnedStoreIdsKey = "OwnedStore";
        @PropertyName(OwnedStoreIdsKey)
        public Map<String, String> OwnedStoreIds = new HashMap<>();
        @Exclude
        public List<StoreModel> OwnedStores = new ArrayList<>();
        public FoodleUserProfileModel setOwnedStores(List<StoreModel> ownedStores) {
                OwnedStores = ownedStores;
                return this;
        }
        // endregion
        
}

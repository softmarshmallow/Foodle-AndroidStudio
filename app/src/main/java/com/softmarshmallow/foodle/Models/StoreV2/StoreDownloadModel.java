package com.softmarshmallow.foodle.Models.StoreV2;

import android.util.Pair;

import com.softmarshmallow.foodle.Services.FirebaseUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by uzu on 9/14/17.
 */

public class StoreDownloadModel extends StoreTransferBaseModel
{
        
        public String Id;
        
        public boolean isLocalUserIsOwner(){
                return StoreOwnerId.equals( FirebaseUserService.GetUserUID());
        }
        public List<String> StorePhotoUrls = new ArrayList<String>();//{{add("https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_120x44dp.png");}};
        
        // FIXME This raises deserialize error, consider hashmap
        public List<String> MenusIds = new ArrayList<>();
        public Map<String, String> StoreReviewsIds = new HashMap<>();
        
        
        public String getMainStorePhotoUrl() {
                return StorePhotoUrls == null ?
                        ("")
                        : (StorePhotoUrls.size() > 0 ?
                        StorePhotoUrls.get(0)
                        : ("")
                );
        }

        
        
        public StoreDownloadModel setId(String id) {
                Id = id;
                return this;
        }
        
        public StoreDownloadModel setStorePhotoUrls(List<String> storePhotoUrls) {
                StorePhotoUrls = storePhotoUrls;
                return this;
        }
        
        public StoreDownloadModel setMenusIds(List<String> menusIds) {
                MenusIds = menusIds;
                return this;
        }
        
        public StoreDownloadModel setStoreReviewsIds(Map<String, String> storeReviewsIds) {
                StoreReviewsIds = storeReviewsIds;
                return this;
        }
}

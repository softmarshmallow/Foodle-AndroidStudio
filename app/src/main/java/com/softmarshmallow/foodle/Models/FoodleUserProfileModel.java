package com.softmarshmallow.foodle.Models;

import android.net.Uri;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by UZU on 29/08/2017.
 */

public class FoodleUserProfileModel
{
        @Exclude
        public String Id;
        @Exclude
        public Uri LocalProfilePictureUri;
        public String ProfilePictureUrl;
        public Map<String, String> OwnedStoresIds = new HashMap<>();
        public Map<String, String> LikedStoresIds = new HashMap<>();
}

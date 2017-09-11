package com.softmarshmallow.foodle.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.softmarshmallow.foodle.FoodleApp;


public class UserPreference
{

        static SharedPreferences UserPrefs = FoodleApp.getAppContext().getSharedPreferences("LoginPreferences",
                Context.MODE_PRIVATE);

        static SharedPreferences.Editor UserPrefsEditor = UserPrefs.edit();


        //region user uid
        private static final String userUIDKey ="UserUID";
        public static void setUserUUID(String userUID){
                UserPrefsEditor.putString(userUIDKey, userUID);
                UserPrefsEditor.apply();
        }
        public static String getUserUUID(){
                return UserPrefs.getString(userUIDKey, null);
        }
        //endregion

}

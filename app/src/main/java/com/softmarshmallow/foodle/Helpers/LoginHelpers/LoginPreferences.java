package com.softmarshmallow.foodle.Helpers.LoginHelpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.softmarshmallow.foodle.FoodleApp;

public class LoginPreferences
{

        static SharedPreferences LoginPrefs = FoodleApp.getAppContext().getSharedPreferences("LoginPreferences",
                Context.MODE_PRIVATE);

        static SharedPreferences.Editor LoginPrefsEditor = LoginPrefs.edit();

        public static void clearLoginPreference(){
                LoginPrefsEditor.clear();
                LoginPrefsEditor.apply();
        }

        // region isLoggedIn
        private static final String isLoggedInKey ="isLoggedIn";
        public static boolean getIsLoggedIn(){
                return LoginPrefs.getBoolean(isLoggedInKey, false);
        }

        public static void setIsLoggedIn(boolean isLoggedIn){
                LoginPrefsEditor.putBoolean(isLoggedInKey, isLoggedIn);
                LoginPrefsEditor.apply();
        }
        // endregion
        
        
        //region loginType
        private static final String loginTypeKey ="loginType";
        public static void setLoginType(LoginType loginType){
                LoginPrefsEditor.putString(loginTypeKey, loginType.toString());
                LoginPrefsEditor.apply();
        }
        public static LoginType getLoginType(){
                String loginTypeAsString = LoginPrefs.getString(loginTypeKey, LoginType.Email.toString());
                return LoginType.valueOf(loginTypeAsString);
        }
        //endregion
        
        
        
        //region facebook
        private static final String facebookTokenKey ="facebookToken";
        public static void setFacebookToken(String facebookToken){
                LoginPrefsEditor.putString(facebookTokenKey, facebookToken);
                LoginPrefsEditor.apply();
        }
        public static String getFacebookToken() {
                return LoginPrefs.getString(facebookTokenKey, null);
        }
        //endregion


        //region Email
        private static final String emailKey= "email";

        public static void setUserEmail(String email){
                LoginPrefsEditor.putString(emailKey, email);
                LoginPrefsEditor.apply();
        }

        public static String getUserEmail() {
                return LoginPrefs.getString(emailKey, null);
        }

        // endregion



        //region Password

        private static final String passwordKey= "password";

        public static void setUserPassword(String password){
                LoginPrefsEditor.putString(passwordKey, password);
                LoginPrefsEditor.apply();
        }

        public static String getUserPassword() {
                return LoginPrefs.getString(passwordKey, null);
        }
        
        
        // endregion

}




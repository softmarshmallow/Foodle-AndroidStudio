package com.softmarshmallow.foodle.Helpers;

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

package com.softmarshmallow.foodle.Services;

import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.softmarshmallow.foodle.Models.User.UserProfile;

/**
 * Created by UZU on 29/08/2017.
 */

public class UserService
{

        public static UserProfile GetUserProfile(){

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                        // Name, email address, and profile photo Url
                        String name = user.getDisplayName();
                        String email = user.getEmail();
                        Uri photoUrl = user.getPhotoUrl();

                        // Check if user's email is verified
                        boolean emailVerified = user.isEmailVerified();

                        // The user's ID, unique to the Firebase project. Do NOT use this value to
                        // authenticate with your backend server, if you have one. Use
                        // FirebaseUser.getToken() instead.
                        String uid = user.getUid();
                }
                return  new UserProfile();
        }

        public static void UpdateUserProfile(){

        }

        public static String GetUserUID(){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                return user.getUid();
        }



}

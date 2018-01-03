package com.softmarshmallow.foodle.Services;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.reactivex.functions.Consumer;


public class FirebaseUserService
{
        
        
        
        private static final String TAG = FirebaseUserService.class.getName();
        private static FirebaseAuth mAuth = FirebaseAuth.getInstance();



        static FirebaseUser getCurrentUser(){
                 return FirebaseAuth.getInstance().getCurrentUser();
        }
        
        
        
        public static String GetUserUID(){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                return user.getUid();
        }
        
        
        
        //region
        public static String getDisplayName(){
                FirebaseUser user  =  getCurrentUser();
                return user.getDisplayName();
        }
        
        public  static void updateUserDisplayname(final String newDisplayname, final Consumer<String> resultCallback, final Consumer<String> errorCallback){
                FirebaseUser user  =  getCurrentUser();
        
                UserProfileChangeRequest displaynameUpdateRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newDisplayname)
                        .build();
        
                user.updateProfile(displaynameUpdateRequest)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                                Log.d(TAG, "User Displayname updated.");
                                                try {
                                                        resultCallback.accept(newDisplayname);
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }else {
                                                try {
                                                        errorCallback.accept(task.getException().getMessage());
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        });
        }
        //endregion
        
        //region
        
        public static boolean isProfilePhotoSetted(){
                return getCurrentUser().getPhotoUrl() != null;
        }
        
        public static String getProfilePhotoUrl(){
                if (isProfilePhotoSetted()) {
                        return getCurrentUser().getPhotoUrl().toString();
                } else {
                        return "";
                }
        }
        
        public  static void updateProfilePhotoUrl(final String newProfilePhotoUrl, final Consumer<String> resultCallback, final Consumer<String> errorCallback){
                FirebaseUser user  =  getCurrentUser();
        
                UserProfileChangeRequest profilePhotoUpdateRequest = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(Uri.parse(newProfilePhotoUrl))
                        .build();
        
                user.updateProfile(profilePhotoUpdateRequest)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                                Log.d(TAG, "User Displayname updated.");
                                                try {
                                                        resultCallback.accept(newProfilePhotoUrl);
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }else {
                                                try {
                                                        errorCallback.accept(task.getException().getMessage());
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        });
        }
        
        //endregion
        
        
        //region
        public static String getUserEmail(){
                return getCurrentUser().getEmail();
        }
        
        public static void updateUserEmail(final String newEmail, final Consumer<String> resultCallback, final Consumer<String> errorCallback){
                getCurrentUser().updateEmail(newEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>()
                        {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                                try {
                                                        resultCallback.accept(newEmail);
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }else {
                                                try {
                                                        errorCallback.accept(task.getException().getMessage());
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        });
        }
        //endregion
        
        public static String getUserPhoneNumber(){
                return getCurrentUser().getPhoneNumber();
        }


}

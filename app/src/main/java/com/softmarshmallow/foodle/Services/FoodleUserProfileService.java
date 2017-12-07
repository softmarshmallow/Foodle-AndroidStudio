package com.softmarshmallow.foodle.Services;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;
import com.softmarshmallow.foodle.Models.User.FoodleUserProfileModel;

import io.reactivex.functions.Consumer;

public class FoodleUserProfileService
{
        
        
        private static final String TAG = "FoodleUserProfileService";
        private static DatabaseReference FoodleUserProfileDatabaseReference = ApiController.database.getReference(
                "FoodleUserProfiles");
        
        
        public static void getUserProfileExists(final String userId, final Consumer<Boolean> resultCallback, final Consumer<String> errorCallback){
                FoodleUserProfileDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                                boolean doesProfileExists = snapshot.hasChild(userId);
                                try {
                                        resultCallback.accept(doesProfileExists);
                                }
                                catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                                try {
                                        errorCallback.accept(databaseError.getMessage());
                                }
                                catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
        
        public  static void getUserProfile(String userId, final Consumer<FoodleUserProfileModel> resultCallback, final Consumer<String> errorCallback){
                FoodleUserProfileDatabaseReference.child(userId).addListenerForSingleValueEvent(
                        new ValueEventListener()
                        {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                        FoodleUserProfileModel retrievedFoodleUserProfileData = dataSnapshot.getValue(
                                                FoodleUserProfileModel.class);
                                        try {
                                                resultCallback.accept(retrievedFoodleUserProfileData);
                                        }
                                        catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }
        
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                        try {
                                                errorCallback.accept(databaseError.getMessage());
                                        }
                                        catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                        });
        }
        
        
        public static void updateUserProfile(final String userId, final FoodleUserProfileModel newProfile, final Consumer<FoodleUserProfileModel> resultCallback, final Consumer<String> errorCallback){
                
                FoodleUserProfileDatabaseReference.child(userId).setValue(newProfile,
                        new DatabaseReference.CompletionListener()
                        {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                        if (databaseError == null) {
                                                Log.d(TAG, "UploadMenu complete, Id : "+userId);
                                                try {
                                                        resultCallback.accept(newProfile);
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        } else {
                                                try {
                                                        errorCallback.accept(databaseError.getMessage());
                                                }
                                                catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        });
        }
        
        
        
        
        
        
        
}

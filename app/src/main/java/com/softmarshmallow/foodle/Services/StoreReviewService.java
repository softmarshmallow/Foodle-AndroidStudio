package com.softmarshmallow.foodle.Services;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.softmarshmallow.foodle.Models.StoreV2.StoreReviewModel;

import io.reactivex.functions.Consumer;

// import java.util.function.Consumer;

public class StoreReviewService
{


        private static final String TAG = "StoreReviewService";
        private static DatabaseReference storeReviewsDatabaseReference = ApiController.database.getReference(
                "StoreReviews");
        
        public static final String StoreReviewsIdKey = "StoreReviewsIds";


        public static class StoreReviewCreator{

                private final StoreReviewModel storeReviewDataToCreate;
                private final String baseStoreId;
                private final Consumer<StoreReviewModel> resultCallback;
                private final Consumer<String> errorCallback;

                DatabaseReference newStoreReviewDatabaseReference = storeReviewsDatabaseReference.push();
                String newStoreReviewId = newStoreReviewDatabaseReference.getKey();
                public StoreReviewCreator(final StoreReviewModel storeReviewDataToCreate, @NonNull String baseStoreId, final Consumer<StoreReviewModel> resultCallback, final Consumer<String> errorCallback) {
                        this.storeReviewDataToCreate = storeReviewDataToCreate;
                        this.baseStoreId = baseStoreId;
                        this.resultCallback = resultCallback;
                        this.errorCallback = errorCallback;

                }

                public  void CreateStoreReview(){
                        Operation1_CreateStoreReview();
                }

                void Operation1_CreateStoreReview(){
                        newStoreReviewDatabaseReference.setValue(storeReviewDataToCreate,
                                new DatabaseReference.CompletionListener()
                                {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                if (databaseError == null){
                                                        Operation2_UpdateStoreReviewInStoreData();
                                                }else {
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

                void Operation2_UpdateStoreReviewInStoreData(){
                        StoreService.storesDatabaseReference.child(baseStoreId).child(StoreReviewsIdKey).push().setValue(
                                newStoreReviewId
                                , new DatabaseReference.CompletionListener()
                                {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                if (databaseError == null){
                                                        try {
                                                                resultCallback.accept(storeReviewDataToCreate);
                                                        }
                                                        catch (Exception e) {
                                                                e.printStackTrace();
                                                        }
                                                }else {
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




        public static void GetStoreReview(String storeReviewId, final Consumer<StoreReviewModel> resultCallback, final Consumer<String>errorCallback){
storeReviewsDatabaseReference.child(storeReviewId).addListenerForSingleValueEvent(
        new ValueEventListener()
        {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        StoreReviewModel retrievedStoreReviewData = dataSnapshot.getValue(StoreReviewModel.class);
                        try {
                                Log.d(TAG, "GetStoreReview StoreReview = " + retrievedStoreReviewData.toString());
                                resultCallback.accept(retrievedStoreReviewData);
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



}

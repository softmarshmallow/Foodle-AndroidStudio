package com.softmarshmallow.foodle.Services;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.softmarshmallow.foodle.Models.StoreUploadRequestModel;

import io.reactivex.functions.Consumer;

/**
 * Created by softmarshmallow on 2/14/18.
 */

public class StoreUploadRequestService
{
        private static final String TAG = "StoreUploadRequesService";
        private static final String StoreUploadRequestKey = "StoreUploadRequest";
        
        static DatabaseReference StoreUploadRequestDatabaseReference = ApiController.database.getReference(
                StoreUploadRequestKey);
        
        public static  class StoreUploadRequestCreator{
                private Consumer<String> errorCallback;
                private Consumer<String> resultCallback;
                final DatabaseReference newStoreUploadRequestDatabaseReference = StoreUploadRequestDatabaseReference.push();
                private  StoreUploadRequestModel storeUploadRequestModeltoCreate;
        
                public StoreUploadRequestCreator(final StoreUploadRequestModel storeUploadRequesttoCreate, final Consumer<String> resultCallback,final Consumer<String> errorCallback) {
                        this.storeUploadRequestModeltoCreate = storeUploadRequesttoCreate;
                        this.resultCallback = resultCallback;
                        this.errorCallback = errorCallback;
                }
                public void CreateStoreUploadRequest(){
                         newStoreUploadRequestDatabaseReference.setValue(
                                 storeUploadRequestModeltoCreate,
                                 new DatabaseReference.CompletionListener()
                                 {
                                         @Override
                                         public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                 if (databaseError == null) {
                                                         try {
                                                                 resultCallback.accept("Done");
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
                                 }
                         );
                }
        
        }
}

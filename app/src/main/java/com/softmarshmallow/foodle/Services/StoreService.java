package com.softmarshmallow.foodle.Services;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.util.Log;

import com.google.android.gms.nearby.connection.Strategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softmarshmallow.foodle.Models.Store.StoreModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
// import java.util.function.Consumer;

public class StoreService
{
        
        private static final String TAG = "StoreService";
        private static final String StoresKey = "Stores";
        public static DatabaseReference storesDatabaseReference = ApiController.database.getReference(
                StoresKey);
        
        public static StorageReference storesStorageReference = ApiController.storage.getReference(
                StoresKey);


        //
        //
        //

        public static class StoreUploader
        {
                private final StoreModel storeData;
                private final Consumer<StoreModel> resultCallback;
                private final Consumer<String> errorCallback;

                final DatabaseReference newStoreDatabaseReference = storesDatabaseReference.push();
                final String newStoreDatabaseReferenceKey = newStoreDatabaseReference.getKey();

                public StoreUploader(final StoreModel storeData, final Consumer<StoreModel> resultCallback, final Consumer<String> errorCallback) {
                        this.storeData = storeData;
                        this.resultCallback = resultCallback;
                        this.errorCallback = errorCallback;
                }

                public void CreateStore(){
                        Operation1_UploadStorePhotos();
                }

                void Operation1_UploadStorePhotos(){

                        final int totalPhotosToUploadCount = storeData.StorePhotoLocalUris.size();
                        Log.d(TAG,  "UploadStorePhotos localPhotos : " +totalPhotosToUploadCount );
                        Log.d(TAG,  "UploadStorePhotos databaseReference : " + newStoreDatabaseReference.getKey());
                        int index = 0;
                        for (String photoUriString : storeData.StorePhotoLocalUris) {
                                Log.d(TAG,  "UploadStorePhotos, photoUri : "+ photoUriString);
                                Uri photoUri = Uri.parse(photoUriString);
                                UploadTask uploadTask = storesStorageReference.child(newStoreDatabaseReferenceKey).child("StorePhotos").child(String.valueOf(index)).putFile(photoUri);
                                uploadTask.addOnCompleteListener(
                                        new OnCompleteListener<UploadTask.TaskSnapshot>()
                                        {
                                                @Override
                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                        if (task.getException() == null) {
                                                                Uri uri = task.getResult()
                                                                        .getDownloadUrl();
                                                                storeData.StorePhotoUrls.add(
                                                                        uri.toString());
                                                                Log.d(TAG,
                                                                        "OnComplete uri : " + uri.toString());
                                                                if (totalPhotosToUploadCount == storeData.StorePhotoUrls.size()) {
                                                                        Operation2_UploadStoreInstance_Include_Photos_Download_Urls();
                                                                }
                                                        } else {
                                                                try {
                                                                        errorCallback.accept(
                                                                                task.getException()
                                                                                        .getMessage());
                                                                }
                                                                catch (Exception e) {
                                                                        e.printStackTrace();
                                                                }
                                                        }
                                                }
                                        });
                                Log.d(TAG, "downloadUrl : " + storesStorageReference.child(newStoreDatabaseReferenceKey).getDownloadUrl());
                                index ++;
                        }
                }

                void Operation2_UploadStoreInstance_Include_Photos_Download_Urls(){

                        newStoreDatabaseReference
                                .setValue(storeData,
                                        new DatabaseReference.CompletionListener()
                                        {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                        if (databaseError == null) {
                                                                Log.d(TAG, "CreateStore, onComplete");
                                                                storeData.Id = databaseReference.getKey();
                                                                try {
                                                                        resultCallback.accept(storeData);
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

        //
        //
        //


        
        public static void GetAllStores(final Consumer<List<StoreModel>> resultCallback, final Consumer<String> errorCallback) {
                Log.d(TAG, "GetAllStores");

                storesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
                {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                List<StoreModel> allStores = new ArrayList<StoreModel>();
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        StoreModel value = child.getValue(StoreModel.class);
                                        value.Id = child.getKey();
                                        allStores.add(value);
                                }
                                try {
                                        resultCallback.accept(allStores);
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
        
        
        public static void GetStore(final String storeId, final Consumer<StoreModel> resultCallback, final Consumer<String> errorCallback) {
                Log.d(TAG, "GetStore");

                storesDatabaseReference.child(storeId)
                        .addListenerForSingleValueEvent(new ValueEventListener()
                        {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                        StoreModel value = snapshot.getValue(StoreModel.class);
                                        value.Id = storeId;
                                        try {
                                                resultCallback.accept(value);
                                        }
                                        catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                        Log.d(TAG, value.StoreName);
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
        
        
        public static void Test() {

                StoreService.GetAllStores(
                        new Consumer<List<StoreModel>>()
                        {
                                @Override
                                public void accept(List<StoreModel> storeModels) throws Exception {
                                        for (StoreModel storeModel : storeModels) {
                                                Log.d(TAG, "\n\n\n\n");
                                                Log.d(TAG, storeModel.StoreName);
                                                Log.d(TAG, storeModel.GetMainStorePhotoUrl());
                                        }
                                }
                        }, new Consumer<String>()
                        {
                                @Override
                                public void accept(String databaseError) throws Exception {
                                        Log.d(TAG, databaseError);
                                }
                        });
        }
        
}

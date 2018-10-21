package com.softmarshmallow.foodle.Services;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softmarshmallow.foodle.Models.StoreV2.StoreContainerModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreDownloadModel;
import com.softmarshmallow.foodle.Models.StoreV2.StoreUploadBundleModel;

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
                private final StoreUploadBundleModel storeUploadBundleData;
                private final Consumer<StoreContainerModel> resultCallback;
                private final Consumer<String> errorCallback;

                final DatabaseReference newStoreDatabaseReference = storesDatabaseReference.push();
                final String newStoreDatabaseReferenceKey = newStoreDatabaseReference.getKey();

                public StoreUploader(final StoreUploadBundleModel storeUploadBundleData, final Consumer<StoreContainerModel> resultCallback, final Consumer<String> errorCallback) {
                        this.storeUploadBundleData = storeUploadBundleData;
                        this.resultCallback = resultCallback;
                        this.errorCallback = errorCallback;
                }

                public void CreateStore(){
                        Operation1_UploadStorePhotos();
                }

                void Operation1_UploadStorePhotos(){

                        final int totalPhotosToUploadCount = storeUploadBundleData.StorePhotoLocalUris.size();
                        Log.d(TAG,  "UploadStorePhotos localPhotos : " +totalPhotosToUploadCount );
                        Log.d(TAG,  "UploadStorePhotos databaseReference : " + newStoreDatabaseReference.getKey());
                        
                        final ArrayList<String> storePhotoUrls = new ArrayList<>();
                        int index = 0;
                        for (String photoUriString : storeUploadBundleData.StorePhotoLocalUris) {
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
                                                                storeUploadBundleData.storeTransferBaseModel.StorePhotoUrls.add(uri.toString());
                                                                storePhotoUrls.add(
                                                                        uri.toString());
                                                                Log.d(TAG,
                                                                        "OnComplete uri : " + uri.toString());
                                                                if (totalPhotosToUploadCount == storePhotoUrls.size()) {
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
                                .setValue(storeUploadBundleData.storeTransferBaseModel,
                                        new DatabaseReference.CompletionListener()
                                        {
                                                @Override
                                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                        if (databaseError == null) {
                                                                Log.d(TAG, "CreateStore, onComplete");
//                                                                storeUploadBundleData.storeTransferBaseModel.Id = databaseReference.getKey();
                                                                try {
                                                                        // retrieve Created Data
                                                                        //// FIXME:
//                                                                        resultCallback.accept(null);
                                                                        GetStore(
                                                                                newStoreDatabaseReferenceKey,
                                                                                new Consumer<StoreContainerModel>()
                                                                                {
                                                                                        @Override
                                                                                        public void accept(StoreContainerModel storeDownloadModel) throws Exception {
                                                                                                resultCallback.accept(storeDownloadModel);
                                                                                        }
                                                                                },
                                                                                new Consumer<String>()
                                                                                {
                                                                                        @Override
                                                                                        public void accept(String s) throws Exception {
                                                                                                errorCallback.accept(s);
                                                                                        }
                                                                                });
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
        
        
        public static List<StoreContainerModel> storeContainerDataList = new ArrayList<>();
        public static void GetAllStores(final Consumer<List<StoreContainerModel>> resultCallback, final Consumer<String> errorCallback) {
                Log.d(TAG, "GetAllStores");

                storesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
                {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                List<StoreContainerModel> allStores = new ArrayList<StoreContainerModel>();
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        Log.d(TAG, "child:: " + child);
                                        StoreContainerModel value = child.getValue(StoreContainerModel.class);
                                        value.Id = child.getKey();
                                        allStores.add(value);
                                }
                                try {
                                        storeContainerDataList = allStores;
                                        resultCallback.accept(storeContainerDataList);
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
        
        public static void GetStore(final String storeId, final Consumer<StoreContainerModel> resultCallback, final Consumer<String> errorCallback) {
                Log.d(TAG, "GetStore");

                storesDatabaseReference.child(storeId)
                        .addListenerForSingleValueEvent(new ValueEventListener()
                        {
                                @Override
                                public void onDataChange(DataSnapshot snapshot) {
                                        StoreContainerModel value = snapshot.getValue(StoreContainerModel.class);
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
                        new Consumer<List<StoreContainerModel>>()
                        {
                                @Override
                                public void accept(List<StoreContainerModel> storeModels) throws Exception {
                                        for (StoreDownloadModel storeModel : storeModels) {
                                                Log.d(TAG, "\n\n\n\n");
                                                Log.d(TAG, storeModel.StoreName);
                                                Log.d(TAG, storeModel.getMainStorePhotoUrl());
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

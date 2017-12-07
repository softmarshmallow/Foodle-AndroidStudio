package com.softmarshmallow.foodle.Services;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.softmarshmallow.foodle.Models.Menus.MenuModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


public class MenuService
{
        
        private static final String TAG = "MenuService";
        private static final String MenusKey = "Menus";
        private static final String MenusIdsKey = "MenusIds";
        
        static DatabaseReference menusDatabaseReference = ApiController.database.getReference(
                MenusKey);
        
        public static StorageReference menusStorageReference = ApiController.storage.getReference(
                MenusKey);


        public static class MenuCreator
        {

                private MenuModel menuDataToCreate;
                private String baseStoreId;
                private Consumer<MenuModel> resultCallback;
                private Consumer<String> errorCallback;


                public MenuCreator(final MenuModel menuDataToCreate, final String baseStoreId, final Consumer<MenuModel> resultCallback, final Consumer<String> errorCallback){

                        this.menuDataToCreate = menuDataToCreate;
                        this.baseStoreId = baseStoreId;
                        this.resultCallback = resultCallback;
                        this.errorCallback = errorCallback;
                }


                final DatabaseReference newMenuDatabaseReference = menusDatabaseReference.push();
                String newMenuId = newMenuDatabaseReference.getKey();

                public void CreateMenu(){
                        Operation1_UploadMenuPhoto();
                }

                void Operation1_UploadMenuPhoto(){
                        // upload photo with created menu id
                        Log.d(TAG, "Operation1_UploadMenuPhoto, Started");
                        UploadTask uploadTask = menusStorageReference.child(newMenuId)
                                .child("MenuPhoto")
                                .putFile(Uri.parse(menuDataToCreate.MenuMainLocalPhotoUri));
                        uploadTask.addOnCompleteListener(
                                new OnCompleteListener<UploadTask.TaskSnapshot>()
                                {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                        Uri downloadUri = task.getResult()
                                                                .getDownloadUrl();
                                                        // set download url
                                                        menuDataToCreate.MenuMainPhotoUrl = downloadUri.toString();
                                                        Log.d(TAG,
                                                                "Operation1_UploadMenuPhoto, OnComplete uri : " + downloadUri.toString());
                                                        Operation2_UploadMenuData_MenuPhotoUrlIncluded();
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
                }


                void Operation2_UploadMenuData_MenuPhotoUrlIncluded(){
                        newMenuDatabaseReference.setValue(menuDataToCreate,
                                new DatabaseReference.CompletionListener()
                                {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                                if (databaseError == null) {
                                                        Log.d(TAG, "UploadMenu complete, Id : "+ newMenuDatabaseReference.getKey());
                                                        Operation3_Retrieve_Created_Data();
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


                void Operation3_Retrieve_Created_Data(){
                        GetMenu(newMenuDatabaseReference.getKey(),
                                new Consumer<MenuModel>()
                                {
                                        @Override
                                        public void accept(MenuModel menuModel) throws Exception {
                                                Log.d(TAG,
                                                        "UploadMenu Retrieving CreatedMenu Id : " + menuModel.Id);

                                                // Link to Store
                                                Log.d(TAG, "BaseStoreId : " + baseStoreId);
                                                Log.d(TAG,
                                                        "storesDatabaseReference == null : " + (StoreService.storesDatabaseReference == null));
                                                Log.d(TAG,
                                                        "newMenuDatabaseReference.getKey() : " + newMenuDatabaseReference.getKey());
                                                StoreService.storesDatabaseReference.child(
                                                        baseStoreId)
                                                        .child(
                                                                MenusIdsKey)
                                                        .push()
                                                        .setValue(
                                                                newMenuDatabaseReference.getKey());

                                                resultCallback.accept(
                                                        menuModel);
                                        }
                                },
                                new Consumer<String>()
                                {
                                        @Override
                                        public void accept(String databaseError) throws Exception {
                                                errorCallback.accept(
                                                        databaseError);
                                        }
                                });
                }
        }

//V2
        //
        //
        //


        
        public static void UpdateMenu(MenuModel menuDataToUpdate, String menuId) throws Exception {
// TODO: 24/08/2017
                throw new Exception();
                
        }
        
        
        
        
        
        public static void GetAllMenu(final Consumer<List<MenuModel>> resultCallback, final Consumer<String> errorCallback){
                Log.d(TAG, "GetAllStores");
        
                menusDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener()
                {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                List<MenuModel> allStores = new ArrayList<MenuModel>();
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        MenuModel value = child.getValue(MenuModel.class);
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
        
        
        
        
        
        public static void GetMenu(String menuID, final Consumer<MenuModel> resultCallback, final Consumer<String> errorCallback) {
                menusDatabaseReference.child(menuID)
                        .addListenerForSingleValueEvent(
                                new ValueEventListener()
                                {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                MenuModel retrievedMenuData = dataSnapshot.getValue(
                                                        MenuModel.class);
                                                try {
                                                        resultCallback.accept(retrievedMenuData);
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



        public static void TEST(){

        }
        
}

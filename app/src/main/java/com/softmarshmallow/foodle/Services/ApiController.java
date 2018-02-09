package com.softmarshmallow.foodle.Services;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.softmarshmallow.foodle.FoodleApp;
import com.softmarshmallow.foodle.Views.Test.SeverTest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by UZU on 22/08/2017.
 */

public class ApiController
{
        public static  FirebaseDatabase database = FirebaseDatabase.getInstance();
        public static FirebaseStorage storage = FirebaseStorage.getInstance();

        public static Retrofit retrofit = null;
        public static Retrofit retrofit_amazon = null;
        public static String AmazonUrl = "https://s3.ap-northeast-2.amazonaws.com/foodle-cdn-server/";

        public static Retrofit getRetrofit() {
                if(retrofit == null){
                        retrofit = new Retrofit.Builder()
                                .baseUrl(FoodleApp.URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                }
                return retrofit;
        }
        public static Retrofit getRetrofit_amazon() {

                if(retrofit_amazon == null){
                        retrofit_amazon = new Retrofit.Builder()
                                .baseUrl("https://s3.ap-northeast-2.amazonaws.com/foodle-cdn-server/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                }

                return retrofit_amazon;
        }
        // Initialize AWSMobileClient if not initialized upon the app startup.


}

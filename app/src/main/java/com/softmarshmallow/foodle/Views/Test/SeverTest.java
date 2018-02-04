package com.softmarshmallow.foodle.Views.Test;

import com.softmarshmallow.foodle.Views.PhotoSelectorView.PhotofitRepo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by yuntaeil on 2018. 2. 3..
 */
public interface SeverTest {

    @GET("POST /photo")
    Call<PhotofitRepo> getIndex(
            @Query("name") String name
    );

    @GET("POST /photo")
    Call<PhotofitRepo> getItem(
            @QueryMap Map<String, String> option
    );

    @FormUrlEncoded
    @POST("POST /photo")
    Call<PhotofitRepo> getPost(
            @Field("name") String name
    );

}


package com.softmarshmallow.foodle.Views.Test;

import com.softmarshmallow.foodle.Models.StoreUploadRequestModel;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yuntaeil on 2018. 2. 3..
 */
public interface SeverTest {


    @GET("/photo")
    Call<File> getIndex(
            @Query("name") String name
    );

    @GET("/photo")
    Call<String[]> getImageLink(
    );

    @GET("{id}/{filename}")
    Call<File> getImageFile(
            @Path("filename") String filename,
            @Path("id") int id
    );

    @POST("/photo")
    Call<File[]> setImage(
            @Body File images[]
    );

    @POST("/apply/store")
    Call<StoreUploadRequestModel> sendRegistrationApplication(
        @Body StoreUploadRequestModel Data
    );
}


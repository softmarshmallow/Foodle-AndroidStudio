package com.softmarshmallow.foodle.Models.RetroFit;

import com.softmarshmallow.foodle.Models.StoreUploadRequestModel;

import java.io.File;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Silvia on 22/02/2018.
 */

public interface RetrofitAPI {


    @Multipart
    @POST("/account/register")
    Call<String> SignUp(
            @Part String username,
            @Part String email,
            @Part String password

    );
    @Multipart
    @POST("/api-token-auth")
    Call<String> TokenAuth(
            @Part String email,
            @Part String password
    );
    @POST("/api-token-refresh")
    Call<String> TokenRefresh(
            @Body String token
    );
    @POST("/api-token-verify")
    Call<String> TokenVerify(
            @Body String token
    );
}

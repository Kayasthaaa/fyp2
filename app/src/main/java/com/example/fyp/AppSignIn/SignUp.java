package com.example.fyp.AppSignIn;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface SignUp {

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> createUser(
        //    @Header("Content-Type")
            @Field("username") String username,
            @Field("password") String password


            );

}

package com.example.fyp.Profiles.userProfileDisplay.ProfilePatch;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProfilePatch {

    static Retrofit getRetro(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://10.0.2.2:8000/profile/")
                .build();

        return retrofit;

    }

    public static ProfilePatch profilePatch(){

        ProfilePatch profilePatch = getRetro().create(ProfilePatch.class);
        return profilePatch;
    }

}

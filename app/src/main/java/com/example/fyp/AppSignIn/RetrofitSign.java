package com.example.fyp.AppSignIn;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSign {

    private static final String BASE_URL = "http://10.0.2.2:8000/api/";
    private static RetrofitSign mInstance;
    private Retrofit retrofit;

    private RetrofitSign(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitSign getInstance(){
        if (mInstance == null){
            mInstance = new RetrofitSign();
        }
        return mInstance;
    }

    public SignUp getSignUp(){
        return retrofit.create(SignUp.class);
    }

}

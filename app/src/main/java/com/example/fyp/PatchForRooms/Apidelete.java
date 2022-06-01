package com.example.fyp.PatchForRooms;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apidelete {

    static Retrofit deleteRetro(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://10.0.2.2:8000/api/")
                .build();

        return retrofit;

    }

    public static Roomdelete roomdelete(){

        Roomdelete roomdelete = deleteRetro().create(Roomdelete.class);
                return roomdelete;

    }


}

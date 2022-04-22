package com.example.fyp.UserProfiles;

import com.example.fyp.UserProfiles.UserProfResponse;
import com.example.fyp.UserProfiles.UserProfRquest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface UserPost {

    @POST("profile")
    Call<UserProfResponse> postProfile (/*@HeaderMap Map<String, String> Token, */@Body UserProfRquest userProfRquest);



}

package com.example.fyp.Profiles.userProfileDisplay;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

public interface PlaceHolderApi {

    @GET("userprofile")
    Call<List<profileDetails>> getDetails(@HeaderMap Map<String, String> token);

}

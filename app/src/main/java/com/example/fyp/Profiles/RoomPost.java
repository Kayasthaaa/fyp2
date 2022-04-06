package com.example.fyp.Profiles;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RoomPost {

    @POST("rooms")
    Call<RoomPostResponse> postRooms (@HeaderMap Map<String, String> token, @Body RoomPostRequest loginRequest);



  // Call<RoomPostResponse> postRooms(Map<String, String> params, RoomPostRequest roomPostRequest);
}

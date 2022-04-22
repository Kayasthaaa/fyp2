package com.example.fyp.Profiles;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RoomPost {
    @POST("rooms")
    Call<RoomPostResponse> postRooms(@HeaderMap Map<String, String> token, /*@Part MultipartBody.Part part, RequestBody imagedata, @Part("imagedata")*/ @Body RoomPostRequest roomPostRequest);


/*

    @Multipart
    @POST("rooms")
    Call<RoomPostResponse> postRooms(
            @HeaderMap Map<String, String> token,
            @PartMap Map<String, RequestBody> data,
            @Part MultipartBody.Part photo
    );
*/

}

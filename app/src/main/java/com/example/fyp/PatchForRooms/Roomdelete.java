package com.example.fyp.PatchForRooms;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface Roomdelete {

    @DELETE("myrooms/{id}")
    Call<RoomPatchResponse> deleteRoom(@HeaderMap Map<String, String> token, @Path("id") int id);

}

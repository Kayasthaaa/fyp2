package com.example.fyp.PatchForRooms;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface RoomPatch {

    @PATCH("myrooms/{id}")
    Call<RoomPatchResponse> patchRooms(@HeaderMap Map<String, String> token,@Path("id") int id, @Body RoomPatchRequest roomPatchRequest);
}

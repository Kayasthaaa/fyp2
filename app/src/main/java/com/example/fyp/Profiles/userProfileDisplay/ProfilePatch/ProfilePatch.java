package com.example.fyp.Profiles.userProfileDisplay.ProfilePatch;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ProfilePatch {

    @PATCH("profile/{id}")
    Call<ProfilePatchResponse> ProfilePatch(@HeaderMap Map<String, String> token, @Path("id") int id, @Body ProfilePatchRequest patchRequest);

}

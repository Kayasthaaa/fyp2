package com.example.fyp.AppLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Login {


    @POST("login")
    Call<LoginResponse> userLogin (@Body LoginRequest loginRequest);


}


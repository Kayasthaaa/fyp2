package com.example.fyp.RecyclerView.Report;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReportUser {

    @POST("{id}/report")
    Call<ReportResponse> ReportResponse(@HeaderMap Map<String, String> token, @Path("id") int id, @Body ReportRequest reportRequest);

}

package com.uyenpham.censusapplication.service;

import com.uyenpham.censusapplication.models.SyncDTO;
import com.uyenpham.censusapplication.models.family.FamilyResponse;
import com.uyenpham.censusapplication.models.family.SingleFamilyResponse;
import com.uyenpham.censusapplication.models.locality.LocalityResponse;
import com.uyenpham.censusapplication.models.user.LoginDTO;
import com.uyenpham.censusapplication.models.user.ResponseLoginDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    //login
    @POST("/login")
    Call<ResponseLoginDTO> login(
            @Body LoginDTO loginDTO
            );

    //get list locality
    @GET("/bangkediaban")
    Call<LocalityResponse> getListLocality();

    //get list family
    @GET("/bangkeho")
    Call<FamilyResponse> getListFamily(
      @Query("page")int page,
      @Query("items")int item,
      @Query("IDDB") String idLocality
    );

    //get info family
    @POST("/dieutra")
    Call<SingleFamilyResponse> syncNew(
            @Body SyncDTO syncDTO
    );
}

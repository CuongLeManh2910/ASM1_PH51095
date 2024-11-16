package com.example.asm1_and103_md19304_ph51095;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    String DOMAIN = "http://192.168.88.148:3000/";
    @GET("/api/list")
    Call<List<PhoneModel>> getPhones();

    @POST("/api/add_phone")
    Call<List<PhoneModel>> addPhone(@Body PhoneModel dt);

    @PUT("/api/update_phone")
    Call<List<PhoneModel>> updateCar(@Body PhoneModel dt);

    @DELETE("/api/xoa_dt/{id}")
    Call<List<PhoneModel>> deletePhone(@Path("id") String id);
}
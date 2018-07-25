package com.treebricks.dokuter.api;

import com.treebricks.dokuter.models.Patient;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("/api/patients")
    @FormUrlEncoded
    Call<Patient> savePatient(@FieldMap Map<String, String> fields);

    @GET("/api/patient/{uid}")
    Call<Patient> getPatient(@Path("uid") String uid);
}

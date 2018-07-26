package com.treebricks.dokuter.api;

import com.treebricks.dokuter.models.Patient;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionService {
    @POST("/api/questions")
    @FormUrlEncoded
    Call<String> saveQuestion(@FieldMap Map<String, String> fields);

    @GET("/api/questions/{uid}")
    Call<Patient> getQuestions(@Path("id") String uid);

    @GET("/api/question/{id}")
    Call<Patient> getSingleQuestion(@Path("id") String id);
}

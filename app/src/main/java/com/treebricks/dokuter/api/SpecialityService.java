package com.treebricks.dokuter.api;

import com.treebricks.dokuter.models.Speciality;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpecialityService {
    @GET("/api/specialities")
    Call<List<Speciality>> getSpeciality();
}

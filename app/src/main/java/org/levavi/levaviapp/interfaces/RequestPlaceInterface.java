package org.levavi.levaviapp.interfaces;

import org.levavi.levaviapp.pojos.GooglePredictionData;
import org.levavi.levaviapp.pojos.TimeData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

//interface for retrofit

public interface RequestPlaceInterface {

    @GET("json?")
    Call<GooglePredictionData> getJSON(@Query("query") String query, @Query("location") String location
    ,@Query("radius") String radius ,@Query("language") String language, @Query("key") String key);
}
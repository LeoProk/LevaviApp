package org.levavi.levaviapp.interfaces;

import org.levavi.levaviapp.main.TimeData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

//interface for retrofit

public interface RequestPlaceInterface {
    @GET("query={query}&location={latitude},{longitude}&radius=5000&key=AIzaSyD2SJMgrrCuhXx9LbLXfnyqdWbvN28FkKc")
    Call<TimeData> getJSON(@Path("query") String query, @Path("latitude") String latitude,
                           @Path("longitude") String longitude);
}
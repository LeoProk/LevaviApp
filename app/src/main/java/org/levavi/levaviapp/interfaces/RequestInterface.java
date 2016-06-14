package org.levavi.levaviapp.interfaces;

import org.levavi.levaviapp.main.TimeData;

import retrofit2.Call;
import retrofit2.http.GET;

//interface for retrofit

public interface RequestInterface {
    @GET("/?zone=Asia/Jerusalem&format=json&key=THMPIQOI1JTT")
    Call<TimeData> getJSON();
}
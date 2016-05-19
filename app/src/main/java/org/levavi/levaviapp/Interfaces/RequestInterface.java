package org.levavi.levaviapp.Interfaces;

import org.levavi.levaviapp.AppSpecifics.TimeData;

import retrofit2.Call;
import retrofit2.http.GET;

//interface for retrofit

public interface RequestInterface {
    @GET("/?zone=Asia/Jerusalem&format=json&key=THMPIQOI1JTT")
    Call<TimeData> getJSON();
}
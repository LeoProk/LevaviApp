package org.levavi.levaviapp.Interfaces;

import org.levavi.levaviapp.AppSpecifics.NewItem;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Leo on 12/16/2015.
 */
public interface PostItem {

    @POST("/items")
    Call<NewItem> createTask(@Body NewItem task);
}

package org.levavi.levaviapp.Interfaces;

import org.levavi.levaviapp.AppSpecifics.NewItem;

import java.util.List;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * get json be item name
 */
public interface GetItem {
    @GET("/items/{item}")
    List<NewItem> itemsList(@Path("item") String itemName);
}

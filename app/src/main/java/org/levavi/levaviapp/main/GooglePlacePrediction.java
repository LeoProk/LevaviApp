package org.levavi.levaviapp.main;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.interfaces.RequestPlaceInterface;
import org.levavi.levaviapp.interfaces.RequestTimeInterface;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * this class send info to google using rest and get json respond to be
 * displayed inside autocompletetextview
 */
public class GooglePlacePrediction implements FactoryInterface{

    @Override
    public Object doTask() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestPlaceInterface request = retrofit.create(RequestPlaceInterface.class);
        Call<TimeData> call = request.getJSON();
        return null;
    }

}

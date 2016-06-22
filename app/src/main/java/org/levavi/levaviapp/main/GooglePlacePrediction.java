package org.levavi.levaviapp.main;

import android.util.Log;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.interfaces.RequestPlaceInterface;
import org.levavi.levaviapp.pojos.GooglePredictionData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * this class send info to google using rest and get json respond to be
 * displayed inside autocompletetextview
 */
final class GooglePlacePrediction implements FactoryInterface{

    private String mQuery,mLatitude,mLongitude;

    public GooglePlacePrediction(String query,String latitude,String longitude){
        mQuery = query;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    @Override
    public Object doTask() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/json?")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestPlaceInterface request = retrofit.create(RequestPlaceInterface.class);
        Call<GooglePredictionData> call = request.getJSON(mQuery,mLatitude,mLongitude);
        call.enqueue(new Callback<GooglePredictionData>() {
            @Override
            public void onResponse(Call<GooglePredictionData> call, Response<GooglePredictionData> response) {
                Log.e("adress",response.body().getName());
            }

            @Override
            public void onFailure(Call<GooglePredictionData> call, Throwable t) {

            }
        });
        return null;
    }

}

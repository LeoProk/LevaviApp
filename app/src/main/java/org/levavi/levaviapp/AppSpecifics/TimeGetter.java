package org.levavi.levaviapp.AppSpecifics;

import android.text.format.DateFormat;

import org.levavi.levaviapp.Interfaces.FactoryInterface;
import org.levavi.levaviapp.Interfaces.RequestInterface;

import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * get the current time in string format from json
 */

public class TimeGetter implements FactoryInterface {

    private String mCurrentDate;

    @Override
    public Object doTask() {
        mCurrentDate = null;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.timezonedb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<TimeData> call = request.getJSON();
        call.enqueue(new Callback<TimeData>() {
            @Override
            public void onResponse(Call<TimeData> call, Response<TimeData> response) {
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(response.body().getTimestamp());
                mCurrentDate = DateFormat.format("dd-M-yyyy hh:mm", cal).toString();
            }

            @Override
            public void onFailure(Call<TimeData> call, Throwable t) {

            }
        });
        return mCurrentDate;
    }
}

package org.levavi.levaviapp.main;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.interfaces.OnDateCompleted;
import org.levavi.levaviapp.interfaces.RequestTimeInterface;
import org.levavi.levaviapp.pojos.TimeData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * get the current time in string format from json
 */

final class TimeGetter implements FactoryInterface {

    private OnDateCompleted mListener;

    public TimeGetter(OnDateCompleted listener){
        mListener = listener;
    }


    @Override
    public Object doTask() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.timezonedb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestTimeInterface request = retrofit.create(RequestTimeInterface.class);
        Call<TimeData> call = request.getJSON();
        call.enqueue(new Callback<TimeData>() {
            @Override
            public void onResponse(Call<TimeData> call, Response<TimeData> response) {
                //set the timestamp to negative value for ordering
                mListener.onTaskCompleted(String.valueOf(-1 * (response.body().getTimestamp())));
              /*  Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(response.body().getTimestamp());
                mCurrentDate = DateFormat.format("dd-M-yyyy hh:mm", cal).toString();*/
            }

            @Override
            public void onFailure(Call<TimeData> call, Throwable t) {

            }
        });
        return null;
    }
}

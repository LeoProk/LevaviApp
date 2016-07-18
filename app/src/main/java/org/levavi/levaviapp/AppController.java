package org.levavi.levaviapp;

import android.app.Application;
import android.location.Location;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.pojos.FirebaseItem;

/**
 * application class:
 * @mFragmentTag is tag name of current fragment
 * @mSubject is he subject of item clicked in drawer list
 */
public class AppController extends Application
{

    //fragment tag control
    public String mFragmentTag;
    //subject of items list fragment
    public String mSubject;
    //info of currnet clicked item inside item list
    public FirebaseItem mItemInfo;
    //the timestamp of selected date in new item fragment
    public String mTimestamp;
    //the current users location
    public Location mCurrentLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        mSubject = "start";
        mTimestamp = "null";
    }
}

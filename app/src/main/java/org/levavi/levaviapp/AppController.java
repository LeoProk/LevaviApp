package org.levavi.levaviapp;

import android.app.Application;
import android.location.Location;

import org.levavi.levaviapp.pojos.FirebaseItem;

/**
 * application class:
 * @mFragmentTag is tag name of current fragment
 * @mSubject is he subject of item clicked in drawer list
 */
public class AppController extends Application
{

    //fragment tag control
    public String fragmentTag;
    //subject of items list fragment
    public String subject;
    //info of currnet clicked item inside item list
    public FirebaseItem itemInfo;
    //the timestamp of selected date in new item fragment
    public String timestamp;
    //the current users location
    public static Location sCurrentLocation;

    @Override
    public void onCreate() {
        super.onCreate();
        subject = "start";
        timestamp = "null";
    }
}

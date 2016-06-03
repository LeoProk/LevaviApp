package org.levavi.levaviapp;

import android.app.Application;

import com.firebase.client.Firebase;

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

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);;
        mSubject = "start";
    }
}

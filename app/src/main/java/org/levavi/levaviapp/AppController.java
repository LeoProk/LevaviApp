package org.levavi.levaviapp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Leo on 10/13/2015.
 */
public class AppController extends Application {

    //fragment tag control
    public String mFragmentTag;

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this);
    }
}

package org.levavi.levaviapp;

import android.app.Application;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.pojos.ClickedItemInfo;

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
    public ClickedItemInfo mItemInfo;
    //the timestamp of slected date in new item fragment
    public String mTimestamp;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);;
        mSubject = "start";
    }
}

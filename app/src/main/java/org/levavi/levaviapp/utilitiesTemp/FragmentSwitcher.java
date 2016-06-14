package org.levavi.levaviapp.utilitiesTemp;
 
import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.interfacesTemp.FactoryInterface;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Switches between 2 fragments
 */
final class FragmentSwitcher implements FactoryInterface {

    private Context mContext;

    private String mNewFragTag;


    public FragmentSwitcher(Context context, String newFragTag) {

        mNewFragTag = newFragTag;

        mContext = context;
    }

    @Override
    public Object doTask() {
        //gets the application class
        final AppController appController = (AppController) mContext.getApplicationContext();
        final AppCompatActivity activity = (AppCompatActivity) mContext;
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.remove(activity.getFragmentManager().findFragmentByTag(appController.mFragmentTag));
        appController.mFragmentTag = mNewFragTag;
        ft.show(activity.getFragmentManager().findFragmentByTag(mNewFragTag)).commit();


        return null;
    }
}

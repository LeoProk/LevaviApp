package org.levavi.levaviapp.utilities;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.interfaces.FactoryInterface;

/**
 * Removes fagment by tag
 */
final class RemoveFragment implements FactoryInterface {

    private Context mContext;

    public RemoveFragment(Context context){
        mContext = context;
    }

    @Override
    public Object doTask() {
        final AppController appController = (AppController) mContext.getApplicationContext();
        //Fragment fragment = ((AppCompatActivity)mContext).getSupportFragmentManager().findFragmentByTag(appController.mFragmentTag);
        ((AppCompatActivity)mContext).getFragmentManager().popBackStack();
        appController.mFragmentTag = null;
        return null;
    }
}

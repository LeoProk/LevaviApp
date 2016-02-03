package org.levavi.levaviapp.Utilities;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.Interfaces.FactoryInterface;

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
        final AppController appController = (AppController) mContext;
        Fragment fragment = ((AppCompatActivity)mContext).getSupportFragmentManager().findFragmentByTag(appController.mFragmentTag);
        ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        appController.mFragmentTag = null;
        return null;
    }
}

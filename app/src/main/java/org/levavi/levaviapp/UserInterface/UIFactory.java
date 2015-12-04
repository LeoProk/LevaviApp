package org.levavi.levaviapp.UserInterface;

import android.content.Context;
import android.support.v7.widget.Toolbar;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

/**
 * Factory for tollbar and drawer
 */
public class UIFactory {

    //Creates toolbar actionbar must be disabled in manifest
    public static FactoryInterface getToolbar(Context context, Toolbar toolbar) {
        return new CustomToolbar(context, toolbar);
    }


}



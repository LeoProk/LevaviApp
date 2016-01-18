package org.levavi.levaviapp.AppSpecifics;

import android.content.Context;
import android.view.View;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

/**
 * Created by Leo on 1/10/2016.
 */
public class AppFactory {
    public static FactoryInterface titlePopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"title");
    }
}

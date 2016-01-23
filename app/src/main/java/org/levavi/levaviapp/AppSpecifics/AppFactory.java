package org.levavi.levaviapp.AppSpecifics;

import com.google.android.gms.common.api.GoogleApiClient;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

/**
 * Created by Leo on 1/10/2016.
 */
public class AppFactory {
    // show pop up message when title text view is missing
    public static FactoryInterface titlePopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"title");
    }
    //
    public static FactoryInterface signIn(Context context , GoogleApiClient googleApiClient){
        return new GoogleSignIn(context,googleApiClient,0,"signIn",null,null);
    }
    public static FactoryInterface signInResult(int requestCod,Intent data,ListView drawerList){
        return new GoogleSignIn(null,null,requestCod,"result",data,drawerList);
    }
}

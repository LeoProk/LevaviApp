package org.levavi.levaviapp.AppSpecifics;

import com.google.android.gms.common.api.GoogleApiClient;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class hold or the factory methods of the app
 */
public class AppFactory {
    // show pop up message when title text view is missing
    public static FactoryInterface titlePopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"title");
    }
    // show pop up message when address text view is missing
    public static FactoryInterface addressPopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"address");
    }
    // show pop up message when phone text view is missing
    public static FactoryInterface phonePopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"phone");
    }
    // show pop up message when info text view is missing
    public static FactoryInterface infoPopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"info");
    }
    //
    public static FactoryInterface signIn(Context context , GoogleApiClient googleApiClient){
        return new GoogleSignIn(context,googleApiClient,0,"signIn",null);
    }
    public static FactoryInterface signInResult(int requestCod,Intent data){
        return new GoogleSignIn(null,null,requestCod,"result",data);
    }
    // create new fire base entry
    public static FactoryInterface saveFireBase(NewItem newItem){
        return new SaveNewItem(newItem);
    }
    //get data from firebase server
    public static FactoryInterface getFireBase(){
        return new FirebaseGet();
    }
}

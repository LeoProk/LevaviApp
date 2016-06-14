package org.levavi.levaviapp.main;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import android.content.Context;
import android.view.View;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.interfaces.OnDateCompleted;

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
    // show pop up message when subject option is missing
    public static FactoryInterface subjectPopUp(View view ,Context context) {
        return new PopUpGenerator(view,context,"subject");
    }
    // create new fire base entry
    public static FactoryInterface saveFireBase(FirebaseItem newItem){
        return new SaveNewItem(newItem);
    }
    //gets data from firebase server
    public static FactoryInterface getFireBase(){
        return new FirebaseGet();
    }

    //gets current time and date
    public static FactoryInterface getTimeGetter(OnDateCompleted onDateCompleted){return new TimeGetter(onDateCompleted);}

    //gets data from firebase server
    public static FactoryInterface getLogInPopup(View anchorView, Context context,FactoryInterface factoryInterface,
                                                 GoogleSignInOptions gso){
        return new LogInPopup(anchorView,context,factoryInterface,gso);
    }

}

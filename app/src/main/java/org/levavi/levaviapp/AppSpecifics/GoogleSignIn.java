package org.levavi.levaviapp.AppSpecifics;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.levavi.levaviapp.Interfaces.FactoryInterface;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 *this class responsible for google sign in and saving user id to internal storage
 */
final class GoogleSignIn implements FactoryInterface {

    private Context mContext;

    private GoogleApiClient mGoogleApiClient;

    private String mCondition;

    private int mRequestCode;

    private Intent mData;

    private static final int RC_SIGN_IN = 0;

    public GoogleSignIn(Context context, GoogleApiClient googleApiClient, int requestCode, String condition, Intent data){
        mContext =  context;
        mGoogleApiClient = googleApiClient;
        mRequestCode = requestCode;
        mCondition = condition;
        mData = data;
    }

    @Override
    public Object doTask() {
        switch (mCondition){
            // starting the intent with startActivityForResult
            case "signIn":
                signIn();
                break;
            case "result":
                // result starting the intent prompts the user to select a Google account to sign in with.
                checkResult();
                break;
            default:
                break;
        }
        return null;
    }

    //starting the intent with startActivityForResult
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        ((Activity)mContext).startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // check if sign-in succeeded
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            //after successfully signing in save the user id
            GoogleSignInAccount acct = result.getSignInAccount();
            UtilitiesFactory.saveFile(mContext, "user", acct.getId());
        } else {

        }
    }

    // Starting the intent prompts the user to select a Google account to sign in with
    private void checkResult(){
        if (mRequestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(mData);
            handleSignInResult(result);
        }

    }
}

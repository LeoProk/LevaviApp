package org.levavi.levaviapp;

import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.AppSpecifics.DrawerAdapter;
import org.levavi.levaviapp.AppSpecifics.RowItem;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private ActionBarDrawerToggle mDrawerToggle;

    private GoogleApiClient mGoogleApiClient;

    private static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // sets direction to rtl
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        //sets toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        UtilitiesFactory.getToolbar(this, toolbar).doTask();
        //check if the user sign in to the app before
        SignInButton signInButton = null;
        if(((String)UtilitiesFactory.getFile(this,"user").doTask()).isEmpty()) {
            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken("947543842597-6ssol53at50cs7juqhqbb4ed3huaqk4q.apps.googleusercontent.com")
                    .requestEmail()
                    .build();
            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            signInButton = (SignInButton) findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            });
        }
        //create the drawer
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ListView drawerList = (ListView) findViewById(R.id.slider_list);
        Log.e("TRY",(String)UtilitiesFactory.getFile(this,"user").doTask());
        if(((String)UtilitiesFactory.getFile(this,"user").doTask()).length()==0) {
            mDrawerToggle = (ActionBarDrawerToggle) UtilitiesFactory.getDrawer(this, drawerLayout, drawerList, toolbar
                    , signInButton).doTask();
        }else {
            mDrawerToggle = (ActionBarDrawerToggle) UtilitiesFactory.getDrawer(this, drawerLayout, drawerList, toolbar
                    ,null).doTask();
        }
        AppFactory.getFireBase().doTask();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if(requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.e("error",result.getStatus().toString());
            handleSignInResult(result);
        }
    }

    @Override
    public void onBackPressed() {
        final AppController appController = (AppController) getApplicationContext();
        if(appController.mFragmentTag!=null){
            UtilitiesFactory.removeFragment(this).doTask();
        }
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    // check if sign-in succeeded
    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("TAt", "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            //after successfully signing in save the user id
            GoogleSignInAccount acct = result.getSignInAccount();
            UtilitiesFactory.saveFile(this, "user", acct.getId());
            Log.e("TRY",acct.getId());
        } else {
            Log.e("TRY","error");
        }
    }
}

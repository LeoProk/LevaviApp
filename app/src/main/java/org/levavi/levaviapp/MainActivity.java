package org.levavi.levaviapp;

import org.levavi.levaviapp.fragments.ItemInfoFragment;
import org.levavi.levaviapp.fragments.NewItemFragment;
import org.levavi.levaviapp.main.AppFactory;
import org.levavi.levaviapp.fragments.ItemsListFragment;
import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.utilities.UtilitiesFactory;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks
        ,FactoryInterface,LocationListener {

    private ActionBarDrawerToggle mDrawerToggle;

    private GoogleApiClient mGoogleApiClient;

    private GoogleSignInOptions mGso;

    private DrawerLayout mDrawerLayout;

    private PopupWindow mLogInPopup;

    private LocationRequest mLocationRequest;

    private static final int RC_SIGN_IN = 1000;

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
        mGso = null;
        if(((String)UtilitiesFactory.getFile(this,"user").doTask()).isEmpty()) {
            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            mGso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestProfile()
                    .build();
            // Build a GoogleApiClient with access to the Google Sign-In API location API and the
            // options specified by gso.
        }
        buildGoogleApi();
        //create the drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ListView drawerList = (ListView) findViewById(R.id.slider_list);
        mDrawerToggle = (ActionBarDrawerToggle) UtilitiesFactory.getDrawer(this, mDrawerLayout, drawerList, toolbar).doTask();
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
        final AppController appController = (AppController) this.getApplicationContext();
        final MenuItem menuSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuSearch);
        final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                UtilitiesFactory.replaceFragment(MainActivity.this, new ItemsListFragment()
                        , "userSearch", true).doTask();
                appController.subject = query;
                menuSearch.collapseActionView();
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
        //check what the current fragment is and replace the fragment based on it
        NewItemFragment newFragment = (NewItemFragment)getFragmentManager().findFragmentByTag("new");
        if (newFragment != null && newFragment.isVisible()) {
            UtilitiesFactory.removeFragment(this).doTask();
        }else {
            ItemInfoFragment infoFragment = (ItemInfoFragment)getFragmentManager().findFragmentByTag("item_info");
            if(infoFragment!= null && infoFragment.isVisible()) {
                UtilitiesFactory.removeFragment(this).doTask();
            }else {
                super.onBackPressed();
            }
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
            UtilitiesFactory.saveFile(this, "user", acct.getId()).doTask();
            mLogInPopup.dismiss();
            Log.e("TRY",acct.getId());
        } else {
            Log.e("TRY","error");
        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (lastLocation != null) {
            AppController.sCurrentLocation = new Location(lastLocation);
        }
        UtilitiesFactory.addFragment(this,new ItemsListFragment(),"start",true).doTask();
    }
    //check the current location
    private void getCurrentLocation(){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        mGoogleApiClient.disconnect();
    }
    @Override
    public void onConnectionSuspended(int i) {

    }
    //build the google api with place api and location api
    public void buildGoogleApi(){
        if(mGso==null){
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addApi(LocationServices.API)
                    .build();
        }else {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, mGso)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .addApi(LocationServices.API)
                    .build();
        }
    }
    @Override
    public Object doTask() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        return null;
    }
    //pop up with the google log in button
    public void googleLogInPopup(){
        mLogInPopup = (PopupWindow)AppFactory.getLogInPopup(mDrawerLayout,this,MainActivity.this,mGso).doTask();
    }

    //used be the drawer when use click on create new item option in the list. connects to google api again
    public void googleApiConnect(){
        mGoogleApiClient.connect();
    }

    //saves the new location from listener to static location inside applicatin class
    @Override
    public void onLocationChanged(Location location) {
        AppController.sCurrentLocation = new Location(location);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if( mGoogleApiClient != null )
            mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }
}

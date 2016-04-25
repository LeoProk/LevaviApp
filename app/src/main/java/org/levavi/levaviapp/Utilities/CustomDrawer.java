/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.levavi.levaviapp.Utilities;

import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.AppSpecifics.DrawerAdapter;
import org.levavi.levaviapp.AppSpecifics.RowItem;
import org.levavi.levaviapp.Fragments.NewItemFragment;
import org.levavi.levaviapp.Interfaces.FactoryInterface;
import org.levavi.levaviapp.R;

import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;

/**
 * This class contain logic of drawer.
 */

final class CustomDrawer implements FactoryInterface {

    private Context mContext;

    private DrawerLayout mDrawerLayout;

    private ListView mDrawerList;

    private Toolbar mToolbar;

    private SignInButton mSignInButton;

    protected CustomDrawer(Context context, DrawerLayout drawerLayout, ListView drawerList,
                           Toolbar toolbar,SignInButton signInButton) {
        mContext = context;
        mDrawerLayout = drawerLayout;
        mDrawerList = drawerList;
        mToolbar = toolbar;
        mSignInButton = signInButton;
    }

    //class of creating and populating navigation drawer
    @Override
    public ActionBarDrawerToggle doTask() {

        final AppCompatActivity activity = (AppCompatActivity) mContext;
        final ArrayList<RowItem> rowItems = new ArrayList<>();
        //get the arrays from strings
        final String[] menuTitles = mContext.getResources().getStringArray(R.array.titles);
        final TypedArray menuIcons = mContext.getResources().obtainTypedArray(R.array.icons);
        //create the drawer
        ActionBarDrawerToggle drawerToggle = null;
        if(mToolbar!= null) {
            drawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
                    mToolbar, R.string.drawer_open, R.string.drawer_close) {

                //* Called when a drawer has settled in a completely closed state.
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }

                //* Called when a drawer has settled in a completely open state.
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                }
            };
            drawerToggle.syncState();
        }
        //check if user login if not disable add new item option and shows the log in button
        int startingListNum = 0;
        if(mSignInButton != null){
            startingListNum = 2;
            mSignInButton.setVisibility(View.VISIBLE);
        }
        //create the drawer list names and icons
        for (int i = startingListNum; i < menuTitles.length; i++) {
            RowItem items = new RowItem(menuTitles[i], menuIcons.getResourceId(i, -1));
            rowItems.add(items);

        }
        //sets drawer adapter
        DrawerAdapter adapter = new DrawerAdapter(mContext, rowItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new CustomDrawer.SlideitemListener());
        menuIcons.recycle();
        mDrawerLayout.setDrawerListener(drawerToggle);
        return drawerToggle;
    }

    //create fragment based on clicked position
    private void updateDisplay(int position) {
        //empty fragment and tag
        String tag = null;
        Fragment fragment = null;
        if(mSignInButton != null){
            position = position + 2;
        }
        switch (position) {
            case 0:
                    fragment = new NewItemFragment();
                    tag = "new";
                break;
            case 1:
                //fragment = new LoginFragment();
                break;
            default:
                break;
        }
        //witch to new fragment and closes the drawer
        if (fragment != null && (boolean)UtilitiesFactory.checkNetwork(mContext,true).doTask()) {
            UtilitiesFactory.replaceFragment(mContext,fragment,tag,true).doTask();
            mDrawerLayout.closeDrawers();
        }
    }

    // called when one of the items in drawer is clicked
    class SlideitemListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            updateDisplay(position);
        }
    }

}

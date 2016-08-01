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

package org.levavi.levaviapp.utilities;


import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.adapters.DrawerAdapter;
import org.levavi.levaviapp.pojos.RowItem;
import org.levavi.levaviapp.fragments.ItemsListFragment;
import org.levavi.levaviapp.fragments.NewItemFragment;
import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.MainActivity;
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

import java.util.ArrayList;

/**
 * This class contain logic of drawer.
 */

final class CustomDrawer implements FactoryInterface {

    private Context mContext;

    private DrawerLayout mDrawerLayout;

    private ListView mDrawerList;

    private Toolbar mToolbar;

    protected CustomDrawer(Context context, DrawerLayout drawerLayout, ListView drawerList,Toolbar toolbar) {
        mContext = context;
        mDrawerLayout = drawerLayout;
        mDrawerList = drawerList;
        mToolbar = toolbar;
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
        final AppController appController = (AppController) mContext.getApplicationContext();
        //empty fragment and tag
        String tag = "search";
        Fragment fragment = null;
        //create new fragment bassed on click location in drawer list
        // set the search value for item list fragments
        switch (position) {
            case 0:
                //check if the use loged into google if no create log in popup
                if (((String) UtilitiesFactory.getFile(mContext, "user").doTask()).isEmpty()) {
                    MainActivity mainActivity = (MainActivity)mContext;
                    //connects google api
                    mainActivity.googleApiConnect();
                    mainActivity.googleLogInPopup();
                } else{
                fragment = new NewItemFragment();
                tag = "new";
                }
                break;
            case 1:
                appController.subject = "מסעדות";
                fragment = new ItemsListFragment();
                tag = "item1";
                break;
            case 2:
                appController.subject = "אטרקציות ופנאי";
                fragment = new ItemsListFragment();
                tag = "item2";
                break;
            case 3:
                appController.subject = "טיפוח וספא";
                fragment = new ItemsListFragment();
                tag = "item3";
                break;
            case 4:
                appController.subject = "בריאות וכושר";
                fragment = new ItemsListFragment();
                tag = "item4";
                break;
            case 5:
                appController.subject = "אלקטרוניקה ומחשבים";
                fragment = new ItemsListFragment();
                tag = "item5";
                break;
            case 6:
                appController.subject = "לבית ולגן";
                fragment = new ItemsListFragment();
                tag = "6";
                break;
            case 7:
                appController.subject = "תינוקות ילדים וצעצועים";
                fragment = new ItemsListFragment();
                tag = "item7";
                break;
            case 8:
                appController.subject = "ביגוד והנעלה";
                fragment = new ItemsListFragment();
                tag = "item8";
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

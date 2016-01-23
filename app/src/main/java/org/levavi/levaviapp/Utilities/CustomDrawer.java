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

import org.levavi.levaviapp.AppSpecifics.DrawerAdapter;
import org.levavi.levaviapp.Interfaces.FactoryInterface;
import org.levavi.levaviapp.R;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
        //get the arrays from strings
        /*final String[] menutitles = context.getResources().getStringArray(R.array.titles);
        final TypedArray menuIcons = context.getResources().obtainTypedArray(R.array.icons);*/
        //create the drawer
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(activity, mDrawerLayout,
                mToolbar,R.string.drawer_open, R.string.drawer_close) {

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
        };/*
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        for (int i = 0; i < menutitles.length; i++) {
            RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(i, -1));
            rowItems.add(items);

        }
        menuIcons.recycle();*/
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        return drawerToggle;
    }

    //create fragment based on clicked position
    private void updateDisplay(int position) {

    }

    // called when one of the items in drawer is clicked
    class SlideitemListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            updateDisplay(position);
        }
    }

}

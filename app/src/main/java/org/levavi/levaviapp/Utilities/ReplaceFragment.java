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

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.Interfaces.FactoryInterface;
import org.levavi.levaviapp.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

/**
 * Creates new fragment and replace the existing one/
 */
final class ReplaceFragment implements FactoryInterface {


    private Context mContext;

    private Fragment mFragment;

    private String mTag;

    private boolean mAddToBackStack;

    public ReplaceFragment(Context context, Fragment fragment, String tag, boolean addToBackStack) {
        mContext = context;
        mFragment = fragment;
        mTag = tag;
        mAddToBackStack = addToBackStack;

    }

    @Override
    public Object doTask() {
        final AppController appController = (AppController) mContext.getApplicationContext();
        appController.mFragmentTag = mTag;
        if (mAddToBackStack) {
            Fragment fragment = mFragment;
            FragmentManager fragmentManager = ((Activity) mContext)
                    .getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, mTag)
                    .addToBackStack(mTag).commit();

        } else {
            Fragment fragment = mFragment;
            FragmentManager fragmentManager = ((Activity) mContext)
                    .getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment, mTag)
                    .commit();

        }
        return null;
    }
}

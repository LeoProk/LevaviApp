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

package org.levavi.levaviapp.AppSpecifics;
import org.levavi.levaviapp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomItemsAdapter extends BaseExpandableListAdapter {

    private HashMap<String, ArrayList<NewItem>> mListDataChild;

    private Activity mActivity;

    private ArrayList<String> mListDataHeader;

    private LayoutInflater mInflater;

    public CustomItemsAdapter(Activity activity, HashMap<String, ArrayList<NewItem>> listHeaders,
                              ArrayList<String> listDataHeader) {
        mActivity = activity;
        mListDataChild = listHeaders;
        mListDataHeader = listDataHeader;
    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListDataChild.get(mListDataHeader.get(groupPosition))
                .get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    //expandable list view group handling
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }
        TextView listHeader = (TextView) convertView.findViewById(R.id.listHeader);
        listHeader.setText(headerTitle);
        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    //expandable list view child handling
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // getting task data for the row
        final NewItem task = (NewItem) getChild(groupPosition, childPosition);

        // sets layout
        if (mInflater == null) {
            mInflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        convertView = mInflater.inflate(R.layout.item_row, null);
        //gets the application class
        return convertView;
    }

    // assigning text views and create array list of text views
    private ArrayList<TextView> createTextViews(View convertView) {
        ArrayList<TextView> textViews = new ArrayList<>();
        TextView text1 = (TextView) convertView.findViewById(R.id.text1);
        textViews.add(text1);
        TextView text2 = (TextView) convertView.findViewById(R.id.text2);
        textViews.add(text2);
        TextView text3 = (TextView) convertView.findViewById(R.id.text3);
        textViews.add(text3);
        TextView text4 = (TextView) convertView.findViewById(R.id.text4);
        textViews.add(text4);
        TextView text5 = (TextView) convertView.findViewById(R.id.text5);
        textViews.add(text5);
        TextView text6 = (TextView) convertView.findViewById(R.id.text6);
        textViews.add(text6);
        TextView text7 = (TextView) convertView.findViewById(R.id.text7);
        textViews.add(text7);
        TextView text8 = (TextView) convertView.findViewById(R.id.text8);
        textViews.add(text8);
        TextView text9 = (TextView) convertView.findViewById(R.id.text9);
        textViews.add(text9);
        TextView text10 = (TextView) convertView.findViewById(R.id.text10);
        textViews.add(text10);
        return textViews;
    }

}
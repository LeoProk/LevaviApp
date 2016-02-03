package org.levavi.levaviapp.AppSpecifics;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class saves object from hash map on firebase server
 */
final class SaveNewItem implements FactoryInterface {

    private HashMap<String,String> mItem;

    private ArrayList<NewItem> mNewItems;

    public SaveNewItem(HashMap<String,String> item,ArrayList<NewItem> newItems) {
        mItem = item;
        mNewItems = newItems;
    }

    @Override
    public Object doTask() {
        Firebase myFirebaseRef = new Firebase("https://luminous-fire-5859.firebaseio.com/");
        Firebase postRef = myFirebaseRef.child("items");
        Firebase newPostRef = postRef.push();
        newPostRef.setValue(mItem);
        for (int i = 0; i <mNewItems.size(); i++) {
            newPostRef.child("item"+Integer.toString(i)).setValue(mNewItems.get(i));
        }
        return null;
    }
}

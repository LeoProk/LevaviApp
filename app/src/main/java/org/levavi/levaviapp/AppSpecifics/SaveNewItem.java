package org.levavi.levaviapp.AppSpecifics;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class saves object from hash map on firebase server
 */
final class SaveNewItem implements FactoryInterface {

    private HashMap<String,String> mItemInfo;
    private HashMap<String,NewItem> mNewItems;

    public SaveNewItem(HashMap<String,String> item,HashMap<String,NewItem> newItems) {
        mItemInfo = item;
        mNewItems = newItems;
    }

    @Override
    public Object doTask() {
        Firebase myFirebaseRef = new Firebase("https://luminous-fire-5859.firebaseio.com/");
        Firebase postRef = myFirebaseRef.child("input");
        Firebase newPostRef = postRef.push();
        newPostRef.setValue(mItemInfo);
        newPostRef.child("items").setValue(mNewItems);
        return null;
    }
}

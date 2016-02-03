package org.levavi.levaviapp.AppSpecifics;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

import java.util.HashMap;

/**
 * this class saves object from hash map on firebase server
 */
final class SaveNewItem implements FactoryInterface {

    private HashMap<String,String> mItem;

    public SaveNewItem(HashMap<String,String> item){
        mItem = item;
    }

    @Override
    public Object doTask() {
        Firebase myFirebaseRef = new Firebase("https://luminous-fire-5859.firebaseio.com/");
        myFirebaseRef.child("items").push().setValue(mItem);
        return null;
    }
}

package org.levavi.levaviapp.AppSpecifics;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;


import org.levavi.levaviapp.Interfaces.FactoryInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * gets the data from firebase
 */
final class FirebaseGet implements FactoryInterface {
    @Override
    public Object doTask() {
        Firebase ref = new Firebase("https://luminous-fire-5859.firebaseio.com/input");
        Query queryRef = ref.orderByChild("date");
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Map<String, Object> value = (Map<String, Object>)snapshot.getValue();
               // GenericTypeIndicator<HashMap<String, NewItem>> t = new GenericTypeIndicator<HashMap<String, NewItem>>() {};
               // HashMap<String,NewItem> yays  = snapshot.child("items").getValue(t);
                //Log.e("YAy",yays.get("item0").getName());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return null;
    }
}

package org.levavi.levaviapp.AppSpecifics;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.levavi.levaviapp.Interfaces.FactoryInterface;

/**
 * gets the data from firebase
 */
final class FirebaseGet implements FactoryInterface {
    @Override
    public Object doTask() {
        Firebase ref = new Firebase("https://luminous-fire-5859.firebaseio.com/items");
        // Attach an listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e("The read failed: ", firebaseError.getMessage());
            }
        });
        return null;
    }
}

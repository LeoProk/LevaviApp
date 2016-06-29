package org.levavi.levaviapp.main;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.pojos.FirebaseItem;

/**
 * this class saves object from hash map on firebase server
 */
final class SaveNewItem implements FactoryInterface {

    private FirebaseItem mFirebaseItem;

    public SaveNewItem(FirebaseItem firebaseItem) {
        mFirebaseItem = firebaseItem;
    }

    @Override
    public Object doTask() {
        Firebase myFirebaseRef = new Firebase("https://luminous-fire-5859.firebaseio.com/");
        Firebase postRef = myFirebaseRef.child("input");
        Firebase newPostRef = postRef.push();
        newPostRef.setValue(mFirebaseItem);
        return null;
    }
}

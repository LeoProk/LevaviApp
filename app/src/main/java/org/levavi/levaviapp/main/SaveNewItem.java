package org.levavi.levaviapp.main;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        final DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(("https://levavi-1185.firebaseio.com/"));
        final DatabaseReference postRef = ref.child("input");
        final DatabaseReference newPostRef = postRef.push();
        newPostRef.setValue(mFirebaseItem);
        return null;
    }
}

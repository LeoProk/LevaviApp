package org.levavi.levaviapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.levavi.levaviapp.AppSpecifics.FirebaseItem;
import org.levavi.levaviapp.R;

import java.util.ArrayList;

/**
 * this class show all the latest subject added on app start
 */
public class StartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.start_fragment, container, false);
        final ListView itemsList = (ListView) rootView.findViewById(R.id.itemsList);
        final ArrayList<FirebaseItem> firebaseItems = new ArrayList<>();
        // Get a reference to our posts
        final Firebase ref = new Firebase("https://luminous-fire-5859.firebaseio.com/input");
        // Attach an listener to read the data at our posts reference
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
        return rootView;
    }
}

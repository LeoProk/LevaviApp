package org.levavi.levaviapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.adapters.CustomListAdapter;
import org.levavi.levaviapp.pojos.FirebaseItem;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.utilities.UtilitiesFactory;

import java.util.ArrayList;
import java.util.Collections;

/**
 * class that displayer items bassed on subject input
 */
public class ItemsListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.start_fragment, container, false);
        final ListView itemsList = (ListView) rootView.findViewById(R.id.itemsList);
        final ArrayList<FirebaseItem> firebaseItems = new ArrayList<>();
        final AppController appController = (AppController) getActivity().getApplicationContext();
        final CustomListAdapter listAdapter = new CustomListAdapter(getActivity(),firebaseItems);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                appController.itemInfo = firebaseItems.get(i);
                UtilitiesFactory.addFragment(getActivity(),new ItemInfoFragment(),"item_info",true).doTask();
            }
        });
        // Get a reference to firebase database
        final DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(("https://levavi-1185.firebaseio.com/"));
        //get user clicked subject from application class
        final String fragTag = appController.fragmentTag;
        if(fragTag.equals("start")){
            // Attach an listener to read the data at our posts reference
            Query queryOrder = ref.orderByChild("timeStamp");
            queryOrder.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                    }
                    Collections.sort(firebaseItems);
                    itemsList.setAdapter(listAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else {
            if(fragTag.equals("userSearch")){
                Query queryValue = ref.orderByChild("timeStamp");
                queryValue.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            FirebaseItem firebaseItem = postSnapshot.getValue(FirebaseItem.class);
                            if(firebaseItem.getText().contains(appController.subject)||firebaseItem.getTitle().contains(appController.subject)) {
                                firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                            }
                        }
                        Collections.sort(firebaseItems);
                        itemsList.setAdapter(listAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }


                });
            }else {
                Query queryValue = ref.orderByChild("subject").equalTo(appController.subject);
                queryValue.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                        }
                        Collections.sort(firebaseItems);
                        itemsList.setAdapter(listAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        return rootView;
    }
}

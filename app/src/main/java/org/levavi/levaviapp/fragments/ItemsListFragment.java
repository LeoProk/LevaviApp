package org.levavi.levaviapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.adapters.CustomListAdapter;
import org.levavi.levaviapp.pojos.FirebaseItem;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.utilities.UtilitiesFactory;

import java.util.ArrayList;

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
        itemsList.setAdapter(listAdapter);
        itemsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                appController.itemInfo = firebaseItems.get(i);
                UtilitiesFactory.replaceFragment(getActivity(),new ItemInfoFragment(),"item_info",false).doTask();
            }
        });
        // Get a reference to firebase database
        final Firebase ref = new Firebase("https://luminous-fire-5859.firebaseio.com/input");
        //get user clicked subject from application class
        final String fragTag = appController.subject;
        if(fragTag.equals("start")){
            // Attach an listener to read the data at our posts reference
            Query queryOrder = ref.orderByChild("timeStamp");
            queryOrder.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                    }
                    listAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        }else {
            if(fragTag.equals("search")){
                Query queryValue = ref.orderByChild("text").startAt();
                queryValue.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                        }
                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }else {
                Query queryValue = ref.orderByChild("subject").equalTo(appController.subject);
                queryValue.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            firebaseItems.add(postSnapshot.getValue(FirebaseItem.class));
                        }
                        listAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }
        }

        return rootView;
    }
}

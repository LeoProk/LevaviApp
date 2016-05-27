package org.levavi.levaviapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.firebase.client.Firebase;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.AppSpecifics.FirebaseItem;
import org.levavi.levaviapp.Interfaces.OnDateCompleted;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * First fragment on app run that show the latest items added
 */
public class NewItemFragment extends Fragment implements OnDateCompleted {

    private EditText mTitle,mAddress,mPhone,mText;

    private Spinner mSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (EditText) rootView.findViewById(R.id.address);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
        mText = (EditText) rootView.findViewById(R.id.text);
        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("yay", "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("err", "An error occurred: " + status);
            }
        });
        //array of spinner items
        final String[] menuTitles = getActivity().getResources().getStringArray(R.array.spinner_titles);
        //creates and sets spinner adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, menuTitles);
        mSpinner.setAdapter(adapter);
        //on submit button click
        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if all field are full if yes create new parse item and goes back to last window
                if (mTitle.getText().toString().isEmpty()) {
                    AppFactory.titlePopUp(mPhone, getActivity()).doTask();
                } else {
                    if (mAddress.getText().toString().isEmpty()) {
                        AppFactory.addressPopUp(mPhone, getActivity()).doTask();
                    } else {

                        if (mPhone.getText().toString().isEmpty()) {
                            AppFactory.phonePopUp(mPhone, getActivity()).doTask();

                        } else {
                            if(mSpinner.getSelectedItem().toString().equals("")){
                                AppFactory.subjectPopUp(mPhone, getActivity()).doTask();
                            }else {
                                AppFactory.getTimeGetter(NewItemFragment.this).doTask();
                            }
                        }
                    }
                }
            }
        });
        return rootView;
    }

    @Override
    public void onTaskCompleted(String date) {
        //change fragment
        UtilitiesFactory.removeFragment(getActivity()).doTask();
        String[] fullTime =date.split(" ");
        //save to firebase after creating hashmap of the new items array list
        FirebaseItem itemForSave = new FirebaseItem(mSpinner.getSelectedItem().toString(),fullTime[0],mText.getText().toString(),mAddress.getText().toString(),
                mPhone.getText().toString(),mTitle.getText().toString(),(String)UtilitiesFactory.getFile(getActivity(),"user").doTask());
        AppFactory.saveFireBase(itemForSave).doTask();
    }
}

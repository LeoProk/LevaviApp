package org.levavi.levaviapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;

import com.firebase.client.Firebase;

import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leo on 12/12/2015.
 */
public class NewItemFragment extends Fragment {

    private EditText mTitle,mAddress,mPhone,mText;

    private TimePicker mTimePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (EditText) rootView.findViewById(R.id.address);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
        mText = (EditText) rootView.findViewById(R.id.text);
        mTimePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        mTimePicker.setIs24HourView(true);
        final HashMap<String, String> infoMap = new HashMap<>();
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
                            infoMap.put("title", mTitle.getText().toString());
                            infoMap.put("address", mAddress.getText().toString());
                            infoMap.put("phone", mPhone.getText().toString());
                            infoMap.put("text", mText.getText().toString());
                            //get select time in time picker and conver it to seconds
                            long timeInSecs =  mTimePicker.getCurrentHour()*3600 + mTimePicker.getCurrentMinute()*60;
                            infoMap.put("time", Long.toString(System.currentTimeMillis()/1000 + timeInSecs ));
                            //if also field are filled do the following
                            //change fragment
                            UtilitiesFactory.removeFragment(getActivity()).doTask();
                            //save to firebase after creating hashmap of the new items array list
                            AppFactory.saveFireBase(infoMap).doTask();
                        }
                    }
                }
            }
        });
        return rootView;
    }

}

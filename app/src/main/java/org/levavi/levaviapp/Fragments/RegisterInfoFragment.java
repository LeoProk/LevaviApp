package org.levavi.levaviapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.R;

/**
 * this fragment will follow google or facebook log in user will
 */
public class RegisterInfoFragment extends Fragment  {

    private EditText mTitle,mAddress,mPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.register_info_fragment, container, false);
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (EditText) rootView.findViewById(R.id.address);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
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

                }
            }
        }

        return rootView;
    }
}

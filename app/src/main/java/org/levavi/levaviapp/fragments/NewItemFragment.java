package org.levavi.levaviapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.main.AppFactory;
import org.levavi.levaviapp.pojos.FirebaseItem;
import org.levavi.levaviapp.interfaces.OnDateCompleted;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.utilities.UtilitiesFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * First fragment on app run that show the latest items added
 */
public class NewItemFragment extends Fragment implements OnDateCompleted {

    private EditText mTitle,mPhone,mText,mPrice;

    private AutoCompleteTextView mAddress;

    private Spinner mSpinner;

    private String mDuration;

    private String mLatitude;

    private String mLongitude;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (AutoCompleteTextView) rootView.findViewById(R.id.address);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
        mText = (EditText) rootView.findViewById(R.id.text);
        mPrice = (EditText) rootView.findViewById(R.id.price);
        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        mDuration = "null";
        mAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                AppFactory.getGooglePlacePrediction(charSequence.toString(),"32.112650","34.792527").doTask();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.no_duration:
                        mDuration = "null";
                        break;
                    case R.id.yes_duration:
                        AppFactory.getDatePopup(mPhone,getActivity()).doTask();
                        break;
                    default:
                        break;
                }
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
        String selectedTime = "null";
        final AppController appController = (AppController) getActivity().getApplicationContext();
        String[] fullTime =date.split(" ");
        if(!appController.mTimestamp.equals(null)) {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date dateParser = formatter.parse(appController.mTimestamp);
                selectedTime = String.valueOf(dateParser.getTime() * -1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //save to firebase after creating hashmap of the new items array list
        FirebaseItem itemForSave = new FirebaseItem(mSpinner.getSelectedItem().toString(),fullTime[0],mText.getText().toString(),mAddress.getText().toString(),
                mPhone.getText().toString(),mTitle.getText().toString(),(String)UtilitiesFactory.getFile(getActivity(),"user").doTask(),mDuration,mPrice.getText().toString());
        AppFactory.saveFireBase(itemForSave).doTask();
    }
}

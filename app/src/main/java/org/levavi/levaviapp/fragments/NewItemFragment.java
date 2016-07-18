package org.levavi.levaviapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import org.levavi.levaviapp.interfaces.RequestPlaceInterface;
import org.levavi.levaviapp.main.AppFactory;
import org.levavi.levaviapp.pojos.FirebaseItem;
import org.levavi.levaviapp.interfaces.OnDateCompleted;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.pojos.GooglePredictionData;
import org.levavi.levaviapp.utilities.UtilitiesFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * First fragment on app run that show the latest items added
 */
public class NewItemFragment extends Fragment implements OnDateCompleted {

    private EditText mTitle,mPhone,mText,mPrice;

    private AutoCompleteTextView mAddress;

    private Spinner mSpinner;

    private String mLatitude;

    private String mLongitude;

    private ArrayList<String> mPredictions;

    private ArrayList<String> mItems;

    Subscription mSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //get the application class
        final AppController appController = (AppController) getActivity().getApplicationContext();
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (AutoCompleteTextView) rootView.findViewById(R.id.address);
        mItems = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, mItems);
        mAddress.setAdapter(arrayAdapter);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
        mText = (EditText) rootView.findViewById(R.id.text);
        mPrice = (EditText) rootView.findViewById(R.id.price);
        mSpinner = (Spinner) rootView.findViewById(R.id.spinner);
        //get info from google place prediction using rxandroid and retrofit
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/")
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(mSubscription!=null){
                    if(!mSubscription.isUnsubscribed()) {
                        mSubscription.unsubscribe();
                    }
                }
                RequestPlaceInterface requestPlace = retrofit.create(RequestPlaceInterface.class);
                final Observable<GooglePredictionData> call = requestPlace.getJSON(charSequence.toString(),"31.0461" + "," + "34.8516", "5000", "iw", "AIzaSyD2SJMgrrCuhXx9LbLXfnyqdWbvN28FkKc");
                mSubscription = call
                        .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<GooglePredictionData>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(GooglePredictionData googlePredictionData) {
                                ArrayList<String> temp = new ArrayList<String>();
                                //loops true all the prediction
                                for (int i = 0; i < googlePredictionData.getResults().size(); i++) {
                                    Log.e("address", "hello" + googlePredictionData.getResults().get(i).getFormattedAddress());
                                   temp.add(googlePredictionData.getResults().get(i).getFormattedAddress());
                                }
                                String[] data = temp.toArray(new String[temp.size()]);
                                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,data);
                                mAddress.setAdapter(arrayAdapter);
                                arrayAdapter.notifyDataSetChanged();
                            }
                        });
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
                        appController.mTimestamp = "null";
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
                            if(mSpinner.getSelectedItem().toString().equals("קטגוריות")){
                                AppFactory.subjectPopUp(mPhone, getActivity()).doTask();
                            }else {
                                    AppFactory.getTimeGetter(NewItemFragment.this).doTask();
                                    UtilitiesFactory.removeFragment(getActivity()).doTask();
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
        //UtilitiesFactory.removeFragment(getActivity()).doTask();
        String selectedTime = "null";
        final AppController appController = (AppController) getActivity().getApplicationContext();
        String[] fullTime =date.split(" ");
        if(appController.mTimestamp!=null) {
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
                mPhone.getText().toString(),mTitle.getText().toString(),(String)UtilitiesFactory.getFile(getActivity(),"user").doTask()
                ,appController.mTimestamp,mPrice.getText().toString(),"null");
        AppFactory.saveFireBase(itemForSave).doTask();
    }
}

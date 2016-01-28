package org.levavi.levaviapp.Fragments;

import com.google.gson.Gson;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.AppSpecifics.CustomNewItemAdapter;
import org.levavi.levaviapp.AppSpecifics.NewItem;
import org.levavi.levaviapp.Interfaces.OnItemDelete;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import java.util.ArrayList;

/**
 * Created by Leo on 12/12/2015.
 */
public class NewItemFragment extends Fragment {

    private EditText mTitle,mAddress,mPhone,mInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (EditText) rootView.findViewById(R.id.address);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
        mInfo = (EditText) rootView.findViewById(R.id.info);
        // this code used to make list of item not usable for now
       /* mNewItemsList = new ArrayList<>();
        final ListView itemsList = (ListView) rootView.findViewById(R.id.items);
        itemsList.setAdapter(mItemsAdapter);
        mItemsAdapter = new CustomNewItemAdapter(getActivity(),mNewItemsList,this);
        mItem = (EditText) rootView.findViewById(R.id.item_name);
        mUnit = (EditText) rootView.findViewById(R.id.units);
        mPrice = (EditText) rootView.findViewById(R.id.price);
        final ImageButton addItem = (ImageButton)rootView.findViewById(R.id.add);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewItemsList.add(new NewItem(mItem.getText().toString(),mUnit.getText().toString()
                        ,mPrice.getText().toString()));
                mItemsAdapter.notifyDataSetChanged();
            }
        });*/
        //on sumbit button click
        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if all field are full if yes create new parse item and goes back to last window
                if(mTitle.getText().toString().isEmpty()){
                    AppFactory.titlePopUp(mPhone,getActivity()).doTask();
                }else{
                    if(mAddress.getText().toString().isEmpty()){
                        AppFactory.addressPopUp(mPhone, getActivity()).doTask();
                    }else {

                        if(mPhone.getText().toString().isEmpty()){
                            AppFactory.phonePopUp(mPhone, getActivity()).doTask();
                        }else {

                            if(mInfo.getText().toString().isEmpty()){
                                AppFactory.infoPopUp(mPhone, getActivity()).doTask();
                            }else {

                                //if also field are filled do the following
                                //change fragment
                                UtilitiesFactory.switchFragments(getActivity(),"items").doTask();
                                //crate parse object

                            }
                        }
                    }
                }
            }
        });
        return rootView;
    }

}

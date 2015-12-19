package org.levavi.levaviapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.levavi.levaviapp.AppSpecifics.CustomNewItemAdapter;
import org.levavi.levaviapp.AppSpecifics.NewItem;
import org.levavi.levaviapp.R;

import java.util.ArrayList;

/**
 * Created by Leo on 12/12/2015.
 */
public class NewItemFragment extends Fragment {

    private TextView mTitle,mAddress,mPhone,mItem,mUnits,mPrice;

    private CustomNewItemAdapter mItemsAdapter;

    private ArrayList<NewItem> mNewItemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //creates array list and adapter and list view
        final ListView itemsList = (ListView) rootView.findViewById(R.id.items);
        mNewItemsList = new ArrayList<>();
        mItemsAdapter = new CustomNewItemAdapter(getActivity(),mNewItemsList);
        itemsList.setAdapter(mItemsAdapter);
        //initializes views
        mTitle = (TextView) rootView.findViewById(R.id.title);
        mAddress  = (TextView) rootView.findViewById(R.id.address);
        mPhone  = (TextView) rootView.findViewById(R.id.phone);
        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create json and send it to the server
                createJson();
            }
        });
        return rootView;
    }


    //creates json object with all the entered values
    private void createJson(){
        JSONObject object = new JSONObject();
        try {
            object.put("title",mTitle.getText().toString());
            object.put("address",mAddress.getText().toString());
            object.put("phone",mPhone.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void updateList(int position){
        mNewItemsList.remove(position);
        mItemsAdapter.notifyDataSetChanged();
    }
}

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
import org.levavi.levaviapp.AppSpecifics.CustomNewItemAdapter;
import org.levavi.levaviapp.AppSpecifics.NewItem;
import org.levavi.levaviapp.Interfaces.OnItemDelete;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import java.util.ArrayList;

/**
 * Created by Leo on 12/12/2015.
 */
public class NewItemFragment extends Fragment implements OnItemDelete {

    private EditText mTitle,mAddress,mPhone,mItem,mUnit,mPrice;

    private CustomNewItemAdapter mItemsAdapter;

    private ArrayList<NewItem> mNewItemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        //creates array list and adapter and list view
        final ListView itemsList = (ListView) rootView.findViewById(R.id.items);
        mNewItemsList = new ArrayList<>();
        mItemsAdapter = new CustomNewItemAdapter(getActivity(),mNewItemsList,this);
        itemsList.setAdapter(mItemsAdapter);
        //initializes views
        mTitle = (EditText) rootView.findViewById(R.id.title);
        mAddress  = (EditText) rootView.findViewById(R.id.address);
        mPhone  = (EditText) rootView.findViewById(R.id.phone);
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
        });
        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTitle.getText().toString().isEmpty()){

                }else{
                }
                //create json and send it to the server
                createJson();
                //change fragment
                UtilitiesFactory.switchFragments(getActivity(),"items").doTask();
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
            object.put("items",new Gson().toJson(mNewItemsList));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemDeleted(int position) {
        mNewItemsList.remove(position);
        mItemsAdapter.notifyDataSetChanged();
    }
}

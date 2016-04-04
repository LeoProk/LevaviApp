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
import org.levavi.levaviapp.AppSpecifics.AppFactory;
import org.levavi.levaviapp.AppSpecifics.CustomNewItemAdapter;
import org.levavi.levaviapp.AppSpecifics.NewItem;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.Utilities.UtilitiesFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leo on 12/12/2015.
 */
public class NewItemFragment extends Fragment {

    private EditText mItem,mUnit,mPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        final HashMap<String, String> infoMap = new HashMap<>();
        final HashMap<String,NewItem> itemsForSave = new HashMap<>();
        final ArrayList<NewItem> newItemsList = new ArrayList<>();
        final ListView itemsList = (ListView) rootView.findViewById(R.id.items);
        final CustomNewItemAdapter itemsAdapter = new CustomNewItemAdapter(getActivity(),newItemsList);
        itemsList.setAdapter(itemsAdapter);
        mItem = (EditText) rootView.findViewById(R.id.item_name);
        mUnit = (EditText) rootView.findViewById(R.id.units);
        mPrice = (EditText) rootView.findViewById(R.id.price);
        final ImageButton addItem = (ImageButton)rootView.findViewById(R.id.add);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newItemsList.add(new NewItem(mItem.getText().toString(),mUnit.getText().toString()
                        ,mPrice.getText().toString()));
                itemsAdapter.notifyDataSetChanged();
            }
        });
        //on submit button click
        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                            if (itemsAdapter.isEmpty()) {

                            } else {
                                infoMap.put("title", mTitle.getText().toString());
                                infoMap.put("address", mAddress.getText().toString());
                                infoMap.put("phone", mPhone.getText().toString());
                                //if also field are filled do the following
                                //change fragment
                                UtilitiesFactory.removeFragment(getActivity()).doTask();
                                //save to firebase after creating hashmap of the new items array list
                                for (int i = 0; i < newItemsList.size() ; i++) {
                                    itemsForSave.put("item"+Integer.toString(i),newItemsList.get(i));
                                }
                                AppFactory.saveFireBase(infoMap, itemsForSave).doTask();
                            }





            }
        });
        return rootView;
    }

}

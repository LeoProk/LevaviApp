package org.levavi.levaviapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.levavi.levaviapp.R;

/**
 * Created by Leo on 12/12/2015.
 */
public class NewItemFragment extends Fragment {

    TextView mTitle,mAddress,mPhone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_new_item, container, false);
        mTitle = (TextView) rootView.findViewById(R.id.title);
        mAddress  = (TextView) rootView.findViewById(R.id.address);
        mPhone  = (TextView) rootView.findViewById(R.id.phone);
        final Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createJson();
            }
        });
        return rootView;
    }

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
}

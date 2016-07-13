package org.levavi.levaviapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.pojos.ClickedItemInfo;
import org.levavi.levaviapp.pojos.FirebaseItem;

import java.util.Calendar;
import java.util.Locale;

/**
 * this item display information when clicked on item in list view
 */
public class ItemInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final AppController appController = (AppController) getActivity().getApplicationContext();
        final FirebaseItem itemInfo = appController.mItemInfo;
        final View rootView = inflater.inflate(R.layout.item_info, container, false);
        final TextView title = (TextView) rootView.findViewById(R.id.title);
        final TextView time = (TextView) rootView.findViewById(R.id.time);
        final TextView subject = (TextView) rootView.findViewById(R.id.subject);
        final TextView text = (TextView) rootView.findViewById(R.id.text);
        final TextView length = (TextView) rootView.findViewById(R.id.length);
        final TextView price = (TextView) rootView.findViewById(R.id.price);
        //calls the contact number
        final Button call = (Button) rootView.findViewById(R.id.call);
        //open the location in waze
        final Button go = (Button) rootView.findViewById(R.id.go);
        title.setText(itemInfo.getTitle());
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(itemInfo.getTimeStamp())*-1000);
        time.setText("פורסם ב-"+ DateFormat.format("dd/MM hh:mm", cal).toString());
        text.setText(itemInfo.getText());
        subject.setText(itemInfo.getSubject());
        price.setText(itemInfo.getPrice());


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + itemInfo.getPhone().replaceAll("[^0-9]+", "")));
                getActivity().startActivity(intent);
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=" + itemInfo.getAddress()));
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }
}

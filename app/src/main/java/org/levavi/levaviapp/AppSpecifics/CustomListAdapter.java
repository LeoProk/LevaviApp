package org.levavi.levaviapp.AppSpecifics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.levavi.levaviapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Leo on 5/16/2016.
 */

public class CustomListAdapter extends BaseAdapter {

    private Activity mActivity;
    private LayoutInflater mInflater;
    private ArrayList<FirebaseItem> mFirebaseItems;

    public CustomListAdapter(Activity activity, ArrayList<FirebaseItem> firebaseItem) {
        mActivity = activity;
        mFirebaseItems = firebaseItem;
    }


    @Override
    public int getCount() {
        return mFirebaseItems.size();
    }

    @Override
    public Object getItem(int location) {
        return mFirebaseItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (mInflater == null)
            mInflater = (LayoutInflater) mActivity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.item_list_row, null);

        final TextView title = (TextView) convertView.findViewById(R.id.title);
        final TextView text = (TextView) convertView.findViewById(R.id.text);
        final TextView time = (TextView) convertView.findViewById(R.id.time);
        //calls the contact number
        final Button call = (Button) convertView.findViewById(R.id.call);
        //open the location in waze
        final Button go = (Button) convertView.findViewById(R.id.go);

        // getting movie data for the row
        final FirebaseItem firebaseItem = mFirebaseItems.get(position);
        title.setText(firebaseItem.getTitle());
        text.setText(firebaseItem.getText());
        //converts the timestamp to date
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(firebaseItem.getTimeStamp()));
        time.setText(DateFormat.format("dd-M-yyyy hh:mm", cal).toString());
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + firebaseItem.getPhone().replaceAll("[^0-9]+", "")));
                mActivity.startActivity(intent);
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=" + firebaseItem.getAddress()));
                mActivity.startActivity(intent);
            }
        });

        return convertView;
    }
}

package org.levavi.levaviapp.adapters;

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
import org.levavi.levaviapp.pojos.FirebaseItem;

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
        final TextView time = (TextView) convertView.findViewById(R.id.time);
        final TextView subject = (TextView) convertView.findViewById(R.id.subject);
        final TextView length = (TextView) convertView.findViewById(R.id.length);
        final TextView price = (TextView) convertView.findViewById(R.id.price);

        // getting movie data for the row
        final FirebaseItem firebaseItem = mFirebaseItems.get(position);
        title.setText(firebaseItem.getTitle());
        subject.setText(firebaseItem.getSubject());
        length.setText("5");
        price.setText(firebaseItem.getPrice());
        //converts the timestamp to date
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(firebaseItem.getTimeStamp())*1000);
        time.setText(DateFormat.format("dd-MM-yyyy hh:mm", cal).toString());
        return convertView;
    }
}

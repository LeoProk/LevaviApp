package org.levavi.levaviapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.levavi.levaviapp.R;
import org.levavi.levaviapp.pojos.FirebaseItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * base adapter for list view of items
 * the item info is taken from firebase servers in pojo
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
        final TextView duration = (TextView) convertView.findViewById(R.id.duration);
        final ImageView image = (ImageView)convertView.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.dinner_table);
        // getting item data for the row
        final FirebaseItem firebaseItem = mFirebaseItems.get(position);
        //checks if item have end date
        if(firebaseItem.getDuration().equals("null")){
            duration.setVisibility(View.INVISIBLE);
        }else{
            //gets the current time
            Long tsLong = System.currentTimeMillis()/1000;
            if(Long.valueOf(firebaseItem.getDuration())>tsLong){
                //get the time left until the item listing ends
                Long timeLeft = Long.valueOf(firebaseItem.getDuration()) - tsLong;
                //converts the timestamp to date
                Calendar cal = Calendar.getInstance(Locale.ENGLISH);
                cal.setTimeInMillis(timeLeft*1000);
                duration.setText("תקף עד-"+DateFormat.format("hh:mm", cal).toString());
            }else {
               duration.setText(mActivity.getResources().getString(R.string.item_end));
            }
        }
        //check if user uploaded image if no uses defualt images
        if(firebaseItem.getImage().equals("null")){
        switch (firebaseItem.getSubject()){
            case "מסעדות":
                image.setImageResource(R.drawable.dinner_table);
                break;
            case "אטרקציות ופנאי":
                image.setImageResource(R.drawable.beach);
                break;
            case "טיפוח וספא":
                image.setImageResource(R.drawable.spa);
                break;
            case "בריאות וכושר":
                image.setImageResource(R.drawable.gym);
                break;
            case "אלקטרוניקה ומחשבים":
                image.setImageResource(R.drawable.electornics);
                break;
            case "לבית ולגן":
                image.setImageResource(R.drawable.garden);
                break;
            case "תינוקות ילדים וצעצועים":
                image.setImageResource(R.drawable.toy);
                break;
            case "ביגוד והנעלה":
                image.setImageResource(R.drawable.clothes);
                break;
            default:
                break;
        }
        }else {

        }
        title.setText(firebaseItem.getTitle());
        length.setText("5");
        price.setText(firebaseItem.getPrice()+" "+"₪");
        //converts the timestamp to date
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(firebaseItem.getTimeStamp())*-1000);
        time.setText("פורסם ב-"+DateFormat.format("dd/MM hh:mm", cal).toString());
        return convertView;
    }
}

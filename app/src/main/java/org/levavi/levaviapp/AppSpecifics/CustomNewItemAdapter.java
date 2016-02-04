package org.levavi.levaviapp.AppSpecifics;

import org.levavi.levaviapp.Interfaces.OnItemDelete;
import org.levavi.levaviapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter for list view inside the add new items fragment.
 */
public class CustomNewItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    private Context mContext;

    private ArrayList<NewItem> mItemsList;

    public CustomNewItemAdapter(Context context,ArrayList itemsList){
        mContext = context;
        mItemsList = itemsList;
    }

    public int getCount() {
        return mItemsList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final NewItem newItem = mItemsList.get(position);
        if (mInflater == null)
            mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.new_item_row, null);
        //set values of text views and on click event
        final TextView itemName =(TextView)convertView.findViewById(R.id.item_name);
        itemName.setText(newItem.getName());
        final TextView units =(TextView)convertView.findViewById(R.id.units);
        units.setText(newItem.getUnits());
        final TextView price =(TextView)convertView.findViewById(R.id.price);
        price.setText(newItem.getPrice());
        final ImageButton remove =(ImageButton)convertView.findViewById(R.id.cancel);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemsList.remove(position);
                notifyDataSetChanged();
            }
        });
        return  convertView;
    }
}

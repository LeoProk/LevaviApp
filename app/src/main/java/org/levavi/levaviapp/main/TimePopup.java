package org.levavi.levaviapp.main;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.R;
import org.levavi.levaviapp.interfaces.FactoryInterface;

/**
 * this class create popup with date and time
 */
final class TimePopup implements FactoryInterface {

    private LayoutInflater mInflater;

    private View mAnchorView;

    private Context mContext;

    public TimePopup(View anchorView, Context context) {
        mAnchorView = anchorView;
        mContext = context;
    }

    @Override
    public Object doTask() {
        // Sets the right layout to the view
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = mInflater.inflate(R.layout.time_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //gets the application class
        final AppController appController = (AppController) mContext;
        final TimePicker timePicker = (TimePicker) popupView.findViewById(R.id.timePicker);
        final Button accept = (Button) popupView.findViewById(R.id.accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                //gets the selected date
                String selectedDate = appController.mTimestamp;
                //checks the current version of api then gets the full date
                if (Build.VERSION.SDK_INT >= 23 ) {
                    appController.mTimestamp = timePicker.getHour()+":"+timePicker.getMinute() + " " +selectedDate;
                }
                else {
                    appController.mTimestamp = timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute() + " " +selectedDate;
                }

            }
        });
        final Button cancel = (Button) popupView.findViewById(R.id.call);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        // Initialize more widgets from `popup_layout.xml`
        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(mAnchorView, Gravity.CENTER,
                0, 0);

        return popupWindow;
    }

}

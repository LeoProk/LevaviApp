package org.levavi.levaviapp.AppSpecifics;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.levavi.levaviapp.AppController;
import org.levavi.levaviapp.Interfaces.FactoryInterface;
import org.levavi.levaviapp.R;

/**
 * Created by Leo on 12/22/2015.
 */
final class PopUpGenerator implements FactoryInterface {

    private LayoutInflater mInflater;

    private View mAnchorView;

    private Context mContext;

    private String mInputCondition;

    public PopUpGenerator(View anchorView, Context context, String inputCondition) {
        mAnchorView = anchorView;
        mContext = context;
        mInputCondition = inputCondition;
    }
    @Override
    public Object doTask() {
        // Sets the right layout to the view
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = mInflater.inflate(R.layout.popup_generator_fragment, null);
        //gets the application class
        final AppController appController = (AppController) mContext.getApplicationContext();
        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        final TextView textView = (TextView) popupView.findViewById(R.id.message);
        textView.setTypeface(null, Typeface.BOLD);
        //change load message bassed on input
        switch (mInputCondition) {
            case "title":
                textView.setText(mContext.getResources().getString(R.string.wrong_title));
                break;
            case "address":
                textView.setText(mContext.getResources().getString(R.string.wrong_address));
                break;
            case "phone":
                textView.setText(mContext.getResources().getString(R.string.wrong_phone));
                break;
            default:
                break;
        }
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
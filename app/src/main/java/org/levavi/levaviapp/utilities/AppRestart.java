package org.levavi.levaviapp.utilities;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.MainActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * quite the app and launch it agin
 */
final class AppRestart implements FactoryInterface {

    Context mContext;

    public AppRestart(Context context) {
        mContext = context;
    }

    @Override
    public Object doTask() {
        Intent mStartActivity = new Intent(mContext, MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(mContext, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
        return null;
    }
}

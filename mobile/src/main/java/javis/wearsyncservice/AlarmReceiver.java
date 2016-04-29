package javis.wearsyncservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;


/**
 * Created by AshleyTheMagnificant on 4/27/16.
 */

/* This revieves the trigger at a set time and based on what is defined here will
    preform those actions */
public class AlarmReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("ALARM Reciever", "Alarm received");
        //this will update the UI with message
        NotificationView inst = NotificationView.instance();
        Log.d("Alarm Reciever", "Alarm! Wake up! Wake up!");

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
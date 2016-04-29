package javis.wearsyncservice;

/**
 * Created by AshleyTheMagnificant on 4/27/16.
 */

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/* This class pretty much creates a notification once it is started.
* Can replace this with Notification builder stuff maybe? */
public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("h:mm a");
        String time = df.format(calendar.getTime());
        Log.d("Alarm service", "Time on the watch = " + time);

        sendNotification("Wake Up! Wake Up!");
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);

        Intent msgIntent = new Intent(this, SendWatchMessageIntentService.class);
        msgIntent.putExtra(SendWatchMessageIntentService.INPUT_EXTRA, "ALARM;" + " Its time to brush");
        startService(msgIntent);
        Log.d("AlarmService", "Notification sent.");
    }
}
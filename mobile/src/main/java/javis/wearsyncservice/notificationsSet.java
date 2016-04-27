package javis.wearsyncservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class NotificationsSet extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_notifications);
    }

    public void cancel(View v) {
        Intent cancel = new Intent(this, NotificationView.class);
        startActivity(cancel);
    }

    public void save(View v) {
        Intent save = new Intent(this, NotificationView.class);

        fireMessage("Set Alarm");

        startActivity(save);
    }

    public void fireMessage(String text)
    {
        Intent msgIntent = new Intent(this, SendWatchMessageIntentService.class);
        Log.d("Bazooka alarmmobileside", text);
        msgIntent.putExtra(SendWatchMessageIntentService.INPUT_EXTRA, text);
        startService(msgIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mWifiScanReceiver, new IntentFilter(Constant.MY_INTENT_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver();
    }

    private BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals(Constant.MY_INTENT_FILTER)) {
               //Do STUFF
            }
        }
    };

    private void unregisterReceiver() {
        try {
            if (mWifiScanReceiver != null) {
                unregisterReceiver(mWifiScanReceiver);
            }
        } catch (IllegalArgumentException e) {
            mWifiScanReceiver = null;
        }
    }
}

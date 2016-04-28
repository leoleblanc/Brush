package javis.wearsyncservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;


/**
 * Created by Me on 4/16/16.
 */
public class NotificationsSet extends AppCompatActivity {

    private Switch mySwitch;
    private TimePicker alarmTimePicker;
    private Boolean switcherStatus;
    String NOTIFICATIONS = "notifications";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_notifications);
        mySwitch = (Switch) findViewById(R.id.repeat_switch);
//        mToggle = (ToggleButton) findViewById(R.id.repeat_toggle);
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        mySwitch.setChecked(true);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    // Add this to current Data string
                    switcherStatus = true;
                } else {
                    switcherStatus = false;
                }
            }
        });
        //check the current state before we display the screen
        if (mySwitch.isChecked()){
            switcherStatus = true;
        }
        else {
            switcherStatus = false;
        }
   }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void cancel(View v) {
        Intent cancel = new Intent(this, NotificationView.class);
        startActivity(cancel);
    }

    public void save(View v) {
        Intent save = new Intent(this, NotificationView.class);
        EditText editText = (EditText) findViewById(R.id.label_notif);
        String label = editText.getText().toString();
        String new_data = alarmTimePicker.getCurrentHour() + "_" + alarmTimePicker.getCurrentMinute()
                + "_" + switcherStatus.toString() + "_" + label;
        Log.d("Notif Save", "data before = " + new_data);


        SharedPreferences accessor = getSharedPreferences(NOTIFICATIONS, MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences(NOTIFICATIONS, MODE_PRIVATE).edit();

        //Set the number of notifications
        String notification_data =  accessor.getString("notification_data", null);
        if (notification_data != null) {
            new_data = notification_data + "__" + new_data;
        }
        Log.d("SharedPref", "Data adding to SP = " + new_data);
        editor.putString("notification_data", new_data);
        editor.commit();

//        DataHolder.getInstance().setData(data);// Temporary TODO change to SharedPreferences
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

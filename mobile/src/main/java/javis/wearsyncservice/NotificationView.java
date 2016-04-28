package javis.wearsyncservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

public class NotificationView extends AppCompatActivity {

    String ALARM_TAG = "ALARM";
    AlarmManager alarmManager;
    private static NotificationView inst;
    private RecyclerView mRecyclerView;
    private android.support.v7.widget.RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private HashMap<Integer, PendingIntent> pendingAlarms = new HashMap<>();


    public static NotificationView instance() {
        return inst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        mRecyclerView = (RecyclerView) findViewById(R.id.notification_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNotification();
            }
        });

        String data = DataHolder.getInstance().getData();
        if (data != null) {
            inflateViews(data);
        }
        Log.d("notif_data", "data = " + data);
    }

////TODO delete ToggleClicked
//    public void onToggleClicked(View view) { // TODO Move this to NotifViewActivity
////    public void onSave(View view) {
//        Log.d(ALARM_TAG, "Toggle button selected");
//        if (((ToggleButton) view).isChecked()) {
//            Log.d("MyActivity", "Alarm On");
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
//            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
//            Intent myIntent = new Intent(NotificationView.this, AlarmReceiver.class); // Alarm managers notifies AlarmReciever
//            pendingIntent = PendingIntent.getBroadcast(NotificationView.this, 0, myIntent, 0);
//            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
//
////
////            DateFormat df = new SimpleDateFormat("h:mm a");
////            String date = df.format(Calendar.getInstance().getTime());
////            Log.d(ALARM_TAG, "Actual Time = " + date);
//        } else {
//            alarmManager.cancel(pendingIntent);
//            setAlarmText("");
//            Log.d("MyActivity", "Alarm Off");
//        }
//    }

//    public void setAlarmText(String alarmText) {
//        alarmTextView.setText(alarmText);
//    }
//    // END ALARM STUFF

    public void newNotification() {
        Intent newAlarm = new Intent(this, NotificationsSet.class);
        startActivity(newAlarm);
    }

    public void inflateViews(String data) {
        String[] notifs = data.split("__"); // 2 underscores "_" separate notification information
        mAdapter = new NotificationAdapter(notifs, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addPendingAlarm(int position, PendingIntent pIntent) {
        pendingAlarms.put(position, pIntent);
    }

    public  PendingIntent getPendingAlarm(int position) {
        return pendingAlarms.get(position);
    }


}

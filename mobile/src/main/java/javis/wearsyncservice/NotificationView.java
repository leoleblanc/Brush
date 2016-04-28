package javis.wearsyncservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
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
    String NOTIFICATIONS = "notifications";
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

        SharedPreferences accessor = getSharedPreferences(NOTIFICATIONS, MODE_PRIVATE);
        String notification_data =  accessor.getString("notification_data", null);
//        String notification_data = DataHolder.getInstance().getData();


        if (notification_data != null) {
            inflateViews(notification_data);
        }
        Log.d("NotifView", "data from SP = " + notification_data);
    }

    public void newNotification() {
        Intent newAlarm = new Intent(this, NotificationsSet.class);
        startActivity(newAlarm);
    }

    public void inflateViews(String data) {
        String[] notifs = data.split("__"); // 2 underscores "_" separate notification information
        mAdapter = new NotificationAdapter(notifs, this);
        mRecyclerView.setAdapter(mAdapter);
    }


}

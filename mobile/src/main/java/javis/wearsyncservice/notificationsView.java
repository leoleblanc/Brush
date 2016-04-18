package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class notificationsView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notifications);
    }

    public void newNotification(View v) {
        Intent newAlarm = new Intent(this, notificationsSet.class);
        startActivity(newAlarm);
    }


}

package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class notificationsSet extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_notifications);
    }

    public void cancel(View v) {
        Intent cancel = new Intent(this, notificationsView.class);
        startActivity(cancel);
    }

    public void save(View v) {
        Intent save = new Intent(this, notificationsView.class);
        startActivity(save);
    }
}

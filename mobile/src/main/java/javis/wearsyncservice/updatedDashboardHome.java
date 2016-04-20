package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class updatedDashboardHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updated_home);
    }

    public void toDashboard(View v) {
        Intent dash = new Intent(this, updatedDashboard.class);
        startActivity(dash);
    }

    public void toNotifications(View v) {
        Intent notifications = new Intent(this, notificationsView.class);
        startActivity(notifications);
    }

    public void toSettings(View v) {
        Intent toSettings = new Intent(this, settings.class);
        startActivity(toSettings);
    }

    public void toPlot(View v) {
        Intent toPlot = new Intent(this, simplePlot.class);
        startActivity(toPlot);
    }
}

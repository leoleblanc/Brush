package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class unupdatedDashboardHome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unupdated_dashboard_home);
    }

    public void toUnupdatedDashboard(View v) {
        Intent next = new Intent(this, DashboardDay.class);
        startActivity(next);
    }

    public void toSettings(View v) {
        Intent next = new Intent(this, settings.class);
        startActivity(next);
    }

    public void toNotifications(View v) {
        Intent next = new Intent(this, NotificationView.class);
        startActivity(next);
    }

    public void toPlot(View v) {
        Intent next = new Intent(this, simplePlot.class);
        startActivity(next);
    }
}

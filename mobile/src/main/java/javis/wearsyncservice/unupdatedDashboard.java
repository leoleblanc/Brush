package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class unupdatedDashboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unupdated_dashboard);
    }

    public void toUnupdatedHome(View v) {
        Intent next = new Intent(this, unupdatedDashboardHome.class);
        startActivity(next);
    }

    public void toUnupdatedGraph(View v) {
        Intent next = new Intent(this, unupdatedDashboardGraph.class);
        startActivity(next);
    }

    public void toUnupdatedSendMessage(View v) {
        Intent next = new Intent(this, SendMsgActivity.class);
        startActivity(next);
    }
}

package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class updatedDashboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updated_dashboard);
    }

    public void toGraph(View v) {
        Intent toGraph = new Intent(this, updatedDashboardGraph.class);
        startActivity(toGraph);
    }

    public void hitHome(View v) {
        Intent toHome = new Intent(this, updatedDashboardHome.class);
        startActivity(toHome);
    }

    public void sendMessage(View v) {
        Intent send = new Intent(this, SendMsgActivity.class);
        startActivity(send);
    }
}

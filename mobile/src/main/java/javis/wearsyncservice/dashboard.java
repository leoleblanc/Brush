package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class dashboard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    public void toGraph(View v) {
        Intent toGraph = new Intent(this, dashboardGraph.class);
        startActivity(toGraph);
    }

    public void hitHome(View v) {
        Intent toHome = new Intent(this, home.class);
        startActivity(toHome);
    }

    public void sendMessage(View v) {
        Intent send = new Intent(this, SendMsgActivity.class);
        startActivity(send);
    }
}

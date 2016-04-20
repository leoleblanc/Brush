package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class unupdatedDashboardGraph extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unupdated_graph);
    }

    public void toUnupdatedDashboard(View v) {
        Intent next = new Intent(this, unupdatedDashboard.class);
        startActivity(next);
    }
}

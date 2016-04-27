package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class updatedDashboardGraph extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updated_dashboard_graph);
    }

    public void toDashboard(View v) {
        Intent back = new Intent(this, updatedDashboard.class);
        startActivity(back);
    }
}

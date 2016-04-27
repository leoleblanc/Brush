package javis.wearsyncservice;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class DashboardDay extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_day);
        this.setTitle("Dashboard");
        String[] tempScores = {"86_60_40_70", "0_0_0_0"};
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DashboardAdapter(tempScores);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void toDashboardDay(View v) {
        Intent next = new Intent(this, DashboardDay.class);
        startActivity(next);
    }

    public void toDashboardWeek(View v) {
        Intent next = new Intent(this, unupdatedDashboardGraph.class);
        startActivity(next);
    }
//
    public void sendWatchMessage(View v)
    /**
     * Called onClick of the Send Message button
     */
    {
        /*Intent next = new Intent(this, NotificationView.class);
        startActivity(next); NOT THE CORRECT FLOW INTERACTION*/

        /*final Dialog d = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        d.setContentView(R.layout.custom_dialog);
        d.setCanceledOnTouchOutside(true);
        d.setCancelable(true);
        d.getWindow().setLayout(400,400);
        d.show();*/

        Intent i = new Intent(this, CustomDialog.class);
        startActivity(i);

    }
}

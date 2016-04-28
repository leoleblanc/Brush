package javis.wearsyncservice;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileInputStream;

/**
 * Created by Me on 4/19/16.
 */
public class DashboardDay extends SlidingMenuActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_day);*/

        if (savedInstanceState!=null)
        {
            savedInstanceState.putString("TITLE", "DASHBOARD");
            savedInstanceState.putInt("LAYOUT", R.layout.dashboard_day);
            savedInstanceState.putInt("LAYOUT_ID", R.id.dashboard_rel);
            super.onCreate(savedInstanceState);
        }
        else
        {
            Bundle b = new Bundle();
            b.putString("TITLE", "DASHBOARD");
            b.putInt("LAYOUT", R.layout.dashboard_day);
            b.putInt("LAYOUT_ID", R.id.dashboard_rel); //id of top level Relative/Linear etc Layout
            super.onCreate(b);
        }


        this.setTitle("Dashboard");
        String[] tempScores = {"86_60_40_70", "0_0_0_0"};
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DashboardAdapter(tempScores);
        mRecyclerView.setAdapter(mAdapter);


        Bitmap bmp = getImageBitmap(this, "profile","BMP");;
        ImageView img = (ImageView) findViewById(R.id.cropped_final);
        img.setImageDrawable(new RoundedAvatarDrawable(bmp));

    }

    public void toDashboardDay(View v) {
        Intent next = new Intent(this, DashboardDay.class);
        startActivity(next);
    }

    public void toDashboardWeek(View v) {
        Intent next = new Intent(this, DashboardWeek.class);
        startActivity(next);
    }
//
    public void sendWatchMessage(View v)
    /**
     * Called onClick of the Send Message button
     */
    {
        Intent next = new Intent(this, NotificationView.class);
        startActivity(next); // ASHLEY - FOR TESTING

        /*final Dialog d = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        d.setContentView(R.layout.custom_dialog);
        d.setCanceledOnTouchOutside(true);
        d.setCancelable(true);
        d.getWindow().setLayout(400,400);
        d.show();*/

//        Intent i = new Intent(this, CustomDialog.class);
//        startActivity(i); // CORRECT FLOW

    }

    public Bitmap getImageBitmap(Context context,String name,String extension){
        name=name+"."+extension;
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e){
        }
        //return null;
        //If there is no selected image displays the fox icon
        Bitmap Icon = BitmapFactory.decodeResource(getResources(), R.drawable.foxicon);
        return Icon;
    }

}

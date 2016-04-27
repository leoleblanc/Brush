package javis.wearsyncservice;

import android.os.Bundle;


import android.app.Activity;
import android.os.Bundle;
//Just extend the SlidingMenuActivity to have the sliding menu in your app

/**
 * Demo class to show you how to get the sliding menu in your activity and
 * still have the regular layout underneath.
 */
public class DrawerImplementation extends SlidingMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState!=null)
        {
            savedInstanceState.putString("TITLE", "DASHBOARD");
            savedInstanceState.putInt("LAYOUT", R.layout.activity_drawer_implementation);
            savedInstanceState.putInt("LAYOUT_ID", R.id.contents);
            super.onCreate(savedInstanceState);
        }
        else
        {
            Bundle b = new Bundle();
            b.putString("TITLE", "DASHBOARD");
            b.putInt("LAYOUT", R.layout.activity_drawer_implementation);
            b.putInt("LAYOUT_ID", R.id.contents); //id of top level Relative/Linear etc Layout
            super.onCreate(b);
        }

    }
}

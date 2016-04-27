package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class learnMore extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_more);
    }

    public void toDashboard(View v) {
        Intent next = new Intent(this, updatedDashboard.class);
        startActivity(next);
    }
}

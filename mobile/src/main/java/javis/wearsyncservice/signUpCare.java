package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class signUpCare extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_care);
    }

    public void toSignUpWithInfoName(View v) {
        Intent next = new Intent(this, signUpWithInfoName.class);
        startActivity(next);
    }

    public void toBrush(View v) {
        Intent next = new Intent(this, signUpBrush.class);
        startActivity(next);
    }

    public void toTrack(View v) {
        Intent next = new Intent(this, signUpTrack.class);
        startActivity(next);
    }
}

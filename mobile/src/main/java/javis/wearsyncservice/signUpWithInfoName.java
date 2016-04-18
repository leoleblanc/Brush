package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class signUpWithInfoName extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_with_info_name);
    }

    public void toSignUpWithInfoAge(View v) {
        Intent next = new Intent(this, toSignUpWithInfoAge.class);
        startActivity(next);
    }
}

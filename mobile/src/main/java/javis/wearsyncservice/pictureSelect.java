package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class pictureSelect extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_select);
    }

//    public void toChildQuestionsPink(View v) {
//        Intent next = new Intent(this, childQuestionsPink.class);
//        startActivity(next);
//    }

    public void toSignUpWithInfoName(View v) {
        Intent next = new Intent(this, signUpWithInfoName.class);
        startActivity(next);
    }
}

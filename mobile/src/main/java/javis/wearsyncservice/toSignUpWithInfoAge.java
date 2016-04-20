package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/16/16.
 */
public class toSignUpWithInfoAge extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_with_info_age);
    }

    public void toWeeklyQuestions(View v) {
        Intent next = new Intent(this, weeklyQuestions.class);
        startActivity(next);
    }

    public void toPictureSelect(View v) {
        Intent next = new Intent(this, pictureSelect.class);
        startActivity(next);
    }
}

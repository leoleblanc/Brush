package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class childQuestionsOrange extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_questions_orange);
    }

    public void toChildQuestionsBlue(View v) {
        Intent next = new Intent(this, childQuestionsBlue.class);
        startActivity(next);
    }
}

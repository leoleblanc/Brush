package javis.wearsyncservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Me on 4/19/16.
 */
public class childQuestionsPink extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_questions_pink);
    }

    public void toChildQuestionsOrange(View v) {
        Intent next = new Intent(this, childQuestionsOrange.class);
        startActivity(next);
    }
}
